package com.particle.dataquery.infrastructure.dataapi.gateway.impl;

import com.particle.dataquery.domain.dataapi.DataQueryDataApi;
import com.particle.dataquery.domain.dataapi.enums.DataQueryDataApiAdaptType;
import com.particle.dataquery.domain.dataapi.enums.DataQueryDataApiCustomScriptType;
import com.particle.dataquery.domain.dataapi.gateway.DataApiQueryGateway;
import com.particle.dataquery.domain.dataapi.value.DataQueryDataApiCustomScriptAdaptConfig;
import com.particle.dataquery.domain.dataapi.value.DataQueryDataApiMultipleAggregationAdaptConfig;
import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApiId;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceId;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceApiGateway;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceGateway;
import com.particle.dataquery.domain.datasource.gateway.DatasourceApiQueryGateway;
import com.particle.dataquery.domain.gateway.DataQueryDictGateway;
import com.particle.dataquery.infrastructure.DataQueryInfrastructureConfiguration;
import com.particle.dataquery.infrastructure.dataapi.structmapping.DataQueryDataApiInfrastructureStructMapping;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceApiDO;
import com.particle.dataquery.infrastructure.datasource.gateway.impl.DatasourceApiQueryGatewayHelper;
import com.particle.dataquery.infrastructure.datasource.service.IDataQueryDatasourceApiService;
import com.particle.global.big.datasource.bigdatasource.api.DefaultBigDatasourceApi;
import com.particle.global.exception.Assert;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.script.Bindings;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 数据接口查询网关 实现
 * </p>
 *
 * @author yangwei
 * @since 2023-03-21 22:49
 */
@Component
public class DataApiQueryGatewayImpl implements DataApiQueryGateway {
	@Autowired
	private DatasourceApiQueryGateway datasourceApiQueryGateway;
	@Autowired
	private DataQueryDatasourceApiGateway dataQueryDatasourceApiGateway;

	@Autowired
	private DataQueryDictGateway dataQueryDictGateway;
	@Autowired
	private DataQueryDatasourceGateway dataQueryDatasourceGateway;
	@Autowired
	private DatasourceApiQueryGatewayHelper datasourceApiQueryGatewayHelper;
	@Qualifier(DataQueryInfrastructureConfiguration.dataQueryDataApiExecutor)
	@Autowired
	private ExecutorService dataQueryDataApiExecutor;
	@Autowired
	private IDataQueryDatasourceApiService iDataQueryDatasourceApiService;

	@Override
	public Object query(DataQueryDataApi dataQueryDataApi, Object param,String queryString) {
		// todo 缓存性能
		return queryRealtime(dataQueryDataApi,param,queryString);
	}

	@Override
	public Object queryRealtime(DataQueryDataApi dataQueryDataApi, Object param,String queryString) {
		DataQueryDatasourceApi dataQueryDatasourceApi = DataQueryDataApiInfrastructureStructMapping.instance.dataQueryDataApiToDataQueryDatasourceApi(dataQueryDataApi);
		// 一对一直连时判断直接取数据查询数据源接口配置
		Long adaptTypeDictId = dataQueryDataApi.getAdaptTypeDictId();
		String adaptTypeDictValue = dataQueryDictGateway.getDictValueById(adaptTypeDictId);
		DataQueryDataApiAdaptType dataQueryDataApiAdaptType = DataQueryDataApiAdaptType.valueOf(adaptTypeDictValue);
		if (DataQueryDataApiAdaptType.single_direct == dataQueryDataApiAdaptType) {
			DataQueryDatasourceApi singleDirectDataQueryDatasourceApi = dataQueryDatasourceApiGateway.getById(DataQueryDatasourceApiId.of(dataQueryDataApi.getDataQueryDatasourceApiId()));
			dataQueryDatasourceApi = singleDirectDataQueryDatasourceApi;
		}
		DefaultBigDatasourceApi defaultBigDatasourceApi = datasourceApiQueryGatewayHelper.createDefaultBigDatasourceApiByDataQueryDatasourceApi(dataQueryDatasourceApi,null);
		// 不需要config,因为这里只需要实现自己的逻辑，并不根据config配置进行逻辑处理
		defaultBigDatasourceApi.setConfig(null);

		return new DataApiQueryExecutor((command) -> doExecute(dataQueryDataApi,command,queryString)).execute(defaultBigDatasourceApi, param,queryString);
	}

	/**
	 * 真正执行
	 * @param dataQueryDataApi
	 * @param param
	 * @return
	 */
	@SneakyThrows
	public Object doExecute(DataQueryDataApi dataQueryDataApi, Object param,String queryString) {
		Long adaptTypeDictId = dataQueryDataApi.getAdaptTypeDictId();
		String adaptTypeDictValue = dataQueryDictGateway.getDictValueById(adaptTypeDictId);
		DataQueryDataApiAdaptType dataQueryDataApiAdaptType = DataQueryDataApiAdaptType.valueOf(adaptTypeDictValue);
		if (DataQueryDataApiAdaptType.single_direct == dataQueryDataApiAdaptType) {
			return doExecuteByDatasourceApiId(dataQueryDataApi.getDataQueryDatasourceApiId(), param,queryString);
		}
		if (DataQueryDataApiAdaptType.multiple_aggregation == dataQueryDataApiAdaptType) {
			DataQueryDataApiMultipleAggregationAdaptConfig config = dataQueryDataApi.multipleAggregationAdaptConfig();
			Map<String, Object> result = new ConcurrentHashMap<>();
			int size = config.getAggregationItems().size();
			CountDownLatch countDownLatch = new CountDownLatch(size);

			for (DataQueryDataApiMultipleAggregationAdaptConfig.AggregationItem aggregationItem : config.getAggregationItems()) {
				dataQueryDataApiExecutor.submit(()->{
					try {
						Object o = doExecuteByDatasourceApiId(aggregationItem.getDataQueryDatasourceApiId(), param,queryString);
						result.put(aggregationItem.getOutputKey(), o);
					} finally {
						countDownLatch.countDown();
					}

				});
			}

			// 默认等待两分钟
			countDownLatch.await(2, TimeUnit.MINUTES);

			return result;
		}
		if (DataQueryDataApiAdaptType.custom_script == dataQueryDataApiAdaptType) {

			Map<String, Object> renderMap = TemplateRenderDataWrap.create(param).toRenderMap();
			renderMap.put("datasourceApi", new DatasourceApiInvoker(this));
			Bindings bindings = GroovyTool.createBindings();
			bindings.putAll(renderMap);
			DataQueryDataApiCustomScriptAdaptConfig dataQueryDataApiCustomScriptAdaptConfig = dataQueryDataApi.customScriptAdaptConfig();

			DataQueryDataApiCustomScriptType customScriptType = dataQueryDataApiCustomScriptAdaptConfig.getCustomScriptType();
			if (customScriptType == DataQueryDataApiCustomScriptType.groovy_script) {
				Object o = GroovyTool.compileAndEval(dataQueryDataApiCustomScriptAdaptConfig.getScriptTemplate(), bindings, true);
				return o;

			}
			throw new RuntimeException("customScriptType " + customScriptType.itemValue() + " not support currently");


		}

		throw new RuntimeException("adaptType " + dataQueryDataApiAdaptType.itemValue() + " not support currently");
	}

	public Object doExecuteByDatasourceApiId(Long datasourceApiId,Object param,String queryString){
		DataQueryDatasourceApi dataQueryDatasourceApi = dataQueryDatasourceApiGateway.getById(DataQueryDatasourceApiId.of(datasourceApiId));
		DataQueryDatasource dataQueryDatasource = dataQueryDatasourceGateway.getById(DataQueryDatasourceId.of(dataQueryDatasourceApi.getDataQueryDatasourceId()));
		return datasourceApiQueryGateway.queryRealtime(dataQueryDatasource, dataQueryDatasourceApi, param,queryString);
	}

	public Object doExecuteByDatasourceApiCode(String code, Object param,String queryString) {
		DataQueryDatasourceApiDO byCode = iDataQueryDatasourceApiService.getByCode(code);
		Assert.notNull(byCode,"数据查询数据源接口编码 "+ code +" 不存在");
		return doExecuteByDatasourceApiId(byCode.getId(),param,queryString);
	}

	/**
	 * 脚本支持，可以直接根据 code 调用数据查询数据源接口
	 */
	public static class DatasourceApiInvoker{

		private DataApiQueryGatewayImpl dataApiQueryGateway;

		public DatasourceApiInvoker(DataApiQueryGatewayImpl dataApiQueryGateway) {
			this.dataApiQueryGateway = dataApiQueryGateway;
		}

		public Object invoke(String datasourceApiCode, Object param,String queryString) {
			return dataApiQueryGateway.doExecuteByDatasourceApiCode(datasourceApiCode, param,queryString);
		}
	}
}

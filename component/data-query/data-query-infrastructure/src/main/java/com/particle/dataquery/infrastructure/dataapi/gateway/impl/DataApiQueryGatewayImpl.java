package com.particle.dataquery.infrastructure.dataapi.gateway.impl;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import com.particle.dataquery.domain.dataapi.DataQueryDataApi;
import com.particle.dataquery.domain.dataapi.enums.DataQueryDataApiAdaptType;
import com.particle.dataquery.domain.dataapi.enums.DataQueryDataApiCustomScriptType;
import com.particle.dataquery.domain.dataapi.gateway.DataApiQueryGateway;
import com.particle.dataquery.domain.dataapi.gateway.DataApiRemoteQueryGateway;
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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

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

	private static TimedCache<Object, DataQueryDatasourceApi> dataQueryDatasourceApiByIdCache = CacheUtil.newTimedCache(17 * 1 * 60000);
	private static TimedCache<Object, DataQueryDatasourceApiDO> dataQueryDatasourceApiByCodeCache = CacheUtil.newTimedCache(23 * 1 * 60000);
	private static TimedCache<Object, DataQueryDatasource> dataQueryDatasourceByIdCache = CacheUtil.newTimedCache(19 * 1 * 60000);

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

	@Autowired
	private IDataQueryDatasourceApiService iDataQueryDatasourceApiService;
	@Autowired(required = false)
	private DataApiRemoteQueryGateway dataApiRemoteQueryGateway;

	@Qualifier(DataQueryInfrastructureConfiguration.dataQueryDataApiExecutor)
	@Autowired
	private ExecutorService dataQueryDataApiExecutor;

	@Override
	public Object query(DataQueryDataApi dataQueryDataApi, Object param,String queryString) {

		// 添加一个前置处理，主要是为了兼容远程开放接口
		if (dataApiRemoteQueryGateway != null) {
			if (dataApiRemoteQueryGateway.support(dataQueryDataApi,param,queryString)) {
				return dataApiRemoteQueryGateway.query(dataQueryDataApi, param, queryString,()-> queryRealtime(dataQueryDataApi,param,queryString,false));
			}
		}

		return queryRealtime(dataQueryDataApi,param,queryString,false);
	}

	@Override
	public Object queryRealtime(DataQueryDataApi dataQueryDataApi, Object param,String queryString,boolean isTest) {
		// 正常来讲这里返回的对象的config应该是没有值的，确保万无一失，再设置一下null
		DefaultBigDatasourceApi defaultBigDatasourceApi = datasourceApiQueryGatewayHelper.createDefaultBigDatasourceApiByDataQueryDataApi(dataQueryDataApi,isTest);
		// 不需要config,因为这里只需要实现自己的逻辑，并不根据config配置进行逻辑处理
		// 因为 {@link com.particle.dataquery.infrastructure.dataapi.gateway.impl.DataApiQueryExecutor.doExecute} 方法并没有取 config 数据进行处理
		// 相关于数据查询api也是大数据源的一个执行器作为开始入口
		defaultBigDatasourceApi.setConfig(null);

		return new DataApiQueryExecutor((command) -> doExecute(dataQueryDataApi,command,queryString,isTest))
				.execute(defaultBigDatasourceApi, param,queryString);
	}

	/**
	 * 真正执行
	 * @param dataQueryDataApi
	 * @param param
	 * @return
	 */
	@SneakyThrows
	public Object doExecute(DataQueryDataApi dataQueryDataApi, Object param,String queryString,boolean isTest) {
		Long adaptTypeDictId = dataQueryDataApi.getAdaptTypeDictId();
		String adaptTypeDictValue = dataQueryDictGateway.getDictValueById(adaptTypeDictId);
		DataQueryDataApiAdaptType dataQueryDataApiAdaptType = DataQueryDataApiAdaptType.valueOf(adaptTypeDictValue);
		if (DataQueryDataApiAdaptType.single_direct == dataQueryDataApiAdaptType) {
			return doExecuteByDatasourceApiId(dataQueryDataApi.getDataQueryDatasourceApiId(), param,queryString,isTest);
		}
		if (DataQueryDataApiAdaptType.multiple_aggregation == dataQueryDataApiAdaptType) {
			DataQueryDataApiMultipleAggregationAdaptConfig config = dataQueryDataApi.multipleAggregationAdaptConfig();
			Map<String, Future<?>> resultFuture = new HashMap<>();

			for (DataQueryDataApiMultipleAggregationAdaptConfig.AggregationItem aggregationItem : config.getAggregationItems()) {
				Future<?> submit = dataQueryDataApiExecutor.submit(() -> doExecuteByDatasourceApiId(aggregationItem.getDataQueryDatasourceApiId(), param, queryString,isTest));
				resultFuture.put(aggregationItem.getOutputKey(), submit);
			}

			Map<String, Object> result = new HashMap<>();
			for (Map.Entry<String, Future<?>> stringObjectEntry : resultFuture.entrySet()) {
				result.put(stringObjectEntry.getKey(), stringObjectEntry.getValue().get());
			}

			return result;
		}
		if (DataQueryDataApiAdaptType.custom_script == dataQueryDataApiAdaptType) {

			Map<String, Object> renderMap = TemplateRenderDataWrap.create(param).toRenderMap();
			renderMap.put("queryString", queryString);
			renderMap.putAll(datasourceApiQueryGatewayHelper.outExtConfigBindingsMap(isTest));
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

	public Object doExecuteByDatasourceApiId(Long datasourceApiId,Object param,String queryString,boolean isTest){
		DataQueryDatasourceApi dataQueryDatasourceApi = dataQueryDatasourceApiByIdCache.get(datasourceApiId, () -> {
			DataQueryDatasourceApi queryDatasourceApi = dataQueryDatasourceApiGateway.getById(DataQueryDatasourceApiId.of(datasourceApiId));
			if (isTest) {
				Assert.notNull(queryDatasourceApi,"数据查询数据源接口尚未发布,code=" + queryDatasourceApi.getCode());
			}
			return queryDatasourceApi;
		});
		DataQueryDatasource dataQueryDatasource = dataQueryDatasourceByIdCache.get(dataQueryDatasourceApi.getDataQueryDatasourceId(), () -> dataQueryDatasourceGateway.getById(DataQueryDatasourceId.of(dataQueryDatasourceApi.getDataQueryDatasourceId())));
		return datasourceApiQueryGateway.queryRealtime(dataQueryDatasource, dataQueryDatasourceApi, param,queryString,isTest);
	}

	private Object doExecuteByDatasourceApiCode(String code, Object param,String queryString,boolean isTest) {
		DataQueryDatasourceApiDO byCode = dataQueryDatasourceApiByCodeCache.get(code, () -> {
			DataQueryDatasourceApiDO byCode1 = iDataQueryDatasourceApiService.getByCode(code);
			Assert.notNull(byCode1,"数据查询数据源接口编码 "+ code +" 不存在");
			return byCode1;
		});

		return doExecuteByDatasourceApiId(byCode.getId(),param,queryString,isTest);
	}

	@Override
	public Object doExecuteByDatasourceApiCodeForTrans(String code, Object param, String queryString) {
		DataQueryDatasourceApiDO byCode = dataQueryDatasourceApiByCodeCache.get(code, () -> iDataQueryDatasourceApiService.getByCode(code));
		Assert.notNull(byCode,"数据查询数据源接口编码 "+ code +" 不存在");
		if (byCode.getIsSupportTrans() == null || !byCode.getIsSupportTrans()) {
			return null;
		}
		return doExecuteByDatasourceApiId(byCode.getId(),param,queryString,false);
	}

	@Override
	public boolean deleteCache(DataQueryDatasourceApiId dataQueryDatasourceApiId) {
		DataQueryDatasourceApi byId = dataQueryDatasourceApiGateway.getById(dataQueryDatasourceApiId);
		Assert.notNull(byId,"数据查询数据源接口编码 "+ dataQueryDatasourceApiId.getId() +" 不存在");
		dataQueryDatasourceApiByIdCache.remove(dataQueryDatasourceApiId.getId());
		if (StrUtil.isNotEmpty(byId.getCode())) {
			dataQueryDatasourceApiByCodeCache.remove(byId.getCode());
		}
		dataQueryDatasourceByIdCache.remove(byId.getDataQueryDatasourceId());

		return true;
	}
	@Override
	public boolean refreshCache(DataQueryDatasourceApiId dataQueryDatasourceApiId) {
		DataQueryDatasourceApi byId = dataQueryDatasourceApiGateway.getById(dataQueryDatasourceApiId);
		Assert.notNull(byId,"数据查询数据源接口编码 "+ dataQueryDatasourceApiId.getId() +" 不存在");
		dataQueryDatasourceApiByIdCache.put(dataQueryDatasourceApiId.getId(),byId);
		if (StrUtil.isNotEmpty(byId.getCode())) {
			DataQueryDatasourceApiDO dataQueryDatasourceApiDO = iDataQueryDatasourceApiService.getById(byId.getId().getId());
			dataQueryDatasourceApiByCodeCache.put(byId.getCode(),dataQueryDatasourceApiDO);
		}
		DataQueryDatasource dataQueryDatasource = dataQueryDatasourceGateway.getById(DataQueryDatasourceId.of(byId.getDataQueryDatasourceId()));
		dataQueryDatasourceByIdCache.put(byId.getDataQueryDatasourceId(),dataQueryDatasource);

		return true;
	}

	/**
	 * 脚本支持，可以直接根据 code 调用数据查询数据源接口
	 */
	public static class DatasourceApiInvoker{

		private DataApiQueryGatewayImpl dataApiQueryGateway;

		private boolean isTest;

		public DatasourceApiInvoker(DataApiQueryGatewayImpl dataApiQueryGateway,boolean isTest) {
			this.dataApiQueryGateway = dataApiQueryGateway;
			this.isTest = isTest;
		}

		/**
		 * 脚本方法
		 * @param datasourceApiCode
		 * @param param
		 * @param queryString
		 * @return
		 */
		public Object invoke(String datasourceApiCode, Object param,String queryString) {
			return dataApiQueryGateway.doExecuteByDatasourceApiCode(datasourceApiCode, param,queryString,isTest);
		}
	}
}

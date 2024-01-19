package com.particle.dataquery.app.dataapi.executor.representation;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryCommand;
import com.particle.dataquery.domain.dataapi.DataQueryDataApi;
import com.particle.dataquery.domain.dataapi.DataQueryDataApiId;
import com.particle.dataquery.domain.dataapi.enums.DataQueryDataApiAdaptType;
import com.particle.dataquery.domain.dataapi.gateway.DataApiQueryGateway;
import com.particle.dataquery.domain.dataapi.gateway.DataQueryDataApiGateway;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiParamType;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiInParamTestCaseConfig;
import com.particle.dataquery.domain.gateway.DataQueryDictGateway;
import com.particle.dataquery.infrastructure.dataapi.dos.DataQueryDataApiDO;
import com.particle.dataquery.infrastructure.dataapi.service.IDataQueryDataApiService;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceApiDO;
import com.particle.dataquery.infrastructure.datasource.service.IDataQueryDatasourceApiService;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 数据查询数据接口 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Slf4j
@Component
@Validated
public class DataQueryDataApiDataApiQueryCommandExecutor extends AbstractBaseQueryExecutor {
	private IDataQueryDataApiService iDataQueryDataApiService;
	private DataApiQueryGateway dataApiQueryGateway;
	private DataQueryDataApiGateway dataQueryDataApiGateway;

	private DataQueryDictGateway dataQueryDictGateway;

	private IDataQueryDatasourceApiService iDataQueryDatasourceApiService;
	/**
	 * 缓存13分钟,13为质数
	 */
	private static TimedCache<String, DataQueryDataApi> dataQueryDataApiCache = CacheUtil.newTimedCache(13 * 1 * 60000);


	/**
	 * 数据接口服务查询执行方法
	 * @param dataQueryDataApiQueryCommand
	 * @return
	 */
	public Object dataApiQuery(@Valid DataQueryDataApiQueryCommand dataQueryDataApiQueryCommand){
		Assert.isTrue(StrUtil.isNotEmpty(dataQueryDataApiQueryCommand.getUrl()),"数据接口地址 不能为空");
		String cacheUrlKey = cacheUrlKey(dataQueryDataApiQueryCommand.getUrl());
		DataQueryDataApi dataQueryDataApi = dataQueryDataApiCache.get(cacheUrlKey,
				() -> dataQueryDataApi(cacheUrlKey));
		Assert.notNull(dataQueryDataApi,"数据接口地址不存在" + dataQueryDataApiQueryCommand.getUrl());

		return dataApiQueryGateway.query(dataQueryDataApi, dataQueryDataApiQueryCommand.getParam(),dataQueryDataApiQueryCommand.getQueryString());
	}

	/**
	 * 缓存key单独提出
	 * @param url
	 * @return
	 */
	private String cacheUrlKey(String url) {
		return url;
	}

	/**
	 * 对数据查询接口预热，由于有脚本逻辑，和数据源初始化逻辑，需要预热以加快访问速度
	 * @return
	 */
	public Response warmUp() {

		long start = System.currentTimeMillis();
		log.info("dataquery api warmUp start");
		// 查询所有接口
		List<DataQueryDataApiDO> list = iDataQueryDataApiService.list();
		if (CollectionUtil.isEmpty(list)) {
			log.info("dataquery api warmUp end,no data");
			// 无数据直接返回
			return Response.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		int current = 0;
		int size = list.size();
		// 遍历接口
		for (DataQueryDataApiDO dataQueryDataApiDO : list) {
			log.warn("dataquery api warmUp process,current url={},{}/{}",dataQueryDataApiDO.getUrl(),++current,size);
			// 取出测试用例，以作为参数
			String inParamTestCaseDataConfigJson = dataQueryDataApiDO.getInParamTestCaseDataConfigJson();
			// 入参类型
			Long inParamTypeDictId = dataQueryDataApiDO.getInParamTypeDictId();

			Long adaptTypeDictId = dataQueryDataApiDO.getAdaptTypeDictId();
			String adaptTypeDictValue = dataQueryDictGateway.getDictValueById(adaptTypeDictId);
			DataQueryDataApiAdaptType dataQueryDataApiAdaptType = DataQueryDataApiAdaptType.valueOf(adaptTypeDictValue);

			// 为空，且为一对一直连，取一对一直连配置
			if (inParamTypeDictId == null) {
				if (DataQueryDataApiAdaptType.single_direct == dataQueryDataApiAdaptType) {
					DataQueryDatasourceApiDO byId = iDataQueryDatasourceApiService.getById(dataQueryDataApiDO.getDataQueryDatasourceApiId());
					inParamTypeDictId = byId.getInParamTypeDictId();
				}
			}
			// 入参类型为空，代表无入参
			if (inParamTypeDictId == null) {
				warmUp(dataQueryDataApiDO.getUrl(), null);
				continue;
			}

			// 为空，且为一对一直连，取一对一直连配置
			if (StrUtil.isEmpty(inParamTestCaseDataConfigJson)) {
				if (DataQueryDataApiAdaptType.single_direct == dataQueryDataApiAdaptType) {
					DataQueryDatasourceApiDO byId = iDataQueryDatasourceApiService.getById(dataQueryDataApiDO.getDataQueryDatasourceApiId());
					inParamTestCaseDataConfigJson = byId.getInParamTestCaseDataConfigJson();
				}
			}
			// 入参类型
			DataQueryDatasourceApiParamType dataQueryDatasourceApiParamType = null;
			if (inParamTypeDictId != null) {
				String inParamTypeDictValue = dataQueryDictGateway.getDictValueById(inParamTypeDictId);
				dataQueryDatasourceApiParamType = DataQueryDatasourceApiParamType.valuesOf(inParamTypeDictValue);
			}
			// 配置了测试用例
			if (StrUtil.isNotEmpty(inParamTestCaseDataConfigJson)) {
				// 多个测试用例，都跑一次
				DataQueryDatasourceApiInParamTestCaseConfig fromJsonStr = DataQueryDatasourceApiInParamTestCaseConfig.createFromJsonStr(inParamTestCaseDataConfigJson);
				List<DataQueryDatasourceApiInParamTestCaseConfig.TestCaseItem> inParamTestCases = fromJsonStr.getInParamTestCases();
				for (DataQueryDatasourceApiInParamTestCaseConfig.TestCaseItem inParamTestCase : inParamTestCases) {
					Object object = inParamTestCase.contentToObj(dataQueryDatasourceApiParamType);
					warmUp(dataQueryDataApiDO.getUrl(), object);
				}

			}else {
				// 	没有配置测试用例
				// 	暂先不跑
				log.warn("url={},no test case，ignored!",dataQueryDataApiDO.getUrl());
			}
		}

		long end = System.currentTimeMillis();
		log.info("dataquery api warmUp end,duration={}ms",end - start);
		return Response.buildSuccess();
	}

	/**
	 * 执行 数据查询数据接口 删除缓存指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<String> deleteCache(@Valid IdCommand deleteCommand) {
		DataQueryDataApiId dataQueryDataApiId = DataQueryDataApiId.of(deleteCommand.getId());
		DataQueryDataApi dataQueryDataApi = dataQueryDataApiGateway.getById(dataQueryDataApiId);
		Assert.notNull(dataQueryDataApi, ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		String cacheUrlKey = dataQueryDataApi.getUrl();
		dataQueryDataApiCache.remove(cacheUrlKey);
		return SingleResponse.of(NetUtil.getLocalhostStr());
	}
	/**
	 * 执行 数据查询数据接口 刷新缓存指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<String> refreshCache(@Valid IdCommand deleteCommand) {
		DataQueryDataApiId dataQueryDataApiId = DataQueryDataApiId.of(deleteCommand.getId());
		DataQueryDataApi dataQueryDataApi = dataQueryDataApiGateway.getById(dataQueryDataApiId);
		Assert.notNull(dataQueryDataApi, ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		String cacheUrlKey = dataQueryDataApi.getUrl();
		dataQueryDataApiCache.put(cacheUrlKey,dataQueryDataApi);
		return SingleResponse.of(NetUtil.getLocalhostStr());
	}

	/**
	 * 预热调用
	 * @param url
	 * @param object
	 */
	private void warmUp(String url, Object object) {
		DataQueryDataApiQueryCommand dataQueryDataApiQueryCommand = new DataQueryDataApiQueryCommand();
		dataQueryDataApiQueryCommand.setParam(object);
		dataQueryDataApiQueryCommand.setUrl(url);
		try {
			dataApiQuery(dataQueryDataApiQueryCommand);
		} catch (Exception e) {
			log.warn("dataquery api warmUp error,url={}",url,e);
		}
	}

	/**
	 * 测试
	 * @param dataQueryDataApiQueryCommand
	 * @return
	 */
	public Object dataApiQueryTest(@Valid DataQueryDataApiQueryCommand dataQueryDataApiQueryCommand){
		Assert.isTrue(StrUtil.isNotEmpty(dataQueryDataApiQueryCommand.getUrl()),"数据接口地址 不能为空");
		DataQueryDataApi dataQueryDataApi = dataQueryDataApi(dataQueryDataApiQueryCommand.getUrl());
		Assert.notNull(dataQueryDataApi,"数据接口地址不存在" + dataQueryDataApiQueryCommand.getUrl());

		Object o = dataApiQueryGateway.queryRealtime(dataQueryDataApi, dataQueryDataApiQueryCommand.getParam(), dataQueryDataApiQueryCommand.getQueryString());
		dataQueryDataApi.changeTestPassed();
		dataQueryDataApiGateway.save(dataQueryDataApi);
		return o;
	}

	/**
	 * 根据url获取 dataApi数据
	 * @param url
	 * @return
	 */
	private DataQueryDataApi dataQueryDataApi(String url) {

		DataQueryDataApiDO serviceByUrl = iDataQueryDataApiService.getByUrl(url);
		if (serviceByUrl == null) {
			return null;
		}
		return dataQueryDataApiGateway.getById(DataQueryDataApiId.of(serviceByUrl.getId()));
	}

	@Autowired
	public void setiDataQueryDataApiService(IDataQueryDataApiService iDataQueryDataApiService) {
		this.iDataQueryDataApiService = iDataQueryDataApiService;
	}

	@Autowired
	public void setDataApiQueryGateway(DataApiQueryGateway dataApiQueryGateway) {
		this.dataApiQueryGateway = dataApiQueryGateway;
	}

	@Autowired
	public void setDataQueryDataApiGateway(DataQueryDataApiGateway dataQueryDataApiGateway) {
		this.dataQueryDataApiGateway = dataQueryDataApiGateway;
	}

	@Autowired
	public void setDataQueryDictGateway(DataQueryDictGateway dataQueryDictGateway) {
		this.dataQueryDictGateway = dataQueryDictGateway;
	}
	@Autowired
	public void setiDataQueryDatasourceApiService(IDataQueryDatasourceApiService iDataQueryDatasourceApiService) {
		this.iDataQueryDatasourceApiService = iDataQueryDatasourceApiService;
	}
}

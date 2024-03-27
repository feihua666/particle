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
import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApiId;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceId;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiParamType;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceApiGateway;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceGateway;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiInParamTestCaseConfig;
import com.particle.dataquery.domain.gateway.DataQueryDictGateway;
import com.particle.dataquery.domain.gateway.DataQueryNotifyGateway;
import com.particle.dataquery.infrastructure.dataapi.dos.DataQueryDataApiDO;
import com.particle.dataquery.infrastructure.dataapi.service.IDataQueryDataApiService;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceApiDO;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceDO;
import com.particle.dataquery.infrastructure.datasource.service.IDataQueryDatasourceApiService;
import com.particle.dataquery.infrastructure.datasource.service.IDataQueryDatasourceService;
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
import java.util.Optional;

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
	private DataQueryDatasourceApiGateway dataQueryDatasourceApiGateway;
	private DataQueryDatasourceGateway dataQueryDatasourceGateway;

	private DataQueryDictGateway dataQueryDictGateway;

	private IDataQueryDatasourceApiService iDataQueryDatasourceApiService;
	private IDataQueryDatasourceService iDataQueryDatasourceService;

	private DataQueryNotifyGateway dataQueryNotifyGateway;
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
		String cacheUrlKey = cacheUrlKey(dataQueryDataApiQueryCommand.getUrl());
		DataQueryDataApi dataQueryDataApi = dataQueryDataApiCache.get(cacheUrlKey,
				() -> {
					DataQueryDataApi dataQueryDataApi1 = Optional.ofNullable(dataQueryDataApi(cacheUrlKey)).orElse(null);
					Assert.notNull(dataQueryDataApi1,"数据接口地址不存" + dataQueryDataApiQueryCommand.getUrl());
					Assert.isTrue(dataQueryDataApi1.getIsPublished(),"数据接口尚未发布" + dataQueryDataApiQueryCommand.getUrl());
					return dataQueryDataApi1;
				});



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
	 * 该预热方法，根据测试用例入参调用数据查询接口，因为会实际的去调用供应商接口，可能会产生对三方的实际调用
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

		dataQueryNotifyGateway.notifySystem("数据查询接口预热开始",
				"dataquery.warmup.start",
				"您可以通过添加配置 particle.dataquery.api.warm-up=false 来关闭应用启动预热",
				StrUtil.format("需要预热的数据查询接口数量，size={}",size));

		// 遍历接口
		for (DataQueryDataApiDO dataQueryDataApiDO : list) {
			log.warn("dataquery api warmUp process,current url={},name={},{}/{}",dataQueryDataApiDO.getUrl(),dataQueryDataApiDO.getName(),++current,size);
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
				doSingleWarmUp(dataQueryDataApiDO.getUrl(), null);
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
					Object object = null;
					try {
						object = inParamTestCase.contentToObj(dataQueryDatasourceApiParamType);
					} catch (Exception e) {
						log.warn("dataquery api warmUp error,contentToObj,url={},name={}",dataQueryDataApiDO.getUrl(),dataQueryDataApiDO.getName(),e);
						dataQueryNotifyGateway.notifySystem("数据查询接口预热异常",
								"dataquery.warmup.contentToObj",
								"您可以通过添加配置 particle.dataquery.api.warm-up=false 来关闭应用启动预热",
								StrUtil.format("testCase={},url={},name={}", inParamTestCase.getName(), dataQueryDataApiDO.getUrl(),dataQueryDataApiDO.getName()));
					}
					doSingleWarmUp(dataQueryDataApiDO.getUrl(), object);
				}

			}else {
				// 	没有配置测试用例
				// 	暂先不跑
				log.warn("url={},name={},no test case，ignored!",dataQueryDataApiDO.getUrl(),dataQueryDataApiDO.getName());
			}
		}

		long end = System.currentTimeMillis();
		log.info("dataquery api warmUp end,duration={}ms",end - start);

		dataQueryNotifyGateway.notifySystem("数据查询接口预热完成",
				"dataquery.warmup.finished",
				"您可以通过添加配置 particle.dataquery.api.warm-up=false 来关闭应用启动预热",
				StrUtil.format("duration={}ms",end - start));
		return Response.buildSuccess();
	}


	/**
	 * 经量级预热，仅会对数据查询接口
	 * 该预热不实际调用接口，仅编译配置的脚本，和数据查询数据源无关，不会加载数据源，如果没有配置在启动时加载数据源，首次访问数据查询接口可能还是会稍慢，
	 * @return
	 */
	public Response warmUpLightForDataqueryApi() {

		long start = System.currentTimeMillis();
		log.info("dataquery api warmUpLight start");
		// 查询所有接口
		List<DataQueryDataApiDO> dataQueryDataApiList = iDataQueryDataApiService.list();
		if (CollectionUtil.isEmpty(dataQueryDataApiList)) {
			log.info("dataquery api warmUpLight end,no data");
		}else {
			int current = 0;
			int size = dataQueryDataApiList.size();
			dataQueryNotifyGateway.notifySystem("数据查询接口经量级预热开始",
					"dataqueryapi.warmUpLight.start",
					"您可以通过添加配置 particle.dataquery.api.warm-up-light=false 来关闭应用启动预热",
					StrUtil.format("需要预热的数据查询接口数量，size={}",size));

			for (DataQueryDataApiDO dataQueryDataApiDO : dataQueryDataApiList) {
				log.warn("dataquery api warmUpLight process,current url={},name=,{}/{}",dataQueryDataApiDO.getUrl(),dataQueryDataApiDO.getName(),++current,size);
				DataQueryDataApi dataQueryDataApi = dataQueryDataApiGateway.getById(DataQueryDataApiId.of(dataQueryDataApiDO.getId()));
				dataQueryDataApi.warmUpLight();

			}
			long end = System.currentTimeMillis();
			log.info("dataquery api warmUpLight end,duration={}ms",end - start);
			dataQueryNotifyGateway.notifySystem("数据查询接口经量级预热完成",
					"dataqueryapi.warmUpLight.finished",
					"您可以通过添加配置 particle.dataquery.api.warm-up-light=false 来关闭应用启动预热",
					StrUtil.format("duration={}ms",end - start));
		}
		return Response.buildSuccess();
	}
	/**
	 * 经量级预热，仅会对数据源接口
	 * 该预热不实际调用接口，仅编译配置的脚本，和数据查询数据源无关，不会加载数据源，如果没有配置在启动时加载数据源，首次访问数据查询接口可能还是会稍慢，
	 * @return
	 */
	public Response warmUpLightForDataqueryDatasourceApi() {

		long start = System.currentTimeMillis();
		log.info("dataqueryDatasource api warmUpLight start");
		// 查询所有接口
		List<DataQueryDatasourceApiDO> dataQueryDatasourceApiList = iDataQueryDatasourceApiService.list();
		if (CollectionUtil.isEmpty(dataQueryDatasourceApiList)) {
			log.info("dataqueryDatasource api warmUpLight end,no data");
		}else {
			int current = 0;
			int size = dataQueryDatasourceApiList.size();
			dataQueryNotifyGateway.notifySystem("数据源接口经量级预热开始",
					"dataqueryDatasourceapi.warmUpLight.start",
					"您可以通过添加配置 particle.dataqueryDatasource.api.warm-up-light=false 来关闭应用启动预热",
					StrUtil.format("需要预热的数据源接口数量，size={}",size));

			for (DataQueryDatasourceApiDO dataQueryDatasourceApiDO : dataQueryDatasourceApiList) {
				log.warn("dataqueryDatasource api warmUpLight process,current code={},name={},{}/{}",dataQueryDatasourceApiDO.getCode(),dataQueryDatasourceApiDO.getName(),++current,size);
				DataQueryDatasourceApi queryDatasourceApi = dataQueryDatasourceApiGateway.getById(DataQueryDatasourceApiId.of(dataQueryDatasourceApiDO.getId()));
				queryDatasourceApi.warmUpLight();

			}
			long end = System.currentTimeMillis();
			log.info("dataqueryDatasource api warmUpLight end,duration={}ms",end - start);
			dataQueryNotifyGateway.notifySystem("数据源接口经量级预热完成",
					"dataqueryDatasourceapi.warmUpLight.finished",
					"您可以通过添加配置 particle.dataqueryDatasource.api.warm-up-light=false 来关闭应用启动预热",
					StrUtil.format("duration={}ms",end - start));
		}
		return Response.buildSuccess();
	}

	/**
	 * 经量级预热，仅会对数据源接口
	 * 该预热不实际调用接口，仅编译配置的脚本，和数据查询数据源无关，不会加载数据源，如果没有配置在启动时加载数据源，首次访问数据查询接口可能还是会稍慢，
	 * @return
	 */
	public Response warmUpLightForDataqueryDatasource() {

		long start = System.currentTimeMillis();
		log.info("dataqueryDatasource warmUpLight start");
		// 查询所有接口
		List<DataQueryDatasourceDO> dataQueryDatasourceList = iDataQueryDatasourceService.list();
		if (CollectionUtil.isEmpty(dataQueryDatasourceList)) {
			log.info("dataqueryDatasource warmUpLight end,no data");
		}else {
			int current = 0;
			int size = dataQueryDatasourceList.size();
			dataQueryNotifyGateway.notifySystem("数据源经量级预热开始",
					"dataqueryDatasource.warmUpLight.start",
					"您可以通过添加配置 particle.dataqueryDatasource.warm-up-light=false 来关闭应用启动预热",
					StrUtil.format("需要预热的数据源数量，size={}",size));

			for (DataQueryDatasourceDO dataQueryDatasourceDO : dataQueryDatasourceList) {
				log.warn("dataqueryDatasource warmUpLight process,current code={},name={},{}/{}",dataQueryDatasourceDO.getCode(),dataQueryDatasourceDO.getName(),++current,size);
				DataQueryDatasource queryDatasource = dataQueryDatasourceGateway.getById(DataQueryDatasourceId.of(dataQueryDatasourceDO.getId()));
				queryDatasource.warmUpLight();

			}
			long end = System.currentTimeMillis();
			log.info("dataqueryDatasource warmUpLight end,duration={}ms",end - start);
			dataQueryNotifyGateway.notifySystem("数据源接口经量级预热完成",
					"dataqueryDatasource.warmUpLight.finished",
					"您可以通过添加配置 particle.dataqueryDatasource.warm-up-light=false 来关闭应用启动预热",
					StrUtil.format("duration={}ms",end - start));
		}
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
	private void doSingleWarmUp(String url, Object object) {
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

		Object o = dataApiQueryGateway.queryRealtime(dataQueryDataApi, dataQueryDataApiQueryCommand.getParam(), dataQueryDataApiQueryCommand.getQueryString(),true);
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
	public void setDataQueryDatasourceApiGateway(DataQueryDatasourceApiGateway dataQueryDatasourceApiGateway) {
		this.dataQueryDatasourceApiGateway = dataQueryDatasourceApiGateway;
	}
	@Autowired
	public void setDataQueryDatasourceGateway(DataQueryDatasourceGateway dataQueryDatasourceGateway) {
		this.dataQueryDatasourceGateway = dataQueryDatasourceGateway;
	}

	@Autowired
	public void setDataQueryDictGateway(DataQueryDictGateway dataQueryDictGateway) {
		this.dataQueryDictGateway = dataQueryDictGateway;
	}
	@Autowired
	public void setiDataQueryDatasourceApiService(IDataQueryDatasourceApiService iDataQueryDatasourceApiService) {
		this.iDataQueryDatasourceApiService = iDataQueryDatasourceApiService;
	}
	@Autowired
	public void setiDataQueryDatasourceService(IDataQueryDatasourceService iDataQueryDatasourceService) {
		this.iDataQueryDatasourceService = iDataQueryDatasourceService;
	}

	@Autowired
	public void setDataQueryNotifyGateway(DataQueryNotifyGateway dataQueryNotifyGateway) {
		this.dataQueryNotifyGateway = dataQueryNotifyGateway;
	}
}

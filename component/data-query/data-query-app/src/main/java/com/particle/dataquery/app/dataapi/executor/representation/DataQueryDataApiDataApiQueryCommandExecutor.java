package com.particle.dataquery.app.dataapi.executor.representation;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.WeakCache;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryCommand;
import com.particle.dataquery.domain.dataapi.DataQueryDataApi;
import com.particle.dataquery.domain.dataapi.DataQueryDataApiId;
import com.particle.dataquery.domain.dataapi.gateway.DataApiQueryGateway;
import com.particle.dataquery.domain.dataapi.gateway.DataQueryDataApiGateway;
import com.particle.dataquery.infrastructure.dataapi.dos.DataQueryDataApiDO;
import com.particle.dataquery.infrastructure.dataapi.service.IDataQueryDataApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 数据查询数据接口 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Component
@Validated
public class DataQueryDataApiDataApiQueryCommandExecutor extends AbstractBaseQueryExecutor {
	private IDataQueryDataApiService iDataQueryDataApiService;
	private DataApiQueryGateway dataApiQueryGateway;
	private DataQueryDataApiGateway dataQueryDataApiGateway;

	/**
	 * 缓存10分钟
	 */
	public static WeakCache<String, DataQueryDataApi> dataApiQueryCache = CacheUtil.newWeakCache(10 * 1 * 6000);


	/**
	 * 数据接口服务查询执行方法
	 * @param dataQueryDataApiQueryCommand
	 * @return
	 */
	public Object dataApiQuery(@Valid DataQueryDataApiQueryCommand dataQueryDataApiQueryCommand){
		DataQueryDataApi dataQueryDataApi = dataApiQueryCache.get(dataQueryDataApiQueryCommand.getUrl(), () -> dataQueryDataApi(dataQueryDataApiQueryCommand.getUrl()));

		return dataApiQueryGateway.query(dataQueryDataApi, dataQueryDataApiQueryCommand.getParam());
	}

	/**
	 * 测试
	 * @param dataQueryDataApiQueryCommand
	 * @return
	 */
	public Object dataApiQueryTest(@Valid DataQueryDataApiQueryCommand dataQueryDataApiQueryCommand){
		DataQueryDataApi dataQueryDataApi = dataQueryDataApi(dataQueryDataApiQueryCommand.getUrl());

		return dataApiQueryGateway.queryRealtime(dataQueryDataApi, dataQueryDataApiQueryCommand.getParam());
	}

	/**
	 * 根据url获取 dataApi数据
	 * @param url
	 * @return
	 */
	private DataQueryDataApi dataQueryDataApi(String url) {

		DataQueryDataApiDO serviceByUrl = iDataQueryDataApiService.getByUrl(url);
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
}

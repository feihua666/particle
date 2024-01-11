package com.particle.dataquery.app.dataapi.executor.representation;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.app.dataapi.structmapping.DataQueryDataApiAppStructMapping;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryCommand;
import com.particle.dataquery.client.dataapi.dto.data.DataQueryDataApiVO;
import com.particle.dataquery.domain.dataapi.DataQueryDataApi;
import com.particle.dataquery.domain.dataapi.DataQueryDataApiId;
import com.particle.dataquery.domain.dataapi.gateway.DataApiQueryGateway;
import com.particle.dataquery.domain.dataapi.gateway.DataQueryDataApiGateway;
import com.particle.dataquery.infrastructure.dataapi.dos.DataQueryDataApiDO;
import com.particle.dataquery.infrastructure.dataapi.service.IDataQueryDataApiService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
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

	private String cacheUrlKey(String url) {
		return url;
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
	 * 测试
	 * @param dataQueryDataApiQueryCommand
	 * @return
	 */
	public Object dataApiQueryTest(@Valid DataQueryDataApiQueryCommand dataQueryDataApiQueryCommand){
		Assert.isTrue(StrUtil.isNotEmpty(dataQueryDataApiQueryCommand.getUrl()),"数据接口地址 不能为空");
		DataQueryDataApi dataQueryDataApi = dataQueryDataApi(dataQueryDataApiQueryCommand.getUrl());
		Assert.notNull(dataQueryDataApi,"数据接口地址不存在" + dataQueryDataApiQueryCommand.getUrl());

		return dataApiQueryGateway.queryRealtime(dataQueryDataApi, dataQueryDataApiQueryCommand.getParam(),dataQueryDataApiQueryCommand.getQueryString());
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
}

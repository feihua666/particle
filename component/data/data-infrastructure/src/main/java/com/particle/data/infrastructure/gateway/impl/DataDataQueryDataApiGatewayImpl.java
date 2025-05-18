package com.particle.data.infrastructure.gateway.impl;

import com.particle.data.domain.gateway.DataDataQueryDataApiGateway;
import com.particle.dataquery.adapter.feign.client.dataapi.rpc.DataQueryDataApiRpcFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 数据查询接口服务依赖
 * </p>
 *
 * @author yangwei
 * @since 2025-04-23 17:36:58
 */
@Component
public class DataDataQueryDataApiGatewayImpl implements DataDataQueryDataApiGateway {

	private DataQueryDataApiRpcFeignClient dataQueryDataApiRpcFeignClient;

	@Override
	public Object invoke(String code, Object command, String queryString) {
		return dataQueryDataApiRpcFeignClient.invoke(code,command,queryString);
	}

	@Autowired
	public void setDataQueryDataApiRpcFeignClient(DataQueryDataApiRpcFeignClient dataQueryDataApiRpcFeignClient) {
		this.dataQueryDataApiRpcFeignClient = dataQueryDataApiRpcFeignClient;
	}
}

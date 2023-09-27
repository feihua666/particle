package com.particle.report.infrastructure.gateway.impl;

import com.particle.dataquery.adapter.feign.client.dataapi.rpc.DataQueryDataApiRpcFeignClient;
import com.particle.report.domain.gateway.ReportDataQueryDataApiGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 数据查询接口服务依赖
 * </p>
 *
 * @author yangwei
 * @since 2023-09-11 11:27
 */
@Component
public class ReportDataQueryDataApiGatewayImpl implements ReportDataQueryDataApiGateway {

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

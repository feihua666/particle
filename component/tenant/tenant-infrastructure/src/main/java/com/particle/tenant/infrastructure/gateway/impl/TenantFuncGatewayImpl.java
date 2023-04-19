package com.particle.tenant.infrastructure.gateway.impl;

import com.particle.func.adapter.feign.client.funcapplicationfuncrel.rpc.FuncApplicationFuncRelRpcFeignClient;
import com.particle.tenant.domain.gateway.TenantFuncGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 功能模块依赖远程调用实现类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-20 00:03
 */
@Component("tenantFuncsGatewayImpl")
public class TenantFuncGatewayImpl implements TenantFuncGateway {

	private FuncApplicationFuncRelRpcFeignClient funcApplicationFuncRelRpcFeignClient;


	@Override
	public List<Long> getFuncIdsByFuncApplicationId(Long funcApplicationId) {
		return funcApplicationFuncRelRpcFeignClient.getFuncIdsByFuncApplicationId(funcApplicationId);
	}


	@Autowired
	public void setFuncApplicationFuncRelRpcFeignClient(FuncApplicationFuncRelRpcFeignClient funcApplicationFuncRelRpcFeignClient) {
		this.funcApplicationFuncRelRpcFeignClient = funcApplicationFuncRelRpcFeignClient;
	}
}

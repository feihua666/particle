package com.particle.tenant.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

import java.util.List;

/**
 * <p>
 * 功能相关远程调用
 * </p>
 *
 * @author yangwei
 * @since 2023-04-20 00:03:01
 */
public interface TenantFuncFuncGateway extends IGateway {

	List<Long> getFuncIdsByFuncApplicationId(Long funcApplicationId);
}

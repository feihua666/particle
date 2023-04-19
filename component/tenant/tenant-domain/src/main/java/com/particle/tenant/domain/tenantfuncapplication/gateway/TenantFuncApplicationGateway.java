package com.particle.tenant.domain.tenantfuncapplication.gateway;

import com.particle.tenant.domain.tenantfuncapplication.TenantFuncApplication;
import com.particle.tenant.domain.tenantfuncapplication.TenantFuncApplicationId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 租户功能应用 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
public interface TenantFuncApplicationGateway extends IBaseGateway<TenantFuncApplicationId,TenantFuncApplication> {
}

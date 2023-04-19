package com.particle.tenant.domain.gateway;

import com.particle.tenant.domain.Tenant;
import com.particle.tenant.domain.TenantId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 租户 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
public interface TenantGateway extends IBaseGateway<TenantId,Tenant> {
}

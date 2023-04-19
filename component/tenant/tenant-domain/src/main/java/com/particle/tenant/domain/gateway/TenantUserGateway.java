package com.particle.tenant.domain.gateway;

import com.particle.tenant.domain.TenantUser;
import com.particle.tenant.domain.TenantUserId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 租户用户 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
public interface TenantUserGateway extends IBaseGateway<TenantUserId,TenantUser> {
}

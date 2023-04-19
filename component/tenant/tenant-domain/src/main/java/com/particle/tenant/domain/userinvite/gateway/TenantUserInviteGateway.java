package com.particle.tenant.domain.userinvite.gateway;

import com.particle.tenant.domain.userinvite.TenantUserInvite;
import com.particle.tenant.domain.userinvite.TenantUserInviteId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 租户用户邀请 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
public interface TenantUserInviteGateway extends IBaseGateway<TenantUserInviteId,TenantUserInvite> {
}

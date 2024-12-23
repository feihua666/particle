package com.particle.tenant.domain.userinvite.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.tenant.domain.userinvite.TenantUserInviteUserRecord;
import com.particle.tenant.domain.userinvite.TenantUserInviteUserRecordId;

/**
 * <p>
 * 租户用户邀请记录 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
public interface TenantUserInviteUserRecordGateway extends IBaseGateway<TenantUserInviteUserRecordId,TenantUserInviteUserRecord> {
}

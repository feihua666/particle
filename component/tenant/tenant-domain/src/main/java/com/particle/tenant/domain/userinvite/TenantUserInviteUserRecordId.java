package com.particle.tenant.domain.userinvite;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 租户用户邀请记录 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
public class TenantUserInviteUserRecordId extends Id {

	public TenantUserInviteUserRecordId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 租户用户邀请记录 领域模型id
	 * @param id
	 * @return
	 */
	public static TenantUserInviteUserRecordId of(Long id){
		return new TenantUserInviteUserRecordId(id);
	}
}

package com.particle.tenant.domain.userinvite;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 租户用户邀请 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
public class TenantUserInviteId extends Id {

	public TenantUserInviteId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 租户用户邀请 领域模型id
	 * @param id
	 * @return
	 */
	public static TenantUserInviteId of(Long id){
		return new TenantUserInviteId(id);
	}
}

package com.particle.tenant.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 租户用户 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
public class TenantUserId extends Id {

	public TenantUserId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 租户用户 领域模型id
	 * @param id
	 * @return
	 */
	public static TenantUserId of(Long id){
		return new TenantUserId(id);
	}
}

package com.particle.tenant.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 租户 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
public class TenantId extends Id {

	public TenantId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 租户 领域模型id
	 * @param id
	 * @return
	 */
	public static TenantId of(Long id){
		return new TenantId(id);
	}
}

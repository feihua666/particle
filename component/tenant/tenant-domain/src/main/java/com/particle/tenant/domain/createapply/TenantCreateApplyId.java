package com.particle.tenant.domain.createapply;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 租户创建申请 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
public class TenantCreateApplyId extends Id {

	public TenantCreateApplyId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 租户创建申请 领域模型id
	 * @param id
	 * @return
	 */
	public static TenantCreateApplyId of(Long id){
		return new TenantCreateApplyId(id);
	}
}

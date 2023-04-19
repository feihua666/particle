package com.particle.tenant.domain.tenantfuncapplication;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 租户功能应用 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
public class TenantFuncApplicationId extends Id {

	public TenantFuncApplicationId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 租户功能应用 领域模型id
	 * @param id
	 * @return
	 */
	public static TenantFuncApplicationId of(Long id){
		return new TenantFuncApplicationId(id);
	}
}

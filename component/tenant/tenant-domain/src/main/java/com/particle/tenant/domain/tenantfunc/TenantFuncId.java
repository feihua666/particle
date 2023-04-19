package com.particle.tenant.domain.tenantfunc;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 租户功能菜单 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
public class TenantFuncId extends Id {

	public TenantFuncId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 租户功能菜单 领域模型id
	 * @param id
	 * @return
	 */
	public static TenantFuncId of(Long id){
		return new TenantFuncId(id);
	}
}

package com.particle.crm.domain.customer;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 客户 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
public class CrmCustomerId extends Id {

	public CrmCustomerId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 客户 领域模型id
	 * @param id
	 * @return
	 */
	public static CrmCustomerId of(Long id){
		return new CrmCustomerId(id);
	}
}

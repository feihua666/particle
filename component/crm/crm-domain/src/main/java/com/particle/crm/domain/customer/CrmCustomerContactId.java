package com.particle.crm.domain.customer;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 客户联系方式 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
public class CrmCustomerContactId extends Id {

	public CrmCustomerContactId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 客户联系方式 领域模型id
	 * @param id
	 * @return
	 */
	public static CrmCustomerContactId of(Long id){
		return new CrmCustomerContactId(id);
	}
}

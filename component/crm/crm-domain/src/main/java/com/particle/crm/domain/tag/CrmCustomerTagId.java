package com.particle.crm.domain.tag;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 客户标签 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
public class CrmCustomerTagId extends Id {

	public CrmCustomerTagId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 客户标签 领域模型id
	 * @param id
	 * @return
	 */
	public static CrmCustomerTagId of(Long id){
		return new CrmCustomerTagId(id);
	}
}

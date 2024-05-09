package com.particle.crm.domain.tag;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 客户标签关系 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
public class CrmCustomerTagRelId extends Id {

	public CrmCustomerTagRelId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 客户标签关系 领域模型id
	 * @param id
	 * @return
	 */
	public static CrmCustomerTagRelId of(Long id){
		return new CrmCustomerTagRelId(id);
	}
}

package com.particle.crm.domain.relation;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 客户与客户关系 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
public class CrmCustomerRelationId extends Id {

	public CrmCustomerRelationId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 客户与客户关系 领域模型id
	 * @param id
	 * @return
	 */
	public static CrmCustomerRelationId of(Long id){
		return new CrmCustomerRelationId(id);
	}
}

package com.particle.crm.domain.relation;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 客户关系定义 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
public class CrmCustomerRelationDefineId extends Id {

	public CrmCustomerRelationDefineId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 客户关系定义 领域模型id
	 * @param id
	 * @return
	 */
	public static CrmCustomerRelationDefineId of(Long id){
		return new CrmCustomerRelationDefineId(id);
	}
}

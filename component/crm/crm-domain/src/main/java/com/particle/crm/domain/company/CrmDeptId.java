package com.particle.crm.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 客户公司部门 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
public class CrmDeptId extends Id {

	public CrmDeptId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 客户公司部门 领域模型id
	 * @param id
	 * @return
	 */
	public static CrmDeptId of(Long id){
		return new CrmDeptId(id);
	}
}

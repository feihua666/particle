package com.particle.crm.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 客户公司 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
public class CrmCompanyId extends Id {

	public CrmCompanyId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 客户公司 领域模型id
	 * @param id
	 * @return
	 */
	public static CrmCompanyId of(Long id){
		return new CrmCompanyId(id);
	}
}

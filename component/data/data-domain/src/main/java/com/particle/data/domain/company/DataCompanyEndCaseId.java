package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业终本案件 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:57
 */
public class DataCompanyEndCaseId extends Id {

	public DataCompanyEndCaseId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业终本案件 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyEndCaseId of(Long id){
		return new DataCompanyEndCaseId(id);
	}
}

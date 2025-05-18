package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业投资机构 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:13
 */
public class DataCompanyVcInvestInstitutionId extends Id {

	public DataCompanyVcInvestInstitutionId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业投资机构 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyVcInvestInstitutionId of(Long id){
		return new DataCompanyVcInvestInstitutionId(id);
	}
}

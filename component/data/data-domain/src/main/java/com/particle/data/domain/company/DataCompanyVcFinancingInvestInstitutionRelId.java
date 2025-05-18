package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业融资历史投资机构关系 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
public class DataCompanyVcFinancingInvestInstitutionRelId extends Id {

	public DataCompanyVcFinancingInvestInstitutionRelId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业融资历史投资机构关系 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyVcFinancingInvestInstitutionRelId of(Long id){
		return new DataCompanyVcFinancingInvestInstitutionRelId(id);
	}
}

package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业年报对外投资 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
public class DataCompanyAnnualReportForeignInvestId extends Id {

	public DataCompanyAnnualReportForeignInvestId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业年报对外投资 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyAnnualReportForeignInvestId of(Long id){
		return new DataCompanyAnnualReportForeignInvestId(id);
	}
}

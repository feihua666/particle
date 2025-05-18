package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业年报行政许可 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
public class DataCompanyAnnualReportAdministrativeLicenseId extends Id {

	public DataCompanyAnnualReportAdministrativeLicenseId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业年报行政许可 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyAnnualReportAdministrativeLicenseId of(Long id){
		return new DataCompanyAnnualReportAdministrativeLicenseId(id);
	}
}

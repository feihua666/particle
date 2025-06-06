package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业年报社保 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
public class DataCompanyAnnualReportSocialSecurityId extends Id {

	public DataCompanyAnnualReportSocialSecurityId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业年报社保 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyAnnualReportSocialSecurityId of(Long id){
		return new DataCompanyAnnualReportSocialSecurityId(id);
	}
}

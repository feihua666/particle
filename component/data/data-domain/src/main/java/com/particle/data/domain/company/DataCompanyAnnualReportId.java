package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业年报 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
public class DataCompanyAnnualReportId extends Id {

	public DataCompanyAnnualReportId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业年报 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyAnnualReportId of(Long id){
		return new DataCompanyAnnualReportId(id);
	}
}

package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业年报变更 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:43
 */
public class DataCompanyAnnualReportChangeId extends Id {

	public DataCompanyAnnualReportChangeId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业年报变更 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyAnnualReportChangeId of(Long id){
		return new DataCompanyAnnualReportChangeId(id);
	}
}

package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业年报股东 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
public class DataCompanyAnnualReportShareholderId extends Id {

	public DataCompanyAnnualReportShareholderId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业年报股东 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyAnnualReportShareholderId of(Long id){
		return new DataCompanyAnnualReportShareholderId(id);
	}
}

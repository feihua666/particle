package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业年报网站网店 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
public class DataCompanyAnnualReportWebsiteId extends Id {

	public DataCompanyAnnualReportWebsiteId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业年报网站网店 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyAnnualReportWebsiteId of(Long id){
		return new DataCompanyAnnualReportWebsiteId(id);
	}
}

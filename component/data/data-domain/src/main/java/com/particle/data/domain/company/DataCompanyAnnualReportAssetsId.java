package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业资产状况信息 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:31
 */
public class DataCompanyAnnualReportAssetsId extends Id {

	public DataCompanyAnnualReportAssetsId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业资产状况信息 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyAnnualReportAssetsId of(Long id){
		return new DataCompanyAnnualReportAssetsId(id);
	}
}

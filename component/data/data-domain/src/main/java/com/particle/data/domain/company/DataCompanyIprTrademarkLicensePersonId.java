package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权商标许可人 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
public class DataCompanyIprTrademarkLicensePersonId extends Id {

	public DataCompanyIprTrademarkLicensePersonId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权商标许可人 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprTrademarkLicensePersonId of(Long id){
		return new DataCompanyIprTrademarkLicensePersonId(id);
	}
}

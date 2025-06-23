package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权商标许可信息 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:10
 */
public class DataCompanyIprTrademarkLicenseId extends Id {

	public DataCompanyIprTrademarkLicenseId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权商标许可信息 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprTrademarkLicenseId of(Long id){
		return new DataCompanyIprTrademarkLicenseId(id);
	}
}

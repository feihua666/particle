package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业行政许可 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
public class DataCompanyAdministrativeLicenseId extends Id {

	public DataCompanyAdministrativeLicenseId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业行政许可 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyAdministrativeLicenseId of(Long id){
		return new DataCompanyAdministrativeLicenseId(id);
	}
}

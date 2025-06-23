package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业主要人员职位 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
public class DataCompanyPrimeStaffPositionId extends Id {

	public DataCompanyPrimeStaffPositionId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业主要人员职位 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyPrimeStaffPositionId of(Long id){
		return new DataCompanyPrimeStaffPositionId(id);
	}
}

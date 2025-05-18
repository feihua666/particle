package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业个人 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
public class DataCompanyPersonId extends Id {

	public DataCompanyPersonId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业个人 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyPersonId of(Long id){
		return new DataCompanyPersonId(id);
	}
}

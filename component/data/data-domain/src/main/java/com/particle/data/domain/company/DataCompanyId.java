package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
public class DataCompanyId extends Id {

	public DataCompanyId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyId of(Long id){
		return new DataCompanyId(id);
	}
}

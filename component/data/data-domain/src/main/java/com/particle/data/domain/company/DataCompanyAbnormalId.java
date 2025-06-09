package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业经营异常 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
public class DataCompanyAbnormalId extends Id {

	public DataCompanyAbnormalId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业经营异常 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyAbnormalId of(Long id){
		return new DataCompanyAbnormalId(id);
	}
}

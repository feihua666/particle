package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权植物新品种 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:40
 */
public class DataCompanyIprPlantVarietyId extends Id {

	public DataCompanyIprPlantVarietyId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权植物新品种 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprPlantVarietyId of(Long id){
		return new DataCompanyIprPlantVarietyId(id);
	}
}

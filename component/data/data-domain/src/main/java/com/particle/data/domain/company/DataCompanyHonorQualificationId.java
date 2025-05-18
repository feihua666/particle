package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业荣誉资质 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
public class DataCompanyHonorQualificationId extends Id {

	public DataCompanyHonorQualificationId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业荣誉资质 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyHonorQualificationId of(Long id){
		return new DataCompanyHonorQualificationId(id);
	}
}

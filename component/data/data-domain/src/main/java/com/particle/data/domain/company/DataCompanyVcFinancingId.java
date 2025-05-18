package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业融资 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
public class DataCompanyVcFinancingId extends Id {

	public DataCompanyVcFinancingId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业融资 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyVcFinancingId of(Long id){
		return new DataCompanyVcFinancingId(id);
	}
}

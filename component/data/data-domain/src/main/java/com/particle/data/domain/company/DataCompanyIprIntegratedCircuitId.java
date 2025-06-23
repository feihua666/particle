package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权集成电路 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
public class DataCompanyIprIntegratedCircuitId extends Id {

	public DataCompanyIprIntegratedCircuitId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权集成电路 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprIntegratedCircuitId of(Long id){
		return new DataCompanyIprIntegratedCircuitId(id);
	}
}

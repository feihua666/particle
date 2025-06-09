package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业统计 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
public class DataCompanyStatisticId extends Id {

	public DataCompanyStatisticId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业统计 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyStatisticId of(Long id){
		return new DataCompanyStatisticId(id);
	}
}

package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权专利统计 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
public class DataCompanyIprPatentStatisticId extends Id {

	public DataCompanyIprPatentStatisticId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权专利统计 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprPatentStatisticId of(Long id){
		return new DataCompanyIprPatentStatisticId(id);
	}
}

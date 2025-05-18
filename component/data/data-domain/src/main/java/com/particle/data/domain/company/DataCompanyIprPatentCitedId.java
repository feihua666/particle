package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权专利被引证信息 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:15
 */
public class DataCompanyIprPatentCitedId extends Id {

	public DataCompanyIprPatentCitedId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权专利被引证信息 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprPatentCitedId of(Long id){
		return new DataCompanyIprPatentCitedId(id);
	}
}

package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权专利 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
public class DataCompanyIprPatentId extends Id {

	public DataCompanyIprPatentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权专利 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprPatentId of(Long id){
		return new DataCompanyIprPatentId(id);
	}
}

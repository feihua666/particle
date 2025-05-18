package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权专利内容 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
public class DataCompanyIprPatentContentId extends Id {

	public DataCompanyIprPatentContentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权专利内容 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprPatentContentId of(Long id){
		return new DataCompanyIprPatentContentId(id);
	}
}

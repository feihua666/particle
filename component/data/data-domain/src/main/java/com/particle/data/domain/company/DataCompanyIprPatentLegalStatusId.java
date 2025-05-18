package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权专利法律状态 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
public class DataCompanyIprPatentLegalStatusId extends Id {

	public DataCompanyIprPatentLegalStatusId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权专利法律状态 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprPatentLegalStatusId of(Long id){
		return new DataCompanyIprPatentLegalStatusId(id);
	}
}

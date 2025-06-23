package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权地理标识 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:33
 */
public class DataCompanyIprGeograId extends Id {

	public DataCompanyIprGeograId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权地理标识 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprGeograId of(Long id){
		return new DataCompanyIprGeograId(id);
	}
}

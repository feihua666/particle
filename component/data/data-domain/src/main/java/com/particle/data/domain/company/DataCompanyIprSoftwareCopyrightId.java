package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权软件著作 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:01
 */
public class DataCompanyIprSoftwareCopyrightId extends Id {

	public DataCompanyIprSoftwareCopyrightId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权软件著作 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprSoftwareCopyrightId of(Long id){
		return new DataCompanyIprSoftwareCopyrightId(id);
	}
}

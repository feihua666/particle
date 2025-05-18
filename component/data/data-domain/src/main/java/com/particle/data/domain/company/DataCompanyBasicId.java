package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业基本信息 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
public class DataCompanyBasicId extends Id {

	public DataCompanyBasicId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业基本信息 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyBasicId of(Long id){
		return new DataCompanyBasicId(id);
	}
}

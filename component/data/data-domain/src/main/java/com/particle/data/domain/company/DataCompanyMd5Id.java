package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业md5 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
public class DataCompanyMd5Id extends Id {

	public DataCompanyMd5Id(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业md5 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyMd5Id of(Long id){
		return new DataCompanyMd5Id(id);
	}
}

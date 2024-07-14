package com.particle.data.domain.temp;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业md5ids 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
public class DataCompanyMd5IdsId extends Id {

	public DataCompanyMd5IdsId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业md5ids 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyMd5IdsId of(Long id){
		return new DataCompanyMd5IdsId(id);
	}
}

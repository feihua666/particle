package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业抽查检查 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
public class DataCompanySpotCheckId extends Id {

	public DataCompanySpotCheckId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业抽查检查 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanySpotCheckId of(Long id){
		return new DataCompanySpotCheckId(id);
	}
}

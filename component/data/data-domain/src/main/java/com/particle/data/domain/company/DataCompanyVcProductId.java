package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业融资产品 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
public class DataCompanyVcProductId extends Id {

	public DataCompanyVcProductId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业融资产品 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyVcProductId of(Long id){
		return new DataCompanyVcProductId(id);
	}
}

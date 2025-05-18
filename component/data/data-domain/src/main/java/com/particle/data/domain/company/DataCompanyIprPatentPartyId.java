package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权当事人 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
public class DataCompanyIprPatentPartyId extends Id {

	public DataCompanyIprPatentPartyId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权当事人 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprPatentPartyId of(Long id){
		return new DataCompanyIprPatentPartyId(id);
	}
}

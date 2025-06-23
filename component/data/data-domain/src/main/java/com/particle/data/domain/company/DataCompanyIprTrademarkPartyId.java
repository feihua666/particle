package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权商标当事人 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:34
 */
public class DataCompanyIprTrademarkPartyId extends Id {

	public DataCompanyIprTrademarkPartyId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权商标当事人 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprTrademarkPartyId of(Long id){
		return new DataCompanyIprTrademarkPartyId(id);
	}
}

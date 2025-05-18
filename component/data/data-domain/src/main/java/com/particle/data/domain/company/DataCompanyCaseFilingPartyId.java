package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业立案信息当事人 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:50
 */
public class DataCompanyCaseFilingPartyId extends Id {

	public DataCompanyCaseFilingPartyId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业立案信息当事人 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyCaseFilingPartyId of(Long id){
		return new DataCompanyCaseFilingPartyId(id);
	}
}

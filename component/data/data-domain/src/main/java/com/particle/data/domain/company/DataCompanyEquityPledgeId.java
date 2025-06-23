package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业股权出质 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
public class DataCompanyEquityPledgeId extends Id {

	public DataCompanyEquityPledgeId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业股权出质 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyEquityPledgeId of(Long id){
		return new DataCompanyEquityPledgeId(id);
	}
}

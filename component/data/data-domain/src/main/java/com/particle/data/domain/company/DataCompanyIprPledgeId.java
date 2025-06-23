package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权出质 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:21
 */
public class DataCompanyIprPledgeId extends Id {

	public DataCompanyIprPledgeId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权出质 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprPledgeId of(Long id){
		return new DataCompanyIprPledgeId(id);
	}
}

package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业限制高消费 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
public class DataCompanyRestrictHighConsumeId extends Id {

	public DataCompanyRestrictHighConsumeId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业限制高消费 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyRestrictHighConsumeId of(Long id){
		return new DataCompanyRestrictHighConsumeId(id);
	}
}

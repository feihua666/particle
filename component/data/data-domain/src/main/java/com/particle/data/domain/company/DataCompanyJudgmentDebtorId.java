package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业被执行人 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:07
 */
public class DataCompanyJudgmentDebtorId extends Id {

	public DataCompanyJudgmentDebtorId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业被执行人 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyJudgmentDebtorId of(Long id){
		return new DataCompanyJudgmentDebtorId(id);
	}
}

package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业失信被执行人 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
public class DataCompanyDiscreditedJudgmentDebtorId extends Id {

	public DataCompanyDiscreditedJudgmentDebtorId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业失信被执行人 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyDiscreditedJudgmentDebtorId of(Long id){
		return new DataCompanyDiscreditedJudgmentDebtorId(id);
	}
}

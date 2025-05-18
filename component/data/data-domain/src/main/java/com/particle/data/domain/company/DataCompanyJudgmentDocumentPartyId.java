package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业裁判文书当事人 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:05
 */
public class DataCompanyJudgmentDocumentPartyId extends Id {

	public DataCompanyJudgmentDocumentPartyId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业裁判文书当事人 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyJudgmentDocumentPartyId of(Long id){
		return new DataCompanyJudgmentDocumentPartyId(id);
	}
}

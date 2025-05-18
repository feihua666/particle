package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业裁判文书 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
public class DataCompanyJudgmentDocumentId extends Id {

	public DataCompanyJudgmentDocumentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业裁判文书 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyJudgmentDocumentId of(Long id){
		return new DataCompanyJudgmentDocumentId(id);
	}
}

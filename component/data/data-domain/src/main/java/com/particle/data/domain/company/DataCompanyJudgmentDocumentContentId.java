package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业裁判文书内容 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
public class DataCompanyJudgmentDocumentContentId extends Id {

	public DataCompanyJudgmentDocumentContentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业裁判文书内容 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyJudgmentDocumentContentId of(Long id){
		return new DataCompanyJudgmentDocumentContentId(id);
	}
}

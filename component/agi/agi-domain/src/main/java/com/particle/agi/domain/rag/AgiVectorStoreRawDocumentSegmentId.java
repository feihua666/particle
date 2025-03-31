package com.particle.agi.domain.rag;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 知识存储原始文档片段 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
public class AgiVectorStoreRawDocumentSegmentId extends Id {

	public AgiVectorStoreRawDocumentSegmentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 知识存储原始文档片段 领域模型id
	 * @param id
	 * @return
	 */
	public static AgiVectorStoreRawDocumentSegmentId of(Long id){
		return new AgiVectorStoreRawDocumentSegmentId(id);
	}
}

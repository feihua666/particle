package com.particle.agi.domain.rag;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 知识存储原始文档 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
public class AgiVectorStoreRawDocumentId extends Id {

	public AgiVectorStoreRawDocumentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 知识存储原始文档 领域模型id
	 * @param id
	 * @return
	 */
	public static AgiVectorStoreRawDocumentId of(Long id){
		return new AgiVectorStoreRawDocumentId(id);
	}
}

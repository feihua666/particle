package com.particle.agi.domain.rag.gateway;

import com.particle.agi.domain.rag.AgiVectorStoreRawDocument;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 知识存储原始文档 防腐层
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
public interface AgiVectorStoreRawDocumentGateway extends IBaseGateway<AgiVectorStoreRawDocumentId,AgiVectorStoreRawDocument> {
}

package com.particle.agi.infrastructure.gateway.impl;

import com.google.common.collect.Lists;
import com.particle.agi.domain.gateway.AgiEmbeddingGateway;
import com.particle.agi.domain.values.AgiDocument;
import com.particle.agi.infrastructure.tool.AgiDocumentTool;
import com.particle.common.domain.gateway.IGateway;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 嵌入网关,主要是提供文档向量化的操作
 * </p>
 *
 * @author yangwei
 * @since 2025-01-14 17:34:22
 */
@Component
public class AgiEmbeddingGatewayImpl implements AgiEmbeddingGateway {

    private VectorStore vectorStore;

    @Override
    public Boolean deleteEmbeddingDataById(String id) {
        return deleteEmbeddingDataByIds(List.of(id));
    }

    @Override
    public Boolean deleteEmbeddingDataByIds(List<String> ids) {
         vectorStore.delete(ids);
         return true;
    }

    @Override
    public Boolean embedding(AgiDocument agiDocument) {
        Document document = AgiDocumentTool.toDocument(agiDocument);
        vectorStore.add(Lists.newArrayList(document));
        return true;
    }

    @Autowired
    public void setVectorStore(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }
}

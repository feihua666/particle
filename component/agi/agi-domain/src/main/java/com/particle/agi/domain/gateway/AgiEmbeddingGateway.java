package com.particle.agi.domain.gateway;

import com.particle.agi.domain.values.AgiDocument;
import com.particle.common.domain.gateway.IGateway;

import java.util.List;

/**
 * <p>
 * 嵌入网关,主要是提供文档向量化的操作
 * </p>
 *
 * @author yangwei
 * @since 2025-01-14 17:34:22
 */
public interface AgiEmbeddingGateway extends IGateway {

    /**
     * 根据 id 删除向量数据
     * @param id
     * @return
     */
    public Boolean deleteEmbeddingDataById(String id);
    /**
     * 根据 ids 删除向量数据
     * @param ids
     * @return
     */
    public Boolean deleteEmbeddingDataByIds(List<String> ids);
    /**
     * 向量化文档
     * @param agiDocument 文档
     * @return
     */
    public Boolean embedding(AgiDocument agiDocument);
}

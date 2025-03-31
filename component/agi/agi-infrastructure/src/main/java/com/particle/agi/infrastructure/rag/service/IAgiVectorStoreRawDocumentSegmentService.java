package com.particle.agi.infrastructure.rag.service;

import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentSegmentDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 知识存储原始文档片段 服务类
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
public interface IAgiVectorStoreRawDocumentSegmentService extends IBaseService<AgiVectorStoreRawDocumentSegmentDO> {

    /**
     * 根据知识存储原始文档表id查询
     * @param agiVectorStoreRawDocumentId
     * @return
     */
    default List<AgiVectorStoreRawDocumentSegmentDO> getByAgiVectorStoreRawDocumentId(Long agiVectorStoreRawDocumentId) {
        Assert.notNull(agiVectorStoreRawDocumentId,"agiVectorStoreRawDocumentId 不能为空");
        return list(Wrappers.<AgiVectorStoreRawDocumentSegmentDO>lambdaQuery().eq(AgiVectorStoreRawDocumentSegmentDO::getAgiVectorStoreRawDocumentId, agiVectorStoreRawDocumentId));
    }
    /**
     * 统计
     * 根据知识存储原始文档表id查询未嵌入的片段数量
     * @param agiVectorStoreRawDocumentId
     * @return
     */
    default long countNoneEmbeddedByAgiVectorStoreRawDocumentId(Long agiVectorStoreRawDocumentId) {
        Assert.notNull(agiVectorStoreRawDocumentId,"agiVectorStoreRawDocumentId 不能为空");
        return count(Wrappers.<AgiVectorStoreRawDocumentSegmentDO>lambdaQuery()
                .eq(AgiVectorStoreRawDocumentSegmentDO::getAgiVectorStoreRawDocumentId, agiVectorStoreRawDocumentId)
                .eq(AgiVectorStoreRawDocumentSegmentDO::getIsEmbedded, false)
        );
    }


    /**
     * 根据知识存储原始文档表id查询多个
     * @param agiVectorStoreRawDocumentIds
     * @return
     */
    default List<AgiVectorStoreRawDocumentSegmentDO> getByAgiVectorStoreRawDocumentIds(List<Long> agiVectorStoreRawDocumentIds) {
        Assert.notEmpty(agiVectorStoreRawDocumentIds,"agiVectorStoreRawDocumentIds 不能为空");
        return list(Wrappers.<AgiVectorStoreRawDocumentSegmentDO>lambdaQuery().in(AgiVectorStoreRawDocumentSegmentDO::getAgiVectorStoreRawDocumentId, agiVectorStoreRawDocumentIds));
    }












}

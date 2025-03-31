package com.particle.agi.infrastructure.rag.mapper;

import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentSegmentDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 知识存储原始文档片段 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Mapper
public interface AgiVectorStoreRawDocumentSegmentMapper extends IBaseMapper<AgiVectorStoreRawDocumentSegmentDO> {

}

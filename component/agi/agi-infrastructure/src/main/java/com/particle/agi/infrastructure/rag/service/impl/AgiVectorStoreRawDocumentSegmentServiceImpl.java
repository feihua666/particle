package com.particle.agi.infrastructure.rag.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentSegmentDO;
import com.particle.agi.infrastructure.rag.mapper.AgiVectorStoreRawDocumentSegmentMapper;
import com.particle.agi.infrastructure.rag.service.IAgiVectorStoreRawDocumentSegmentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 知识存储原始文档片段 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Component
public class AgiVectorStoreRawDocumentSegmentServiceImpl extends IBaseServiceImpl<AgiVectorStoreRawDocumentSegmentMapper, AgiVectorStoreRawDocumentSegmentDO> implements IAgiVectorStoreRawDocumentSegmentService {
	private IBaseQueryCommandMapStruct<AgiVectorStoreRawDocumentSegmentDO> queryCommandMapStruct;

	@Override
	protected AgiVectorStoreRawDocumentSegmentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<AgiVectorStoreRawDocumentSegmentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(AgiVectorStoreRawDocumentSegmentDO po) {
	}

	@Override
	protected void preUpdate(AgiVectorStoreRawDocumentSegmentDO po) {
    
	}
}

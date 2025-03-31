package com.particle.agi.infrastructure.rag.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentDO;
import com.particle.agi.infrastructure.rag.mapper.AgiVectorStoreRawDocumentMapper;
import com.particle.agi.infrastructure.rag.service.IAgiVectorStoreRawDocumentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 知识存储原始文档 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Component
public class AgiVectorStoreRawDocumentServiceImpl extends IBaseServiceImpl<AgiVectorStoreRawDocumentMapper, AgiVectorStoreRawDocumentDO> implements IAgiVectorStoreRawDocumentService {
	private IBaseQueryCommandMapStruct<AgiVectorStoreRawDocumentDO> queryCommandMapStruct;

	@Override
	protected AgiVectorStoreRawDocumentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<AgiVectorStoreRawDocumentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(AgiVectorStoreRawDocumentDO po) {
	}

	@Override
	protected void preUpdate(AgiVectorStoreRawDocumentDO po) {
    
	}
}

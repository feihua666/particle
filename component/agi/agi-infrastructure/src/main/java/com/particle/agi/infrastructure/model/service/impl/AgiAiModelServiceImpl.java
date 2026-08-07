package com.particle.agi.infrastructure.model.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.agi.infrastructure.model.dos.AgiAiModelDO;
import com.particle.agi.infrastructure.model.mapper.AgiAiModelMapper;
import com.particle.agi.infrastructure.model.service.IAgiAiModelService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * AI模型 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Component
public class AgiAiModelServiceImpl extends IBaseServiceImpl<AgiAiModelMapper, AgiAiModelDO> implements IAgiAiModelService {
	private IBaseQueryCommandMapStruct<AgiAiModelDO> queryCommandMapStruct;

	@Override
	protected AgiAiModelDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<AgiAiModelDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(AgiAiModelDO po) {
	}

	@Override
	protected void preUpdate(AgiAiModelDO po) {
    
	}
}

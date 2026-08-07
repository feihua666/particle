package com.particle.workflow.infrastructure.definition.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionHistoryDO;
import com.particle.workflow.infrastructure.definition.mapper.WorkflowDefinitionHistoryMapper;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionHistoryService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 工作流定义历史 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Component
public class WorkflowDefinitionHistoryServiceImpl extends IBaseServiceImpl<WorkflowDefinitionHistoryMapper, WorkflowDefinitionHistoryDO> implements IWorkflowDefinitionHistoryService {
	private IBaseQueryCommandMapStruct<WorkflowDefinitionHistoryDO> queryCommandMapStruct;

	@Override
	protected WorkflowDefinitionHistoryDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<WorkflowDefinitionHistoryDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(WorkflowDefinitionHistoryDO po) {
	}

	@Override
	protected void preUpdate(WorkflowDefinitionHistoryDO po) {
    
	}
}

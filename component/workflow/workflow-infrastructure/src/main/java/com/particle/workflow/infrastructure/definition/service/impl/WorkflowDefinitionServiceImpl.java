package com.particle.workflow.infrastructure.definition.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionDO;
import com.particle.workflow.infrastructure.definition.mapper.WorkflowDefinitionMapper;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 工作流定义 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Component
public class WorkflowDefinitionServiceImpl extends IBaseServiceImpl<WorkflowDefinitionMapper, WorkflowDefinitionDO> implements IWorkflowDefinitionService {
	private IBaseQueryCommandMapStruct<WorkflowDefinitionDO> queryCommandMapStruct;

	@Override
	protected WorkflowDefinitionDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<WorkflowDefinitionDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(WorkflowDefinitionDO po) {
	}

	@Override
	protected void preUpdate(WorkflowDefinitionDO po) {
    
	}
}

package com.particle.workflow.infrastructure.execution.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionDO;
import com.particle.workflow.infrastructure.execution.mapper.WorkflowExecutionMapper;
import com.particle.workflow.infrastructure.execution.service.IWorkflowExecutionService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 工作流执行实例 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Component
public class WorkflowExecutionServiceImpl extends IBaseServiceImpl<WorkflowExecutionMapper, WorkflowExecutionDO> implements IWorkflowExecutionService {
	private IBaseQueryCommandMapStruct<WorkflowExecutionDO> queryCommandMapStruct;

	@Override
	protected WorkflowExecutionDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<WorkflowExecutionDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(WorkflowExecutionDO po) {
	}

	@Override
	protected void preUpdate(WorkflowExecutionDO po) {
    
	}
}

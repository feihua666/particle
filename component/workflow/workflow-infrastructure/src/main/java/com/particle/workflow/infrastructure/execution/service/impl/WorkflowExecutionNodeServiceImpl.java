package com.particle.workflow.infrastructure.execution.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionNodeDO;
import com.particle.workflow.infrastructure.execution.mapper.WorkflowExecutionNodeMapper;
import com.particle.workflow.infrastructure.execution.service.IWorkflowExecutionNodeService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 工作流节点执行实例 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Component
public class WorkflowExecutionNodeServiceImpl extends IBaseServiceImpl<WorkflowExecutionNodeMapper, WorkflowExecutionNodeDO> implements IWorkflowExecutionNodeService {
	private IBaseQueryCommandMapStruct<WorkflowExecutionNodeDO> queryCommandMapStruct;

	@Override
	protected WorkflowExecutionNodeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<WorkflowExecutionNodeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(WorkflowExecutionNodeDO po) {
	}

	@Override
	protected void preUpdate(WorkflowExecutionNodeDO po) {
    
	}
}

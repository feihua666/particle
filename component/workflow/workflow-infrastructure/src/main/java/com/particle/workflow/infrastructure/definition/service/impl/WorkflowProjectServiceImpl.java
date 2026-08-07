package com.particle.workflow.infrastructure.definition.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.workflow.infrastructure.definition.dos.WorkflowProjectDO;
import com.particle.workflow.infrastructure.definition.mapper.WorkflowProjectMapper;
import com.particle.workflow.infrastructure.definition.service.IWorkflowProjectService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 工作流项目 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:12
 */
@Component
public class WorkflowProjectServiceImpl extends IBaseServiceImpl<WorkflowProjectMapper, WorkflowProjectDO> implements IWorkflowProjectService {
	private IBaseQueryCommandMapStruct<WorkflowProjectDO> queryCommandMapStruct;

	@Override
	protected WorkflowProjectDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<WorkflowProjectDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(WorkflowProjectDO po) {
	}

	@Override
	protected void preUpdate(WorkflowProjectDO po) {
    
	}
}

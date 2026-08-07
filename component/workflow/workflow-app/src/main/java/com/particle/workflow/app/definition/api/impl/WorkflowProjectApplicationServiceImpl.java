package com.particle.workflow.app.definition.api.impl;

import com.particle.workflow.app.definition.executor.WorkflowProjectCreateCommandExecutor;
import com.particle.workflow.app.definition.executor.WorkflowProjectDeleteCommandExecutor;
import com.particle.workflow.app.definition.executor.WorkflowProjectUpdateCommandExecutor;
import com.particle.workflow.app.definition.executor.WorkflowProjectCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.workflow.client.definition.dto.command.WorkflowProjectUpdateCommand;
import com.particle.workflow.client.definition.api.IWorkflowProjectApplicationService;
import com.particle.workflow.client.definition.dto.command.WorkflowProjectCreateCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowProjectVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 工作流项目 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:12
 */
@Transactional
@Service
@CatchAndLog
public class WorkflowProjectApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IWorkflowProjectApplicationService {

    private WorkflowProjectCreateCommandExecutor workflowProjectCreateCommandExecutor;

    private WorkflowProjectDeleteCommandExecutor workflowProjectDeleteCommandExecutor;

    private WorkflowProjectUpdateCommandExecutor workflowProjectUpdateCommandExecutor;

    private WorkflowProjectCommandExecutor workflowProjectCommandExecutor;


    @Override
    public SingleResponse<WorkflowProjectVO> create(WorkflowProjectCreateCommand workflowProjectCreateCommand) {
        return workflowProjectCreateCommandExecutor.execute(workflowProjectCreateCommand);
    }

    @Override
    public SingleResponse<WorkflowProjectVO> delete(CommonIdCommand deleteCommand) {
        return workflowProjectDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<WorkflowProjectVO> update(WorkflowProjectUpdateCommand workflowProjectUpdateCommand) {
        return workflowProjectUpdateCommandExecutor.execute(workflowProjectUpdateCommand);
    }


    @Autowired
    public void setWorkflowProjectCreateCommandExecutor(WorkflowProjectCreateCommandExecutor workflowProjectCreateCommandExecutor) {
        this.workflowProjectCreateCommandExecutor = workflowProjectCreateCommandExecutor;
    }

    @Autowired
    public void setWorkflowProjectDeleteCommandExecutor(WorkflowProjectDeleteCommandExecutor workflowProjectDeleteCommandExecutor) {
        this.workflowProjectDeleteCommandExecutor = workflowProjectDeleteCommandExecutor;
    }
    @Autowired
    public void setWorkflowProjectUpdateCommandExecutor(WorkflowProjectUpdateCommandExecutor workflowProjectUpdateCommandExecutor) {
        this.workflowProjectUpdateCommandExecutor = workflowProjectUpdateCommandExecutor;
    }
    @Autowired
    public void setWorkflowProjectCommandExecutor(WorkflowProjectCommandExecutor workflowProjectCommandExecutor) {
        this.workflowProjectCommandExecutor = workflowProjectCommandExecutor;
    }
}

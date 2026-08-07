package com.particle.workflow.app.definition.api.impl;

import com.particle.workflow.app.definition.executor.WorkflowDefinitionCreateCommandExecutor;
import com.particle.workflow.app.definition.executor.WorkflowDefinitionDeleteCommandExecutor;
import com.particle.workflow.app.definition.executor.WorkflowDefinitionUpdateCommandExecutor;
import com.particle.workflow.app.definition.executor.WorkflowDefinitionCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionUpdateCommand;
import com.particle.workflow.client.definition.api.IWorkflowDefinitionApplicationService;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionCreateCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 工作流定义 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Transactional
@Service
@CatchAndLog
public class WorkflowDefinitionApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IWorkflowDefinitionApplicationService {

    private WorkflowDefinitionCreateCommandExecutor workflowDefinitionCreateCommandExecutor;

    private WorkflowDefinitionDeleteCommandExecutor workflowDefinitionDeleteCommandExecutor;

    private WorkflowDefinitionUpdateCommandExecutor workflowDefinitionUpdateCommandExecutor;

    private WorkflowDefinitionCommandExecutor workflowDefinitionCommandExecutor;


    @Override
    public SingleResponse<WorkflowDefinitionVO> create(WorkflowDefinitionCreateCommand workflowDefinitionCreateCommand) {
        return workflowDefinitionCreateCommandExecutor.execute(workflowDefinitionCreateCommand);
    }

    @Override
    public SingleResponse<WorkflowDefinitionVO> delete(CommonIdCommand deleteCommand) {
        return workflowDefinitionDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<WorkflowDefinitionVO> update(WorkflowDefinitionUpdateCommand workflowDefinitionUpdateCommand) {
        return workflowDefinitionUpdateCommandExecutor.execute(workflowDefinitionUpdateCommand);
    }


    @Autowired
    public void setWorkflowDefinitionCreateCommandExecutor(WorkflowDefinitionCreateCommandExecutor workflowDefinitionCreateCommandExecutor) {
        this.workflowDefinitionCreateCommandExecutor = workflowDefinitionCreateCommandExecutor;
    }

    @Autowired
    public void setWorkflowDefinitionDeleteCommandExecutor(WorkflowDefinitionDeleteCommandExecutor workflowDefinitionDeleteCommandExecutor) {
        this.workflowDefinitionDeleteCommandExecutor = workflowDefinitionDeleteCommandExecutor;
    }
    @Autowired
    public void setWorkflowDefinitionUpdateCommandExecutor(WorkflowDefinitionUpdateCommandExecutor workflowDefinitionUpdateCommandExecutor) {
        this.workflowDefinitionUpdateCommandExecutor = workflowDefinitionUpdateCommandExecutor;
    }
    @Autowired
    public void setWorkflowDefinitionCommandExecutor(WorkflowDefinitionCommandExecutor workflowDefinitionCommandExecutor) {
        this.workflowDefinitionCommandExecutor = workflowDefinitionCommandExecutor;
    }
}

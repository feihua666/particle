package com.particle.workflow.app.execution.api.impl;

import com.particle.workflow.app.execution.executor.WorkflowExecutionNodeCreateCommandExecutor;
import com.particle.workflow.app.execution.executor.WorkflowExecutionNodeDeleteCommandExecutor;
import com.particle.workflow.app.execution.executor.WorkflowExecutionNodeUpdateCommandExecutor;
import com.particle.workflow.app.execution.executor.WorkflowExecutionNodeCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.workflow.client.execution.dto.command.WorkflowExecutionNodeUpdateCommand;
import com.particle.workflow.client.execution.api.IWorkflowExecutionNodeApplicationService;
import com.particle.workflow.client.execution.dto.command.WorkflowExecutionNodeCreateCommand;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionNodeVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 工作流节点执行实例 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Transactional
@Service
@CatchAndLog
public class WorkflowExecutionNodeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IWorkflowExecutionNodeApplicationService {

    private WorkflowExecutionNodeCreateCommandExecutor workflowExecutionNodeCreateCommandExecutor;

    private WorkflowExecutionNodeDeleteCommandExecutor workflowExecutionNodeDeleteCommandExecutor;

    private WorkflowExecutionNodeUpdateCommandExecutor workflowExecutionNodeUpdateCommandExecutor;

    private WorkflowExecutionNodeCommandExecutor workflowExecutionNodeCommandExecutor;


    @Override
    public SingleResponse<WorkflowExecutionNodeVO> create(WorkflowExecutionNodeCreateCommand workflowExecutionNodeCreateCommand) {
        return workflowExecutionNodeCreateCommandExecutor.execute(workflowExecutionNodeCreateCommand);
    }

    @Override
    public SingleResponse<WorkflowExecutionNodeVO> delete(CommonIdCommand deleteCommand) {
        return workflowExecutionNodeDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<WorkflowExecutionNodeVO> update(WorkflowExecutionNodeUpdateCommand workflowExecutionNodeUpdateCommand) {
        return workflowExecutionNodeUpdateCommandExecutor.execute(workflowExecutionNodeUpdateCommand);
    }


    @Autowired
    public void setWorkflowExecutionNodeCreateCommandExecutor(WorkflowExecutionNodeCreateCommandExecutor workflowExecutionNodeCreateCommandExecutor) {
        this.workflowExecutionNodeCreateCommandExecutor = workflowExecutionNodeCreateCommandExecutor;
    }

    @Autowired
    public void setWorkflowExecutionNodeDeleteCommandExecutor(WorkflowExecutionNodeDeleteCommandExecutor workflowExecutionNodeDeleteCommandExecutor) {
        this.workflowExecutionNodeDeleteCommandExecutor = workflowExecutionNodeDeleteCommandExecutor;
    }
    @Autowired
    public void setWorkflowExecutionNodeUpdateCommandExecutor(WorkflowExecutionNodeUpdateCommandExecutor workflowExecutionNodeUpdateCommandExecutor) {
        this.workflowExecutionNodeUpdateCommandExecutor = workflowExecutionNodeUpdateCommandExecutor;
    }
    @Autowired
    public void setWorkflowExecutionNodeCommandExecutor(WorkflowExecutionNodeCommandExecutor workflowExecutionNodeCommandExecutor) {
        this.workflowExecutionNodeCommandExecutor = workflowExecutionNodeCommandExecutor;
    }
}

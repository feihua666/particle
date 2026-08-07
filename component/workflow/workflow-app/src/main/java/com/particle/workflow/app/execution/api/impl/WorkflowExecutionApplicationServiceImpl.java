package com.particle.workflow.app.execution.api.impl;

import com.particle.workflow.app.execution.executor.WorkflowExecutionCreateCommandExecutor;
import com.particle.workflow.app.execution.executor.WorkflowExecutionDeleteCommandExecutor;
import com.particle.workflow.app.execution.executor.WorkflowExecutionUpdateCommandExecutor;
import com.particle.workflow.app.execution.executor.WorkflowExecutionCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.workflow.client.execution.dto.command.WorkflowExecutionUpdateCommand;
import com.particle.workflow.client.execution.api.IWorkflowExecutionApplicationService;
import com.particle.workflow.client.execution.dto.command.WorkflowExecutionCreateCommand;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 工作流执行实例 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Transactional
@Service
@CatchAndLog
public class WorkflowExecutionApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IWorkflowExecutionApplicationService {

    private WorkflowExecutionCreateCommandExecutor workflowExecutionCreateCommandExecutor;

    private WorkflowExecutionDeleteCommandExecutor workflowExecutionDeleteCommandExecutor;

    private WorkflowExecutionUpdateCommandExecutor workflowExecutionUpdateCommandExecutor;

    private WorkflowExecutionCommandExecutor workflowExecutionCommandExecutor;


    @Override
    public SingleResponse<WorkflowExecutionVO> create(WorkflowExecutionCreateCommand workflowExecutionCreateCommand) {
        return workflowExecutionCreateCommandExecutor.execute(workflowExecutionCreateCommand);
    }

    @Override
    public SingleResponse<WorkflowExecutionVO> delete(CommonIdCommand deleteCommand) {
        return workflowExecutionDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<WorkflowExecutionVO> update(WorkflowExecutionUpdateCommand workflowExecutionUpdateCommand) {
        return workflowExecutionUpdateCommandExecutor.execute(workflowExecutionUpdateCommand);
    }


    @Autowired
    public void setWorkflowExecutionCreateCommandExecutor(WorkflowExecutionCreateCommandExecutor workflowExecutionCreateCommandExecutor) {
        this.workflowExecutionCreateCommandExecutor = workflowExecutionCreateCommandExecutor;
    }

    @Autowired
    public void setWorkflowExecutionDeleteCommandExecutor(WorkflowExecutionDeleteCommandExecutor workflowExecutionDeleteCommandExecutor) {
        this.workflowExecutionDeleteCommandExecutor = workflowExecutionDeleteCommandExecutor;
    }
    @Autowired
    public void setWorkflowExecutionUpdateCommandExecutor(WorkflowExecutionUpdateCommandExecutor workflowExecutionUpdateCommandExecutor) {
        this.workflowExecutionUpdateCommandExecutor = workflowExecutionUpdateCommandExecutor;
    }
    @Autowired
    public void setWorkflowExecutionCommandExecutor(WorkflowExecutionCommandExecutor workflowExecutionCommandExecutor) {
        this.workflowExecutionCommandExecutor = workflowExecutionCommandExecutor;
    }
}

package com.particle.workflow.app.definition.api.impl;

import com.particle.workflow.app.definition.executor.WorkflowDefinitionHistoryCreateCommandExecutor;
import com.particle.workflow.app.definition.executor.WorkflowDefinitionHistoryDeleteCommandExecutor;
import com.particle.workflow.app.definition.executor.WorkflowDefinitionHistoryUpdateCommandExecutor;
import com.particle.workflow.app.definition.executor.WorkflowDefinitionHistoryCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionHistoryCreateDraftCommand;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionHistoryUpdateCommand;
import com.particle.workflow.client.definition.api.IWorkflowDefinitionHistoryApplicationService;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionHistoryCreateCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionHistoryVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 工作流定义历史 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Transactional
@Service
@CatchAndLog
public class WorkflowDefinitionHistoryApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IWorkflowDefinitionHistoryApplicationService {

    private WorkflowDefinitionHistoryCreateCommandExecutor workflowDefinitionHistoryCreateCommandExecutor;

    private WorkflowDefinitionHistoryDeleteCommandExecutor workflowDefinitionHistoryDeleteCommandExecutor;

    private WorkflowDefinitionHistoryUpdateCommandExecutor workflowDefinitionHistoryUpdateCommandExecutor;

    private WorkflowDefinitionHistoryCommandExecutor workflowDefinitionHistoryCommandExecutor;


    @Override
    public SingleResponse<WorkflowDefinitionHistoryVO> create(WorkflowDefinitionHistoryCreateCommand workflowDefinitionHistoryCreateCommand) {
        return workflowDefinitionHistoryCreateCommandExecutor.execute(workflowDefinitionHistoryCreateCommand);
    }

    @Override
    public SingleResponse<WorkflowDefinitionHistoryVO> createDraft(WorkflowDefinitionHistoryCreateDraftCommand workflowDefinitionHistoryCreateDraftCommand) {
        return workflowDefinitionHistoryCreateCommandExecutor.createDraft(workflowDefinitionHistoryCreateDraftCommand);
    }

    @Override
    public SingleResponse<WorkflowDefinitionHistoryVO> delete(CommonIdCommand deleteCommand) {
        return workflowDefinitionHistoryDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<WorkflowDefinitionHistoryVO> update(WorkflowDefinitionHistoryUpdateCommand workflowDefinitionHistoryUpdateCommand) {
        return workflowDefinitionHistoryUpdateCommandExecutor.execute(workflowDefinitionHistoryUpdateCommand);
    }


    @Autowired
    public void setWorkflowDefinitionHistoryCreateCommandExecutor(WorkflowDefinitionHistoryCreateCommandExecutor workflowDefinitionHistoryCreateCommandExecutor) {
        this.workflowDefinitionHistoryCreateCommandExecutor = workflowDefinitionHistoryCreateCommandExecutor;
    }

    @Autowired
    public void setWorkflowDefinitionHistoryDeleteCommandExecutor(WorkflowDefinitionHistoryDeleteCommandExecutor workflowDefinitionHistoryDeleteCommandExecutor) {
        this.workflowDefinitionHistoryDeleteCommandExecutor = workflowDefinitionHistoryDeleteCommandExecutor;
    }
    @Autowired
    public void setWorkflowDefinitionHistoryUpdateCommandExecutor(WorkflowDefinitionHistoryUpdateCommandExecutor workflowDefinitionHistoryUpdateCommandExecutor) {
        this.workflowDefinitionHistoryUpdateCommandExecutor = workflowDefinitionHistoryUpdateCommandExecutor;
    }
    @Autowired
    public void setWorkflowDefinitionHistoryCommandExecutor(WorkflowDefinitionHistoryCommandExecutor workflowDefinitionHistoryCommandExecutor) {
        this.workflowDefinitionHistoryCommandExecutor = workflowDefinitionHistoryCommandExecutor;
    }
}

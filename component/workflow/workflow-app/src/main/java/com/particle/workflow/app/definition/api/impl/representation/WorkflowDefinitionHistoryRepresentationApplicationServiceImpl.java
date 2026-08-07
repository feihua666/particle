package com.particle.workflow.app.definition.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.workflow.app.definition.executor.representation.WorkflowDefinitionHistoryQueryCommandExecutor;
import com.particle.workflow.client.definition.api.representation.IWorkflowDefinitionHistoryRepresentationApplicationService;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionHistoryPageQueryCommand;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionHistoryQueryListCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 工作流定义历史 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Service
@CatchAndLog
public class WorkflowDefinitionHistoryRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IWorkflowDefinitionHistoryRepresentationApplicationService {

    private WorkflowDefinitionHistoryQueryCommandExecutor workflowDefinitionHistoryQueryCommandExecutor;

    @Override
    public SingleResponse<WorkflowDefinitionHistoryVO> queryDetail(CommonIdCommand detailCommand) {
        return workflowDefinitionHistoryQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<WorkflowDefinitionHistoryVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return workflowDefinitionHistoryQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<WorkflowDefinitionHistoryVO> pageQuery(WorkflowDefinitionHistoryPageQueryCommand workflowDefinitionHistoryPageQueryCommand) {
        return workflowDefinitionHistoryQueryCommandExecutor.execute(workflowDefinitionHistoryPageQueryCommand);
    }

    @Override
    public MultiResponse<WorkflowDefinitionHistoryVO> queryList(WorkflowDefinitionHistoryQueryListCommand workflowDefinitionHistoryQueryListCommand) {
        return workflowDefinitionHistoryQueryCommandExecutor.execute(workflowDefinitionHistoryQueryListCommand);
    }


    @Autowired
    public void setWorkflowDefinitionHistoryQueryCommandExecutor(WorkflowDefinitionHistoryQueryCommandExecutor workflowDefinitionHistoryQueryCommandExecutor) {
        this.workflowDefinitionHistoryQueryCommandExecutor = workflowDefinitionHistoryQueryCommandExecutor;
    }
}

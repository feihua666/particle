package com.particle.workflow.app.definition.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.workflow.app.definition.executor.representation.WorkflowDefinitionQueryCommandExecutor;
import com.particle.workflow.client.definition.api.representation.IWorkflowDefinitionRepresentationApplicationService;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionPageQueryCommand;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionQueryListCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 工作流定义 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Service
@CatchAndLog
public class WorkflowDefinitionRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IWorkflowDefinitionRepresentationApplicationService {

    private WorkflowDefinitionQueryCommandExecutor workflowDefinitionQueryCommandExecutor;

    @Override
    public SingleResponse<WorkflowDefinitionVO> queryDetail(CommonIdCommand detailCommand) {
        return workflowDefinitionQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<WorkflowDefinitionVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return workflowDefinitionQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<WorkflowDefinitionVO> pageQuery(WorkflowDefinitionPageQueryCommand workflowDefinitionPageQueryCommand) {
        return workflowDefinitionQueryCommandExecutor.execute(workflowDefinitionPageQueryCommand);
    }

    @Override
    public MultiResponse<WorkflowDefinitionVO> queryList(WorkflowDefinitionQueryListCommand workflowDefinitionQueryListCommand) {
        return workflowDefinitionQueryCommandExecutor.execute(workflowDefinitionQueryListCommand);
    }


    @Autowired
    public void setWorkflowDefinitionQueryCommandExecutor(WorkflowDefinitionQueryCommandExecutor workflowDefinitionQueryCommandExecutor) {
        this.workflowDefinitionQueryCommandExecutor = workflowDefinitionQueryCommandExecutor;
    }
}

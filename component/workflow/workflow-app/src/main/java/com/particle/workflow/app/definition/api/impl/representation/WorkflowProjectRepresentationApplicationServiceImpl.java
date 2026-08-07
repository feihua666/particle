package com.particle.workflow.app.definition.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.workflow.app.definition.executor.representation.WorkflowProjectQueryCommandExecutor;
import com.particle.workflow.client.definition.api.representation.IWorkflowProjectRepresentationApplicationService;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowProjectPageQueryCommand;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowProjectQueryListCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 工作流项目 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:12
 */
@Service
@CatchAndLog
public class WorkflowProjectRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IWorkflowProjectRepresentationApplicationService {

    private WorkflowProjectQueryCommandExecutor workflowProjectQueryCommandExecutor;

    @Override
    public SingleResponse<WorkflowProjectVO> queryDetail(CommonIdCommand detailCommand) {
        return workflowProjectQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<WorkflowProjectVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return workflowProjectQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<WorkflowProjectVO> pageQuery(WorkflowProjectPageQueryCommand workflowProjectPageQueryCommand) {
        return workflowProjectQueryCommandExecutor.execute(workflowProjectPageQueryCommand);
    }

    @Override
    public MultiResponse<WorkflowProjectVO> queryList(WorkflowProjectQueryListCommand workflowProjectQueryListCommand) {
        return workflowProjectQueryCommandExecutor.execute(workflowProjectQueryListCommand);
    }


    @Autowired
    public void setWorkflowProjectQueryCommandExecutor(WorkflowProjectQueryCommandExecutor workflowProjectQueryCommandExecutor) {
        this.workflowProjectQueryCommandExecutor = workflowProjectQueryCommandExecutor;
    }
}

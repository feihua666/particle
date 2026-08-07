package com.particle.workflow.app.execution.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.workflow.app.execution.executor.representation.WorkflowExecutionQueryCommandExecutor;
import com.particle.workflow.client.execution.api.representation.IWorkflowExecutionRepresentationApplicationService;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionPageQueryCommand;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionQueryListCommand;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 工作流执行实例 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Service
@CatchAndLog
public class WorkflowExecutionRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IWorkflowExecutionRepresentationApplicationService {

    private WorkflowExecutionQueryCommandExecutor workflowExecutionQueryCommandExecutor;

    @Override
    public SingleResponse<WorkflowExecutionVO> queryDetail(CommonIdCommand detailCommand) {
        return workflowExecutionQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<WorkflowExecutionVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return workflowExecutionQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<WorkflowExecutionVO> pageQuery(WorkflowExecutionPageQueryCommand workflowExecutionPageQueryCommand) {
        return workflowExecutionQueryCommandExecutor.execute(workflowExecutionPageQueryCommand);
    }

    @Override
    public MultiResponse<WorkflowExecutionVO> queryList(WorkflowExecutionQueryListCommand workflowExecutionQueryListCommand) {
        return workflowExecutionQueryCommandExecutor.execute(workflowExecutionQueryListCommand);
    }


    @Autowired
    public void setWorkflowExecutionQueryCommandExecutor(WorkflowExecutionQueryCommandExecutor workflowExecutionQueryCommandExecutor) {
        this.workflowExecutionQueryCommandExecutor = workflowExecutionQueryCommandExecutor;
    }
}

package com.particle.workflow.app.execution.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.workflow.app.execution.executor.representation.WorkflowExecutionNodeQueryCommandExecutor;
import com.particle.workflow.client.execution.api.representation.IWorkflowExecutionNodeRepresentationApplicationService;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionNodePageQueryCommand;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionNodeQueryListCommand;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionNodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 工作流节点执行实例 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Service
@CatchAndLog
public class WorkflowExecutionNodeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IWorkflowExecutionNodeRepresentationApplicationService {

    private WorkflowExecutionNodeQueryCommandExecutor workflowExecutionNodeQueryCommandExecutor;

    @Override
    public SingleResponse<WorkflowExecutionNodeVO> queryDetail(CommonIdCommand detailCommand) {
        return workflowExecutionNodeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<WorkflowExecutionNodeVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return workflowExecutionNodeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<WorkflowExecutionNodeVO> pageQuery(WorkflowExecutionNodePageQueryCommand workflowExecutionNodePageQueryCommand) {
        return workflowExecutionNodeQueryCommandExecutor.execute(workflowExecutionNodePageQueryCommand);
    }

    @Override
    public MultiResponse<WorkflowExecutionNodeVO> queryList(WorkflowExecutionNodeQueryListCommand workflowExecutionNodeQueryListCommand) {
        return workflowExecutionNodeQueryCommandExecutor.execute(workflowExecutionNodeQueryListCommand);
    }


    @Autowired
    public void setWorkflowExecutionNodeQueryCommandExecutor(WorkflowExecutionNodeQueryCommandExecutor workflowExecutionNodeQueryCommandExecutor) {
        this.workflowExecutionNodeQueryCommandExecutor = workflowExecutionNodeQueryCommandExecutor;
    }
}

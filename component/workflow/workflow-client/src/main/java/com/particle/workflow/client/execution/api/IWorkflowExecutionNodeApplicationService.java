package com.particle.workflow.client.execution.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.workflow.client.execution.dto.command.WorkflowExecutionNodeCreateCommand;
import com.particle.workflow.client.execution.dto.command.WorkflowExecutionNodeUpdateCommand;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionNodeVO;
/**
 * <p>
 * 工作流节点执行实例 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
public interface IWorkflowExecutionNodeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param workflowExecutionNodeCreateCommand
	 * @return
	 */
	SingleResponse<WorkflowExecutionNodeVO> create(WorkflowExecutionNodeCreateCommand workflowExecutionNodeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<WorkflowExecutionNodeVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param workflowExecutionNodeUpdateCommand
	 * @return
	 */
	SingleResponse<WorkflowExecutionNodeVO> update(WorkflowExecutionNodeUpdateCommand workflowExecutionNodeUpdateCommand);
}

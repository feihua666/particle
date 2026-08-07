package com.particle.workflow.client.execution.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.workflow.client.execution.dto.command.WorkflowExecutionCreateCommand;
import com.particle.workflow.client.execution.dto.command.WorkflowExecutionUpdateCommand;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionVO;
/**
 * <p>
 * 工作流执行实例 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
public interface IWorkflowExecutionApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param workflowExecutionCreateCommand
	 * @return
	 */
	SingleResponse<WorkflowExecutionVO> create(WorkflowExecutionCreateCommand workflowExecutionCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<WorkflowExecutionVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param workflowExecutionUpdateCommand
	 * @return
	 */
	SingleResponse<WorkflowExecutionVO> update(WorkflowExecutionUpdateCommand workflowExecutionUpdateCommand);
}

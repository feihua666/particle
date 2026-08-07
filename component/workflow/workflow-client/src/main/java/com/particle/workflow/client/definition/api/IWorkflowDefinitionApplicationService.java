package com.particle.workflow.client.definition.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionCreateCommand;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionUpdateCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionVO;
/**
 * <p>
 * 工作流定义 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
public interface IWorkflowDefinitionApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param workflowDefinitionCreateCommand
	 * @return
	 */
	SingleResponse<WorkflowDefinitionVO> create(WorkflowDefinitionCreateCommand workflowDefinitionCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<WorkflowDefinitionVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param workflowDefinitionUpdateCommand
	 * @return
	 */
	SingleResponse<WorkflowDefinitionVO> update(WorkflowDefinitionUpdateCommand workflowDefinitionUpdateCommand);
}

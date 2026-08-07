package com.particle.workflow.client.definition.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.workflow.client.definition.dto.command.WorkflowProjectCreateCommand;
import com.particle.workflow.client.definition.dto.command.WorkflowProjectUpdateCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowProjectVO;
/**
 * <p>
 * 工作流项目 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:12
 */
public interface IWorkflowProjectApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param workflowProjectCreateCommand
	 * @return
	 */
	SingleResponse<WorkflowProjectVO> create(WorkflowProjectCreateCommand workflowProjectCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<WorkflowProjectVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param workflowProjectUpdateCommand
	 * @return
	 */
	SingleResponse<WorkflowProjectVO> update(WorkflowProjectUpdateCommand workflowProjectUpdateCommand);
}

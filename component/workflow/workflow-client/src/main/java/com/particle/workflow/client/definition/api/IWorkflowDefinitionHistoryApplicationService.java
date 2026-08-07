package com.particle.workflow.client.definition.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionHistoryCreateCommand;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionHistoryCreateDraftCommand;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionHistoryUpdateCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionHistoryVO;
/**
 * <p>
 * 工作流定义历史 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
public interface IWorkflowDefinitionHistoryApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param workflowDefinitionHistoryCreateCommand
	 * @return
	 */
	SingleResponse<WorkflowDefinitionHistoryVO> create(WorkflowDefinitionHistoryCreateCommand workflowDefinitionHistoryCreateCommand);
	/**
	 * 创建草稿
	 * @param workflowDefinitionHistoryCreateDraftCommand
	 * @return
	 */
	SingleResponse<WorkflowDefinitionHistoryVO> createDraft(WorkflowDefinitionHistoryCreateDraftCommand workflowDefinitionHistoryCreateDraftCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<WorkflowDefinitionHistoryVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param workflowDefinitionHistoryUpdateCommand
	 * @return
	 */
	SingleResponse<WorkflowDefinitionHistoryVO> update(WorkflowDefinitionHistoryUpdateCommand workflowDefinitionHistoryUpdateCommand);
}

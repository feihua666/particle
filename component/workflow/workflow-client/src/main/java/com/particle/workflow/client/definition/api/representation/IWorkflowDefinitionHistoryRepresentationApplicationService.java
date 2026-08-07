package com.particle.workflow.client.definition.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionHistoryPageQueryCommand;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionHistoryQueryListCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionHistoryVO;

/**
 * <p>
 * 工作流定义历史 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IWorkflowDefinitionHistoryRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<WorkflowDefinitionHistoryVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<WorkflowDefinitionHistoryVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param workflowDefinitionHistoryQueryListCommand
	 * @return
	 */
	MultiResponse<WorkflowDefinitionHistoryVO> queryList(WorkflowDefinitionHistoryQueryListCommand workflowDefinitionHistoryQueryListCommand);

	/**
	 * 分页查询
	 * @param workflowDefinitionHistoryPageQueryCommand
	 * @return
	 */
	PageResponse<WorkflowDefinitionHistoryVO> pageQuery(WorkflowDefinitionHistoryPageQueryCommand workflowDefinitionHistoryPageQueryCommand);

}

package com.particle.workflow.client.definition.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionPageQueryCommand;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionQueryListCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionVO;

/**
 * <p>
 * 工作流定义 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IWorkflowDefinitionRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<WorkflowDefinitionVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<WorkflowDefinitionVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param workflowDefinitionQueryListCommand
	 * @return
	 */
	MultiResponse<WorkflowDefinitionVO> queryList(WorkflowDefinitionQueryListCommand workflowDefinitionQueryListCommand);

	/**
	 * 分页查询
	 * @param workflowDefinitionPageQueryCommand
	 * @return
	 */
	PageResponse<WorkflowDefinitionVO> pageQuery(WorkflowDefinitionPageQueryCommand workflowDefinitionPageQueryCommand);

}

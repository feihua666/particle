package com.particle.workflow.client.execution.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionNodePageQueryCommand;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionNodeQueryListCommand;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionNodeVO;

/**
 * <p>
 * 工作流节点执行实例 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IWorkflowExecutionNodeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<WorkflowExecutionNodeVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<WorkflowExecutionNodeVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param workflowExecutionNodeQueryListCommand
	 * @return
	 */
	MultiResponse<WorkflowExecutionNodeVO> queryList(WorkflowExecutionNodeQueryListCommand workflowExecutionNodeQueryListCommand);

	/**
	 * 分页查询
	 * @param workflowExecutionNodePageQueryCommand
	 * @return
	 */
	PageResponse<WorkflowExecutionNodeVO> pageQuery(WorkflowExecutionNodePageQueryCommand workflowExecutionNodePageQueryCommand);

}

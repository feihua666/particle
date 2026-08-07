package com.particle.workflow.client.execution.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionPageQueryCommand;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionQueryListCommand;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionVO;

/**
 * <p>
 * 工作流执行实例 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IWorkflowExecutionRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<WorkflowExecutionVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<WorkflowExecutionVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param workflowExecutionQueryListCommand
	 * @return
	 */
	MultiResponse<WorkflowExecutionVO> queryList(WorkflowExecutionQueryListCommand workflowExecutionQueryListCommand);

	/**
	 * 分页查询
	 * @param workflowExecutionPageQueryCommand
	 * @return
	 */
	PageResponse<WorkflowExecutionVO> pageQuery(WorkflowExecutionPageQueryCommand workflowExecutionPageQueryCommand);

}

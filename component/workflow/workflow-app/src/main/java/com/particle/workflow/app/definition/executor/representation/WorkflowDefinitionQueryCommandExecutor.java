package com.particle.workflow.app.definition.executor.representation;

import com.particle.workflow.app.definition.structmapping.WorkflowDefinitionAppStructMapping;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionQueryListCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionVO;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionDO;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionService;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionPageQueryCommand;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 工作流定义 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Component
@Validated
public class WorkflowDefinitionQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IWorkflowDefinitionService iWorkflowDefinitionService;

	/**
	 * 执行 工作流定义 列表查询指令
	 * @param workflowDefinitionQueryListCommand
	 * @return
	 */
	public MultiResponse<WorkflowDefinitionVO> execute(@Valid WorkflowDefinitionQueryListCommand workflowDefinitionQueryListCommand) {
		List<WorkflowDefinitionDO> workflowDefinitionDO = iWorkflowDefinitionService.list(workflowDefinitionQueryListCommand);
		List<WorkflowDefinitionVO> workflowDefinitionVOs = WorkflowDefinitionAppStructMapping.instance.workflowDefinitionDOsToWorkflowDefinitionVOs(workflowDefinitionDO);
		return MultiResponse.of(workflowDefinitionVOs);
	}
	/**
	 * 执行 工作流定义 分页查询指令
	 * @param workflowDefinitionPageQueryCommand
	 * @return
	 */
	public PageResponse<WorkflowDefinitionVO> execute(@Valid WorkflowDefinitionPageQueryCommand workflowDefinitionPageQueryCommand) {
		Page<WorkflowDefinitionDO> page = iWorkflowDefinitionService.listPage(workflowDefinitionPageQueryCommand);
		return WorkflowDefinitionAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 工作流定义 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<WorkflowDefinitionVO> executeDetail(CommonIdCommand detailCommand) {
		WorkflowDefinitionDO byId = iWorkflowDefinitionService.getById(detailCommand.getId());
		WorkflowDefinitionVO workflowDefinitionVO = WorkflowDefinitionAppStructMapping.instance.workflowDefinitionDOToWorkflowDefinitionVO(byId);
		return SingleResponse.of(workflowDefinitionVO);
	}
	/**
	 * 执行 工作流定义 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<WorkflowDefinitionVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		WorkflowDefinitionDO byId = iWorkflowDefinitionService.getById(detailForUpdateCommand.getId());
		WorkflowDefinitionVO workflowDefinitionVO = WorkflowDefinitionAppStructMapping.instance.workflowDefinitionDOToWorkflowDefinitionVO(byId);
		return SingleResponse.of(workflowDefinitionVO);
	}


	@Autowired
	public void setIWorkflowDefinitionService(IWorkflowDefinitionService iWorkflowDefinitionService) {
		this.iWorkflowDefinitionService = iWorkflowDefinitionService;
	}
}

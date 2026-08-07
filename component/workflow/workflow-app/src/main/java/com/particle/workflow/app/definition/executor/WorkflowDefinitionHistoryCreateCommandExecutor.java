package com.particle.workflow.app.definition.executor;

import com.particle.global.exception.Assert;
import com.particle.workflow.app.definition.structmapping.WorkflowDefinitionHistoryAppStructMapping;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionHistoryCreateCommand;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionHistoryCreateDraftCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionHistoryVO;
import com.particle.workflow.domain.definition.WorkflowDefinitionHistory;
import com.particle.workflow.domain.definition.gateway.WorkflowDefinitionHistoryGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionDO;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionHistoryDO;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionHistoryService;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 工作流定义历史 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Component
@Validated
public class WorkflowDefinitionHistoryCreateCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowDefinitionHistoryGateway workflowDefinitionHistoryGateway;

	private IWorkflowDefinitionService workflowDefinitionService;
	private IWorkflowDefinitionHistoryService workflowDefinitionHistoryService;

	/**
	 * 执行工作流定义历史添加指令
	 * @param workflowDefinitionHistoryCreateCommand
	 * @return
	 */
	public SingleResponse<WorkflowDefinitionHistoryVO> execute(@Valid WorkflowDefinitionHistoryCreateCommand workflowDefinitionHistoryCreateCommand) {
		WorkflowDefinitionHistory workflowDefinitionHistory = createByWorkflowDefinitionHistoryCreateCommand(workflowDefinitionHistoryCreateCommand);
		workflowDefinitionHistory.setAddControl(workflowDefinitionHistoryCreateCommand);
		workflowDefinitionHistory.initForAdd();
		boolean save = workflowDefinitionHistoryGateway.save(workflowDefinitionHistory);
		if (save) {
			return SingleResponse.of(WorkflowDefinitionHistoryAppStructMapping.instance.toWorkflowDefinitionHistoryVO(workflowDefinitionHistory));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 执行工作流定义历史添加草稿指令
	 * @param workflowDefinitionHistoryCreateDraftCommand
	 * @return
	 */
	public SingleResponse<WorkflowDefinitionHistoryVO> createDraft(WorkflowDefinitionHistoryCreateDraftCommand workflowDefinitionHistoryCreateDraftCommand) {
		// 检查是否已经存在草稿
		WorkflowDefinitionDO workflowDefinitionDO = workflowDefinitionService.getById(workflowDefinitionHistoryCreateDraftCommand.getWorkflowDefinitionId());
		Assert.isTrue(workflowDefinitionDO.getDraftWorkflowDefinitionHistoryId() == null, "草稿已存在请勿重复创建");

		// 判断新建草稿的版本，从最新发布的历史版本开始加1
		Long latestPublishWorkflowDefinitionHistoryId = workflowDefinitionDO.getLatestPublishWorkflowDefinitionHistoryId();
		Integer workflowDefinitionVersion = null;
		if (latestPublishWorkflowDefinitionHistoryId != null) {
			WorkflowDefinitionHistoryDO workflowDefinitionHistoryDO = workflowDefinitionHistoryService.getById(latestPublishWorkflowDefinitionHistoryId);
			workflowDefinitionVersion = workflowDefinitionHistoryDO.getWorkflowDefinitionVersion() + 1;
			workflowDefinitionHistoryCreateDraftCommand.setGraphDataJson(workflowDefinitionHistoryDO.getGraphDataJson());
			workflowDefinitionHistoryCreateDraftCommand.setConfigJson(workflowDefinitionHistoryDO.getConfigJson());
		}

		WorkflowDefinitionHistory workflowDefinitionHistory = createByWorkflowDefinitionHistoryCreateDraftCommand(workflowDefinitionHistoryCreateDraftCommand);
		workflowDefinitionHistory.setAddControl(workflowDefinitionHistoryCreateDraftCommand);
		workflowDefinitionHistory.initForAdd();
		// 如果有版本，则设置
		if (workflowDefinitionVersion != null) {
			workflowDefinitionHistory.changeWorkflowDefinitionVersion(workflowDefinitionVersion);
		}
		boolean save = workflowDefinitionHistoryGateway.save(workflowDefinitionHistory);
		if (save) {
			// 草稿新建成功之后，需要回填流程定义中的草稿历史id
			WorkflowDefinitionDO workflowDefinitionDOForUpdate = new WorkflowDefinitionDO();
			workflowDefinitionDOForUpdate.setId(workflowDefinitionDO.getId());
			workflowDefinitionDOForUpdate.setVersion(workflowDefinitionDO.getVersion());
			workflowDefinitionDOForUpdate.setDraftWorkflowDefinitionHistoryId(workflowDefinitionHistory.getId().getId());
			workflowDefinitionService.update(workflowDefinitionDOForUpdate);
			return SingleResponse.of(WorkflowDefinitionHistoryAppStructMapping.instance.toWorkflowDefinitionHistoryVO(workflowDefinitionHistory));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 根据工作流定义历史创建指令创建工作流定义历史模型
	 * @param workflowDefinitionHistoryCreateCommand
	 * @return
	 */
	private WorkflowDefinitionHistory createByWorkflowDefinitionHistoryCreateCommand(WorkflowDefinitionHistoryCreateCommand workflowDefinitionHistoryCreateCommand){
		WorkflowDefinitionHistory workflowDefinitionHistory = WorkflowDefinitionHistory.create();
		WorkflowDefinitionHistoryCreateCommandToWorkflowDefinitionHistoryMapping.instance.fillWorkflowDefinitionHistoryByWorkflowDefinitionHistoryCreateCommand(workflowDefinitionHistory, workflowDefinitionHistoryCreateCommand);
		return workflowDefinitionHistory;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  WorkflowDefinitionHistoryCreateCommandToWorkflowDefinitionHistoryMapping{
		WorkflowDefinitionHistoryCreateCommandToWorkflowDefinitionHistoryMapping instance = Mappers.getMapper( WorkflowDefinitionHistoryCreateCommandToWorkflowDefinitionHistoryMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param workflowDefinitionHistory
		 * @param workflowDefinitionHistoryCreateCommand
		 */
		void fillWorkflowDefinitionHistoryByWorkflowDefinitionHistoryCreateCommand(@MappingTarget WorkflowDefinitionHistory workflowDefinitionHistory, WorkflowDefinitionHistoryCreateCommand workflowDefinitionHistoryCreateCommand);
	}


	/**
	 * 根据工作流定义历史草稿创建指令创建工作流定义历史模型
	 * @param workflowDefinitionHistoryCreateDraftCommand
	 * @return
	 */
	private WorkflowDefinitionHistory createByWorkflowDefinitionHistoryCreateDraftCommand(WorkflowDefinitionHistoryCreateDraftCommand workflowDefinitionHistoryCreateDraftCommand){
		WorkflowDefinitionHistory workflowDefinitionHistory = WorkflowDefinitionHistory.create();
		WorkflowDefinitionHistoryCreateDraftCommandToWorkflowDefinitionHistoryMapping.instance.fillWorkflowDefinitionHistoryByWorkflowDefinitionHistoryCreateDraftCommand(workflowDefinitionHistory, workflowDefinitionHistoryCreateDraftCommand);
		return workflowDefinitionHistory;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  WorkflowDefinitionHistoryCreateDraftCommandToWorkflowDefinitionHistoryMapping{
		WorkflowDefinitionHistoryCreateDraftCommandToWorkflowDefinitionHistoryMapping instance = Mappers.getMapper( WorkflowDefinitionHistoryCreateDraftCommandToWorkflowDefinitionHistoryMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param workflowDefinitionHistory
		 * @param workflowDefinitionHistoryCreateDraftCommand
		 */
		void fillWorkflowDefinitionHistoryByWorkflowDefinitionHistoryCreateDraftCommand(@MappingTarget WorkflowDefinitionHistory workflowDefinitionHistory, WorkflowDefinitionHistoryCreateDraftCommand workflowDefinitionHistoryCreateDraftCommand);
	}

	/**
	 * 注入使用set方法
	 * @param workflowDefinitionHistoryGateway
	 */
	@Autowired
	public void setWorkflowDefinitionHistoryGateway(WorkflowDefinitionHistoryGateway workflowDefinitionHistoryGateway) {
		this.workflowDefinitionHistoryGateway = workflowDefinitionHistoryGateway;
	}
	@Autowired
	public void setWorkflowDefinitionService(IWorkflowDefinitionService workflowDefinitionService) {
		this.workflowDefinitionService = workflowDefinitionService;
	}
	@Autowired
	public void setWorkflowDefinitionHistoryService(IWorkflowDefinitionHistoryService workflowDefinitionHistoryService) {
		this.workflowDefinitionHistoryService = workflowDefinitionHistoryService;
	}
}

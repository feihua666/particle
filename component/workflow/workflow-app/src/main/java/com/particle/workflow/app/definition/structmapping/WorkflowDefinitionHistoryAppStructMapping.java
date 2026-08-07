package com.particle.workflow.app.definition.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionHistoryVO;
import com.particle.workflow.domain.definition.WorkflowDefinitionHistory;
import com.particle.workflow.domain.definition.WorkflowDefinitionHistoryId;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionHistoryDO;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionHistoryPageQueryCommand;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionHistoryQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 工作流定义历史 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class WorkflowDefinitionHistoryAppStructMapping  implements IBaseQueryCommandMapStruct<WorkflowDefinitionHistoryDO>{
	public static WorkflowDefinitionHistoryAppStructMapping instance = Mappers.getMapper( WorkflowDefinitionHistoryAppStructMapping.class );

	protected Long map(WorkflowDefinitionHistoryId workflowDefinitionHistoryId){
		if (workflowDefinitionHistoryId == null) {
			return null;
		}
		return workflowDefinitionHistoryId.getId();
	}
	/**
	 * 工作流定义历史领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link WorkflowDefinitionHistoryAppStructMapping#map(WorkflowDefinitionHistoryId)}
	 * @param workflowDefinitionHistory
	 * @return
	 */
	public abstract WorkflowDefinitionHistoryVO toWorkflowDefinitionHistoryVO(WorkflowDefinitionHistory workflowDefinitionHistory);


	/**
	 * 数据对象转视图对象
	 * @param workflowDefinitionHistoryDO
	 * @return
	 */
	public abstract WorkflowDefinitionHistoryVO workflowDefinitionHistoryDOToWorkflowDefinitionHistoryVO(WorkflowDefinitionHistoryDO workflowDefinitionHistoryDO);

	/**
	 * 批量转换
	 * @param workflowDefinitionHistoryDOs
	 * @return
	 */
	public abstract List<WorkflowDefinitionHistoryVO> workflowDefinitionHistoryDOsToWorkflowDefinitionHistoryVOs(List<WorkflowDefinitionHistoryDO> workflowDefinitionHistoryDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<WorkflowDefinitionHistoryVO> infrastructurePageToPageResponse(Page<WorkflowDefinitionHistoryDO> page) {
		return PageResponse.of(workflowDefinitionHistoryDOsToWorkflowDefinitionHistoryVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public WorkflowDefinitionHistoryDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof WorkflowDefinitionHistoryPageQueryCommand) {
			return pageQueryCommandToDO((WorkflowDefinitionHistoryPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof WorkflowDefinitionHistoryQueryListCommand) {
			return queryListCommandToDO(((WorkflowDefinitionHistoryQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract WorkflowDefinitionHistoryDO pageQueryCommandToDO(WorkflowDefinitionHistoryPageQueryCommand workflowDefinitionHistoryPageQueryCommand);

	public abstract WorkflowDefinitionHistoryDO queryListCommandToDO(WorkflowDefinitionHistoryQueryListCommand workflowDefinitionHistoryQueryListCommand);
}

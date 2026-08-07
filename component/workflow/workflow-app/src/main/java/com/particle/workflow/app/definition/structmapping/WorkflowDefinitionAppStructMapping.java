package com.particle.workflow.app.definition.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionVO;
import com.particle.workflow.domain.definition.WorkflowDefinition;
import com.particle.workflow.domain.definition.WorkflowDefinitionId;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionDO;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionPageQueryCommand;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 工作流定义 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class WorkflowDefinitionAppStructMapping  implements IBaseQueryCommandMapStruct<WorkflowDefinitionDO>{
	public static WorkflowDefinitionAppStructMapping instance = Mappers.getMapper( WorkflowDefinitionAppStructMapping.class );

	protected Long map(WorkflowDefinitionId workflowDefinitionId){
		if (workflowDefinitionId == null) {
			return null;
		}
		return workflowDefinitionId.getId();
	}
	/**
	 * 工作流定义领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link WorkflowDefinitionAppStructMapping#map(WorkflowDefinitionId)}
	 * @param workflowDefinition
	 * @return
	 */
	public abstract WorkflowDefinitionVO toWorkflowDefinitionVO(WorkflowDefinition workflowDefinition);


	/**
	 * 数据对象转视图对象
	 * @param workflowDefinitionDO
	 * @return
	 */
	public abstract WorkflowDefinitionVO workflowDefinitionDOToWorkflowDefinitionVO(WorkflowDefinitionDO workflowDefinitionDO);

	/**
	 * 批量转换
	 * @param workflowDefinitionDOs
	 * @return
	 */
	public abstract List<WorkflowDefinitionVO> workflowDefinitionDOsToWorkflowDefinitionVOs(List<WorkflowDefinitionDO> workflowDefinitionDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<WorkflowDefinitionVO> infrastructurePageToPageResponse(Page<WorkflowDefinitionDO> page) {
		return PageResponse.of(workflowDefinitionDOsToWorkflowDefinitionVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public WorkflowDefinitionDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof WorkflowDefinitionPageQueryCommand) {
			return pageQueryCommandToDO((WorkflowDefinitionPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof WorkflowDefinitionQueryListCommand) {
			return queryListCommandToDO(((WorkflowDefinitionQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract WorkflowDefinitionDO pageQueryCommandToDO(WorkflowDefinitionPageQueryCommand workflowDefinitionPageQueryCommand);

	public abstract WorkflowDefinitionDO queryListCommandToDO(WorkflowDefinitionQueryListCommand workflowDefinitionQueryListCommand);
}

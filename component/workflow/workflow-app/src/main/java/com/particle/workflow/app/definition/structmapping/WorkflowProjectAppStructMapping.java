package com.particle.workflow.app.definition.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.workflow.client.definition.dto.data.WorkflowProjectVO;
import com.particle.workflow.domain.definition.WorkflowProject;
import com.particle.workflow.domain.definition.WorkflowProjectId;
import com.particle.workflow.infrastructure.definition.dos.WorkflowProjectDO;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowProjectPageQueryCommand;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowProjectQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 工作流项目 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:12
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class WorkflowProjectAppStructMapping  implements IBaseQueryCommandMapStruct<WorkflowProjectDO>{
	public static WorkflowProjectAppStructMapping instance = Mappers.getMapper( WorkflowProjectAppStructMapping.class );

	protected Long map(WorkflowProjectId workflowProjectId){
		if (workflowProjectId == null) {
			return null;
		}
		return workflowProjectId.getId();
	}
	/**
	 * 工作流项目领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link WorkflowProjectAppStructMapping#map(WorkflowProjectId)}
	 * @param workflowProject
	 * @return
	 */
	public abstract WorkflowProjectVO toWorkflowProjectVO(WorkflowProject workflowProject);


	/**
	 * 数据对象转视图对象
	 * @param workflowProjectDO
	 * @return
	 */
	public abstract WorkflowProjectVO workflowProjectDOToWorkflowProjectVO(WorkflowProjectDO workflowProjectDO);

	/**
	 * 批量转换
	 * @param workflowProjectDOs
	 * @return
	 */
	public abstract List<WorkflowProjectVO> workflowProjectDOsToWorkflowProjectVOs(List<WorkflowProjectDO> workflowProjectDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<WorkflowProjectVO> infrastructurePageToPageResponse(Page<WorkflowProjectDO> page) {
		return PageResponse.of(workflowProjectDOsToWorkflowProjectVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public WorkflowProjectDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof WorkflowProjectPageQueryCommand) {
			return pageQueryCommandToDO((WorkflowProjectPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof WorkflowProjectQueryListCommand) {
			return queryListCommandToDO(((WorkflowProjectQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract WorkflowProjectDO pageQueryCommandToDO(WorkflowProjectPageQueryCommand workflowProjectPageQueryCommand);

	public abstract WorkflowProjectDO queryListCommandToDO(WorkflowProjectQueryListCommand workflowProjectQueryListCommand);
}

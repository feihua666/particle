package com.particle.workflow.app.execution.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionVO;
import com.particle.workflow.domain.execution.WorkflowExecution;
import com.particle.workflow.domain.execution.WorkflowExecutionId;
import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionDO;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionPageQueryCommand;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 工作流执行实例 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class WorkflowExecutionAppStructMapping  implements IBaseQueryCommandMapStruct<WorkflowExecutionDO>{
	public static WorkflowExecutionAppStructMapping instance = Mappers.getMapper( WorkflowExecutionAppStructMapping.class );

	protected Long map(WorkflowExecutionId workflowExecutionId){
		if (workflowExecutionId == null) {
			return null;
		}
		return workflowExecutionId.getId();
	}
	/**
	 * 工作流执行实例领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link WorkflowExecutionAppStructMapping#map(WorkflowExecutionId)}
	 * @param workflowExecution
	 * @return
	 */
	public abstract WorkflowExecutionVO toWorkflowExecutionVO(WorkflowExecution workflowExecution);


	/**
	 * 数据对象转视图对象
	 * @param workflowExecutionDO
	 * @return
	 */
	public abstract WorkflowExecutionVO workflowExecutionDOToWorkflowExecutionVO(WorkflowExecutionDO workflowExecutionDO);

	/**
	 * 批量转换
	 * @param workflowExecutionDOs
	 * @return
	 */
	public abstract List<WorkflowExecutionVO> workflowExecutionDOsToWorkflowExecutionVOs(List<WorkflowExecutionDO> workflowExecutionDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<WorkflowExecutionVO> infrastructurePageToPageResponse(Page<WorkflowExecutionDO> page) {
		return PageResponse.of(workflowExecutionDOsToWorkflowExecutionVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public WorkflowExecutionDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof WorkflowExecutionPageQueryCommand) {
			return pageQueryCommandToDO((WorkflowExecutionPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof WorkflowExecutionQueryListCommand) {
			return queryListCommandToDO(((WorkflowExecutionQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract WorkflowExecutionDO pageQueryCommandToDO(WorkflowExecutionPageQueryCommand workflowExecutionPageQueryCommand);

	public abstract WorkflowExecutionDO queryListCommandToDO(WorkflowExecutionQueryListCommand workflowExecutionQueryListCommand);
}

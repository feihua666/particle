package com.particle.workflow.app.execution.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionNodeVO;
import com.particle.workflow.domain.execution.WorkflowExecutionNode;
import com.particle.workflow.domain.execution.WorkflowExecutionNodeId;
import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionNodeDO;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionNodePageQueryCommand;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionNodeQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 工作流节点执行实例 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class WorkflowExecutionNodeAppStructMapping  implements IBaseQueryCommandMapStruct<WorkflowExecutionNodeDO>{
	public static WorkflowExecutionNodeAppStructMapping instance = Mappers.getMapper( WorkflowExecutionNodeAppStructMapping.class );

	protected Long map(WorkflowExecutionNodeId workflowExecutionNodeId){
		if (workflowExecutionNodeId == null) {
			return null;
		}
		return workflowExecutionNodeId.getId();
	}
	/**
	 * 工作流节点执行实例领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link WorkflowExecutionNodeAppStructMapping#map(WorkflowExecutionNodeId)}
	 * @param workflowExecutionNode
	 * @return
	 */
	public abstract WorkflowExecutionNodeVO toWorkflowExecutionNodeVO(WorkflowExecutionNode workflowExecutionNode);


	/**
	 * 数据对象转视图对象
	 * @param workflowExecutionNodeDO
	 * @return
	 */
	public abstract WorkflowExecutionNodeVO workflowExecutionNodeDOToWorkflowExecutionNodeVO(WorkflowExecutionNodeDO workflowExecutionNodeDO);

	/**
	 * 批量转换
	 * @param workflowExecutionNodeDOs
	 * @return
	 */
	public abstract List<WorkflowExecutionNodeVO> workflowExecutionNodeDOsToWorkflowExecutionNodeVOs(List<WorkflowExecutionNodeDO> workflowExecutionNodeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<WorkflowExecutionNodeVO> infrastructurePageToPageResponse(Page<WorkflowExecutionNodeDO> page) {
		return PageResponse.of(workflowExecutionNodeDOsToWorkflowExecutionNodeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public WorkflowExecutionNodeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof WorkflowExecutionNodePageQueryCommand) {
			return pageQueryCommandToDO((WorkflowExecutionNodePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof WorkflowExecutionNodeQueryListCommand) {
			return queryListCommandToDO(((WorkflowExecutionNodeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract WorkflowExecutionNodeDO pageQueryCommandToDO(WorkflowExecutionNodePageQueryCommand workflowExecutionNodePageQueryCommand);

	public abstract WorkflowExecutionNodeDO queryListCommandToDO(WorkflowExecutionNodeQueryListCommand workflowExecutionNodeQueryListCommand);
}

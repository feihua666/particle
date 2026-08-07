package com.particle.workflow.client.execution.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.time.LocalDateTime;
/**
 * <p>
 * 工作流执行实例 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Data
@Schema
public class WorkflowExecutionVO extends AbstractBaseIdVO {

    @Schema(description = "工作流定义ID")
    private Long workflowDefinitionId;

    @TransBy(tableName = TransTableNameConstants.component_workflow_definition, byFieldName = "workflowDefinitionId", mapValueField = "name")
    @Schema(description = "工作流定义名称")
    private String workflowDefinitionName;

    @Schema(description = "执行时使用的版本ID")
    private Long workflowDefinitionHistoryId;

    @TransBy(tableName = TransTableNameConstants.component_workflow_definition_history, byFieldName = "workflowDefinitionHistoryId", mapValueField = "workflowDefinitionVersion")
    @Schema(description = "最新发布版本流程定义版本")
    private String workflowDefinitionHistoryVersion;

    @Schema(description = "执行状态字典id")
    private Long statusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "statusDictId",mapValueField = "name")
    @Schema(description = "执行状态字典名称")
    private String statusDictName;

    @Schema(description = "触发方式字典id")
    private Long triggerTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "triggerTypeDictId",mapValueField = "name")
    @Schema(description = "触发方式字典名称")
    private String triggerTypeDictName;

    @Schema(description = "当前执行节点ID")
    private String nodeId;

	@Schema(description = "数据来源执行ID")
	private Long copiedWorkflowExecutionId;

    @Schema(description = "全局上下文数据json")
    private String contextJson;

    @Schema(description = "运行开始时间")
    private LocalDateTime startAt;

    @Schema(description = "运行结束时间")
    private LocalDateTime finishAt;

    @Schema(description = "错误信息")
    private String errorMsg;



}

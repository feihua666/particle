package com.particle.workflow.client.definition.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 工作流定义 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Data
@Schema
public class WorkflowDefinitionVO extends AbstractBaseIdVO {

    @Schema(description = "工作流名称")
    private String name;

    @Schema(description = "封面图地址")
    private String coverImageUrl;

    @Schema(description = "项目id")
    private Long workflowProjectId;

    @TransBy(tableName = TransTableNameConstants.component_workflow_project, byFieldName = "workflowProjectId", mapValueField = "name")
    @Schema(description = "项目名称")
    private String workflowProjectName;

    @Schema(description = "最新发布版本流程定义id")
    private Long latestPublishWorkflowDefinitionHistoryId;

    @TransBy(tableName = TransTableNameConstants.component_workflow_definition_history, byFieldName = "latestPublishWorkflowDefinitionHistoryId", mapValueField = "workflowDefinitionVersion")
    @Schema(description = "最新发布版本流程定义版本")
    private String latestPublishWorkflowDefinitionHistoryVersion;

    @Schema(description = "草稿版本流程定义id")
    private Long draftWorkflowDefinitionHistoryId;

    @TransBy(tableName = TransTableNameConstants.component_workflow_definition_history, byFieldName = "draftWorkflowDefinitionHistoryId", mapValueField = "workflowDefinitionVersion")
    @Schema(description = "草稿版本流程定义版本")
    private String draftWorkflowDefinitionHistoryVersion;

    @Schema(description = "描述")
    private String remark;



}

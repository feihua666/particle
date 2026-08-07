package com.particle.workflow.client.definition.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 工作流定义历史 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Data
@Schema
public class WorkflowDefinitionHistoryVO extends AbstractBaseIdVO {

    @Schema(description = "工作流定义id")
    private Long workflowDefinitionId;

    @TransBy(tableName = TransTableNameConstants.component_workflow_definition, byFieldName = "workflowDefinitionId", mapValueField = "name")
    @Schema(description = "工作流定义名称")
    private String workflowDefinitionName;

    @Schema(description = "定义版本号")
    private Integer workflowDefinitionVersion;

    @Schema(description = "流程图数据")
    private String graphDataJson;

    @Schema(description = "工作流级配置json")
    private String configJson;

    @Schema(description = "是否发布")
    private Boolean isPublish;



}

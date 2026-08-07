package com.particle.workflow.client.definition.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 工作流定义历史 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Data
@Schema
public class WorkflowDefinitionHistoryUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "工作流定义id 不能为空")
        @Schema(description = "工作流定义id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long workflowDefinitionId;


    @NotNull(message = "定义版本号 不能为空")
        @Schema(description = "定义版本号",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer workflowDefinitionVersion;


    @Schema(description = "流程图数据")
    private String graphDataJson;


    @Schema(description = "工作流级配置json")
    private String configJson;


    @NotNull(message = "是否发布 不能为空")
        @Schema(description = "是否发布",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isPublish;









}

package com.particle.workflow.client.definition.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 工作流定义 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Data
@Schema
public class WorkflowDefinitionUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "工作流名称 不能为空")
        @Schema(description = "工作流名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "封面图地址")
    private String coverImageUrl;


    @Schema(description = "描述")
    private String remark;









}

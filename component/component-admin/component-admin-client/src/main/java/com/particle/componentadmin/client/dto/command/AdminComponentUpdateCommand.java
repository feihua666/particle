package com.particle.componentadmin.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

/**
 * <p>
 * 组件 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Data
@Schema
public class AdminComponentUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "组件英文名称 不能为空")
        @Schema(description = "组件英文名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;


    @NotEmpty(message = "组件中文名称 不能为空")
        @Schema(description = "组件中文名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotEmpty(message = "组件路径 不能为空")
        @Schema(description = "组件路径",requiredMode = Schema.RequiredMode.REQUIRED)
    private String path;


    @Schema(description = "备注")
    private String remark;









}

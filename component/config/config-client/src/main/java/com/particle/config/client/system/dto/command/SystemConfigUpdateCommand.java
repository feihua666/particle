package com.particle.config.client.system.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 系统参数配置 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Data
@Schema
public class SystemConfigUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "参数配置编码 不能为空")
        @Schema(description = "参数配置编码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;


    @NotEmpty(message = "参数配置名称 不能为空")
        @Schema(description = "参数配置名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotEmpty(message = "参数配置值 不能为空")
        @Schema(description = "参数配置值",requiredMode = Schema.RequiredMode.REQUIRED)
    private String value;


    @NotNull(message = "是否内置 不能为空")
        @Schema(description = "是否内置",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isBuiltIn;


    @NotNull(message = "是否禁用 不能为空")
        @Schema(description = "是否禁用",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isDisabled;


    @Schema(description = "禁用原因")
    private String disabledReason;


    @Schema(description = "标签")
    private String tag;


    @Schema(description = "描述")
    private String remark;









}

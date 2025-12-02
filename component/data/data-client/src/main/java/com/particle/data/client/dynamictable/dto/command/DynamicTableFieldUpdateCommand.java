package com.particle.data.client.dynamictable.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 动态数据表格字段 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Data
@Schema
public class DynamicTableFieldUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "动态数据表格id")
    private Long dynamicTableId;


    @NotEmpty(message = "字段名称 不能为空")
        @Schema(description = "字段名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotEmpty(message = "字段注释 不能为空")
        @Schema(description = "字段注释",requiredMode = Schema.RequiredMode.REQUIRED)
    private String comment;


    @NotEmpty(message = "字段类型 不能为空")
        @Schema(description = "字段类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private String type;


    @NotNull(message = "是否必填 不能为空")
        @Schema(description = "是否必填",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isRequired;

	@Schema(description = "字段默认值")
	private String defaultValue;









}
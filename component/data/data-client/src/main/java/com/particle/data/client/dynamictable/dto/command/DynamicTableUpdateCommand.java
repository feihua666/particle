package com.particle.data.client.dynamictable.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 动态数据表格 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Data
@Schema
public class DynamicTableUpdateCommand extends AbstractBaseUpdateCommand {

	@Schema(description = "字段数量")
	private Integer dynamicTableFieldNum;

	@Schema(description = "数据数量")
	private Integer dynamicTableDataNum;

	@Schema(description = "备注信息")
	private String remark;



    @NotEmpty(message = "表名称 不能为空")
        @Schema(description = "表名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotEmpty(message = "表注释 不能为空")
        @Schema(description = "表注释",requiredMode = Schema.RequiredMode.REQUIRED)
    private String comment;


}
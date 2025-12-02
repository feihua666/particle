package com.particle.data.client.dynamicdata.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 动态数据指标分类 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Data
@Schema
public class DynamicDataIndicatorCategoryUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "动态数据分类id 不能为空")
        @Schema(description = "动态数据分类id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dynamicDataCategoryId;


    @NotEmpty(message = "动态数据指标分类名称 不能为空")
        @Schema(description = "动态数据指标分类名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "是否禁用 不能为空")
        @Schema(description = "是否禁用",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isDisabled;


    @Schema(description = "动态数据指标分类类型")
    private Long typeDictId;

	@Schema(description = "指标数量")
	private Integer dynamicDataIndicatorNum;

	@Schema(description = "数据数量")
	private Integer dynamicDataIndicatorCategoryDataNum;


    @NotEmpty(message = "备注信息 不能为空")
        @Schema(description = "备注信息",requiredMode = Schema.RequiredMode.REQUIRED)
    private String remark;









}
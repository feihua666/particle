package com.particle.data.client.dynamicdata.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 动态数据指标 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
@Data
@Schema
public class DynamicDataIndicatorCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "动态数据分类id 不能为空")
        @Schema(description = "动态数据分类id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dynamicDataCategoryId;


    @NotNull(message = "动态数据指标分类id 不能为空")
        @Schema(description = "动态数据指标分类id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dynamicDataIndicatorCategoryId;


    @NotEmpty(message = "指标名称 不能为空")
        @Schema(description = "指标名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "是否禁用 不能为空")
        @Schema(description = "是否禁用",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isDisabled;


    @NotEmpty(message = "备注信息 不能为空")
        @Schema(description = "备注信息",requiredMode = Schema.RequiredMode.REQUIRED)
    private String remark;









}

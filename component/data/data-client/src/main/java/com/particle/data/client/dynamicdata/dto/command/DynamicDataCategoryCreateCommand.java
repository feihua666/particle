package com.particle.data.client.dynamicdata.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 动态数据分类 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@Data
@Schema
public class DynamicDataCategoryCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "数据类型名称 不能为空")
        @Schema(description = "数据类型名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "是否禁用 不能为空")
        @Schema(description = "是否禁用",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isDisabled;


    @Schema(description = "动态数据分类类型")
    private Long typeDictId;


    @NotEmpty(message = "备注信息 不能为空")
        @Schema(description = "备注信息",requiredMode = Schema.RequiredMode.REQUIRED)
    private String remark;









}

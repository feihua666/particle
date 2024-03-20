package com.particle.openplatform.client.doc.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 开放接口文档模板参数字段 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
@Data
@Schema
public class OpenplatformDocApiDocTemplateParamFieldUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "字段名称 不能为空")
        @Schema(description = "字段名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "字段类型 不能为空")
        @Schema(description = "字段类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long typeDictId;


    @Schema(description = "嵌套字段类型")
    private Long nestTypeDictId;


    @NotNull(message = "是否一定有值 不能为空")
        @Schema(description = "是否一定有值",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isRequired;


    @NotEmpty(message = "字段说明 不能为空")
        @Schema(description = "字段说明",requiredMode = Schema.RequiredMode.REQUIRED)
    private String explanation;


    @NotNull(message = "开放接口文档模板id 不能为空")
        @Schema(description = "开放接口文档模板id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long openplatformDocApiDocTemplateId;


    @NotNull(message = "分类 不能为空")
        @Schema(description = "分类",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long categoryDictId;


    @NotNull(message = "排序 不能为空")
        @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;


    @Schema(description = "父级")
    private Long parentId;


}

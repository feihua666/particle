package com.particle.cms.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 模板内容 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Data
@Schema
public class CmsTemplateContentUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "数据id 不能为空")
        @Schema(description = "数据id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataId;


    @NotEmpty(message = "模板类型 不能为空")
        @Schema(description = "模板类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private String typeName;


    @NotEmpty(message = "模板内容 不能为空")
        @Schema(description = "模板内容",requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;









}

package com.particle.cms.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 模板 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Data
@Schema
public class CmsTemplateCreateCommand extends AbstractBaseCommand {

    @NotEmpty(message = "文件名 不能为空")
        @Schema(description = "文件名",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "是否为目录 不能为空")
        @Schema(description = "是否为目录",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isDirectory;


    @Schema(description = "模板内容")
    private String content;


    @NotNull(message = "排序 不能为空")
        @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;



    @Schema(description = "父级")
    private Long parentId;



















}

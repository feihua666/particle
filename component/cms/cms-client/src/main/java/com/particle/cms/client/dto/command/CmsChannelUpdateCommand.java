package com.particle.cms.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 栏目 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Data
@Schema
public class CmsChannelUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "站点id 不能为空")
        @Schema(description = "站点id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long cmsSiteId;


    @Schema(description = "栏目编码")
    private String code;


    @NotEmpty(message = "栏目名称 不能为空")
        @Schema(description = "栏目名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotEmpty(message = "栏目模板路径 不能为空")
        @Schema(description = "栏目模板路径",requiredMode = Schema.RequiredMode.REQUIRED)
    private String templatePath;


    @NotEmpty(message = "栏目模板 不能为空")
        @Schema(description = "栏目模板",requiredMode = Schema.RequiredMode.REQUIRED)
    private String templateIndex;


    @Schema(description = "栏目静态页存放路径")
    private String staticPath;


    @NotNull(message = "排序 不能为空")
        @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;



    @Schema(description = "父级")
    private Long parentId;



















}

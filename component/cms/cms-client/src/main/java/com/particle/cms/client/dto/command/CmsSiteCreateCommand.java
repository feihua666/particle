package com.particle.cms.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 站点 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Data
@Schema
public class CmsSiteCreateCommand extends AbstractBaseCommand {



    @Schema(description = "站点编码")
    private String code;


    @NotEmpty(message = "站点名称 不能为空")
        @Schema(description = "站点名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "站点域名")
    private String domain;

	@Schema(description = "部署路径，主要用于动态页前端静态页面路径访问")
	private String deployPath;


    @Schema(description = "站点访问上下文路径")
    private String path;


    @NotEmpty(message = "站点模板路径 不能为空")
        @Schema(description = "站点模板路径",requiredMode = Schema.RequiredMode.REQUIRED)
    private String templatePath;


    @NotEmpty(message = "站点首页模板 不能为空")
        @Schema(description = "站点首页模板",requiredMode = Schema.RequiredMode.REQUIRED)
    private String templateIndex;

	@Schema(description = "404模板路径")
	private String template404Path;

	@Schema(description = "404内容模板,默认404.html")
	private String template404Index;


    @Schema(description = "站点静态页存放路径")
    private String staticPath;


    @NotNull(message = "是否主站点 不能为空")
        @Schema(description = "是否主站点",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isPrimeSite;







}
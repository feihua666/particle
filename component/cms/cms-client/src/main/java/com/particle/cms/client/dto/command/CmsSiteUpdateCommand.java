package com.particle.cms.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * <p>
 * 站点 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Data
@Schema
public class CmsSiteUpdateCommand extends AbstractBaseUpdateCommand {

    @Schema(description = "站点编码")
    private String code;


    @NotEmpty(message = "站点名称 不能为空")
        @Schema(description = "站点名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

	@Schema(description = "网页标题，用于页面标题")
	private String title;

	@NotEmpty(message = "站点域名 不能为空")
	@Schema(description = "站点域名",requiredMode = Schema.RequiredMode.REQUIRED)
	private String domain;

	@NotEmpty(message = "站点动态外部域名 不能为空")
	@Schema(description = "站点动态外部域名",requiredMode = Schema.RequiredMode.REQUIRED)
	private String dynamicDomain;

	@Schema(description = "动态访问部署路径，主要用于页面组织访问链接路径")
	private String dynamicDeployPath;


	@Schema(description = "站点访问上下文路径")
	private String siteContextPath;


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

	@Schema(description = "403模板路径")
	private String template403Path;

	@Schema(description = "403内容模板,默认403.ftlh")
	private String template403Index;

	@Schema(description = "channel模板路径")
	private String templateChannelPath;

	@Schema(description = "channel内容模板")
	private String templateChannelIndex;

	@Schema(description = "content模板路径")
	private String templateContentPath;

	@Schema(description = "content内容模板")
	private String templateContentIndex;

	@Schema(description = "站点静态化页存放路径，文件系统绝对路径")
	private String staticSavePath;

	@Schema(description = "静态化站点域名,主要用于在访问页面时，静态化优先时，优先访问静态化页面")
	private String staticDomain;

	@Schema(description = "静态化访问部署路径，主要用于页面组织访问链接路径，该路径和spring context-path无关，一般用于nginx反向代理时可能会配置")
	private String staticDeployPath;


    @NotNull(message = "是否主站点 不能为空")
        @Schema(description = "是否主站点",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isPrimeSite;

	@Schema(description = "简介")
	private String profile;

	@Schema(description = "是否发布")
	private Boolean isPublic;

	@Schema(description = "发布时间")
	private LocalDateTime publicAt;

	@NotNull(message = "是否启用后台记录 不能为空")
	@Schema(description = "是否启用后台记录",requiredMode = Schema.RequiredMode.REQUIRED)
	private Boolean isEnableBackendRecord;

	@Schema(description = "备注")
	private String remark;

    @Schema(description = "初始页面访问量,页面展示次数")
    private Integer initPv;

}

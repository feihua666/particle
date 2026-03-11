package com.particle.cms.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 站点 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Data
@Schema
public class CmsSiteQueryListCommand extends AbstractBaseQueryCommand {

    @Schema(description = "站点id")
    private Long id;

    @Like
    @Schema(description = "站点编码,左前缀匹配")
    private String code;

    @Like
    @Schema(description = "站点名称,左前缀匹配")
    private String name;

    @Like
	@Schema(description = "网页标题,左前缀匹配")
	private String title;


    @Schema(description = "站点域名")
    private String domain;

	@Schema(description = "站点动态外部域名")
	private String dynamicDomain;

	@Schema(description = "动态访问部署路径，主要用于页面组织访问链接路径")
	private String dynamicDeployPath;

	@Schema(description = "站点访问上下文路径")
	private String siteContextPath;


    @Schema(description = "站点模板路径")
    private String templatePath;


    @Schema(description = "站点首页模板")
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


    @Schema(description = "是否主站点")
    private Boolean isPrimeSite;

	@Schema(description = "简介")
	private String profile;

	@Schema(description = "是否发布")
	private Boolean isPublic;

	@Schema(description = "发布时间")
	private LocalDateTime publicAt;

	@Schema(description = "是否启用后台记录")
	private Boolean isEnableBackendRecord;

	@Schema(description = "备注")
	private String remark;


    @Schema(description = "页面访问量")
    private Integer pv;

	@Schema(description = "初始页面访问量,页面展示次数")
	private Integer initPv;


    @Schema(description = "页面访问ip数")
    private Integer iv;


    @Schema(description = "页面访问用户数")
    private Integer uv;









}
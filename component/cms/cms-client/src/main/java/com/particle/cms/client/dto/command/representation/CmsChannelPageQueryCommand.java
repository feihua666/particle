package com.particle.cms.client.dto.command.representation;
import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import com.particle.global.light.share.mybatis.anno.OrderBy;
import com.particle.global.light.share.mybatis.anno.QueryNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 栏目 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@OrderBy("seq")
@Data
@Schema
public class CmsChannelPageQueryCommand extends AbstractBaseTreePageQueryCommand {

    @Schema(description = "栏目id")
    private Long id;

    @Schema(description = "站点id")
    private Long cmsSiteId;


    @Like
    @Schema(description = "栏目编码,左前缀匹配")
    private String code;

    @Like
    @Schema(description = "栏目名称,左前缀匹配")
    private String name;

    @Like
    @Schema(description = "网页标题,左前缀匹配")
    private String title;

	@Schema(description = "栏目访问上下文路径，主要应用于动态页访问，可以实现在一个站点下不同的栏目")
	private String channelContextPath;


    @Schema(description = "栏目模板路径")
    private String templatePath;


    @Schema(description = "栏目模板")
    private String templateIndex;


    @Schema(description = "栏目静态页存放路径")
    private String staticSavePath;

	@Schema(description = "简介")
	private String profile;

	@Schema(description = "是否发布")
	private Boolean isPublic;

	@Schema(description = "发布时间")
	private LocalDateTime publicAt;

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

	@Schema(description = "关联的内容id,适用点击栏目访问内容详情的场景")
	private Long relatedCmsContentId;

	@Schema(description = "自定义url,适用于点击栏目访问自定义url的场景")
	private String customUrl;


    @Schema(description = "排序")
    private Integer seq;


    @Schema(description = "树深度")
    private Integer level;


















}

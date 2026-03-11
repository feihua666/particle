package com.particle.cms.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 栏目 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Data
@Schema
public class CmsChannelVO extends AbstractBaseIdTreeVO {

    @Schema(description = "站点id")
    private Long cmsSiteId;

    @TransBy(tableName = TransTableNameConstants.component_cms_site, byFieldName = "cmsSiteId", mapValueField = "name")
    @Schema(description = "站点名称")
    private String cmsSiteName;

    @Schema(description = "栏目编码")
    private String code;

    @Schema(description = "栏目名称")
    private String name;

	@Schema(description = "网页标题，用于页面标题")
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

    @TransBy(tableName = TransTableNameConstants.component_cms_content, byFieldName = "relatedCmsContentId", mapValueField = "title")
    @Schema(description = "关联的内容标题")
    private String relatedCmsContentTitle;

	@Schema(description = "自定义url,适用于点击栏目访问自定义url的场景")
	private String customUrl;

    @Schema(description = "排序")
    private Integer seq;



}

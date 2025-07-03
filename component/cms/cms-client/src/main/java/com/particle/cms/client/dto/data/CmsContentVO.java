package com.particle.cms.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.time.LocalDateTime;
/**
 * <p>
 * 内容 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Data
@Schema
public class CmsContentVO extends AbstractBaseIdVO {

    @Schema(description = "站点id")
    private Long cmsSiteId;

    @TransBy(tableName = TransTableNameConstants.component_cms_site, byFieldName = "cmsSiteId", mapValueField = "name")
    @Schema(description = "站点名称")
    private String cmsSiteName;

    @Schema(description = "栏目id")
    private Long cmsChannelId;

    @TransBy(tableName = TransTableNameConstants.component_cms_channel, byFieldName = "cmsChannelId", mapValueField = "name")
    @Schema(description = "栏目名称")
    private String cmsChannelName;

    @Schema(description = "内容分类id")
    private Long cmsContentCategoryId;

    @TransBy(tableName = TransTableNameConstants.component_cms_content_category, byFieldName = "cmsContentCategoryId", mapValueField = "name")
    @Schema(description = "内容分类名称")
    private String cmsContentCategoryName;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "来源")
    private String original;

    @Schema(description = "简介")
    private String profile;

    @Schema(description = "审核状态")
    private Long auditStatusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "auditStatusDictId",mapValueField = "value")
    @Schema(description = "审核状态对应字典值")
    private String auditStatusDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "auditStatusDictId",mapValueField = "name")
    @Schema(description = "审核状态对应字典名称")
    private String auditStatusDictName;

    @Schema(description = "是否发布")
    private Boolean isPublic;

    @Schema(description = "发布时间")
    private LocalDateTime publicAt;

    @Schema(description = "内容类型")
    private Long contentTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "contentTypeDictId",mapValueField = "name")
    @Schema(description = "内容类型对应字典名称")
    private String contentTypeDictName;

    @Schema(description = "图片地址")
    private String imageUrl;

    @Schema(description = "图片描述")
    private String imageDescription;

    @Schema(description = "图片地址1")
    private String imageUrl1;

    @Schema(description = "图片描述1")
    private String imageDescription1;

    @Schema(description = "图片地址2")
    private String imageUrl2;

    @Schema(description = "图片描述2")
    private String imageDescription2;

    @Schema(description = "内容模板路径")
    private String templatePath;

    @Schema(description = "内容模板")
    private String templateIndex;

    @Schema(description = "内容静态页存放路径")
    private String staticPath;

    @Schema(description = "页面访问量")
    private Integer pv;

    @Schema(description = "页面访问ip数")
    private Integer iv;

    @Schema(description = "页面访问用户数")
    private Integer uv;

    @Schema(description = "排序")
    private Integer seq;



}

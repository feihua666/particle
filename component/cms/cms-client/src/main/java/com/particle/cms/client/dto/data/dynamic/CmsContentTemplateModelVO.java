package com.particle.cms.client.dto.data.dynamic;

import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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
public class CmsContentTemplateModelVO extends AbstractBaseIdVO {

    @Schema(description = "站点id")
    private Long cmsSiteId;

    @Schema(description = "栏目id")
    private Long cmsChannelId;

    @Schema(description = "内容分类id")
    private Long cmsContentCategoryId;

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

    @Schema(description = "是否发布")
    private Boolean isPublic;

    @Schema(description = "发布时间")
    private LocalDateTime publicAt;

    @Schema(description = "内容类型")
    private Long contentTypeDictId;

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

    @Schema(description = "内容多媒体")
    private List<CmsContentMultimediaTemplateModelVO> contentMultimedias;

    public static CmsContentTemplateModelVO createByCmsContentVO(CmsContentVO cmsContentVO) {
        CmsContentTemplateModelVO cmsContentTemplateModelVO = new CmsContentTemplateModelVO();
        cmsContentTemplateModelVO.setId(cmsContentVO.getId());
        cmsContentTemplateModelVO.setCmsSiteId(cmsContentVO.getCmsSiteId());
        cmsContentTemplateModelVO.setCmsChannelId(cmsContentVO.getCmsChannelId());
        cmsContentTemplateModelVO.setCmsContentCategoryId(cmsContentVO.getCmsContentCategoryId());
        cmsContentTemplateModelVO.setTitle(cmsContentVO.getTitle());
        cmsContentTemplateModelVO.setAuthor(cmsContentVO.getAuthor());
        cmsContentTemplateModelVO.setOriginal(cmsContentVO.getOriginal());
        cmsContentTemplateModelVO.setProfile(cmsContentVO.getProfile());
        cmsContentTemplateModelVO.setAuditStatusDictId(cmsContentVO.getAuditStatusDictId());
        cmsContentTemplateModelVO.setIsPublic(cmsContentVO.getIsPublic());
        cmsContentTemplateModelVO.setPublicAt(cmsContentVO.getPublicAt());
        cmsContentTemplateModelVO.setContentTypeDictId(cmsContentVO.getContentTypeDictId());
        cmsContentTemplateModelVO.setImageUrl(cmsContentVO.getImageUrl());
        cmsContentTemplateModelVO.setImageDescription(cmsContentVO.getImageDescription());
        cmsContentTemplateModelVO.setImageUrl1(cmsContentVO.getImageUrl1());
        cmsContentTemplateModelVO.setImageDescription1(cmsContentVO.getImageDescription1());
        cmsContentTemplateModelVO.setImageUrl2(cmsContentVO.getImageUrl2());
        cmsContentTemplateModelVO.setImageDescription2(cmsContentVO.getImageDescription2());
        cmsContentTemplateModelVO.setTemplatePath(cmsContentVO.getTemplatePath());
        cmsContentTemplateModelVO.setTemplateIndex(cmsContentVO.getTemplateIndex());
        cmsContentTemplateModelVO.setStaticPath(cmsContentVO.getStaticPath());
        cmsContentTemplateModelVO.setPv(cmsContentVO.getPv());
        cmsContentTemplateModelVO.setIv(cmsContentVO.getIv());
        cmsContentTemplateModelVO.setUv(cmsContentVO.getUv());
        cmsContentTemplateModelVO.setSeq(cmsContentVO.getSeq());
        return cmsContentTemplateModelVO;
    }
}

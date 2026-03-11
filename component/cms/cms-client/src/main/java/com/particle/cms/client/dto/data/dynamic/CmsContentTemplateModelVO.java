package com.particle.cms.client.dto.data.dynamic;

import cn.hutool.core.util.StrUtil;
import com.particle.cms.client.constants.CmsConstants;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.global.tool.str.NetPathTool;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Arrays;
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

    @Schema(description = "作者介绍")
    private String authorProfile;

    @Schema(description = "来源")
    private String original;

    @Schema(description = "原文地址")
    private String originalUrl;

    @Schema(description = "原文发布时间")
    private LocalDateTime originalPublicAt;

    @Schema(description = "简介")
    private String profile;

    @Schema(description = "摘要，一般用于详情页")
    private String summary;

    @Schema(description = "关键词，逗号分隔")
    private String keywords;

    @Schema(description = "标签，逗号分隔")
    private String tags;

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
    private String staticSavePath;

    @Schema(description = "页面访问量")
    private Integer pv;

    @Schema(description = "页面访问ip数")
    private Integer iv;

    @Schema(description = "页面访问用户数")
    private Integer uv;

    @Schema(description = "文章字数,中文单字 + 英文单词")
    private Integer wordCount;

    @Schema(description = "阅读耗时")
    private String readingDuration;

    @Schema(description = "图表数量，图片表格数量")
    private Integer imageTableCount;

    @Schema(description = "引用数量，一般是正文标注的引用来源数量，如作者姓氏和年份")
    private Integer citationCount;

    @Schema(description = "参考文献数量，一般是文末列出的引用列表数量，如书名、期刊名、页码")
    private Integer referenceCount;

    @Schema(description = "作为栏目使用时的排序")
    private Integer alsoAsChannelSeq;

    @Schema(description = "排序")
    private Integer seq;



    /***** 下面中额外添加字段 ******/
    @Schema(description = "内容多媒体")
    private List<CmsContentMultimediaTemplateModelVO> contentMultimedias;


    @Schema(description = "额外上下文数据")
    private CmsTemplateModelContext context;
    /**
     * 首页地址
     * 如果是动态站点，首页地址为 domain + deployPath + path + index.htm。如：http://www.example.com/cms/index.htm
     */
    @Schema(description = "首页地址")
    private String indexUrl;

    /**
     * 根路径
     * 如果是动态站点，根路径为 domain + deployPath + path。如：http://www.example.com/cms
     */
    @Schema(description = "根路径")
    private String rootPath;

    @Schema(description = "关键词列表")
    private List<String> keywordList;

    @Schema(description = "标签列表")
    private List<String> tagList;

    /**
     * 初始化
     */
    private void init(CmsSiteTemplateModelVO cmsSiteTemplateModelVO,
                      CmsChannelTemplateModelVO cmsChannelTemplateModelVO,
                      boolean isPreview) {
        Boolean isDynamic = context.getIsDynamic();
        if (isDynamic) {
            String parentRootPath = cmsSiteTemplateModelVO.getRootPath();
            if(cmsChannelTemplateModelVO != null){
                parentRootPath = cmsChannelTemplateModelVO.getRootPath();
            }
            rootPath = NetPathTool.concat(parentRootPath, CmsConstants.requestContentPathPrefix,getId() + "");
            indexUrl = NetPathTool.concat(rootPath, isPreview ? CmsConstants.indexDothtmp :CmsConstants.indexDothtm);
        }else{
            // todo 静态资源路径
        }
        if (StrUtil.isNotBlank(keywords)) {
            keywordList = Arrays.asList(keywords.replace("，",",").split(","));
        }
        if (StrUtil.isNotBlank(tags)) {
            tagList = Arrays.asList(tags.replace("，",",").split(","));
        }
    }

    public static CmsContentTemplateModelVO createByCmsContentVO(CmsContentVO cmsContentVO,
                                                                 CmsSiteTemplateModelVO cmsSiteTemplateModelVO,
                                                                 CmsChannelTemplateModelVO cmsChannelTemplateModelVO,
                                                                 CmsTemplateModelContext context) {
        CmsContentTemplateModelVO vo = new CmsContentTemplateModelVO();
        vo.setId(cmsContentVO.getId());
        vo.setCmsSiteId(cmsContentVO.getCmsSiteId());
        vo.setCmsChannelId(cmsContentVO.getCmsChannelId());
        vo.setCmsContentCategoryId(cmsContentVO.getCmsContentCategoryId());
        vo.setTitle(cmsContentVO.getTitle());
        vo.setAuthor(cmsContentVO.getAuthor());
        vo.setAuthorProfile(cmsContentVO.getAuthorProfile());

        vo.setOriginal(cmsContentVO.getOriginal());
        vo.setOriginalUrl(cmsContentVO.getOriginalUrl());
        vo.setOriginalPublicAt(cmsContentVO.getOriginalPublicAt());
        vo.setProfile(cmsContentVO.getProfile());
        vo.setSummary(cmsContentVO.getSummary());
        vo.setKeywords(cmsContentVO.getKeywords());
        vo.setTags(cmsContentVO.getTags());

        vo.setAuditStatusDictId(cmsContentVO.getAuditStatusDictId());
        vo.setIsPublic(cmsContentVO.getIsPublic());
        vo.setPublicAt(cmsContentVO.getPublicAt());
        vo.setContentTypeDictId(cmsContentVO.getContentTypeDictId());
        vo.setImageUrl(cmsContentVO.getImageUrl());
        vo.setImageDescription(cmsContentVO.getImageDescription());
        vo.setImageUrl1(cmsContentVO.getImageUrl1());
        vo.setImageDescription1(cmsContentVO.getImageDescription1());
        vo.setImageUrl2(cmsContentVO.getImageUrl2());
        vo.setImageDescription2(cmsContentVO.getImageDescription2());
        vo.setTemplatePath(cmsContentVO.getTemplatePath());
        vo.setTemplateIndex(cmsContentVO.getTemplateIndex());
        vo.setStaticSavePath(cmsContentVO.getStaticSavePath());
        vo.setPv(cmsContentVO.getPv() + cmsContentVO.getInitPv());
        vo.setIv(cmsContentVO.getIv());
        vo.setUv(cmsContentVO.getUv());
        vo.setWordCount(cmsContentVO.getWordCount());
        vo.setReadingDuration(cmsContentVO.getReadingDuration());
        vo.setImageTableCount(cmsContentVO.getImageTableCount());
        vo.setCitationCount(cmsContentVO.getCitationCount());
        vo.setReferenceCount(cmsContentVO.getReferenceCount());

        vo.setAlsoAsChannelSeq(cmsContentVO.getAlsoAsChannelSeq());
        vo.setSeq(cmsContentVO.getSeq());


        vo.setContext(context);

        vo.init(cmsSiteTemplateModelVO,
                cmsChannelTemplateModelVO,
                context.getMode() == CmsTemplateModelContext.Mode.preview);

        return vo;
    }
}

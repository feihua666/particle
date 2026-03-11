package com.particle.cms.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * <p>
 * 内容 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Data
@Schema
public class CmsContentCreateCommand extends AbstractBaseCommand {


    @NotNull(message = "站点id 不能为空")
    @Schema(description = "站点id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long cmsSiteId;


    @Schema(description = "栏目id")
    private Long cmsChannelId;


    @Schema(description = "内容分类id")
    private Long cmsContentCategoryId;


    @Schema(description = "标题")
    private String title;


    @NotEmpty(message = "作者 不能为空")
    @Schema(description = "作者", requiredMode = Schema.RequiredMode.REQUIRED)
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

    @Schema(description = "备注")
    private String remark;


    @NotNull(message = "初始页面访问量 不能为空")
    @Schema(description = "初始页面访问量,页面展示次数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer initPv;


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

    @NotNull
    @Schema(description = "是否也作为栏目使用")
    private Boolean isAlsoAsChannel;

    @NotNull(message = "作为栏目使用时的排序 不能为空")
	@Schema(description = "作为栏目使用时的排序")
	private Integer alsoAsChannelSeq;

	@Schema(description = "是否在列表中展示")
	private String isShowInList;

    @NotNull(message = "排序 不能为空")
    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;


    @Schema(description = "内容文本文章")
    private String contentArticle;

    @Schema(description = "是否使用文章分析来统计")
    private Boolean isUseArticleAnalyzer;
}

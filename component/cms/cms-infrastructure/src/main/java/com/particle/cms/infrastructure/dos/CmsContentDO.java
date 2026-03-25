package com.particle.cms.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
/**
 * <p>
 * 内容表
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Accessors(chain = true)
@Data
@TableName("component_cms_content")
public class CmsContentDO extends BaseDO {

    /**
    * 站点id
    */
    private Long cmsSiteId;

    /**
    * 栏目id
    */
    private Long cmsChannelId;

    /**
    * 内容分类id
    */
    private Long cmsContentCategoryId;

    /**
    * 标题
    */
    private String title;

    /**
    * 作者
    */
    private String author;

	/**
	 * 作者介绍
	 */
	private String authorProfile;

    /**
    * 来源，原文，如果是原创，写原创即可
    */
    private String original;

	/**
	 * 原文地址
	 */
	private String originalUrl;

	/**
	 * 原文发布时间
	 */
	private LocalDateTime originalPublicAt;

    /**
    * 简介
    */
    private String profile;

	/**
	 * 摘要，一般用于详情页
	 */
	private String summary;

	/**
	 * 关键词，逗号分隔
	 */
	private String keywords;

	/**
	 * 标签，逗号分隔
	 */
	private String tags;

    /**
    * 审核状态，字典id
    */
    private Long auditStatusDictId;

    /**
    * 是否发布
    */
    private Boolean isPublic;

    /**
    * 发布时间
    */
    private LocalDateTime publicAt;
    
    /**
    * 内容类型，字典id，article=文章，library=文库，gallery=图库
    */
    private Long contentTypeDictId;

    /**
    * 图片地址，主要用于列表展示
    */
    private String imageUrl;

    /**
    * 图片描述，可能主要用于详情展示，如：图片来源于网络
    */
    private String imageDescription;

    /**
    * 图片地址1，主要用于列表展示
    */
    private String imageUrl1;

    /**
    * 图片描述1，可能主要用于详情展示，如：图片来源于网络
    */
    private String imageDescription1;

    /**
    * 图片地址2，主要用于列表展示
    */
    private String imageUrl2;

    /**
    * 图片描述2，可能主要用于详情展示，如：图片来源于网络
    */
    private String imageDescription2;

    /**
    * 内容模板路径
    */
    private String templatePath;

    /**
    * 内容模板,默认index.html
    */
    private String templateIndex;

    /**
    * 内容静态页存放路径
    */
    private String staticSavePath;

	/**
	 * 备注
	 */
	private String remark;

    /**
    * 页面访问量,页面展示次数
    */
    private Integer pv;

	/**
	 * 初始页面访问量,页面展示次数
	 */
	private Integer initPv;

    /**
    * 页面访问ip数,一天之内相同IP地址只被计算一次
    */
    private Integer iv;

    /**
    * 页面访问用户数,页面访问电脑客户端数,一天之内相同cookie的访问只被计算1次
    */
    private Integer uv;

	/**
	 * 文章字数,中文单字 + 英文单词
	 */
	private Integer wordCount;

	/**
	 * 阅读耗时
	 */
	private String readingDuration;

	/**
	 * 图表数量，图片表格数量
	 */
	private Integer imageTableCount;

	/**
	 * 引用数量，一般是正文标注的引用来源数量，如作者姓氏和年份
	 */
	private Integer citationCount;

	/**
	 * 参考文献数量，一般是文末列出的引用列表数量，如书名、期刊名、页码
	 */
	private Integer referenceCount;

	/**
	 * 是否也作为栏目使用
	 */
	private Boolean isAlsoAsChannel;

	/**
	 * 作为栏目使用时的排序
	 */
	private Integer alsoAsChannelSeq;

	/**
	 * 是否在列表中展示
	 */
	private Boolean isShowInList;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;


}

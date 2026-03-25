package com.particle.cms.domain;

import com.particle.cms.domain.enums.CmsContentAuditStatus;
import com.particle.cms.domain.gateway.CmsDictGateway;
import com.particle.cms.domain.value.ArticleStats;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
/**
 * <p>
 * 内容 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Data
@Entity
public class CmsContent extends AggreateRoot {

    private CmsContentId id;

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


    public void initForAdd() {
        this.isPublic = false;
        this.pv = 0;
        this.iv = 0;
        this.uv = 0;
        if (this.initPv == null) {
            this.initPv = 0;
        }

        if (this.wordCount == null) {
            this.wordCount = 0;
        }
        if (this.readingDuration == null) {
            this.readingDuration = "";
        }
        if (this.imageTableCount == null) {
            this.imageTableCount = 0;
        }
        if (this.citationCount == null) {
            this.citationCount = 0;
        }
        if (this.referenceCount == null) {
            this.referenceCount = 0;
        }

        auditWait();
    }

    /**
     * 发布
     */
    public void publish() {
        this.isPublic = true;
        this.publicAt = LocalDateTime.now();
    }
    /**
     * 取消发布
     */
    public void unPublish() {
        this.isPublic = false;
        this.publicAt = null;
    }
    /**
     * 待审核
     */
    public void auditWait() {
        String groupCode = CmsContentAuditStatus.Group.cms_content_audit_status.groupCode();
        this.auditStatusDictId = cmsDictGateway.getDictIdByGroupCodeAndItemValue(groupCode,CmsContentAuditStatus.wait_audit.itemValue());
    }

    /**
     * 审核通过
     */
    public void auditPass() {
        String groupCode = CmsContentAuditStatus.Group.cms_content_audit_status.groupCode();
        this.auditStatusDictId = cmsDictGateway.getDictIdByGroupCodeAndItemValue(groupCode,CmsContentAuditStatus.pass_audit.itemValue());
    }
     /**
     * 审核不通过
     */
    public void auditUnPass() {
        String groupCode = CmsContentAuditStatus.Group.cms_content_audit_status.groupCode();
        this.auditStatusDictId = cmsDictGateway.getDictIdByGroupCodeAndItemValue(groupCode,CmsContentAuditStatus.unpass_audit.itemValue());
    }

    public void changeAuditStatusDictId(Long auditStatusDictId) {
        this.auditStatusDictId = auditStatusDictId;
    }

    public void updateStats(ArticleStats articleStats) {
        this.wordCount = articleStats.getChineseCharCount() + articleStats.getEnglishWordCount();
        this.imageTableCount = articleStats.getTableCount() + articleStats.getImageCount();
        this.citationCount = articleStats.getCitationCount();
        this.referenceCount = articleStats.getReferenceCount();

    }
    /**
     * 创建内容领域模型对象
     * @return 内容领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CmsContent create(){
        return DomainFactory.create(CmsContent.class);
    }


    public static CmsContent create(CmsContentId id){
        CmsContent cmsContent = DomainFactory.create(CmsContent.class);
        cmsContent.setId(id);
        return cmsContent;
    }
    @Autowired
    private CmsDictGateway cmsDictGateway;
}

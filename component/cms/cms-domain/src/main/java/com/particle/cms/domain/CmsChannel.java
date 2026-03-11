package com.particle.cms.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 栏目 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Data
@Entity
public class CmsChannel extends AggreateRoot {

    private CmsChannelId id;

    /**
    * 站点id
    */
    private Long cmsSiteId;

    /**
    * 栏目编码
    */
    private String code;

    /**
    * 栏目名称
    */
    private String name;

	/**
	 * 网页标题，用于页面标题
	 */
	private String title;

	/**
	 * 栏目访问上下文路径，主要应用于动态页访问，可以实现在一个站点下不同的栏目
	 */
	private String channelContextPath;

    /**
    * 栏目模板路径
    */
    private String templatePath;

    /**
    * 栏目模板,默认index.html
    */
    private String templateIndex;

    /**
    * 栏目静态页存放路径
    */
    private String staticSavePath;

	/**
	 * 简介
	 */
	private String profile;

	/**
	 * 是否发布
	 */
	private Boolean isPublic;

	/**
	 * 发布时间
	 */
	private LocalDateTime publicAt;

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
	 * 关联的内容id,适用点击栏目访问内容详情的场景
	 */
	private Long relatedCmsContentId;

	/**
	 * 自定义url,适用于点击栏目访问自定义url的场景
	 */
	private String customUrl;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;

    /**
    * 父级
    */
    private Long parentId;

    public void initForAdd() {
        this.pv = 0;
        this.iv = 0;
        this.uv = 0;
        if (this.initPv == null) {
            this.initPv = 0;
        }
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
     * 创建栏目领域模型对象
     * @return 栏目领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CmsChannel create(){
        return DomainFactory.create(CmsChannel.class);
    }
	public static CmsChannel create(CmsChannelId id){
		CmsChannel cmsChannel = DomainFactory.create(CmsChannel.class);
		cmsChannel.setId(id);
		return cmsChannel;
	}
}

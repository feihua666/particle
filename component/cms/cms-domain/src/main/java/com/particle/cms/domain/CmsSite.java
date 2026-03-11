package com.particle.cms.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 站点 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Data
@Entity
public class CmsSite extends AggreateRoot {

    private CmsSiteId id;

    /**
    * 站点编码
    */
    private String code;

    /**
    * 站点名称
    */
    private String name;

	/**
	 * 网页标题，用于页面标题
	 */
	private String title;

    /**
    * 站点域名
    */
    private String domain;

	/**
	 * 站点动态外部域名
	 */
	private String dynamicDomain;

	/**
	 * 部署路径，主要用于页面组织访问链接路径
	 */
	private String dynamicDeployPath;

	/**
	 * 站点访问上下文路径
	 */
	private String siteContextPath;

    /**
    * 站点模板路径，站点模板存放路径，pc默认default,移动端默认mobile,站点首页模板默认index.html
    */
    private String templatePath;

    /**
    * 站点首页模板,默认index.html
    */
    private String templateIndex;

	/**
	 * 404模板路径
	 */
	private String template404Path;

	/**
	 * 404内容模板,默认404.html
	 */
	private String template404Index;

	/**
	 * 403模板路径
	 */
	private String template403Path;

	/**
	 * 403内容模板,默认403.ftlh
	 */
	private String template403Index;

	/**
	 * channel模板路径
	 */
	private String templateChannelPath;

	/**
	 * channel内容模板
	 */
	private String templateChannelIndex;

	/**
	 * content模板路径
	 */
	private String templateContentPath;

	/**
	 * content内容模板
	 */
	private String templateContentIndex;

	/**
	 * 站点静态化页存放路径，文件系统绝对路径
	 */
	private String staticSavePath;

	/**
	 * 静态化站点域名,主要用于在访问页面时，静态化优先时，优先访问静态化页面
	 */
	private String staticDomain;

	/**
	 * 静态化访问部署路径，主要用于页面组织访问链接路径，该路径和spring context-path无关，一般用于nginx反向代理时可能会配置
	 */
	private String staticDeployPath;

    /**
    * 是否主站点，同一个域名即domain字段下，只能有一个主站
    */
    private Boolean isPrimeSite;

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
	 * 是否启用后台记录
	 */
	private Boolean isEnableBackendRecord;

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


    public void initForAdd() {
        this.pv = 0;
        this.iv = 0;
        this.uv = 0;
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
     * 创建站点领域模型对象
     * @return 站点领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CmsSite create(){
        return DomainFactory.create(CmsSite.class);
    }
	public static CmsSite create(CmsSiteId id){
		CmsSite cmsSite = DomainFactory.create(CmsSite.class);
		cmsSite.setId(id);
		return cmsSite;
	}
}
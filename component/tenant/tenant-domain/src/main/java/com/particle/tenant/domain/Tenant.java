package com.particle.tenant.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 租户 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Data
@Entity
public class Tenant extends AggreateRoot {

    private TenantId id;

    /**
    * 租户编码,模糊查询
    */
    private String code;

    /**
    * 租户名称,模糊查询
    */
    private String name;

    /**
    * 是否禁用
    */
    private Boolean isDisabled;

    /**
    * 禁用原因
    */
    private String disabledReason;

    /**
    * 姓名
    */
    private String userName;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 手机号
    */
    private String mobile;

    /**
    * 租户域名，可根据域名识别租户如：example.com
    */
    private String tenantDomain;

    /**
    * 租户主题，一个代表客户端主题样式的字符串
    */
    private String tenantThemeJson;

    /**
    * 租户默认的页面路由
    */
    private String tenantDefaultRouteJson;
	/**
	 * 是否正式，1=正式，0=试用
	 */
	private Boolean isFormal;
	/**
	 * 用户数量限制
	 */
	private Integer userLimitCount;
	/**
	 * 生效日期，从什么时候开始生效
	 */
	private LocalDateTime effectiveAt;
	/**
	 * 过期时间，从什么时候失效
	 */
	private LocalDateTime expireAt;
	/**
	 * 主用户，一般该用户为租户的超级管理员
	 */
	private Long masterUserId;
    /**
     * 租户logo地址
     */
    private String tenantLogoJson;

	/**
	 * 图标,支持base64编码,支持http://开头的图片地址,支持本地文件路径地址，支持classpath文件路径
	 */
	private String tenantFaviconJson;
    /**
    * 额外配置json
    */
    private String configJson;

    /**
    * 描述
    */
    private String remark;



    /**
     * 创建租户领域模型对象
     * @return 租户领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static Tenant create(){
        return DomainFactory.create(Tenant.class);
    }
}
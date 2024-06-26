package com.particle.tenant.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 租户表
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Data
@TableName("component_tenant")
public class TenantDO extends BaseTreeDO {

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
	 * 租户logo文本
	 */
	private String tenantLogoTextJson;

	/**
	 * 租户web页面标题文本，添加主要是后台管理的页面标题
	 */
	private String tenantWebTitleJson;

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


}
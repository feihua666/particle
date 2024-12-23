package com.particle.navigation.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 导航网站静态部署 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Data
@Entity
public class NavigationStaticDeploy extends AggreateRoot {

    private NavigationStaticDeployId id;

    /**
    * 部署编码
    */
    private String code;

    /**
    * 部署名称
    */
    private String name;

    /**
    * 前端域名地址，如：http://www.example.com
    */
    private String frontDomain;

    /**
    * 前端上下文路径，如：/nav
    */
    private String frontContextPath;

    /**
    * 前端上下文路径的下一级路径，如：/nav
    */
    private String frontSubContextPath;

    /**
    * 是否纯静态部署，1=纯静态部署，0=半静态部署即只部署前端页面图片或接口不可用
    */
    private Boolean isPureStaticDeploy;

	/**
	 * 部署路径，如：/data/nav
	 */
	private String deployPath;

	/**
	 * 部署成功后执行的groovy脚本，方便进行额外处理
	 */
	private String deployPostGroovyScript;

    /**
    * 上一次部署时间
    */
    private LocalDateTime lastDeployAt;

    /**
    * 描述
    */
    private String remark;


    public void changeLastDeployAt(LocalDateTime lastDeployAt) {
        this.lastDeployAt = lastDeployAt;
    }

    /**
     * 创建导航网站静态部署领域模型对象
     * @return 导航网站静态部署领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static NavigationStaticDeploy create(){
        return DomainFactory.create(NavigationStaticDeploy.class);
    }
}

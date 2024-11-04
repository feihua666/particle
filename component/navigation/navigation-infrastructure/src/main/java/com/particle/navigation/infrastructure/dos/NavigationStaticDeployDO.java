package com.particle.navigation.infrastructure.dos;

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
 * 导航网站静态部署表
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Accessors(chain = true)
@Data
@TableName("component_navigation_static_deploy")
public class NavigationStaticDeployDO extends BaseDO {

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


}
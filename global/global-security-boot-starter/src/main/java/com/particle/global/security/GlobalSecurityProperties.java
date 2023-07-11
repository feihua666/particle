package com.particle.global.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 全局安全配置
 * </p>
 *
 * @author yangwei
 * @since 2023-07-11 09:19
 */
@Getter
@Setter
@ConfigurationProperties(prefix = GlobalSecurityProperties.prefix)
public class GlobalSecurityProperties {

	public static final String prefix = "particle.security";

	/**
	 * 强制不使用自定义登录页面
	 * 原因：在spring security配置设置了loginPage就默认禁用了默认的登录界面，这样需要自己添加页面，但在开发时有可能只是想用原来的登录界面，这里提供一个配置强制使用原来的，不使用自己定义的
	 */
	private Boolean forceNotUseCustomLoginPage = false;
}

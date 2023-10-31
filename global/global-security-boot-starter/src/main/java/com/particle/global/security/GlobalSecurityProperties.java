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

	/**
	 * 强制在登录成功后在response中写token响应头
	 * 这在使用cookie和header同时解析sessionId时，如果用户已经登录，但没有退出，重新登录，这时cookie默认是自带发送的，导致在登录时仍能获取后登录用户，在登录成功后响应头中没有token请求头
	 * 如果单独使用header解析，没有问题，这里设置为false即可，本系统默认设置为false，因为默认使用的是header，参见：application-session.yml
	 */
	private Boolean forceWriteLoginHeaderToken = false;
}

package com.particle.global.security.security.config;

import com.particle.global.security.security.login.DefaultAuthenticationFailureHandler;
import com.particle.global.security.security.login.DefaultAuthenticationSuccessHandler;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * <p>
 * 自定义扩展参数
 * </p>
 *
 * @author yangwei
 * @since 2023-05-24 17:50
 */
@Setter
@Getter
public class CustomWebSecurityConfigureExt {

	/**
	 * 登录成功处理器
	 */
	private AuthenticationSuccessHandler defaultAuthenticationSuccessHandler;
	/**
	 * 登录失败处理器
	 */
	private AuthenticationFailureHandler defaultAuthenticationFailureHandler;
}

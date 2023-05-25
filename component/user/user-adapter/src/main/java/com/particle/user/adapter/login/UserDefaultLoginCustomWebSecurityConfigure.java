package com.particle.user.adapter.login;

import com.particle.global.security.security.config.CustomWebSecurityConfigure;
import com.particle.global.security.security.config.CustomWebSecurityConfigureExt;
import com.particle.user.adapter.login.captcha.CaptchaDaoAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户登录配置
 * </p>
 *
 * @author yangwei
 * @since 2022-11-25 20:16
 */
@Component
public class UserDefaultLoginCustomWebSecurityConfigure implements CustomWebSecurityConfigure {

	public static final String login_captcha_url = "/loginCaptcha";


	@Autowired
	private IdentifierUserDetailsServiceImpl identifierUserDetailsService;


	@Override
	public void configure(HttpSecurity http, AuthenticationManager authenticationManager, CustomWebSecurityConfigureExt ext) throws Exception {

		// 配置验证码登录过滤器
		UsernamePasswordAuthenticationFilter captchaUsernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
		captchaUsernamePasswordAuthenticationFilter.setFilterProcessesUrl(login_captcha_url);
		captchaUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager);
		captchaUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(ext.getDefaultAuthenticationSuccessHandler());
		captchaUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(ext.getDefaultAuthenticationFailureHandler());
		http.addFilterAfter(captchaUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder,CustomWebSecurityConfigureExt ext) throws Exception {
		auth.userDetailsService(identifierUserDetailsService).passwordEncoder(passwordEncoder);

		//	验证码登录
		CaptchaDaoAuthenticationProvider captchaDaoAuthenticationProvider = new CaptchaDaoAuthenticationProvider();
		captchaDaoAuthenticationProvider.setUserDetailsService(identifierUserDetailsService);
		auth.authenticationProvider(captchaDaoAuthenticationProvider);
	}
}

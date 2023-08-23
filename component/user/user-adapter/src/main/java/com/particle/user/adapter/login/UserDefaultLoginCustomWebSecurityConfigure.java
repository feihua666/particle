package com.particle.user.adapter.login;

import com.particle.global.captcha.ICaptchaService;
import com.particle.global.security.security.config.CustomDaoAuthenticationProvider;
import com.particle.global.security.security.config.CustomWebSecurityConfigure;
import com.particle.global.security.security.config.CustomWebSecurityConfigureExt;
import com.particle.global.security.security.config.LazyResolveAuthenticationManager;
import com.particle.user.adapter.login.captcha.CaptchaDaoAuthenticationProvider;
import com.particle.user.adapter.login.captcha.CaptchaUsernamePasswordAuthenticationFilter;
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
	@Autowired
	private ICaptchaService captchaService;

	@Override
	public void configure(HttpSecurity http,AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder,CustomWebSecurityConfigureExt ext) throws Exception {

		// 配置验证码登录过滤器
		CaptchaUsernamePasswordAuthenticationFilter captchaUsernamePasswordAuthenticationFilter = new CaptchaUsernamePasswordAuthenticationFilter();
		captchaUsernamePasswordAuthenticationFilter.setFilterProcessesUrl(login_captcha_url);
		AuthenticationManager authenticationManager = new LazyResolveAuthenticationManager(http);
		captchaUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager);
		captchaUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(ext.getDefaultAuthenticationSuccessHandler());
		captchaUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(ext.getDefaultAuthenticationFailureHandler());
		http.addFilterAfter(captchaUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


		CustomDaoAuthenticationProvider customDaoAuthenticationProvider = new CustomDaoAuthenticationProvider();
		customDaoAuthenticationProvider.setUserDetailsService(identifierUserDetailsService);
		customDaoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		authenticationManagerBuilder.authenticationProvider(customDaoAuthenticationProvider);

		// 使用上面的自定义 CustomDaoAuthenticationProvider
		//auth.userDetailsService(identifierUserDetailsService).passwordEncoder(passwordEncoder);

		//	验证码登录
		CaptchaDaoAuthenticationProvider captchaDaoAuthenticationProvider = new CaptchaDaoAuthenticationProvider();
		captchaDaoAuthenticationProvider.setCaptchaService(captchaService);
		captchaDaoAuthenticationProvider.setUserDetailsService(identifierUserDetailsService);
		authenticationManagerBuilder.authenticationProvider(captchaDaoAuthenticationProvider);
	}

}

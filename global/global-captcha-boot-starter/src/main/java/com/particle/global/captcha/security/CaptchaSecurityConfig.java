package com.particle.global.captcha.security;

import com.particle.global.captcha.GlobalCaptchaAutoConfiguration;
import com.particle.global.captcha.ICaptchaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 16:45
 */
@Configuration
public class CaptchaSecurityConfig {


	/**
	 * 该配置不能放到 {@link GlobalCaptchaAutoConfiguration} 中，因为该过滤器需要 验证码服务自动
	 * 注意:如果Filter声明为一个Bean,则不需要定义为FilterRegistrationBean,也会被spring发现并添加,该方式无法定义拦截规则等,默认全局,慎用
	 * @return
	 */
	@Bean
	public CaptchaSecurityFilter captchaSecurityFilter(ICaptchaService captchaService){
		CaptchaSecurityFilter captchaSecurityFilter = new CaptchaSecurityFilter();
		captchaSecurityFilter.setCaptchaService(captchaService);
		return captchaSecurityFilter;
	}
	/**
	 * 该配置不能放到 {@link GlobalCaptchaAutoConfiguration} 中，因为该过滤器需要 验证码服务自动
	 * 注意:如果Filter声明为一个Bean,则不需要定义为FilterRegistrationBean,也会被spring发现并添加,该方式无法定义拦截规则等,默认全局,慎用
	 * @return
	 */
	@Bean
	public DynamicCaptchaSecurityFilter dynamicCaptchaSecurityFilter(ICaptchaService captchaService){
		DynamicCaptchaSecurityFilter captchaSecurityFilter = new DynamicCaptchaSecurityFilter();
		captchaSecurityFilter.setCaptchaService(captchaService);
		return captchaSecurityFilter;
	}
}

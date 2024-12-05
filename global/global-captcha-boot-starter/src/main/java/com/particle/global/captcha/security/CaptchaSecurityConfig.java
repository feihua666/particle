package com.particle.global.captcha.security;

import com.particle.global.captcha.GlobalCaptchaAutoConfiguration;
import com.particle.global.captcha.ICaptchaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * <p>
 * 默认spring将filter bean 会放到全局的filter中，然后在 {@link CaptchaSecurityConfigure} 再使用，会执行两次
 * 所以这里使用了{@link OncePerRequestFilter} 作为父类保证只执行一次
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
		DynamicCaptchaSecurityFilter dynamicCaptchaSecurityFilter = new DynamicCaptchaSecurityFilter();
		dynamicCaptchaSecurityFilter.setCaptchaService(captchaService);
		return dynamicCaptchaSecurityFilter;
	}
}

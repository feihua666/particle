package com.particle.global.captcha.security;

import com.particle.global.captcha.CaptchaAutoConfiguration;
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
	 * 该配置不能放到 {@link CaptchaAutoConfiguration} 中，因为该过滤器需要 验证码服务自动
	 * @return
	 */
	@Bean
	public CaptchaSecurityFilter captchaSecurityFilter(ICaptchaService captchaService){
		CaptchaSecurityFilter captchaSecurityFilter = new CaptchaSecurityFilter();
		captchaSecurityFilter.setCaptchaService(captchaService);
		return captchaSecurityFilter;
	}
}

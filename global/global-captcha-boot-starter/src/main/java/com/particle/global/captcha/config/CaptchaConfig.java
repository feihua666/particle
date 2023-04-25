package com.particle.global.captcha.config;

import com.particle.global.captcha.DefaultCaptchaServiceImpl;
import com.particle.global.captcha.ICaptchaService;
import com.particle.global.captcha.gen.DefaultCaptchaGenServiceImpl;
import com.particle.global.captcha.gen.ICaptchaGenService;
import com.particle.global.captcha.store.HttpSessionStoreServiceImpl;
import com.particle.global.captcha.store.ICaptchaStoreService;
import com.particle.global.captcha.verify.DefaultCaptchaVerifyServiceImpl;
import com.particle.global.captcha.verify.ICaptchaVerifyService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 验证码相关服务配置
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 16:56
 */
@Configuration
public class CaptchaConfig {

	@Bean
	@ConditionalOnMissingBean
	public ICaptchaGenService captchaGenService(){
		return new DefaultCaptchaGenServiceImpl();
	}

	@Bean
	@ConditionalOnMissingBean
	public ICaptchaVerifyService captchaVerifyService(ICaptchaStoreService captchaStoreService){
		return new DefaultCaptchaVerifyServiceImpl(captchaStoreService);
	}
	@Bean
	@ConditionalOnMissingBean
	public ICaptchaStoreService captchaStoreService(){
		return new HttpSessionStoreServiceImpl();
	}
	@Bean
	@ConditionalOnMissingBean
	public ICaptchaService captchaService(ICaptchaGenService captchaGenService, ICaptchaVerifyService captchaVerifyService, ICaptchaStoreService captchaStoreService){
		return new DefaultCaptchaServiceImpl(captchaGenService,captchaVerifyService,captchaStoreService);
	}
}

package com.particle.global.captcha.config;

import com.particle.global.captcha.DefaultCaptchaServiceImpl;
import com.particle.global.captcha.ICaptchaService;
import com.particle.global.captcha.gen.DefaultCaptchaGenServiceImpl;
import com.particle.global.captcha.gen.ICaptchaGenService;
import com.particle.global.captcha.store.HttpSessionStoreServiceImpl;
import com.particle.global.captcha.store.ICaptchaStoreService;
import com.particle.global.captcha.store.JdbcStoreServiceImpl;
import com.particle.global.captcha.store.MemoryStoreServiceImpl;
import com.particle.global.captcha.verify.DefaultCaptchaVerifyServiceImpl;
import com.particle.global.captcha.verify.ICaptchaVerifyService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * <p>
 * 验证码相关服务配置
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 16:56
 */
@Configuration(proxyBeanMethods = false)
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

	/**
	 * 默认使用内存存储
	 * 谨慎使用 {@link HttpSessionStoreServiceImpl} 实现，因为其在没有登录之前不可用
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ICaptchaStoreService memoryCaptchaStoreService(){
		return new MemoryStoreServiceImpl();
	}
	@Bean
	@ConditionalOnMissingBean
	public ICaptchaService captchaService(ICaptchaGenService captchaGenService, ICaptchaVerifyService captchaVerifyService, ICaptchaStoreService captchaStoreService){
		return new DefaultCaptchaServiceImpl(captchaGenService,captchaVerifyService,captchaStoreService);
	}

	/**
	 * 如果满足jdbc使用jdbc
	 */
	@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass(NamedParameterJdbcTemplate.class)
	static class JdbcStoreConfig{
		@Bean
		@ConditionalOnBean(NamedParameterJdbcTemplate.class)
		public ICaptchaStoreService jdbcCaptchaStoreService(NamedParameterJdbcTemplate jdbcTemplate) {
			return new JdbcStoreServiceImpl(jdbcTemplate);
		}
	}
}

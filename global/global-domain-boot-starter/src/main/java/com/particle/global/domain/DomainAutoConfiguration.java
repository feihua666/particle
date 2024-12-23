package com.particle.global.domain;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 自动相较于
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 17:44
 */
@Configuration(proxyBeanMethods = false)
public class DomainAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(ApplicationContextHelper.class)
	public ApplicationContextHelper applicationContextHelper() {
		return new ApplicationContextHelper();
	}
}

package com.particle.global.catchlog;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>
 * 配置
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 18:55
 */
@Configuration
@ConditionalOnProperty(prefix = "particle.catchlog",name = "enableCatchLog",havingValue = "true",matchIfMissing = false)
@EnableAspectJAutoProxy
public class CatchLogAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(CatchLogAspect.class)
	public CatchLogAspect catchLogAspect() {
		return new CatchLogAspect();
	}
}

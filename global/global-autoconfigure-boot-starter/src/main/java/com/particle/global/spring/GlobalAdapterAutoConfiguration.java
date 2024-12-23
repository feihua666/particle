package com.particle.global.spring;

import com.particle.global.tool.id.SnowflakeIdTool;
import com.particle.global.tool.spring.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 全局自动配置类，主要是为了了文件注入到spring环境
 * </p>
 *
 * @author yangwei
 * @since 2022-08-07 14:59
 */
@Configuration(proxyBeanMethods = false)
public class GlobalAdapterAutoConfiguration {


	@Bean
	public SpringContextHolder springContextHolder() {
		return new SpringContextHolder();
	}

	@Bean
	public SnowflakeIdTool snowflakeIdTool(){
		return new SnowflakeIdTool();
	}
}

package com.particle.global.web.mvc;

import com.particle.global.web.mvc.http.jackson2.BootAdminCustomJackson2ObjectMapperBuilderCustomizer;
import de.codecentric.boot.admin.server.utils.jackson.AdminServerModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * mvc 全局自动配置
 * </p>
 *
 * @author yangwei
 * @since 2022-06-22 14:16
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan
public class GlobalWebMvcAutoConfiguration {

	@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass(AdminServerModule.class)
	public static class BootAdminServerDependConfig{
		@Bean
		public BootAdminCustomJackson2ObjectMapperBuilderCustomizer bootAdminCustomJackson2ObjectMapperBuilderCustomizer() {
			return new BootAdminCustomJackson2ObjectMapperBuilderCustomizer();
		}
	}
}

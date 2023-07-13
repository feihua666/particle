package com.particle.global.security.security;

import com.particle.global.security.GlobalSecurityProperties;
import com.particle.global.security.security.config.CustomWebSecurityConfigureExt;
import com.particle.global.security.security.config.GrantedTenantResolveAndPersistentHelper;
import com.particle.global.security.security.config.InnerPathConfig;
import com.particle.global.security.security.login.DefaultAuthenticationFailureHandler;
import com.particle.global.security.security.login.DefaultAuthenticationSuccessHandler;
import com.particle.global.security.security.login.GlobalSecurityAuthenticationHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * web安全自动配置
 * </p>
 *
 * @author yangwei
 * @since 2022-06-03 17:29
 */
@Configuration
@EnableConfigurationProperties(GlobalSecurityProperties.class)
@ComponentScan
public class GlobalSecurityAutoConfiguration {

	@Bean
	public ApplicationContextForSecurityHelper applicationContextForSecurityHelper() {
		return new ApplicationContextForSecurityHelper();
	}
	@Bean
	public InnerPathConfig innerPathConfig() {
		return new InnerPathConfig();
	}

	@Bean
	public DefaultAuthenticationFailureHandler defaultAuthenticationFailureHandler() {
		return new DefaultAuthenticationFailureHandler();
	}

	@Bean
	public DefaultAuthenticationSuccessHandler defaultAuthenticationSuccessHandler() {
		return new DefaultAuthenticationSuccessHandler();
	}

	@Bean
	@ConditionalOnMissingBean
	public CustomWebSecurityConfigureExt customWebSecurityConfigureExt() {
		CustomWebSecurityConfigureExt customWebSecurityConfigureExt = new CustomWebSecurityConfigureExt();
		customWebSecurityConfigureExt.setDefaultAuthenticationSuccessHandler(defaultAuthenticationSuccessHandler());
		customWebSecurityConfigureExt.setDefaultAuthenticationFailureHandler(defaultAuthenticationFailureHandler());
		return customWebSecurityConfigureExt;
	}

	@Bean
	public GlobalSecurityAuthenticationHandler globalSecurityAuthenticationHandler() {
		return new GlobalSecurityAuthenticationHandler();
	}

	@Bean
	public GrantedTenantResolveAndPersistentHelper grantedTenantResolveAndPersistentHelper() {
		return new GrantedTenantResolveAndPersistentHelper();
	}
}

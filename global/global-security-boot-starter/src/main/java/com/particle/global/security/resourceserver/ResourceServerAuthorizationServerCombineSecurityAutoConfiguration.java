package com.particle.global.security.resourceserver;

import com.particle.global.security.GlobalSecurityProperties;
import com.particle.global.security.authorizationserver.AuthorizationServerSecurityAutoConfiguration;
import com.particle.global.security.security.config.WebSecurityConfig;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;

/**
 * <p>
 * 注意：不要开启，开启后 {@link WebSecurityConfig#defaultSecurityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity, org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)} 将不生效，该配置类仅供配置参考，不使用
 * 资源服务自动配置，该配置仅支持资源服务器和授权服务一起（在同一个jvm中）时使用，否则使用 spring security 默认配置即可
 * 参考：{@link OAuth2ResourceServerAutoConfiguration} 由于已经配置了如下两个bean
 * 1. {@link WebSecurityConfig#defaultSecurityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity, org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)}
 * 2. {@link AuthorizationServerSecurityAutoConfiguration#jwtDecoder(com.nimbusds.jose.jwk.source.JWKSource)}
 * 导致 {@link OAuth2ResourceServerAutoConfiguration} 配置里面的 bean基本全部生效，这里主要是自主配置将其生效，旨在可以同时兼容资源服务器和授权服务器同时启动
 * 说明：这也说明了默认情况下，spring security 是对资源服务器和授权服务器分开部署提供了支持
 * </p>
 *
 * @author yangwei
 * @since 2023-08-09 18:07:19
 */
@AutoConfigureAfter(OAuth2ResourceServerAutoConfiguration.class)
@Configuration
@ConditionalOnProperty(prefix = GlobalSecurityProperties.prefix + ".resource-server-authorization-server-combine", name = "enabled", havingValue = "true", matchIfMissing = false)
public class ResourceServerAuthorizationServerCombineSecurityAutoConfiguration {


	/**
	 * 参见 {@link org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.Oauth2ResourceServerConfiguration.JwtConfiguration}
	 * 来源于 {@link org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerJwtConfiguration.OAuth2SecurityFilterChainConfiguration#jwtSecurityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity)}
	 * 注意顺序，必须放在 {@link WebSecurityConfig#defaultSecurityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity, org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)} 之前和 {@link AuthorizationServerSecurityAutoConfiguration#authorizationServerSecurityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity)} 之后
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Order(WebSecurityConfig.defaultSecurityFilterChainOrder - 5)
	@Bean
	@ConditionalOnBean(JwtDecoder.class)
	SecurityFilterChain jwtSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests((requests) -> requests.anyRequest().permitAll());
		http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
		additionalSetting(http);
		return http.build();
	}

	/**
	 * 参考：https://blog.csdn.net/u013887008/article/details/124567095
	 * 参见 {@link org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.Oauth2ResourceServerConfiguration.OpaqueTokenConfiguration}
	 * 来源于 {@link org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerOpaqueTokenConfiguration.OAuth2SecurityFilterChainConfiguration#opaqueTokenSecurityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity)}
	 * 注意顺序，必须放在 {@link WebSecurityConfig#defaultSecurityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity, org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)} 之前和 {@link AuthorizationServerSecurityAutoConfiguration#authorizationServerSecurityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity)} 之后
	 * OpaqueTokenIntrospector bean 在这里有定义 {@link org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerOpaqueTokenConfiguration.OpaqueTokenIntrospectionClientConfiguration#opaqueTokenIntrospector(org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties)}
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Order(WebSecurityConfig.defaultSecurityFilterChainOrder - 5)
	@Bean
	@ConditionalOnBean(OpaqueTokenIntrospector.class)
	SecurityFilterChain opaqueTokenSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests((requests) -> requests.anyRequest().permitAll());
		http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::opaqueToken);

		additionalSetting(http);


		return http.build();
	}

	/**
	 * 额外设置，主要是去掉不使用的过滤器
	 * @param http
	 * @throws Exception
	 */
	private void additionalSetting(HttpSecurity http)  throws Exception {
		http.csrf(httpSecurityCsrfConfigurer -> {
			httpSecurityCsrfConfigurer.disable();
		});
		//http.anonymous().disable();
		http.logout().disable();
		http.formLogin().disable();
		http.rememberMe().disable();
		http.saml2Login().disable();
		http.x509().disable();
		http.saml2Login().disable();
		http.cors().disable();
		http.servletApi().disable();
		http.jee().disable();
		http.httpBasic().disable();
		http.portMapper().disable();
		//http.oauth2Client().disable();
		http.oauth2Login().disable();
	}
}

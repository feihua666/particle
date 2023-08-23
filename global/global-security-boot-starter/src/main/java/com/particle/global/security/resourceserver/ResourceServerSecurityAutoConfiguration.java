package com.particle.global.security.resourceserver;

import com.particle.global.security.GlobalSecurityProperties;
import com.particle.global.security.security.config.CustomWebSecurityConfigure;
import com.particle.global.security.security.config.CustomWebSecurityConfigureExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector;

/**
 * <p>
 * 资源服务自动配置
 * </p>
 *
 * @author yangwei
 * @since 2023-08-03 16:42
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = GlobalSecurityProperties.prefix + ".resource-server", name = "enabled", havingValue = "true", matchIfMissing = true)
public class ResourceServerSecurityAutoConfiguration {

	/**
	 * 不添加 scope_ 前缀
	 */
	public static final String scope_prefix = "";

	/**
	 * 支持jwt相关配置，主要是去掉 scope_ 权限前缀
	 * 本来该 bean 放在{@link ResourceServerAuthorizationServerCombineSecurityAutoConfiguration}中的，但发现多个 SecurityFilterChain 如果请求匹配相同，只能排序靠前的生效，这个单独提出来
	 * 并将多个 SecurityFilterChain 合并成一个，所以{@link ResourceServerAuthorizationServerCombineSecurityAutoConfiguration}基本上就用不到了，但保留以供参考
	 * @return
	 */
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {

		/**
		 * 从 JWT 中解析的权限信息不加前缀，但opaqueToken还是写死了加了前缀参见：{@link SpringOpaqueTokenIntrospector}
		 */
		JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		grantedAuthoritiesConverter.setAuthorityPrefix(scope_prefix);

		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
		return jwtAuthenticationConverter;
	}

	@Configuration
	public static class CustomWebSecurityConfigureConfiguration{

		@Bean
		public CustomWebSecurityConfigureForResourceServer customWebSecurityConfigureForResourceServe(BeanFactory beanFactory){
			return new CustomWebSecurityConfigureForResourceServer(true,true,beanFactory);
		}
	}

	/**
	 * jwt 和 opaqueToken 不能同时配置否则报错，参见：{@link OAuth2ResourceServerConfigurer#validateConfiguration()}
	 */
	public static class CustomWebSecurityConfigureForResourceServer implements CustomWebSecurityConfigure{

		@Value("${" + GlobalSecurityProperties.prefix + ".oauth2.resourceserver.jwt.enable:false}")
		private Boolean isJwt;
		/**
		 * 默认开启 opaqueToken，因为使用 jwt，由于权限太多，token 太长
		 */
		@Value("${" + GlobalSecurityProperties.prefix + ".oauth2.resourceserver.opaquetoken.enable:true}")
		private Boolean isOpaqueToken;
		private BeanFactory beanFactory;

		public CustomWebSecurityConfigureForResourceServer(Boolean isJwt, Boolean isOpaqueToken, BeanFactory beanFactory) {
			this.isJwt = isJwt;
			this.isOpaqueToken = isOpaqueToken;
			this.beanFactory = beanFactory;
		}

		@Override
		public void configure(HttpSecurity http, AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder, CustomWebSecurityConfigureExt ext) throws Exception {
			if (isJwt || isOpaqueToken) {
				http.oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> {
					if (isJwt && existBean(JwtDecoder.class,beanFactory)) {
						httpSecurityOAuth2ResourceServerConfigurer.jwt();
					}
					if (isOpaqueToken && existBean(OpaqueTokenIntrospector.class,beanFactory)) {
						httpSecurityOAuth2ResourceServerConfigurer.opaqueToken();
					}

				});
			}

		}
	}

	/**
	 * 检查bean是否存在
	 * @param requiredType
	 * @param beanFactory
	 * @return
	 */
	private static boolean existBean(Class requiredType,BeanFactory beanFactory) {
		Object bean = null;
		try {
			bean = beanFactory.getBean(requiredType);
		} catch (BeansException e) {
			log.warn("try get bean by type {} but error.this may not effect because only a check",requiredType.getName(),e);
		}
		return bean != null;
	}
}

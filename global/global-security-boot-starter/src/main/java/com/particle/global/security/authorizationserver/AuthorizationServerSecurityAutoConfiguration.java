package com.particle.global.security.authorizationserver;

import cn.hutool.core.collection.CollectionUtil;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.particle.global.security.GlobalSecurityProperties;
import com.particle.global.security.security.config.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * <p>
 * 授权服务自动配置
 * </p>
 *
 * @author yangwei
 * @since 2023-07-07 16:13
 */
@AutoConfigureAfter(AuthorizationServerSecurityServiceAndRepositoryAutoConfiguration.class)
@Configuration
@ConditionalOnProperty(prefix = GlobalSecurityProperties.prefix + ".authorization-server", name = "enabled", havingValue = "true", matchIfMissing = true)
public class AuthorizationServerSecurityAutoConfiguration {

	@Autowired
	private JwtAuthenticationConverter jwtAuthenticationConverter;

	/**
	 * 根据官方文档，该配置排序需要比{@link WebSecurityConfig#defaultSecurityFilterChain(HttpSecurity, org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)} 靠前
	 * @param http 注意：该实例和{@link WebSecurityConfig#defaultSecurityFilterChain(HttpSecurity, org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)} 不是一个实例
	 * @return
	 * @throws Exception
	 */
	@Bean
	@Order(WebSecurityConfig.defaultSecurityFilterChainOrder - 10)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
			throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
				.oidc(Customizer.withDefaults());	// Enable OpenID Connect 1.0
		http
				// Redirect to the login page when not authenticated from the
				// authorization endpoint
				.exceptionHandling((exceptions) -> exceptions
						.defaultAuthenticationEntryPointFor(
								new LoginUrlAuthenticationEntryPoint(WebSecurityConfig.login_page_url),
								new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
						)
				)
				// Accept access tokens for User Info and/or Client Registration
				.oauth2ResourceServer((resourceServer) -> resourceServer
						.jwt(Customizer.withDefaults()));

		return http.build();
	}

	@Bean
	public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
		return (context) -> {
			if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
				Set<String> scopes = new HashSet<>();
				RegisteredClient registeredClient = context.getRegisteredClient();
				if (registeredClient != null && CollectionUtil.isNotEmpty(registeredClient.getScopes())) {
					scopes.addAll(registeredClient.getScopes());
				}
				Set<String> authorizedScopes = context.getAuthorizedScopes();
				if (CollectionUtil.isNotEmpty(authorizedScopes)) {
					scopes.addAll(authorizedScopes);
				}
				context.getClaims().claims((claims) -> {
					/**
					 * jwt 支持两种key，scope和scp，但opaqueToken仅支持scope
					 * jwt 在解析的时候，兼容两种类型，数组和字符串，如果是字符串必须以空格分隔 参见 {@link JwtGrantedAuthoritiesConverter#getAuthorities(org.springframework.security.oauth2.jwt.Jwt)}
					 * opaqueToken 解析参见：{@link SpringOpaqueTokenIntrospector#convertClaimsSet(java.util.Map)}
 					 */
					claims.put("scope", scopes);
				});
			}
		};
	}

	/**
	 * 使用 rsa 对 token 加签验签
	 * 注意：这里是应用启动后自动生成，意味着之前生成的token将会生效，但这也不影响，重新获取一次token即可
	 * 但也有解决方案就是将公钥与密钥配置化，配置化后保持一直不变，但也有可能泄露，每次重启就重置密钥也不失为一种好办法
	 * @return
	 */
	@Bean
	public JWKSource<SecurityContext> jwkSource() {
		KeyPair keyPair = generateRsaKey();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		RSAKey rsaKey = new RSAKey.Builder(publicKey)
				.privateKey(privateKey)
				.keyID(UUID.randomUUID().toString())
				.build();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return new ImmutableJWKSet<>(jwkSet);
	}

	/**
	 * 生成raskey
	 * @return
	 */
	private static KeyPair generateRsaKey() {
		KeyPair keyPair;
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.generateKeyPair();
		}
		catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
		return keyPair;
	}

	/**
	 * 注意：在资源服务器{@link org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerJwtConfiguration.JwtDecoderConfiguration}中也有同样的 jwtDecoder，如果资源服务器和授权服务器在一起启动，只能有一个
	 * 参考：{@link OAuth2ResourceServerAutoConfiguration}
	 * @param jwkSource
	 * @return
	 */
	@Bean
	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}

	@Bean
	public AuthorizationServerSettings authorizationServerSettings() {
		return AuthorizationServerSettings.builder().oidcUserInfoEndpoint("/connect/userinfo").build();
	}
}

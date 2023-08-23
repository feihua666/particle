package com.particle.global.security.authorizationserver;

import com.particle.global.security.GlobalSecurityProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * <p>
 * 配置错综复杂
 * 问题：本来没有该类，该类的配置项全部在{@link AuthorizationServerSecurityAutoConfiguration}中，但总是报找不到 {@link RegisteredClientRepository} 类型bean
 * 奇怪的是发现在原来基础上只需要添加如下依赖就能正常运行，目前没发现其对依赖的影响
 *         <dependency>
 *             <groupId>de.codecentric</groupId>
 *             <artifactId>spring-boot-admin-starter-server</artifactId>
 *         </dependency>
 * 所以这里单独提取出一个配置类 并添加 @AutoConfigureAfter(JdbcTemplateAutoConfiguration.class) 后最终生效
 * </p>
 *
 * @author yangwei
 * @since 2023-08-02 10:13
 */
@AutoConfigureAfter(JdbcTemplateAutoConfiguration.class)
@ConditionalOnBean(JdbcTemplate.class)
@Configuration
@ConditionalOnProperty(prefix = GlobalSecurityProperties.prefix + ".authorization-server", name = "enabled", havingValue = "true", matchIfMissing = true)
public class AuthorizationServerSecurityServiceAndRepositoryAutoConfiguration {

	/**
	 * 这里使用jdbc，因为使用内存存储意义不大
	 * @param jdbcTemplate
	 * @return
	 */
	@ConditionalOnBean(JdbcTemplate.class)
	@Bean
	public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
		return new CustomJdbcRegisteredClientRepository(jdbcTemplate);
	}
	@ConditionalOnBean(JdbcTemplate.class)
	@Bean
	public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate,
														   RegisteredClientRepository registeredClientRepository) {
		return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
	}

	@ConditionalOnBean(JdbcTemplate.class)
	@Bean
	public JdbcOAuth2AuthorizationConsentService jdbcOAuth2AuthorizationConsentService(JdbcTemplate jdbcTemplate,
																					   RegisteredClientRepository registeredClientRepository) {
		// Will be used by the ConsentController
		return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);

	}
}

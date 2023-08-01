package com.particle.global.openapi;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 全局开放接口自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-08-01 14:43
 */
@Configuration
@EnableConfigurationProperties({GlobalOpenapiProperties.class})
@ConditionalOnProperty(prefix = "particle.openapi", name = "enabled", havingValue = "true", matchIfMissing = true)
public class GlobalOpenapiAutoConfiguration {
}

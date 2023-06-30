package com.particle.global.actuator.bootadmin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * spring boot admin server 自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-06-29 17:47
 */
@Configuration
@EnableAdminServer
@ConditionalOnClass(EnableAdminServer.class)
@ConditionalOnProperty(prefix = "particle.actuator.bootadmin.server", name = "enabled", havingValue = "true", matchIfMissing = true)
public class GlobalSpringBootAdminServerAutoConfiguration {
}

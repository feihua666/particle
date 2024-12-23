package com.particle.global.actuator.bootadmin.server;

import de.codecentric.boot.admin.client.config.SpringBootAdminClientEnabledCondition;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import de.codecentric.boot.admin.server.config.SpringBootAdminServerEnabledCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>
 * spring boot admin server 自动配置类
 * server 可以使用 -Dspring.boot.admin.server.enabled=false 关闭，参见 {@link SpringBootAdminServerEnabledCondition#getMatchOutcome(ConditionContext, AnnotatedTypeMetadata)}
 * server 同时也可以使用 -Dparticle.actuator.bootadmin.server.enabled=false 关闭,因为 {@link EnableAdminServer} 会不生效
 * client 可以使用 -Dspring.boot.admin.client.enabled=false 关闭，参见 {@link SpringBootAdminClientEnabledCondition#getMatchOutcome(ConditionContext, AnnotatedTypeMetadata)}
 * </p>
 *
 * @author yangwei
 * @since 2023-06-29 17:47
 */
@Configuration(proxyBeanMethods = false)
@EnableAdminServer
@ConditionalOnClass(EnableAdminServer.class)
@ConditionalOnProperty(prefix = "particle.actuator.bootadmin.server", name = "enabled", havingValue = "true", matchIfMissing = true)
public class GlobalSpringBootAdminServerAutoConfiguration {
}

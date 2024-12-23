package com.particle.global.messaging;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.binder.test.EnableTestBinder;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 添加一个配置类，如果使用单体单机，默认使用单体单机，不依赖三方组件，但不影响编程方式
 * </p>
 *
 * @author yangwei
 * @since 2023-05-19 17:39
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "particle.message.testBinder", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableTestBinder
public class GlobalMessageTestBinderAutoConfiguration {
}

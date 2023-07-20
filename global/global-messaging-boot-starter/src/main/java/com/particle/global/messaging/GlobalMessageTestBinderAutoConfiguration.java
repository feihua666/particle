package com.particle.global.messaging;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * <p>
 * 添加一个配置类，如果使用单体单机，默认使用单体单机，不依赖三方组件，但不影响编程方式
 * particle.message.testBinder 中的 testBinder是延用了 spring-cloud-stream-test-binder 后缀名称，本组件其实已经定义成了名称为 local的binder
 * 关于local的名称详见：META-INF/spring.binders
 * </p>
 *
 * @author yangwei
 * @since 2023-05-19 17:39
 */
@ConditionalOnProperty(prefix = "particle.message.testBinder", name = "enabled", havingValue = "true", matchIfMissing = true)
//@Import(LocalChannelBinderConfiguration.class)
public class GlobalMessageTestBinderAutoConfiguration {
}

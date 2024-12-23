package com.particle.global.ratelimit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 全局限流器自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-08-05 16:19
 */
@ComponentScan
@Configuration(proxyBeanMethods = false)
public class GlobalRatelimitAutoConfiguration {
}

package com.particle.global.wxjava;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-06-28 16:49
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan("com.particle.global.wxjava.advice")
public class GlobalWxJavaAutoConfiguration {
}

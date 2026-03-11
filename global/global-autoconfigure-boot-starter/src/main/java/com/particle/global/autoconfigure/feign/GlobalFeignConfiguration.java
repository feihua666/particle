package com.particle.global.autoconfigure.feign;

import feign.Capability;
import feign.Client;
import feign.RequestInterceptor;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026-03-07 17:22:40
 */
@Configuration(proxyBeanMethods = false)
public class GlobalFeignConfiguration {

    @Configuration
    @ConditionalOnClass(value = RequestInterceptor.class)
    public static class TenantFeignInterceptorConfig{
        @Bean
        public GlobalFeignInterceptor tenantFeignInterceptor(){
            return new GlobalFeignInterceptor();
        }
    }
    @Configuration
    @ConditionalOnClass(value = {
            Client.class,
            MicrometerCapability.class,
            MeterRegistry.class
    })
    public class FeignTracingConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public Capability micrometerCapability(MeterRegistry registry) {
            return new MicrometerCapability(registry);
        }
    }
}

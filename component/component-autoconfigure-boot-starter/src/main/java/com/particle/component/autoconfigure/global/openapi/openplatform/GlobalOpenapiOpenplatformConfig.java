package com.particle.component.autoconfigure.global.openapi.openplatform;

import com.particle.global.openapi.api.GlobalOpenapiApiInfoProvider;
import com.particle.global.openapi.api.GlobalOpenapiClientProvider;
import com.particle.global.openapi.api.GlobalOpenapiCollectPersistentService;
import com.particle.global.openapi.api.limitrule.IGlobalOpenapiRequestLimitDataProvider;
import com.particle.openplatform.adapter.feign.client.globalopenapi.rpc.OpenplatformGlobalJdbcOpenapiClientRpcFeignClient;
import com.particle.openplatform.adapter.feign.client.globalopenapi.rpc.OpenplatformGlobalOpenapiApiInfoRpcFeignClient;
import com.particle.openplatform.adapter.feign.client.globalopenapi.rpc.OpenplatformGlobalOpenapiCollectPersistentServiceRpcFeignClient;
import com.particle.openplatform.adapter.feign.client.globalopenapi.rpc.OpenplatformGlobalOpenapiRequestLimitDataRpcFeignClient;
import com.particle.openplatform.adapter.globalopenapi.OpenPlatformGlobalOpenapiApiInfoProviderImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/3/9 19:35
 */
@Configuration(proxyBeanMethods = false)
public class GlobalOpenapiOpenplatformConfig {

    /**
     * 依赖开放平台组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({OpenplatformGlobalOpenapiApiInfoRpcFeignClient.class, GlobalOpenapiApiInfoProvider.class})
    @ConditionalOnMissingClass("com.particle.openplatform.adapter.globalopenapi.OpenPlatformGlobalOpenapiApiInfoProviderImpl")
    public static class OpenPlatformOpenapiApiInfoRpcDependConfig {
        @Bean
        // @ConditionalOnMissingBean(GlobalOpenapiApiInfoProvider.class)
        @ConditionalOnBean(OpenplatformGlobalOpenapiApiInfoRpcFeignClient.class)
        public OpenPlatformOpenapiApiInfoRpcProvider openPlatformOpenapiApiInfoRpcProvider(){
            return new OpenPlatformOpenapiApiInfoRpcProvider();
        }
    }

    /**
     * 依赖开放平台组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({OpenplatformGlobalJdbcOpenapiClientRpcFeignClient.class, GlobalOpenapiClientProvider.class})
    @ConditionalOnMissingClass("com.particle.openplatform.adapter.globalopenapi.OpenplatformGlobalJdbcOpenapiClientProviderImpl")
    public static class OpenPlatformOpenapiClientRpcDependConfig {
        @Bean
        // @ConditionalOnMissingBean(GlobalOpenapiClientProvider.class)
        @ConditionalOnBean(OpenplatformGlobalJdbcOpenapiClientRpcFeignClient.class)
        public OpenplatformJdbcOpenapiClientRpcProvider openplatformJdbcOpenapiClientRpcProvider(){
            return new OpenplatformJdbcOpenapiClientRpcProvider();
        }
    }
    /**
     * 依赖开放平台组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({OpenplatformGlobalOpenapiRequestLimitDataRpcFeignClient.class, IGlobalOpenapiRequestLimitDataProvider.class})
    @ConditionalOnMissingClass("com.particle.openplatform.adapter.globalopenapi.OpenplatformGlobalOpenapiRequestLimitDataProviderImpl")
    public static class OpenPlatformOpenapiRequestLimitDataRpcDependConfig {
        @Bean
        // @ConditionalOnMissingBean(IGlobalOpenapiRequestLimitDataProvider.class)
        @ConditionalOnBean(OpenplatformGlobalOpenapiRequestLimitDataRpcFeignClient.class)
        public GlobalOpenapiRequestLimitDataRpcProvider globalOpenapiRequestLimitDataRpcProvider(){
            return new GlobalOpenapiRequestLimitDataRpcProvider();
        }
    }
    /**
     * 依赖开放平台组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({OpenplatformGlobalOpenapiCollectPersistentServiceRpcFeignClient.class, GlobalOpenapiCollectPersistentService.class})
    @ConditionalOnMissingClass("com.particle.openplatform.adapter.globalopenapi.OpenplatformGlobalOpenapiCollectPersistentServiceImpl")
    public static class OpenPlatformOpenapiCollectPersistentRpcDependConfig {
        @Bean
        // @ConditionalOnMissingBean(GlobalOpenapiCollectPersistentService.class)
        @ConditionalOnBean(OpenplatformGlobalOpenapiCollectPersistentServiceRpcFeignClient.class)
        public GlobalOpenapiCollectPersistentServiceRpcProvider globalOpenapiCollectPersistentServiceRpcProvider(){
            return new GlobalOpenapiCollectPersistentServiceRpcProvider();
        }
    }
}

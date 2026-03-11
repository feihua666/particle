package com.particle.component.autoconfigure.global.webfilter.tenant;

import com.particle.global.web.filter.*;
import com.particle.tenant.adapter.feign.client.rpc.TenantRpcFeignClient;
import com.particle.tenant.infrastructure.service.ITenantService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * <p>
 * global web filter 依赖租户 组件配置
 * </p>
 *
 * @author yangwei
 * @since 2026-03-08 19:36:28
 */
@Configuration(proxyBeanMethods = false)
public class GlobalWebfilterTenantConfig {
    /**
     * 租户配置 租户id Rpc 解析 配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({TenantContextFilter.TenantIdResolver.class,TenantRpcFeignClient.class})
    public static class TenantIdRpcResolveConfig {

        /**
         * 解析租户id
         * @return
         */
        @Bean
        @ConditionalOnBean(TenantRpcFeignClient.class)
        public TenantContextFilter.TenantIdResolver tenantIdRpcResolverImpl(){
            return new TenantIdRpcResolverImpl();
        }
    }
    /**
     * 租户配置 租户id 本地解析 配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({TenantContextFilter.TenantIdResolver.class,ITenantService.class})
    public static class TenantIdLocalResolveConfig {

        /**
         * 解析租户id
         * @return
         */
        @Bean
        @Primary
        @ConditionalOnBean(ITenantService.class)
        public TenantContextFilter.TenantIdResolver tenantIdLocalResolverImpl(){
            return new TenantIdLocalResolverImpl();
        }
    }




    /**
     * 租户配置 图标配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({FaviconFilter.class,TenantRpcFeignClient.class})
    public static class TenantFaviconConfig {

        /**
         * 图标
         * @return
         */
        @Bean
        @ConditionalOnBean(TenantRpcFeignClient.class)
        public TenantFaviconResolver tenantFaviconResolver(){
            return new TenantFaviconResolver();
        }
    }
    /**
     * 租户配置 logo 配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({LogoFilter.class,TenantRpcFeignClient.class})
    public static class TenantLogoConfig {

        /**
         * logo
         * @return
         */
        @Bean
        @ConditionalOnBean(TenantRpcFeignClient.class)
        public TenantLogoResolver tenantLogoResolver(){
            return new TenantLogoResolver();
        }
    }
    /**
     * 租户配置 logo 文本配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({LogoTextFilter.class,TenantRpcFeignClient.class})
    public static class TenantLogoTextConfig {

        /**
         * logo 文本
         * @return
         */
        @Bean
        @ConditionalOnBean(TenantRpcFeignClient.class)
        public TenantLogoTextResolver tenantLogoTextResolver(){
            return new TenantLogoTextResolver();
        }
    }
    /**
     * 租户配置 标题 配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({WebTitleFilter.class,TenantRpcFeignClient.class})
    public static class TenantWebTitleConfig {

        /**
         * 标题
         * @return
         */
        @Bean
        @ConditionalOnBean(TenantRpcFeignClient.class)
        public TenantWebTitleResolver tenantWebTitleResolver(){
            return new TenantWebTitleResolver();
        }
    }
}

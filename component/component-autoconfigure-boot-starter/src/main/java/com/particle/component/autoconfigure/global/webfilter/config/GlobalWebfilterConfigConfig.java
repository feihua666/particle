package com.particle.component.autoconfigure.global.webfilter.config;

import com.particle.config.adapter.feign.client.system.rpc.SystemConfigRpcFeignClient;
import com.particle.global.web.filter.FaviconFilter;
import com.particle.global.web.filter.LogoFilter;
import com.particle.global.web.filter.LogoTextFilter;
import com.particle.global.web.filter.WebTitleFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * global web filter 依赖配置 相关配置
 * </p>
 *
 * @author yangwei
 * @since 2026/1/30 16:04
 */
@Configuration(proxyBeanMethods = false)
public class GlobalWebfilterConfigConfig {

    /**
     * 系统配置 图标配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({FaviconFilter.class,SystemConfigRpcFeignClient.class})
    public static class SystemConfigFaviconConfig {

        /**
         * 图标
         * @return
         */
        @Bean
        @ConditionalOnBean(SystemConfigRpcFeignClient.class)
        public SystemConfigFaviconResolver systemConfigFaviconResolver(){
            return new SystemConfigFaviconResolver();
        }
    }
    /**
     * 系统配置 logo 配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({LogoFilter.class,SystemConfigRpcFeignClient.class})
    public static class SystemConfigLogoConfig {

        /**
         * logo
         * @return
         */
        @Bean
        @ConditionalOnBean(SystemConfigRpcFeignClient.class)
        public SystemConfigLogoResolver systemConfigLogoResolver(){
            return new SystemConfigLogoResolver();
        }
    }
    /**
     * 系统配置 logo 文本配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({LogoTextFilter.class,SystemConfigRpcFeignClient.class})
    public static class SystemConfigLogoTextConfig {

        /**
         * logo 文本
         * @return
         */
        @Bean
        @ConditionalOnBean(SystemConfigRpcFeignClient.class)
        public SystemConfigLogoTextResolver systemConfigLogoTextResolver(){
            return new SystemConfigLogoTextResolver();
        }
    }
    /**
     * 系统配置 标题 配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({WebTitleFilter.class,SystemConfigRpcFeignClient.class})
    public static class SystemConfigWebTitleConfig {

        /**
         * 标题
         * @return
         */
        @Bean
        @ConditionalOnBean(SystemConfigRpcFeignClient.class)
        public SystemConfigWebTitleResolver systemConfigWebTitleResolver(){
            return new SystemConfigWebTitleResolver();
        }
    }
}

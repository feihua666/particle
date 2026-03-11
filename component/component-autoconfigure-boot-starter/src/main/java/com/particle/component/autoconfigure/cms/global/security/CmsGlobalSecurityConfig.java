package com.particle.component.autoconfigure.cms.global.security;

import com.particle.cms.adapter.dynamic.service.ICmsPermissionService;
import com.particle.global.security.security.SecurityPermissionService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * cms 依赖 global security 组件配置
 * </p>
 *
 * @author yangwei
 * @since 2026/1/20 14:30
 */
@Configuration(proxyBeanMethods = false)
public class CmsGlobalSecurityConfig {

    /**
     * 依赖 权限 组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({SecurityPermissionService.class,ICmsPermissionService.class})
    public static class CmsPermissionConfig {
        @Bean
        @ConditionalOnBean({ SecurityPermissionService.class})
        public CmsPermissionServiceImpl cmsPermissionServiceImpl(){
            return new CmsPermissionServiceImpl();
        }
    }
}

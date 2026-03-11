package com.particle.component.autoconfigure.user.tenant;

import com.particle.tenant.adapter.feign.client.rpc.TenantUserRpcFeignClient;
import com.particle.user.adapter.rpc.UserTransOverrideService;
import com.particle.user.adapter.rpc.UserTransServiceImpl;
import com.particle.user.infrastructure.service.IUserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 用户依赖租户组件配置
 * </p>
 *
 * @author yangwei
 * @since 2025/12/29 19:31
 */
@Configuration(proxyBeanMethods = false)
public class UserTenantConfig {
    /**
     * 租户组件配置
     */
    @Configuration(proxyBeanMethods = true)
    @ConditionalOnClass({IUserService.class, TenantUserRpcFeignClient.class})
    public static class TenantDependConfig{

        /**
         * 用户添加时使用，用户添加时，默认也添加到租户
         * @return
         */
        @Bean
        @ConditionalOnBean({ TenantUserRpcFeignClient.class})
        public TenantUserUserAddServiceListenerImpl tenantUserUserAddServiceListener() {
            return new TenantUserUserAddServiceListenerImpl();
        }

    }


    /**
     * 用户翻译使用依赖
     * 暂时注释掉，没想到意义是什么
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({UserTransServiceImpl.class,TenantUserRpcFeignClient.class})
    public static class UserTransConfig{
        @Bean
        @ConditionalOnBean({ TenantUserRpcFeignClient.class})
        public UserTransOverrideService userTransOverrideService(){
            return new UserTransOverrideServiceImpl();
        }

    }
}

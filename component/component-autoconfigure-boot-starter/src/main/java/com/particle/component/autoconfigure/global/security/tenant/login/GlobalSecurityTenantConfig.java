package com.particle.component.autoconfigure.global.security.tenant.login;

import com.particle.global.security.tenant.SecurityUserTenantService;
import com.particle.tenant.adapter.feign.client.rpc.TenantRpcFeignClient;
import com.particle.tenant.adapter.feign.client.rpc.TenantUserRpcFeignClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 租户组件配置
 * </p>
 *
 * @author yangwei
 * @since 2025/12/29 08:29
 */
@Configuration(proxyBeanMethods = false)
public class GlobalSecurityTenantConfig {

    /**
     * 依赖租户组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({SecurityUserTenantService.class, TenantRpcFeignClient.class})
    public static class TenantDependConfig{

        /**
         * 登录时使用，获取用户的租户信息
         * @return
         */
        @Bean
        @ConditionalOnBean({ TenantRpcFeignClient.class, TenantUserRpcFeignClient.class})
        public SecurityUserTenantService userTenantService(){
            return new SecurityUserTenantServiceImpl();
        }
    }


}

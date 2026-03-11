package com.particle.component.autoconfigure.tenant.role;

import com.particle.role.adapter.feign.client.roleuserrel.rpc.RoleUserRelRpcFeignClient;
import com.particle.tenant.infrastructure.service.ITenantUserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 租户依赖角色组件配置
 * </p>
 *
 * @author yangwei
 * @since 2025/12/29 08:29
 */
@Configuration(proxyBeanMethods = false)
public class TenantRoleConfig {

    /**
     * 依赖角色组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({ITenantUserService.class,RoleUserRelRpcFeignClient.class})
    public static class RoleDependConfig{
        @Bean
        @ConditionalOnBean(RoleUserRelRpcFeignClient.class)
        public RoleTenantUserServiceListener roleTenantUserServiceListener(){
            return new RoleTenantUserServiceListener();
        }
    }

}

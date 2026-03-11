package com.particle.component.autoconfigure.user.role;

import com.particle.role.adapter.feign.client.roleuserrel.rpc.RoleUserRelRpcFeignClient;
import com.particle.role.adapter.feign.client.rpc.RoleRpcFeignClient;
import com.particle.role.infrastructure.service.IRoleService;
import com.particle.user.infrastructure.dos.UserDO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 用户依赖角色组件配置
 * </p>
 *
 * @author yangwei
 * @since 2025/12/29 19:31
 */
@Configuration(proxyBeanMethods = false)
public class UserRoleConfig {
    /**
     * 依赖角色组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({IRoleService.class, RoleUserRelRpcFeignClient.class,RoleRpcFeignClient.class, UserDO.class})
    public static class RoleDependConfig {
        @Bean
        @ConditionalOnBean({ RoleUserRelRpcFeignClient.class,RoleRpcFeignClient.class})
        public RoleUserServiceListenerImpl roleUserServiceListener(){
            return new RoleUserServiceListenerImpl();
        }
    }

}

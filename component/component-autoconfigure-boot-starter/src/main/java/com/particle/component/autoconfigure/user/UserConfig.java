package com.particle.component.autoconfigure.user;

import com.particle.dept.adapter.feign.client.deptuserrel.rpc.DeptUserRelRpcFeignClient;
import com.particle.role.adapter.feign.client.roleuserrel.rpc.RoleUserRelRpcFeignClient;
import com.particle.role.adapter.feign.client.rpc.RoleRpcFeignClient;
import com.particle.role.infrastructure.service.IRoleService;
import com.particle.tenant.adapter.feign.client.rpc.TenantUserRpcFeignClient;
import com.particle.user.infrastructure.service.IUserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 用户组件配置
 * </p>
 *
 * @author yangwei
 * @since 2025/12/29 19:31
 */
@Configuration(proxyBeanMethods = false)
public class UserConfig {
    /**
     * 依赖部门组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({IUserService.class, DeptUserRelRpcFeignClient.class})
    public static class DeptDependConfig {
        @Bean
        @ConditionalOnBean({ DeptUserRelRpcFeignClient.class})
        public DeptUserServiceListener deptUserServiceListener(){
            return new DeptUserServiceListener();
        }
    }
    /**
     * 依赖角色组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({IRoleService.class, RoleUserRelRpcFeignClient.class,RoleRpcFeignClient.class})
    public static class RoleDependConfig {
        @Bean
        @ConditionalOnBean({ RoleUserRelRpcFeignClient.class,RoleRpcFeignClient.class})
        public RoleUserServiceListener roleUserServiceListener(){
            return new RoleUserServiceListener();
        }
    }
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
        public TenantUserUserAddServiceListener tenantUserUserAddServiceListener() {
            return new TenantUserUserAddServiceListener();
        }

    }


    /**
     * 用户翻译使用依赖
     * 暂时注释掉，没想到意义是什么
     */
    /*@Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({UserTransServiceImpl.class,TenantUserRpcFeignClient.class})
    public static class UserTransConfig{
        @Bean
        @ConditionalOnBean({ TenantUserRpcFeignClient.class})
        public UserTransOverrideService userTransOverrideService(){
            return new UserTransOverrideServiceImpl();
        }

    }*/
}

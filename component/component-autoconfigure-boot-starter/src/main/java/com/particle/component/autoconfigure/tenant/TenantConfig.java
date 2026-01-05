package com.particle.component.autoconfigure.tenant;

import com.particle.component.autoconfigure.tenant.login.TenantResolveServiceImpl;
import com.particle.component.autoconfigure.tenant.login.UserTenantServiceImpl;
import com.particle.dept.adapter.feign.client.deptuserrel.rpc.DeptUserRelRpcFeignClient;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.tenant.ITenantResolveService;
import com.particle.global.security.tenant.UserTenantService;
import com.particle.role.adapter.feign.client.roleuserrel.rpc.RoleUserRelRpcFeignClient;
import com.particle.tenant.adapter.feign.client.rpc.TenantRpcFeignClient;
import com.particle.tenant.adapter.feign.client.rpc.TenantUserRpcFeignClient;
import com.particle.tenant.infrastructure.service.ITenantService;
import com.particle.tenant.infrastructure.service.ITenantUserService;
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
public class TenantConfig {

    /**
     * 依赖部门组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({ITenantService.class,DeptUserRelRpcFeignClient.class})
    public static class DeptDependConfig {
        @Bean
        @ConditionalOnBean({ DeptUserRelRpcFeignClient.class})
        public DeptTenantUserServiceListener deptTenantUserServiceListener(){
            return new DeptTenantUserServiceListener();
        }
    }
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

    /**
     * 依赖租户组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({LoginUserTool.class, TenantRpcFeignClient.class})
    public static class TenantDependConfig{
        /**
         * 登录时使用，租户信息处理逻辑
         * @return
         */
        @Bean
        @ConditionalOnBean(TenantRpcFeignClient.class)
        public ITenantResolveService tenantResolveService(){
            return new TenantResolveServiceImpl();
        }


        /**
         * 登录时使用，获取用户的租户信息
         * @return
         */
        @Bean
        @ConditionalOnBean({ TenantRpcFeignClient.class, TenantUserRpcFeignClient.class})
        public UserTenantService userTenantService(){
            return new UserTenantServiceImpl();
        }


    }
}

package com.particle.component.autoconfigure.user.login;

import com.particle.dept.adapter.feign.client.rpc.DeptRpcFeignClient;
import com.particle.func.adapter.feign.client.rpc.FuncRpcFeignClient;
import com.particle.global.security.security.login.UserAuthorityService;
import com.particle.global.security.security.login.UserDeptService;
import com.particle.role.adapter.feign.client.rolefuncrel.rpc.RoleFuncRelRpcFeignClient;
import com.particle.role.adapter.feign.client.rpc.RoleRpcFeignClient;
import com.particle.tenant.adapter.feign.client.tenantfunc.rpc.TenantFuncRpcFeignClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 用户登录权限相关配置
 * </p>
 *
 * @author yangwei
 * @since 2025/12/29 15:43
 */
@Configuration(proxyBeanMethods = false)
public class UserAuthorityConfig {

    /**
     * 依赖角色、功能菜单、租户（可选） 组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({FuncRpcFeignClient.class, RoleFuncRelRpcFeignClient.class})
    public static class UserFuncRetrieveConfig {
        @Bean
        @ConditionalOnBean({ FuncRpcFeignClient.class,RoleFuncRelRpcFeignClient.class})
        public UserFuncRetrieve userFuncRetrieve(ObjectProvider<TenantFuncRpcFeignClient> tenantFuncRpcFeignClientObjectProvider){
            TenantFuncRpcFeignClient tenantFuncRpcFeignClient = tenantFuncRpcFeignClientObjectProvider.getIfAvailable();
            if (tenantFuncRpcFeignClient == null) {
                return new UserFuncRetrieve();
            }else {
                return new UserFuncRetrieve.UserFuncRetrieveSub();
            }
        }
    }
    /**
     * 依赖功能菜单 和 租户 组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({RoleRpcFeignClient.class})
    public static class UserAuthorityServiceConfig {
        @Bean
        @ConditionalOnBean({ RoleRpcFeignClient.class})
        public UserAuthorityService userAuthorityService(){
            return new UserAuthorityServiceImpl();
        }
    }

    /**
     * 依赖部门组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(DeptRpcFeignClient.class)
    public static class DeptDependConfig{
        @Bean
        @ConditionalOnBean({ DeptRpcFeignClient.class})
        public UserDeptService userDeptService(){
            return new UserDeptServiceImpl();
        }
    }
}

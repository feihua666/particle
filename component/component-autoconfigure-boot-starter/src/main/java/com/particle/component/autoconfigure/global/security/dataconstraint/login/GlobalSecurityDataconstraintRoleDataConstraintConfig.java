package com.particle.component.autoconfigure.global.security.dataconstraint.login;

import com.particle.dataconstraint.adapter.feign.client.rpc.DataObjectRpcFeignClient;
import com.particle.dataconstraint.adapter.feign.client.rpc.DataScopeCustomDataRelRpcFeignClient;
import com.particle.dataconstraint.adapter.feign.client.rpc.DataScopeRpcFeignClient;
import com.particle.global.security.security.login.SecurityRoleDataConstraintService;
import com.particle.role.adapter.feign.client.roledatascoperel.rpc.RoleDataScopeRelRpcFeignClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 角色授权数据范围约束配置
 * </p>
 *
 * @author yangwei
 * @since 2025/12/26 21:35
 */

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({
        DataObjectRpcFeignClient.class,
        DataScopeRpcFeignClient.class,
        DataScopeCustomDataRelRpcFeignClient.class,
        RoleDataScopeRelRpcFeignClient.class})
public class GlobalSecurityDataconstraintRoleDataConstraintConfig {

    @Bean
    @ConditionalOnBean({
            DataObjectRpcFeignClient.class,
            DataScopeRpcFeignClient.class,
            DataScopeCustomDataRelRpcFeignClient.class,
            RoleDataScopeRelRpcFeignClient.class})
    public SecurityRoleDataConstraintService roleDataConstraintService() {
        return new SecurityRoleDataConstraintServiceImpl();
    }
}

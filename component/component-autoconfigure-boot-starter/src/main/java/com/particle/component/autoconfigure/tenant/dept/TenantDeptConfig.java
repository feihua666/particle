package com.particle.component.autoconfigure.tenant.dept;

import com.particle.dept.adapter.feign.client.deptuserrel.rpc.DeptUserRelRpcFeignClient;
import com.particle.tenant.infrastructure.service.ITenantService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 租户依赖部门 组件配置
 * </p>
 *
 * @author yangwei
 * @since 2025/12/29 08:29
 */
@Configuration(proxyBeanMethods = false)
public class TenantDeptConfig {

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

}

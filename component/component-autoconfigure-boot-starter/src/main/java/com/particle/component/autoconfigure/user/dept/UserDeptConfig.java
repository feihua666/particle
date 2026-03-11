package com.particle.component.autoconfigure.user.dept;

import com.particle.dept.adapter.feign.client.deptuserrel.rpc.DeptUserRelRpcFeignClient;
import com.particle.user.infrastructure.service.IUserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 用户依赖部门组件配置
 * </p>
 *
 * @author yangwei
 * @since 2026-03-08 20:21:06
 */
@Configuration(proxyBeanMethods = false)
public class UserDeptConfig {
    /**
     * 依赖部门组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({IUserService.class, DeptUserRelRpcFeignClient.class})
    public static class DeptDependConfig {
        @Bean
        @ConditionalOnBean({ DeptUserRelRpcFeignClient.class})
        public DeptUserServiceListenerImpl deptUserServiceListener(){
            return new DeptUserServiceListenerImpl();
        }
    }
}

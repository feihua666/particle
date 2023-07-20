package com.particle.global.security.security.config;

import com.particle.global.security.security.voter.NoAuthenticationAuthorityStrConfigVoter;
import com.particle.global.security.security.voter.SuperAdminRoleVoter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.Ordered;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * 自定义注解扩展,开启controller注解权限配置
 * 注意该bean添加了基础设施角色，因为其父类也添加了，不可避免的在 beanPostProcessor实例化时被把该类也牵扯进去了
 * 否则会报 is not eligible for getting processed by all BeanPostProcessors 之类的提示信息
 * @author yangwei
 * @since 2020/1/14 11:42
 */
@Configuration(proxyBeanMethods = true)
@EnableGlobalMethodSecurity(prePostEnabled = true,order = Ordered.HIGHEST_PRECEDENCE)
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class GlobalMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected AccessDecisionManager accessDecisionManager() {
        AbstractAccessDecisionManager accessDecisionManager = (AbstractAccessDecisionManager) super.accessDecisionManager();
        accessDecisionManager.getDecisionVoters().add(new SuperAdminRoleVoter());
        // 确保开启 @Configuration(proxyBeanMethods = true)，这样在调用配置类内部方法时
        accessDecisionManager.getDecisionVoters().add(noAuthenticationAuthorityStrConfigVoter());
        return accessDecisionManager;
    }
    @Bean
    public NoAuthenticationAuthorityStrConfigVoter noAuthenticationAuthorityStrConfigVoter() {
        return new NoAuthenticationAuthorityStrConfigVoter();
    }
}

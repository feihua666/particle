package com.particle.global.security.security.config;

import com.particle.global.security.security.voter.NoAuthenticationAuthorityStrConfigVoter;
import com.particle.global.security.security.voter.SuperAdminRoleVoter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * 自定义注解扩展,开启controller注解权限配置
 * @author yangwei
 * Created at 2020/1/14 11:42
 */
@Configuration(proxyBeanMethods = true)
@EnableGlobalMethodSecurity(prePostEnabled = true,order = Ordered.HIGHEST_PRECEDENCE)
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

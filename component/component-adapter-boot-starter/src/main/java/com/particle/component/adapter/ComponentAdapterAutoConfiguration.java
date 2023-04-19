package com.particle.component.adapter;

import com.particle.component.adapter.user.login.*;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.global.security.security.login.AbstractUserDetailsService;
import com.particle.role.infrastructure.service.IRoleService;
import com.particle.tenant.infrastructure.service.ITenantService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 组件适配器自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-08-07 18:36
 */
@Configuration
@ComponentScan
public class ComponentAdapterAutoConfiguration {

	@Bean
	@ConditionalOnBean(IRoleService.class)
	public UserAuthorityServiceImpl userAuthorityServiceImpl(){
		return new UserAuthorityServiceImpl();
	}

	@Bean
	@ConditionalOnBean({ IFuncService.class})
	public UserFuncRetrieve userFuncRetrieve(){
		return new UserFuncRetrieve();
	}

	@Bean
	@ConditionalOnBean({ ITenantService.class})
	public UserTenantServiceImpl UserTenantServiceImpl(){
		return new UserTenantServiceImpl();
	}

	@Bean
	public UserLoginTenantChangeListener userLoginTenantChangeListener(){
		return new UserLoginTenantChangeListener();
	}

	@Bean
	public TenantResolveServiceImpl tenantResolveServiceImpl(){
		return new TenantResolveServiceImpl();
	}
}

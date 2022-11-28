package com.particle.component.adapter;

import com.particle.component.adapter.user.login.UserAuthorityServiceImpl;
import com.particle.component.adapter.user.login.UserFuncRetrieve;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.role.infrastructure.service.IRoleService;
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
}

package com.particle.component.adapter;

import com.particle.component.adapter.oplog.OpLogRepositoryImpl;
import com.particle.component.adapter.user.TenantUserAddServiceListener;
import com.particle.component.adapter.user.login.*;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.oplog.infrastructure.service.IOpLogService;
import com.particle.role.infrastructure.service.IRoleService;
import com.particle.tenant.infrastructure.service.ITenantService;
import com.particle.tenant.infrastructure.service.ITenantUserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
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

	@Configuration
	@ConditionalOnClass(IRoleService.class)
	protected static class IRoleServiceDependConfig{

		/**
		 * 登录时使用，获取用户的权限数据
		 * @return
		 */
		@Bean
		@ConditionalOnBean(IRoleService.class)
		public UserAuthorityServiceImpl userAuthorityServiceImpl(){
			return new UserAuthorityServiceImpl();
		}

	}

	@Configuration
	@ConditionalOnClass(IFuncService.class)
	protected static class IFuncServiceDependConfig{

		/**
		 * 登录时使用，获取用户的功能数据
		 * @return
		 */
		@Bean
		@ConditionalOnBean({ IFuncService.class})
		public UserFuncRetrieve userFuncRetrieve(){
			return new UserFuncRetrieve();
		}

	}

	@Configuration
	@ConditionalOnClass(ITenantService.class)
	protected static class ITenantServiceDependConfig{

		/**
		 * 登录时使用，获取用户的租户信息
		 * @return
		 */
		@Bean
		@ConditionalOnBean({ ITenantService.class})
		public UserTenantServiceImpl UserTenantServiceImpl(){
			return new UserTenantServiceImpl();
		}

	}

	/**
	 * 登录时使用，用户租户切换监听
	 * @return
	 */
	@Bean
	public UserLoginTenantChangeListener userLoginTenantChangeListener(){
		return new UserLoginTenantChangeListener();
	}

	/**
	 * 登录时使用，租户信息处理逻辑
	 * @return
	 */
	@Bean
	public TenantResolveServiceImpl tenantResolveServiceImpl(){
		return new TenantResolveServiceImpl();
	}


	@Configuration
	@ConditionalOnClass(ITenantUserService.class)
	protected static class ITenantUserServiceDependConfig{
		/**
		 * 用户添加时使用，用户添加时，默认也添加到租户
		 * @return
		 */
		@Bean
		@ConditionalOnBean({ ITenantUserService.class})
		public TenantUserAddServiceListener tenantUserAddServiceListener() {
			return new TenantUserAddServiceListener();
		}

	}

	@Configuration
	@ConditionalOnClass(IOpLogService.class)
	public static class IOpLogServiceDependConfig{

		/**
		 * 操作日志持久化实现
		 * @return
		 */
		@Bean
		@ConditionalOnBean(IOpLogService.class)
		public OpLogRepositoryImpl opLogRepositoryImpl(){
			return new OpLogRepositoryImpl();
		}

	}
}

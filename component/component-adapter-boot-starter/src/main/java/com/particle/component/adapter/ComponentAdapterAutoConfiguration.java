package com.particle.component.adapter;

import com.particle.component.adapter.oplog.OpLogRepositoryImpl;
import com.particle.component.adapter.tenant.DeptTenantUserServiceListener;
import com.particle.component.adapter.tenant.RoleTenantUserAddServiceListener;
import com.particle.component.adapter.user.DeptUserServiceListener;
import com.particle.component.adapter.user.RoleUserAddServiceListener;
import com.particle.component.adapter.user.TenantUserUserAddServiceListener;
import com.particle.component.adapter.user.UserTransOverrideServiceImpl;
import com.particle.component.adapter.user.login.*;
import com.particle.dept.infrastructure.deptuserrel.service.IDeptUserRelService;
import com.particle.dept.infrastructure.service.IDeptService;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.global.security.security.login.UserDeptService;
import com.particle.oplog.infrastructure.service.IOpLogService;
import com.particle.role.infrastructure.service.IRoleService;
import com.particle.tenant.infrastructure.service.ITenantService;
import com.particle.tenant.infrastructure.service.ITenantUserService;
import com.particle.user.adapter.rpc.UserTransOverrideService;
import com.particle.user.adapter.rpc.UserTransServiceImpl;
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

	/**
	 * 角色组件配置
	 */
	@Configuration
	@ConditionalOnClass(IRoleService.class)
	protected static class RoleDependConfig{

		/**
		 * 登录时使用，获取用户的权限数据
		 * @return
		 */
		@Bean
		@ConditionalOnBean(IRoleService.class)
		public UserAuthorityServiceImpl userAuthorityServiceImpl(){
			return new UserAuthorityServiceImpl();
		}

		/**
		 * 用户添加时支持添加角色
		 * @return
		 */
		@Bean
		@ConditionalOnBean(IRoleService.class)
		public RoleUserAddServiceListener roleUserAddServiceListener(){
			return new RoleUserAddServiceListener();
		}

		@Bean
		@ConditionalOnBean(IRoleService.class)
		public RoleTenantUserAddServiceListener roleTenantUserAddServiceListener(){
			return new RoleTenantUserAddServiceListener();
		}


	}

	/**
	 * 功能组件配置
	 */
	@Configuration
	@ConditionalOnClass(IFuncService.class)
	protected static class FuncDependConfig{

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

	/**
	 * 租户组件配置
	 */
	@Configuration
	@ConditionalOnClass(ITenantService.class)
	protected static class TenantDependConfig{

		/**
		 * 登录时使用，获取用户的租户信息
		 * @return
		 */
		@Bean
		@ConditionalOnBean({ ITenantService.class})
		public UserTenantServiceImpl UserTenantServiceImpl(){
			return new UserTenantServiceImpl();
		}

		/**
		 * 用户添加时使用，用户添加时，默认也添加到租户
		 * @return
		 */
		@Bean
		@ConditionalOnBean({ ITenantUserService.class})
		public TenantUserUserAddServiceListener tenantUserAddServiceListener() {
			return new TenantUserUserAddServiceListener();
		}

		/**
		 * 登录时使用，租户信息处理逻辑
		 * @return
		 */
		@Bean
		public TenantResolveServiceImpl tenantResolveServiceImpl(){
			return new TenantResolveServiceImpl();
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
	 * 操作日志组件配置
	 */
	@Configuration
	@ConditionalOnClass(IOpLogService.class)
	public static class OpLogDependConfig{

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

	/**
	 * 部门组件配置
	 */
	@Configuration
	@ConditionalOnClass(IDeptService.class)
	public static class DeptDependConfig{

		@Bean
		@ConditionalOnBean({ IDeptUserRelService.class})
		public DeptUserServiceListener deptUserAddServiceListener(){
			return new DeptUserServiceListener();
		}


		@Bean
		@ConditionalOnBean({ IDeptUserRelService.class})
		public DeptTenantUserServiceListener deptTenantUserServiceListener(){
			return new DeptTenantUserServiceListener();
		}

		@Bean
		@ConditionalOnBean({ IDeptUserRelService.class})
		public UserDeptService userDeptService(){
			return new UserDeptServiceImpl();
		}
	}


	/**
	 * 用户翻译使用依赖
	 */
	@Configuration
	@ConditionalOnClass({UserTransServiceImpl.class,ITenantUserService.class})
	public static class UserTransConfig{

		@Bean
		public UserTransOverrideService userTransOverrideService(){
			return new UserTransOverrideServiceImpl();
		}

	}
}

package com.particle.component.adapter;

import com.particle.component.adapter.dataconstraint.DefaultDataConstraintDataPermissionServiceImpl;
import com.particle.component.adapter.dataconstraint.login.RoleDataConstraintServiceImpl;
import com.particle.component.adapter.oauth2authorization.Oauth2RegisteredClientOpenapiClientSecretProvider;
import com.particle.component.adapter.oplog.OpLogRepositoryImpl;
import com.particle.component.adapter.tenant.DeptTenantUserServiceListener;
import com.particle.component.adapter.tenant.RoleTenantUserServiceListener;
import com.particle.component.adapter.tenant.TenantServiceListener;
import com.particle.component.adapter.user.DeptUserServiceListener;
import com.particle.component.adapter.user.RoleUserAddServiceListener;
import com.particle.component.adapter.user.TenantUserUserAddServiceListener;
import com.particle.component.adapter.user.UserTransOverrideServiceImpl;
import com.particle.component.adapter.user.login.*;
import com.particle.dataconstraint.adapter.feign.client.rpc.DataObjectRpcFeignClient;
import com.particle.dataconstraint.adapter.feign.client.rpc.DataScopeRpcFeignClient;
import com.particle.dept.infrastructure.deptuserrel.service.IDeptUserRelService;
import com.particle.dept.infrastructure.service.IDeptService;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.global.data.permission.DataPermissionService;
import com.particle.global.security.security.login.RoleDataConstraintService;
import com.particle.global.security.security.login.UserDeptService;
import com.particle.oauth2authorization.infrastructure.client.service.impl.Oauth2RegisteredClientServiceImpl;
import com.particle.oplog.infrastructure.service.IOpLogService;
import com.particle.role.infrastructure.roledatascoperel.service.IRoleDataScopeRelService;
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
		public RoleTenantUserServiceListener roleTenantUserServiceListener(){
			return new RoleTenantUserServiceListener();
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

		/**
		 * 主要是在编辑用户时，清空租户解析缓存
		 * @return
		 */
		@Bean
		public TenantServiceListener tenantServiceListener(){
			TenantServiceListener tenantServiceListener = new TenantServiceListener();
			tenantServiceListener.setITenantResolveService(tenantResolveServiceImpl());
			return tenantServiceListener;
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
	@Configuration
	@ConditionalOnClass({Oauth2RegisteredClientServiceImpl.class})
	public static class Oauth2AuthorizationConfig {

		@Bean
		public Oauth2RegisteredClientOpenapiClientSecretProvider oauth2RegisteredClientOpenapiClientSecretProvider() {
			return new Oauth2RegisteredClientOpenapiClientSecretProvider();
		}
	}

	/**
	 * 数据范围约束配置
	 */
	@Configuration
	public static class DataConstraintConfig{

		/**
		 * 默认的数据范围约束实现
		 * @return
		 */
		@Bean
		public DataPermissionService dataPermissionService(){
			return new DefaultDataConstraintDataPermissionServiceImpl();
		}
	}
	@Configuration
	@ConditionalOnClass({DataObjectRpcFeignClient.class, DataScopeRpcFeignClient.class, IRoleDataScopeRelService.class})
	public static class DataConstraintDependConfig {
		@Bean
		@ConditionalOnBean({ DataObjectRpcFeignClient.class, DataScopeRpcFeignClient.class, IRoleDataScopeRelService.class})
		public RoleDataConstraintService roleDataConstraintService() {
			return new RoleDataConstraintServiceImpl();
		}
	}
}

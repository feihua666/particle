package com.particle.global.mybatis.plus.config;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.particle.global.data.permission.DataPermissionService;
import com.particle.global.mybatis.plus.crud.MetricsAndSlowSqlMybatisInterceptor;
import com.particle.global.mybatis.plus.datapermission.CustomMultiDataPermissionHandler;
import com.particle.global.mybatis.plus.datapermission.DefaultTenantMultiDataPermissionHandler;
import com.particle.global.mybatis.plus.datapermission.LoginUserSuperAdminResolver;
import com.particle.global.mybatis.plus.datapermission.TenantMultiDataPermissionHandler;
import com.particle.global.mybatis.plus.fill.LoginUserIdResolver;
import com.particle.global.mybatis.plus.fill.MpMetaObjectHandler;
import com.particle.global.mybatis.plus.tenant.CustomTenantLineHandler;
import com.particle.global.mybatis.plus.wrapper.DataPermissionServiceWrapper;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.tenant.TenantTool;
import com.particle.global.tool.id.SnowflakeIdTool;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * mybatisplus 配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-06-29 17:35
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "particle.mybatis-plus")
public class GlobalMybatisPlusConfig {

	public static final int INTERCEPTOR_ORDER_START = 1;

	/**
	 * 在没有该类时 {@link LoginUserTool} 使用默认bean
	 */
	public static final String LoginUserToolClassName = "com.particle.global.security.security.login.LoginUserTool";

	/**
	 * 启动多租户，支持，默认不启动
	 */
	private Boolean tenantEnable = true;
	/**
	 * 多租户忽略的表
	 */
	private List<String> tenantIgnoreTables;

	@Configuration
	@ConditionalOnClass(DataPermissionService.class)
	protected static class DataPermissionServiceDependConfig{

		/**
		 * 自定义一个包装类，占位，用来判定 {@link DataPermissionService} 类是否存在来动态启动是否获取数据权限
		 * @return
		 */
		@Bean
		@ConditionalOnClass(DataPermissionService.class)
		DataPermissionServiceWrapper dataPermissionServiceWrapper (){
			return new DataPermissionServiceWrapper();
		}

	}
	/**
	 * 监控通知 mybatis 拦截器
	 * 不依赖于mybatis plus
	 * @return
	 */
	@Order(INTERCEPTOR_ORDER_START - 5)
	@Bean
	public MetricsAndSlowSqlMybatisInterceptor metricsAndSlowSqlMybatisPlusInterceptor(){
		return new MetricsAndSlowSqlMybatisInterceptor();
	}
	/**
	 * mybatisplus拦截器
	 * @return
	 */
	@Order(INTERCEPTOR_ORDER_START)
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
		if (tenantEnable) {
			TenantTool.tenantEnable();
			mybatisPlusInterceptor.addInnerInterceptor(new TenantLineInnerInterceptor(tenantLineHandler()));
		}
		// 数据权限插件
		mybatisPlusInterceptor.addInnerInterceptor( new DataPermissionInterceptor(multiDataPermissionHandler()));

		// 分页插件
		mybatisPlusInterceptor.addInnerInterceptor( new PaginationInnerInterceptor());

		// 数据库乐观锁插件
		mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

		return mybatisPlusInterceptor;
	}

	/**
	 * 租户处理
	 * @return
	 */
	@Bean
	public CustomTenantLineHandler tenantLineHandler(){
		CustomTenantLineHandler customTenantLineHandler = new CustomTenantLineHandler();
		customTenantLineHandler.setIgnoreTables(tenantIgnoreTables);
		return customTenantLineHandler;
	}
	/**
	 * 数据权限处理
	 * @return
	 */
	@Bean
	public CustomMultiDataPermissionHandler multiDataPermissionHandler(){
		TenantLineHandler tenantLineHandler = null;
		if (tenantEnable) {
			tenantLineHandler = tenantLineHandler();
		}
		CustomMultiDataPermissionHandler multiDataPermissionHandler = new CustomMultiDataPermissionHandler(tenantLineHandler);
		return multiDataPermissionHandler;
	}

	@Bean
	@ConfigurationProperties(prefix = "particle.mybatis-plus.tmdp")
	public TenantMultiDataPermissionHandler tenantMultiDataPermissionHandler() {
		return new DefaultTenantMultiDataPermissionHandler();
	}
	/**
	 * 自定义 mybatisplus 填充处理
	 * @return
	 */
	@Bean
	public MpMetaObjectHandler mpMetaObjectHandler(){
		return new MpMetaObjectHandler();
	}


	@Configuration
	@ConditionalOnClass(LoginUserTool.class)
	protected static class LoginUserToolDependConfig{

		/**
		 * 登录用户id解析，主要用于 MpMetaObjectHandler 填充
		 * @return
		 */
		@Bean
		@ConditionalOnClass(LoginUserTool.class)
		public LoginUserIdResolver securityLoginUserIdResolver(){
			return new LoginUserIdResolver(){
				@Override
				public Long resolve() {
					return Optional.ofNullable(LoginUserTool.getLoginUserId()).orElse(LoginUserIdResolver.DEFAULT_USER_ID);
				}
			};
		}


		/**
		 * 登录用户是否为超级管理员解析，主要用于 数据权限不限制操作
		 * @return
		 */
		@Bean
		@ConditionalOnClass(LoginUserTool.class)
		public LoginUserSuperAdminResolver securityLoginUserSuperAdminResolver(){
			return new LoginUserSuperAdminResolver(){
				@Override
				public boolean resolve() {
					return Optional.ofNullable(LoginUserTool.getLoginUser()).map(LoginUser::getIsSuperAdmin).orElse(LoginUserSuperAdminResolver.DEFAULT_USER_SUPER_ADMIN);
				}
			};
		}

	}

	/**
	 * 默认用户id解析，主要用于 MpMetaObjectHandler 填充
	 * @return
	 */
	@Bean
	@ConditionalOnMissingClass(LoginUserToolClassName)
	public LoginUserIdResolver defaultLoginUserIdResolver(){
		return new LoginUserIdResolver(){
			@Override
			public Long resolve() {
				return LoginUserIdResolver.DEFAULT_USER_ID;
			}
		};
	}

	/**
	 * 默认是否为超级管理员解析，主要用于 数据权限不限制操作
	 * @return
	 */
	@Bean
	@ConditionalOnMissingClass(LoginUserToolClassName)
	public LoginUserSuperAdminResolver defaultLoginUserSuperAdminResolver(){
		return new LoginUserSuperAdminResolver(){
			@Override
			public boolean resolve() {
				return LoginUserSuperAdminResolver.DEFAULT_USER_SUPER_ADMIN;
			}
		};
	}

	/**
	 * 参数不能去掉，有依赖关系
	 * 自定义 id 生成器，支持配置 节点id和数据中心id，参见 {@link SnowflakeIdTool}
	 * @param snowflakeIdTool
	 * @return
	 */
	@Bean
	public IdentifierGenerator customIdentifierGenerator(SnowflakeIdTool snowflakeIdTool){
		DefaultIdentifierGenerator customIdentifierGenerator = null;
		Long workerId = SnowflakeIdTool.getWorkerId();
		Long dataCenterId = SnowflakeIdTool.getDataCenterId();
		if (workerId != null && dataCenterId != null) {
			customIdentifierGenerator = new DefaultIdentifierGenerator(workerId,dataCenterId);
		}else {
			customIdentifierGenerator = new DefaultIdentifierGenerator();
		}
		return customIdentifierGenerator;
	}
}

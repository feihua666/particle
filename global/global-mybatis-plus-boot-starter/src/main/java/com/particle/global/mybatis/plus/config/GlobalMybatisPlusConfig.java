package com.particle.global.mybatis.plus.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.particle.global.concurrency.threadpool.CustomExecutors;
import com.particle.global.data.permission.DataPermissionService;
import com.particle.global.mybatis.plus.crud.MetricsAndSlowSqlMybatisInterceptor;
import com.particle.global.mybatis.plus.fill.LoginUserIdResolver;
import com.particle.global.mybatis.plus.fill.MpMetaObjectHandler;
import com.particle.global.mybatis.plus.tenant.CustomTenantLineHandler;
import com.particle.global.mybatis.plus.wrapper.DataPermissionServiceWrapper;
import com.particle.global.security.security.login.LoginUserTool;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

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
public class GlobalMybatisPlusConfig {

	/**
	 * 启动多租户，支持，默认不启动
	 */
	@Value("${particle.mybatis-plus.tenant.enable:false}")
	private Boolean tenantEnable;
	/**
	 * 多租户忽略的表
	 */
	@Value("${particle.mybatis-plus.tenant.ignore-tables:#{null}}")
	private List<String> ignoreTables;
	/**
	 * 自定义一个包装类，占位，用来判定 {@link DataPermissionService} 类是否存在来动态启动是否获取数据权限
	 * @return
	 */
	@Bean
	@ConditionalOnClass(DataPermissionService.class)
	DataPermissionServiceWrapper dataPermissionServiceWrapper (){
		return new DataPermissionServiceWrapper();
	}

	/**
	 * mybatisplus拦截器
	 * @return
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
		if (tenantEnable) {
			mybatisPlusInterceptor.addInnerInterceptor(new TenantLineInnerInterceptor(tenantLineHandler()));
		}

		// 数据库乐观锁插件
		mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
		// 分布插件
		mybatisPlusInterceptor.addInnerInterceptor( new PaginationInnerInterceptor());

		return mybatisPlusInterceptor;
	}
	/**
	 * 监控通知 mybatis 拦截器
	 * 不依赖于mybatis plus
	 * @return
	 */
	@Bean
	public MetricsAndSlowSqlMybatisInterceptor MetricsAndSlowSqlMybatisPlusInterceptor(){
		return new MetricsAndSlowSqlMybatisInterceptor();
	}
	/**
	 * 租户处理
	 * @return
	 */
	@Bean
	public CustomTenantLineHandler tenantLineHandler(){
		CustomTenantLineHandler customTenantLineHandler = new CustomTenantLineHandler();
		customTenantLineHandler.setIgnoreTables(ignoreTables);
		return customTenantLineHandler;
	}
	/**
	 * 自定义 mybatisplus 填充处理
	 * @return
	 */
	@Bean
	public MpMetaObjectHandler mpMetaObjectHandler(){
		return new MpMetaObjectHandler();
	}

	/**
	 * 默认用户id解析，主要用于 MpMetaObjectHandler 填充
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
	 * 默认用户id解析，主要用于 MpMetaObjectHandler 填充
	 * @return
	 */
	@Bean
	@ConditionalOnMissingClass("com.particle.global.security.security.login.LoginUserTool")
	public LoginUserIdResolver defaultLoginUserIdResolver(){
		return new LoginUserIdResolver(){
			@Override
			public Long resolve() {
				return LoginUserIdResolver.DEFAULT_USER_ID;
			}
		};
	}
}

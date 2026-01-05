package com.particle.component.autoconfigure;

import com.particle.component.autoconfigure.dataconstraint.DataConstraintConfig;
import com.particle.component.autoconfigure.dataconstraint.login.RoleDataConstraintConfig;
import com.particle.component.autoconfigure.oplog.OpLogConfig;
import com.particle.component.autoconfigure.tenant.TenantConfig;
import com.particle.component.autoconfigure.user.UserConfig;
import com.particle.component.autoconfigure.user.login.UserAuthorityConfig;
import com.particle.component.autoconfigure.user.login.UserLoginTenantChangeListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>
 * 组件适配器自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-08-07 18:36
 */
@Configuration(proxyBeanMethods = false)
@Import({DataConstraintConfig.class,
        RoleDataConstraintConfig.class,
        OpLogConfig.class,
        TenantConfig.class,
        UserAuthorityConfig.class,
        UserConfig.class})
public class ComponentAutoConfigureAutoConfiguration {

	/**
	 * 登录时使用，用户租户切换监听
	 * @return
	 */
	@Bean
	public UserLoginTenantChangeListener userLoginTenantChangeListener(){
		return new UserLoginTenantChangeListener();
	}

}

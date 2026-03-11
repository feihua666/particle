package com.particle.component.autoconfigure;

import com.particle.component.autoconfigure.cms.global.security.CmsGlobalSecurityConfig;
import com.particle.component.autoconfigure.global.exceptionhandler.oplog.GlobalExceptionhandlerOpLogConfig;
import com.particle.component.autoconfigure.global.openapi.openplatform.GlobalOpenapiOpenplatformConfig;
import com.particle.component.autoconfigure.global.security.dataconstraint.login.GlobalSecurityDataconstraintRoleDataConstraintConfig;
import com.particle.component.autoconfigure.global.security.user.login.GlobalSecurityUserLoginConfig;
import com.particle.component.autoconfigure.global.webfilter.config.GlobalWebfilterConfigConfig;
import com.particle.component.autoconfigure.global.datapermission.dataconstraint.GlobalDataPermissionDataConstraintConfig;
import com.particle.component.autoconfigure.global.webfilter.tenant.GlobalWebfilterTenantConfig;
import com.particle.component.autoconfigure.global.dataaudit.oplog.GlobalDataauditOpLogConfig;
import com.particle.component.autoconfigure.global.webfilter.usagecount.GlobalWebfilterUsageCountConfig;
import com.particle.component.autoconfigure.global.security.tenant.login.GlobalSecurityTenantConfig;
import com.particle.component.autoconfigure.tenant.dept.TenantDeptConfig;
import com.particle.component.autoconfigure.tenant.role.TenantRoleConfig;
import com.particle.component.autoconfigure.user.dept.UserDeptConfig;
import com.particle.component.autoconfigure.user.role.UserRoleConfig;
import com.particle.component.autoconfigure.user.tenant.UserTenantConfig;
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
@Import({
		CmsGlobalSecurityConfig.class,
		GlobalDataPermissionDataConstraintConfig.class,
        GlobalDataauditOpLogConfig.class,
		GlobalSecurityDataconstraintRoleDataConstraintConfig.class,
        GlobalSecurityTenantConfig.class,
		GlobalSecurityUserLoginConfig.class,
		GlobalExceptionhandlerOpLogConfig.class,
		TenantDeptConfig.class,
		TenantRoleConfig.class,
		UserDeptConfig.class,
		UserRoleConfig.class,
		UserTenantConfig.class,
		GlobalWebfilterUsageCountConfig.class,
		GlobalWebfilterConfigConfig.class,
		GlobalWebfilterTenantConfig.class,
		GlobalOpenapiOpenplatformConfig.class,
})
public class ComponentAutoConfigureAutoConfiguration {

}

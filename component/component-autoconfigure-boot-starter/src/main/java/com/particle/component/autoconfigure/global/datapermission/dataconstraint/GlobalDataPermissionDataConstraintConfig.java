package com.particle.component.autoconfigure.global.datapermission.dataconstraint;

import com.particle.global.data.permission.DataPermissionService;
import com.particle.global.tool.login.LoginUserTool;
import com.particle.global.security.security.login.SecurityUserDeptService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * global data permission 实现配置，该配置依赖 global security
 * 数据范围约束配置
 * 实现数据范围约束具体逻辑配置
 * </p>
 *
 * @author yangwei
 * @since 2025/12/26 21:29
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(SecurityUserDeptService.class)
public class GlobalDataPermissionDataConstraintConfig {

    /**
     * 默认的数据范围约束实现
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(LoginUserTool.class)
    public DataPermissionService dataPermissionService(){
        return new DefaultDataConstraintDataPermissionServiceImpl();
    }
}

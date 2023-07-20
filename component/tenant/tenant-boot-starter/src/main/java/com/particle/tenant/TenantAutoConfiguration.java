 package com.particle.tenant;


import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.ApplicationContexSwaggertHelper;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 自动配置类
 * </p>
 *
 * @author yw
 * @since 2023-04-11 18:35:06
 */
@ComponentScan
@Configuration
@MapperScan({
        "com.particle.tenant.infrastructure.mapper",
        "com.particle.tenant.infrastructure.createapply.mapper",
        "com.particle.tenant.infrastructure.tenantfunc.mapper",
        "com.particle.tenant.infrastructure.tenantfuncapplication.mapper",
        "com.particle.tenant.infrastructure.userinvite.mapper",
})
public class TenantAutoConfiguration {


    /**
     * 后端管理文档
     * @param projectInfo 参数不能去，依赖projectInfo
     * @return
     */
    @ConditionalOnBean({ApplicationContexSwaggertHelper.class})
    @Bean
    public GroupedOpenApi createTenantAdminRestApi(ProjectInfo projectInfo) {
        List<SecurityScheme> parameters = new ArrayList<>();
        
        return SwaggerFactory.createRestApi(SwaggerInfo.builder()
                .groupName("tenant接口")
                .basePackage("com.particle.tenant.adapter")

                .securitySchemes(parameters)
                .version(ProjectInfo.VERSION)
                .title(ProjectInfo.NAME + " Swagger Apis")
                .description(ProjectInfo.NAME + " Swagger Apis Description")
                .build());
    }
}

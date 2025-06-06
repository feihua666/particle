 package com.particle.dept;


 import com.particle.global.projectinfo.ProjectInfo;
 import com.particle.global.swagger.ApplicationContexSwaggertHelper;
 import com.particle.global.swagger.SwaggerInfo;
 import com.particle.global.swagger.factory.SwaggerFactory;
 import io.swagger.v3.oas.models.security.SecurityScheme;
 import org.mybatis.spring.annotation.MapperScan;
 import org.springdoc.core.models.GroupedOpenApi;
 import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.ComponentScan;
 import org.springframework.context.annotation.Configuration;

 import java.util.ArrayList;
 import java.util.List;

/**
 * <p>
 * 自动配置类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:22:35
 */
@ComponentScan
@Configuration(proxyBeanMethods = false)
@MapperScan({
        "com.particle.dept.infrastructure.mapper",
        "com.particle.dept.infrastructure.depttreeuserrel.mapper",
        "com.particle.dept.infrastructure.deptuserrel.mapper",
})
public class DeptAutoConfiguration {


    /**
     * 后端管理文档
     * @param projectInfo 参数不能去，依赖projectInfo
     * @return
     */
    @ConditionalOnBean({ApplicationContexSwaggertHelper.class})
    @Bean
    public GroupedOpenApi createDeptAdminRestApi(ProjectInfo projectInfo) {
        List<SecurityScheme> parameters = new ArrayList<>();

        return SwaggerFactory.createRestApi(SwaggerInfo.builder()
                .groupName("dept接口")
                .basePackage("com.particle.dept.adapter")
                .securitySchemes(parameters)
                .version(ProjectInfo.VERSION)
                .title(ProjectInfo.NAME + " Swagger Apis")
                .description(ProjectInfo.NAME + " Swagger Apis Description")
                .build());
    }
}

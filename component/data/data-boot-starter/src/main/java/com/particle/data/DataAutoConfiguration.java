 package com.particle.data;

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
 * @since 2024-07-13 22:53:56
 */
@ComponentScan
@Configuration(proxyBeanMethods = false)
@MapperScan({
        "com.particle.data.infrastructure.mapper",
        "com.particle.data.infrastructure.company.mapper",
        "com.particle.data.infrastructure.dynamicdata.mapper",
        "com.particle.data.infrastructure.dynamictable.mapper",
})
public class DataAutoConfiguration {


    /**
     * 后端管理文档
     * @param projectInfo 参数不能去，依赖projectInfo
     * @return
     */
    @ConditionalOnBean({ApplicationContexSwaggertHelper.class})
    @Bean
    public GroupedOpenApi createDataAdminRestApi(ProjectInfo projectInfo) {
        List<SecurityScheme> parameters = new ArrayList<>();

        return SwaggerFactory.createRestApi(SwaggerInfo.builder()
                .groupName("data接口")
                .basePackage("com.particle.data.adapter")
                                .securitySchemes(parameters)
                .version(ProjectInfo.VERSION)
                .title(ProjectInfo.NAME + " Swagger Apis")
                .description(ProjectInfo.NAME + " Swagger Apis Description")
                .build());
    }
}

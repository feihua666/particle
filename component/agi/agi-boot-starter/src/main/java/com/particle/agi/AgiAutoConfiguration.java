 package com.particle.agi;

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
 * @since 2025-01-08 11:30:42
 */
@ComponentScan
@Configuration(proxyBeanMethods = false)
@MapperScan({
        "com.particle.agi.infrastructure.mapper",
        "com.particle.agi.infrastructure.rag.mapper",
        "com.particle.agi.infrastructure.agent.mapper",
        "com.particle.agi.infrastructure.chat.mapper",
})
public class AgiAutoConfiguration {


    /**
     * 后端管理文档
     * @param projectInfo 参数不能去，依赖projectInfo
     * @return
     */
    @ConditionalOnBean({ApplicationContexSwaggertHelper.class})
    @Bean
    public GroupedOpenApi createAgiAdminRestApi(ProjectInfo projectInfo) {
        List<SecurityScheme> parameters = new ArrayList<>();

        return SwaggerFactory.createRestApi(SwaggerInfo.builder()
                .groupName("agi接口")
                .basePackage("com.particle.agi.adapter")
                                .securitySchemes(parameters)
                .version(ProjectInfo.VERSION)
                .title(ProjectInfo.NAME + " Swagger Apis")
                .description(ProjectInfo.NAME + " Swagger Apis Description")
                .build());
    }
}

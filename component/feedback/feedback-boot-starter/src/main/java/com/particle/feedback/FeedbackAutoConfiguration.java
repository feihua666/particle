 package com.particle.feedback;

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
 * @since 2024-02-23 14:33:11
 */
@ComponentScan
@Configuration(proxyBeanMethods = false)
@MapperScan({
        "com.particle.feedback.infrastructure.feedback.mapper",
        "com.particle.feedback.infrastructure.reply.mapper",
})
public class FeedbackAutoConfiguration {


    /**
     * 后端管理文档
     * @param projectInfo 参数不能去，依赖projectInfo
     * @return
     */
    @ConditionalOnBean({ApplicationContexSwaggertHelper.class})
    @Bean
    public GroupedOpenApi createFeedbackAdminRestApi(ProjectInfo projectInfo) {
        List<SecurityScheme> parameters = new ArrayList<>();

        return SwaggerFactory.createRestApi(SwaggerInfo.builder()
                .groupName("feedback接口")
                .basePackage("com.particle.feedback.adapter")
                                .securitySchemes(parameters)
                .version(ProjectInfo.VERSION)
                .title(ProjectInfo.NAME + " Swagger Apis")
                .description(ProjectInfo.NAME + " Swagger Apis Description")
                .build());
    }
}

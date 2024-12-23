 package com.particle.usagecount;

 import com.particle.global.projectinfo.ProjectInfo;
 import com.particle.global.security.security.config.WebSecurityConfig;
 import com.particle.global.swagger.ApplicationContexSwaggertHelper;
 import com.particle.global.swagger.SwaggerInfo;
 import com.particle.global.swagger.factory.SwaggerFactory;
 import com.particle.usagecount.adapter.filter.UsageCountFilter;
 import io.swagger.v3.oas.models.security.SecurityScheme;
 import org.mybatis.spring.annotation.MapperScan;
 import org.springdoc.core.models.GroupedOpenApi;
 import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
 import org.springframework.boot.web.servlet.FilterRegistrationBean;
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
 * @since 2023-10-19 09:50:14
 */
@ComponentScan
@Configuration(proxyBeanMethods = true)
@MapperScan("com.particle.usagecount.infrastructure.mapper")
public class UsageCountAutoConfiguration {


    /**
     * 后端管理文档
     * @param projectInfo 参数不能去，依赖projectInfo
     * @return
     */
    @ConditionalOnBean({ApplicationContexSwaggertHelper.class})
    @Bean
    public GroupedOpenApi createUsageCountAdminRestApi(ProjectInfo projectInfo) {
        List<SecurityScheme> parameters = new ArrayList<>();

        return SwaggerFactory.createRestApi(SwaggerInfo.builder()
                .groupName("usage-count接口")
                .basePackage("com.particle.usagecount.adapter")
                                .securitySchemes(parameters)
                .version(ProjectInfo.VERSION)
                .title(ProjectInfo.NAME + " Swagger Apis")
                .description(ProjectInfo.NAME + " Swagger Apis Description")
                .build());
    }

    @Bean
    public UsageCountFilter usageCountFilterBean() {
        return new UsageCountFilter();
    }

    /**
     * 使用次数统计过滤器，顺序在 spirng security 之后
     * @return
     */
    @Bean
    public FilterRegistrationBean usageCountFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(usageCountFilterBean());
        registrationBean.setOrder(WebSecurityConfig.defaultSecurityFilterChainOrder + 10);
        return registrationBean;
    }
}

 package com.particle.scheduler;

 import com.particle.global.projectinfo.ProjectInfo;
 import com.particle.global.swagger.ApplicationContexSwaggertHelper;
 import com.particle.global.swagger.SwaggerInfo;
 import com.particle.global.swagger.factory.SwaggerFactory;
 import io.swagger.v3.oas.models.security.SecurityScheme;
 import org.mybatis.spring.annotation.MapperScan;
 import org.springdoc.core.models.GroupedOpenApi;
 import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
 import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
 import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
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
 * @since 2024-08-28 11:31:17
 */
@ComponentScan
@Configuration(proxyBeanMethods = false)
@MapperScan({
        "com.particle.scheduler.infrastructure.mapper",
        "com.particle.scheduler.infrastructure.temptask.mapper",
        "com.particle.scheduler.infrastructure.schedule.mapper",
        "com.particle.scheduler.infrastructure.datatask.mapper",
})
public class SchedulerAutoConfiguration {


    /**
     * 后端管理文档
     * @param projectInfo 参数不能去，依赖projectInfo
     * @return
     */
    @ConditionalOnBean({ApplicationContexSwaggertHelper.class})
    @Bean
    public GroupedOpenApi createSchedulerAdminRestApi(ProjectInfo projectInfo) {
        List<SecurityScheme> parameters = new ArrayList<>();
        /**
         * 自动生成的 groupName=scheduler接口,因为有一个全局的也叫 scheduler接口 导致冲突，这里改一下名字
         * 参见：{@link QuartzSchedulerAutoConfiguration#createQuartzSchedulerRestApi(ProjectInfo)}
         */

        return SwaggerFactory.createRestApi(SwaggerInfo.builder()
                .groupName("scheduler组件接口")
                .basePackage("com.particle.scheduler.adapter")
                                .securitySchemes(parameters)
                .version(ProjectInfo.VERSION)
                .title(ProjectInfo.NAME + " Swagger Apis")
                .description(ProjectInfo.NAME + " Swagger Apis Description")
                .build());
    }

    @Bean
    @ConditionalOnMissingBean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer() {
        return new CustomeLogSchedulerFactoryBeanCustomizer();
    }


}

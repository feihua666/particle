 package com.particle.report;

 import com.particle.global.oss.service.GlobalOssClientService;
 import com.particle.global.projectinfo.ProjectInfo;
 import com.particle.global.swagger.ApplicationContexSwaggertHelper;
 import com.particle.global.swagger.SwaggerInfo;
 import com.particle.global.swagger.factory.SwaggerFactory;
 import com.particle.report.adapter.api.handler.ReportApiGenerateResultOssHandlerImpl;
 import com.particle.report.adapter.api.permission.SecurityPermissionReportSegmentTemplatePermissionCheckServiceImpl;
 import com.particle.report.app.executor.IReportApiGenerateResultHandler;
 import com.particle.report.infrastructure.template.service.IReportSegmentTemplatePermissionCheckService;
 import io.swagger.v3.oas.models.security.SecurityScheme;
 import org.mybatis.spring.annotation.MapperScan;
 import org.springdoc.core.models.GroupedOpenApi;
 import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
 import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
 * @since 2023-09-05 15:55:30
 */
@ComponentScan
@Configuration(proxyBeanMethods = false)
@MapperScan({"com.particle.report.infrastructure.template.mapper","com.particle.report.infrastructure.reportapi.mapper"})
public class ReportAutoConfiguration {


    /**
     * 后端管理文档
     * @param projectInfo 参数不能去，依赖projectInfo
     * @return
     */
    @ConditionalOnBean({ApplicationContexSwaggertHelper.class})
    @Bean
    public GroupedOpenApi createReportAdminRestApi(ProjectInfo projectInfo) {
        List<SecurityScheme> parameters = new ArrayList<>();

        return SwaggerFactory.createRestApi(SwaggerInfo.builder()
                .groupName("report接口")
                .basePackage("com.particle.report.adapter")
                                .securitySchemes(parameters)
                .version(ProjectInfo.VERSION)
                .title(ProjectInfo.NAME + " Swagger Apis")
                .description(ProjectInfo.NAME + " Swagger Apis Description")
                .build());
    }

    /**
     * 配置一个默认的基于登录用户的权限校验器
     * @return
     */
    @ConditionalOnMissingBean
    @Bean
    public IReportSegmentTemplatePermissionCheckService reportSegmentTemplatePermissionCheckService(){
        return new SecurityPermissionReportSegmentTemplatePermissionCheckServiceImpl();
    }

    /**
     * 提供将报告如果为本地文件，上传到oss中
     * @return
     */
    @ConditionalOnMissingBean
    @ConditionalOnBean(GlobalOssClientService.class)
    @Bean
    public IReportApiGenerateResultHandler reportApiGenerateResultHandler() {
        return new ReportApiGenerateResultOssHandlerImpl();
    }
}

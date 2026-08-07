package com.particle.global.workflow;

import com.particle.global.dag.engine.DagEngine;
import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.ApplicationContexSwaggertHelper;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import com.particle.global.workflow.converter.WorkflowGraphConverter;
import com.particle.global.workflow.orchestrator.WorkflowOrchestrationService;
import com.particle.global.workflow.orchestrator.impl.DefaultWorkflowOrchestrationService;
import com.particle.global.workflow.repository.WorkflowExecutionRepository;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 工作流系统自动配置类
 * 提供工作流编排能力，串联 dag 执行与持久化存储
 * </p>
 *
 * @author particle
 * @since 2026-04-16
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan
public class GlobalWorkflowAutoConfiguration {

    /**
     * 工作流编排服务
     * 需要 DagEngine + WorkflowExecutionRepository，WorkflowGraphConverter 可选
     */
    @Bean
    @ConditionalOnBean({DagEngine.class, WorkflowExecutionRepository.class})
    public WorkflowOrchestrationService workflowOrchestrationService(
            DagEngine dagEngine,
            WorkflowExecutionRepository repository,
            ObjectProvider<WorkflowGraphConverter> graphConverterProvider) {
        WorkflowGraphConverter graphConverter = graphConverterProvider.getIfAvailable();
        return new DefaultWorkflowOrchestrationService(dagEngine, repository, graphConverter);
    }

    /**
     * 全局工作流接口文档
     * @param projectInfo 参数不能去，依赖projectInfo
     * @return
     */
    @ConditionalOnBean({ApplicationContexSwaggertHelper.class})
    @Bean
    public GroupedOpenApi createGlobalWorkflowRestApi(ProjectInfo projectInfo) {
        List<SecurityScheme> parameters = new ArrayList<>();

        return SwaggerFactory.createRestApi(SwaggerInfo.builder()
                .groupName("全局workflow接口")
                .basePackage("com.particle.global.workflow.endpoint")
                .securitySchemes(parameters)
                .version(ProjectInfo.VERSION)
                .title(ProjectInfo.NAME + " Swagger Apis")
                .description(ProjectInfo.NAME + " Swagger Apis Description")
                .build());
    }
}

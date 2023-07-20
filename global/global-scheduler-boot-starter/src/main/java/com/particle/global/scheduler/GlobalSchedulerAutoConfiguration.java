package com.particle.global.scheduler;


import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.ApplicationContexSwaggertHelper;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 全局定时任务计划自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-06-09 15:10
 */
@EnableScheduling
@Configuration
@ComponentScan
@ConditionalOnClass(EnableScheduling.class)
@ConditionalOnProperty(prefix = "particle.scheduler", name = "enabled", havingValue = "true", matchIfMissing = true)
public class GlobalSchedulerAutoConfiguration {

	/**
	 * 任务计划相关接口文档
	 * @param projectInfo 参数不能去，依赖projectInfo
	 * @return
	 */
	@ConditionalOnBean({ApplicationContexSwaggertHelper.class})
	@Bean
	public GroupedOpenApi createGlobalSchedulerRestApi(ProjectInfo projectInfo) {
		List<SecurityScheme> parameters = new ArrayList<>();

		return SwaggerFactory.createRestApi(SwaggerInfo.builder()
				.groupName("scheduler接口")
				.basePackage("com.particle.global.scheduler.endpoint")

				.securitySchemes(parameters)
				.version(ProjectInfo.VERSION)
				.title(ProjectInfo.NAME + " Swagger Apis")
				.description(ProjectInfo.NAME + " Swagger Apis Description")
				.build());
	}
}

package com.particle.global.scheduler;

import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spring.web.plugins.Docket;

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
public class GlobalSchedulerAutoConfiguration {

	/**
	 * 任务计划相关接口文档
	 * @param projectInfo 参数不能去，依赖projectInfo
	 * @return
	 */
	@Bean
	public Docket createGlobalSchedulerRestApi(ProjectInfo projectInfo) {
		List<SecurityScheme> parameters = new ArrayList<>();

		return SwaggerFactory.createRestApi(SwaggerInfo.builder()
				.groupName("scheduler接口")
				.basePackage("com.particle.global.scheduler.endpoint")
				//  SwaggerInfo 已自动处理
				.openApiExtensionResolver(null)
				.securitySchemes(parameters)
				.version(ProjectInfo.VERSION)
				.title(ProjectInfo.NAME + " Swagger Apis")
				.description(ProjectInfo.NAME + " Swagger Apis Description")
				.build());
	}
}

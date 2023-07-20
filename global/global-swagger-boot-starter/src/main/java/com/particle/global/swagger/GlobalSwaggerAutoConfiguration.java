package com.particle.global.swagger;

import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * swagger 全局默认开启
 * </p>
 *
 * @author yangwei
 * @since 2022-05-19 16:50
 */
@Configuration
@ComponentScan
@ConditionalOnProperty(prefix = "particle.swagger", name = "enabled", havingValue = "true", matchIfMissing = true)
public class GlobalSwaggerAutoConfiguration {

	@ConditionalOnClass(ProjectInfo.class)
	@Configuration
	public static class GlobalOpenAPIConfiguration {

		/**
		 * 默认使用自定义 GroupedOpenApi 分组方式，这里只是一个可选项
		 *
		 * @param projectInfo
		 * @return
		 */
		@ConditionalOnMissingBean(GroupedOpenApi.class)
		@ConditionalOnBean(ProjectInfo.class)
		@Bean
		public OpenAPI globalOpenApi(ProjectInfo projectInfo) {
			return new OpenAPI()
					.info(SwaggerFactory.apiInfo(
							SwaggerInfo.builder()
									.version(ProjectInfo.VERSION)
									.title(ProjectInfo.NAME + " Swagger Apis")
									.description(ProjectInfo.NAME + " Swagger Apis Description")
									.build()
					));
		}

	}

}

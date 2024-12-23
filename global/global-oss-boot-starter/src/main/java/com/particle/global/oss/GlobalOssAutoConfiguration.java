package com.particle.global.oss;


import com.particle.global.oss.service.DefaultGlobalOssClientServiceImpl;
import com.particle.global.oss.service.GlobalOssClientService;
import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.ApplicationContexSwaggertHelper;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * oss 对象存储自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-27 18:18
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({GlobalOssProperties.class})
@ConditionalOnProperty(prefix = "particle.oss", name = "enabled", havingValue = "true", matchIfMissing = true)
@ComponentScan
public class GlobalOssAutoConfiguration {

	@Bean(destroyMethod = "closeClients")
	public GlobalOssClientService globalOssClientService(GlobalOssProperties globalOssProperties){
		DefaultGlobalOssClientServiceImpl defaultGlobalOssClientService = new DefaultGlobalOssClientServiceImpl(globalOssProperties);
		return defaultGlobalOssClientService;
	}


	/**
	 * oss对象存储接口文档
	 * @param projectInfo 参数不能去，依赖projectInfo
	 * @return
	 */
	@ConditionalOnBean({ApplicationContexSwaggertHelper.class})
	@Bean
	public GroupedOpenApi createGlobalOssRestApi(ProjectInfo projectInfo) {
		List<SecurityScheme> parameters = new ArrayList<>();

		return SwaggerFactory.createRestApi(SwaggerInfo.builder()
				.groupName("oss接口")
				.basePackage("com.particle.global.oss.endpoint")
				.securitySchemes(parameters)
				.version(ProjectInfo.VERSION)
				.title(ProjectInfo.NAME + " Swagger Apis")
				.description(ProjectInfo.NAME + " Swagger Apis Description")
				.build());
	}
}

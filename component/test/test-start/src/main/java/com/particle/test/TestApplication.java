package com.particle.test;

import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.security.GlobalSecurityAutoConfiguration;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author yw
 * @since 2022-07-14 22:42:47
 */
@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, GlobalSecurityAutoConfiguration.class})
public class TestApplication {
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);

		log.info("===============================================");
		log.info("{} stated",TestApplication.class.getSimpleName());
		log.info("===============================================");
	}


	/**
	 * 后端管理文档
	 * @param projectInfo 参数不能去，依赖projectInfo
	 * @return
	 */
	@Bean
	public Docket createAreaAdminRestApi(ProjectInfo projectInfo) {
		List<SecurityScheme> parameters = new ArrayList<>();
		parameters.add(new ApiKey("Token", "token", "header"));
		return SwaggerFactory.createRestApi(SwaggerInfo.builder()
				.groupName("test接口")
				.basePackage("com.particle.test.adapter")
				//  SwaggerInfo 已自动处理
				.openApiExtensionResolver(null)
				.parameters(parameters)
				.version(ProjectInfo.VERSION)
				.title(ProjectInfo.NAME + " Swagger Apis")
				.description(ProjectInfo.NAME + " Swagger Apis Description")
				.build());
	}
}

package com.particle.func;

import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-05-16 21:50
 */
@Slf4j
@SpringBootApplication
public class FuncApplication {
	public static void main(String[] args) {
		SpringApplication.run(FuncApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", FuncApplication.class.getSimpleName());
		log.info("===============================================");
	}


	/**
	 * 区域后端管理文档
	 * @param projectInfo 参数不能去，依赖projectInfo
	 * @return
	 */
	@Bean
	public Docket createAreaAdminRestApi(ProjectInfo projectInfo) {
		List<SecurityScheme> parameters = new ArrayList<>();
		parameters.add(new ApiKey("Token", "token", "header"));
		return SwaggerFactory.createRestApi(SwaggerInfo.builder()
				.groupName("区域后台管理端")
				.basePackage("com.particle.area.adapter.web")
				//  SwaggerInfo 已自动处理
				.openApiExtensionResolver(null)
				.parameters(parameters)
				.version(ProjectInfo.VERSION)
				.title(ProjectInfo.NAME + " Swagger Apis")
				.description(ProjectInfo.NAME + " Swagger Apis Description")
				.build());
	}

}

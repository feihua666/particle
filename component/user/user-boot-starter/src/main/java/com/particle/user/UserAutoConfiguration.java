package com.particle.user;

import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 自动配置类
 * </p>
 *
 * @author yw
 * @since 2022-07-19 18:54:10
 */
@ComponentScan
@Configuration
@MapperScan("com.particle.user.infrastructure.mapper")
public class UserAutoConfiguration {


	/**
	 * 后端管理文档
	 * @param projectInfo 参数不能去，依赖projectInfo
	 * @return
	 */
	@Bean
	public Docket createUserAdminRestApi(ProjectInfo projectInfo) {
		List<SecurityScheme> parameters = new ArrayList<>();
		parameters.add(new ApiKey("Token", "token", "header"));
		return SwaggerFactory.createRestApi(SwaggerInfo.builder()
				.groupName("user接口")
				.basePackage("com.particle.user.adapter")
				//  SwaggerInfo 已自动处理
				.openApiExtensionResolver(null)
				.parameters(parameters)
				.version(ProjectInfo.VERSION)
				.title(ProjectInfo.NAME + " Swagger Apis")
				.description(ProjectInfo.NAME + " Swagger Apis Description")
				.build());
	}
}

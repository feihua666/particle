package com.particle.lowcode;

import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
 * @since 2023-01-03 13:17:50
 */
@ComponentScan
@Configuration
@MapperScan("com.particle.lowcode.infrastructure.generator.mapper")
public class LowcodeAutoConfiguration {


	/**
	 * 后端管理文档
	 * @param projectInfo 参数不能去，依赖projectInfo
	 * @return
	 */
	@Bean
	public Docket createLowcodeAdminRestApi(ProjectInfo projectInfo) {
		List<SecurityScheme> parameters = new ArrayList<>();
		
		return SwaggerFactory.createRestApi(SwaggerInfo.builder()
				.groupName("low-code接口")
				.basePackage("com.particle.lowcode.adapter")
				//  SwaggerInfo 已自动处理
				.openApiExtensionResolver(null)
				.securitySchemes(parameters)
				.version(ProjectInfo.VERSION)
				.title(ProjectInfo.NAME + " Swagger Apis")
				.description(ProjectInfo.NAME + " Swagger Apis Description")
				.build());
	}
}
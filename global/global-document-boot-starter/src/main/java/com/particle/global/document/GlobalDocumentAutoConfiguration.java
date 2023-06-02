package com.particle.global.document;

import com.particle.global.document.template.DefalutGlobalDocumentTemplateProvider;
import com.particle.global.document.template.GlobalDocumentTemplateService;
import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 全局文档相关自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-06-01 15:49
 */
@Configuration
@ComponentScan
public class GlobalDocumentAutoConfiguration {

	/**
	 * 文档相关接口文档
	 * @param projectInfo 参数不能去，依赖projectInfo
	 * @return
	 */
	@Bean
	public Docket createGlobalDocumentRestApi(ProjectInfo projectInfo) {
		List<SecurityScheme> parameters = new ArrayList<>();

		return SwaggerFactory.createRestApi(SwaggerInfo.builder()
				.groupName("document接口")
				.basePackage("com.particle.global.document.endpoint")
				//  SwaggerInfo 已自动处理
				.openApiExtensionResolver(null)
				.securitySchemes(parameters)
				.version(ProjectInfo.VERSION)
				.title(ProjectInfo.NAME + " Swagger Apis")
				.description(ProjectInfo.NAME + " Swagger Apis Description")
				.build());
	}

	/**
	 * 优先级最低，给其它处理器提前处理的机会，因为在入口 {@link GlobalDocumentTemplateService} 中如果存在已支持的就提前返回了
	 * @return
	 */
	@Bean
	@Order(Ordered.LOWEST_PRECEDENCE)
	public DefalutGlobalDocumentTemplateProvider defalutGlobalDocumentTemplateProvider(){
		return new DefalutGlobalDocumentTemplateProvider();
	}
}

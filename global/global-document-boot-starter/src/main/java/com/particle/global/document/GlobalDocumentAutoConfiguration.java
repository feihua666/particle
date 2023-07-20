package com.particle.global.document;


import com.particle.global.document.template.DefalutGlobalDocumentTemplateProvider;
import com.particle.global.document.template.GlobalDocumentTemplateService;
import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.ApplicationContexSwaggertHelper;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;

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
	@ConditionalOnBean({ApplicationContexSwaggertHelper.class})
	@Bean
	public GroupedOpenApi createGlobalDocumentRestApi(ProjectInfo projectInfo) {
		List<SecurityScheme> parameters = new ArrayList<>();

		return SwaggerFactory.createRestApi(SwaggerInfo.builder()
				.groupName("document接口")
				.basePackage("com.particle.global.document.endpoint")
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

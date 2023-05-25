package com.particle.global.captcha;

import com.particle.global.captcha.endpoint.DynamicCaptchaNotifyProperties;
import com.particle.global.captcha.gen.DefaultCaptchaGenServiceImpl;
import com.particle.global.captcha.gen.ICaptchaGenService;
import com.particle.global.captcha.security.CaptchaSecurityFilter;
import com.particle.global.captcha.store.HttpSessionStoreServiceImpl;
import com.particle.global.captcha.store.ICaptchaStoreService;
import com.particle.global.captcha.verify.DefaultCaptchaVerifyServiceImpl;
import com.particle.global.captcha.verify.ICaptchaVerifyService;
import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 全局验证码自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-24 18:17
 */
@Configuration
@ComponentScan
@EnableConfigurationProperties(DynamicCaptchaNotifyProperties.class)
@ConditionalOnProperty(prefix = "particle.captcha", name = "enabled", havingValue = "true", matchIfMissing = true)
public class CaptchaAutoConfiguration {

	/**
	 * 验证码接口文档
	 * @param projectInfo 参数不能去，依赖projectInfo
	 * @return
	 */
	@Bean
	public Docket createGlobalCaptchaRestApi(ProjectInfo projectInfo) {
		List<SecurityScheme> parameters = new ArrayList<>();

		return SwaggerFactory.createRestApi(SwaggerInfo.builder()
				.groupName("captcha接口")
				.basePackage("com.particle.global.captcha.endpoint")
				//  SwaggerInfo 已自动处理
				.openApiExtensionResolver(null)
				.securitySchemes(parameters)
				.version(ProjectInfo.VERSION)
				.title(ProjectInfo.NAME + " Swagger Apis")
				.description(ProjectInfo.NAME + " Swagger Apis Description")
				.build());
	}
}

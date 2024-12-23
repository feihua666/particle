package com.particle.global.notification;

import com.particle.global.bootstrap.boot.OnApplicationRunnerListener;
import com.particle.global.bootstrap.boot.OnApplicationShutdownListener;
import com.particle.global.notification.alert.AlertNotifyListener;
import com.particle.global.notification.email.EmailNotifyListener;
import com.particle.global.notification.notify.ApplicationShutdownNotifyListener;
import com.particle.global.notification.notify.ApplicationStartNotifyListener;
import com.particle.global.notification.notify.LogNotifyListener;
import com.particle.global.notification.sms.SmsNotifyListener;
import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.ApplicationContexSwaggertHelper;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 全局通知自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-08-05 12:02
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan
@EnableConfigurationProperties(GlobalNotificationProperties.class)
public class GlobalNotificationAutoConfiguration {

	@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass(OnApplicationRunnerListener.class)
	protected static class OnApplicationRunnerListenerDependConfig{

		@Bean
		@ConditionalOnClass(OnApplicationRunnerListener.class)
		public OnApplicationRunnerListener applicationStartNotifyListener() {
			return new ApplicationStartNotifyListener();
		}

	}

	@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass(OnApplicationShutdownListener.class)
	protected static class OnApplicationShutdownListenerDependConfig{

		@Bean
		@ConditionalOnClass(OnApplicationShutdownListener.class)
		public OnApplicationShutdownListener OnApplicationShutdownNotifyListener() {
			return new ApplicationShutdownNotifyListener();
		}
	}

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE + 100)
	public LogNotifyListener logNotifyListener() {
		return new LogNotifyListener();
	}

	@Bean
	@ConditionalOnMissingBean
	public EmailNotifyListener emailNotifyListener(){
		EmailNotifyListener emailNotifyListener = new EmailNotifyListener();
		return emailNotifyListener;
	}

	@Bean
	@ConditionalOnMissingBean
	public SmsNotifyListener smsNotifyListener(){
		SmsNotifyListener smsNotifyListener = new SmsNotifyListener();
		return smsNotifyListener;
	}
	@Bean
	@ConditionalOnMissingBean
	public AlertNotifyListener alertNotifyListener(){
		AlertNotifyListener alertNotifyListener = new AlertNotifyListener();
		return alertNotifyListener;
	}


	/**
	 * 通知接口文档
	 * @param projectInfo 参数不能去，依赖projectInfo
	 * @return
	 */
	@ConditionalOnBean({ApplicationContexSwaggertHelper.class})
	@Bean
	public GroupedOpenApi createGlobalNotificationRestApi(ProjectInfo projectInfo) {
		List<SecurityScheme> parameters = new ArrayList<>();

		return SwaggerFactory.createRestApi(SwaggerInfo.builder()
				.groupName("notification接口")
				.basePackage("com.particle.global.notification.endpoint")
				.securitySchemes(parameters)
				.version(ProjectInfo.VERSION)
				.title(ProjectInfo.NAME + " Swagger Apis")
				.description(ProjectInfo.NAME + " Swagger Apis Description")
				.build());
	}
}

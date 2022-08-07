package com.particle.global.notification;

import com.particle.global.bootstrap.boot.OnApplicationRunnerListener;
import com.particle.global.bootstrap.boot.OnApplicationShutdownListener;
import com.particle.global.notification.notify.ApplicationShutdownNotifyListener;
import com.particle.global.notification.notify.ApplicationStartNotifyListener;
import com.particle.global.notification.notify.LogNotifyListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * <p>
 * 全局通知自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-08-05 12:02
 */
@Configuration
public class GlobalNotificationAutoConfiguration {

	@Bean
	@ConditionalOnClass(OnApplicationRunnerListener.class)
	public OnApplicationRunnerListener applicationStartNotifyListener() {
		return new ApplicationStartNotifyListener();
	}

	@Bean
	@ConditionalOnClass(OnApplicationShutdownListener.class)
	public OnApplicationShutdownListener OnApplicationShutdownNotifyListener() {
		return new ApplicationShutdownNotifyListener();
	}

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE + 100)
	public LogNotifyListener logNotifyListener() {
		return new LogNotifyListener();
	}
}

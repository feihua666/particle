package com.particle.global.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 自定义日志滚动 相关系统变量
 * 可以将log4j2.xml中使用的变量配置的application.yml中，方便配置
 * </p>
 * 参考：{@link LoggingApplicationListener}
 * @author yangwei
 * @since 2022-06-03 13:09
 */
public class Log4j2ApplicationListener  implements GenericApplicationListener {

	private static final Class<?>[] EVENT_TYPES = {ApplicationEnvironmentPreparedEvent.class};

	private static final Class<?>[] SOURCE_TYPES = { SpringApplication.class, ApplicationContext.class };

	private boolean isAssignableFrom(Class<?> type, Class<?>... supportedTypes) {
		if (type != null) {
			for (Class<?> supportedType : supportedTypes) {
				if (supportedType.isAssignableFrom(type)) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public boolean supportsEventType(ResolvableType resolvableType) {
		return isAssignableFrom(resolvableType.getRawClass(), EVENT_TYPES);
	}
	@Override
	public boolean supportsSourceType(Class<?> sourceType) {
		return isAssignableFrom(sourceType, SOURCE_TYPES);
	}
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ApplicationEnvironmentPreparedEvent) {
			ConfigurableEnvironment environment = ((ApplicationEnvironmentPreparedEvent) event).getEnvironment();
			mapping.entrySet().forEach(e->{
				String value = environment.getProperty(e.getValue());
				if (value != null) {
					System.setProperty(e.getKey(), value);
				}
			});

		}
	}

	/**
	 * order 需要比 {@link LoggingApplicationListener} 稍早加载
	 * @return
	 */
	@Override
	public int getOrder() {
		return LoggingApplicationListener.DEFAULT_ORDER - 2;
	}

	static Map<String, String> mapping = new HashMap<>();
	static {
		mapping.put("LOG_ROLLING_FILE_PREFIX", "particle.logging.file.rolling-prefix");
		mapping.put("LOG_KAFKA_TOPIC", "particle.logging.kafka.topic");
		mapping.put("LOG_KAFKA_SERVER", "particle.logging.kafka.server");
	}
}

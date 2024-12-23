package com.particle.global.actuator;

import com.particle.global.actuator.monitor.IMonitor;
import com.particle.global.actuator.monitor.MicrometerMonitorImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 全局审计，指标监控自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-08-05 15:06
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan("com.particle.global.actuator.endpoint")
public class GlobalActuatorAutoConfiguration {

	/**
	 * 默认的monitor实现
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public IMonitor defaultIMonitor() {
		return new MicrometerMonitorImpl();
	}
}

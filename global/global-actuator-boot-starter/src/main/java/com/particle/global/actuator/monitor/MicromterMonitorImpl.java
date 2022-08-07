package com.particle.global.actuator.monitor;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 基于 micrometer 的指标监控实现
 * 在 spring 的 actuator 中默认集成了 micrometer，做为promethus的指标输出
 * </p>
 *
 * @author yangwei
 * @since 2021-08-24 15:26
 */
public class MicromterMonitorImpl implements IMonitor {

	@Autowired
	private MeterRegistry meterRegistry;


	@Override
	public void timer(String name, long durationMs, String description,String... tags) {
		Timer register = Timer.builder(name).description(description).tags(tags).register(meterRegistry);
		register.record(durationMs, TimeUnit.MILLISECONDS);
	}

	@Override
	public void count(String name, String description,String... tags) {
		Counter register = Counter.builder(name).description(description).tags(tags).register(meterRegistry);
		register.increment();
	}
}

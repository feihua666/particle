package com.particle.global.actuator.monitor;

/**
 * <p>
 * 监控监听器，主要用于 业务 组件中需要监控的场景
 * 比如限流监控数据记录，qps数据监控记录等
 * 只能有一个实例
 * </p>
 *
 * @author yangwei
 * @since 2021-08-13 09:46
 */
public interface IMonitor {
	/**
	 * 时间记录
	 * @param name
	 * @param durationMs
	 * @param description
	 * @param tags 要么填，要么长度是偶数，因为在micro meter内部限制，键值对形式处理
	 */
	void timer(String name,long durationMs,String description,String... tags);

	/**
	 * count
	 * @param name
	 * @param description
	 * @param tags 要么填，要么长度是偶数，因为在micro meter内部限制，键值对形式处理
	 */
	void count(String name,String description,String... tags);
}

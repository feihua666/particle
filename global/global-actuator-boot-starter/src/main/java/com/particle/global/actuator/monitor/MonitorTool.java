package com.particle.global.actuator.monitor;

import com.particle.global.tool.spring.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 监控工具调用类
 * 可能监控的地方比较多，省去频繁注入{@link IMonitor}的麻烦
 * </p>
 *
 * @author yangwei
 * @since 2021-08-18 22:21
 */
@Slf4j
public class MonitorTool {

	private static volatile IMonitor iMonitor;

	/**
	 * 获取监控实例
	 * @return
	 */
	public static IMonitor getIMonitorListener(){
		if (iMonitor == null) {
			synchronized(MonitorTool.class) {
				if (iMonitor == null) {
					try {
						iMonitor = SpringContextHolder.getBean(IMonitor.class);
					}catch (Exception e){
						log.warn("未从spring容器中获取到IMonitorListener实例");
						iMonitor = new IMonitor() {

							@Override
							public void timer(String name, long durationMs, String description,String... tags) {
								log.debug("monitor timer {} {} {}",name,durationMs,description);
							}

							@Override
							public void count(String name, String description,String... tags) {
								log.debug("monitor count {} {}",name,description);

							}
						};
					}
				}
			}
		}
		return iMonitor;
	}

	/**
	 * 计时监控
	 * @param name
	 * @param durationMs 持续时长 单位毫秒
	 * @param description
	 * @param tags 要么填，要么长度是偶数，因为在micro meter内部限制，键值对形式处理
	 */
	public static void timer(String name, long durationMs, String description,String... tags) {

		getIMonitorListener().timer(name,durationMs,description,tags);
	}

	/**
	 * 统计一次计数
	 * @param name
	 * @param description 描述信息
	 * @param tags 要么填，要么长度是偶数，因为在micro meter内部限制，键值对形式处理
	 */
	public static void count(String name, String description,String... tags) {
		getIMonitorListener().count(name,description,tags);

	}
}

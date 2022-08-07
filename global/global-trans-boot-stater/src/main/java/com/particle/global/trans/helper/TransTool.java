package com.particle.global.trans.helper;

import com.particle.global.actuator.monitor.IMonitor;
import com.particle.global.actuator.monitor.MonitorTool;
import com.particle.global.tool.spring.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 翻译静态工具类
 * </p>
 *
 * @author yangwei
 * @since 2022-08-07 10:45
 */
@Slf4j
public class TransTool {
	
	private static volatile TransHelper transHelper;


	/**
	 * 获取监控实例
	 * @return
	 */
	public static TransHelper getTransHelper(){
		if (transHelper == null) {
			synchronized(TransHelper.class) {
				if (transHelper == null) {
					transHelper = SpringContextHolder.getBean(TransHelper.class);
				}
			}
		}
		return transHelper;
	}

	/**
	 * 开始翻译
	 * @param body
	 * @return
	 */
	public static Object trans(Object body){
		if (transHelper == null) {
			getTransHelper();
		}
		return transHelper.trans(body);
	}
}

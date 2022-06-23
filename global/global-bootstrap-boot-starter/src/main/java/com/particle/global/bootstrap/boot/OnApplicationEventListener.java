package com.particle.global.bootstrap.boot;

import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * 自定义 application 事件监听
 * </p>
 *
 * @author yangwei
 * @since 2021-09-01 15:27
 */
public interface OnApplicationEventListener {

	public void onApplicationEvent(ApplicationEvent event);
}

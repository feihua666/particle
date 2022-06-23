package com.particle.global.bootstrap.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2021-09-01 15:25
 */
@Slf4j
@Component
public class CommonApplicationListener implements ApplicationListener<ApplicationEvent> {
	@Autowired(required = false)
	private List<OnApplicationEventListener> onApplicationEventListenerList;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		log.trace("application event {}",event.getSource());
		if (onApplicationEventListenerList != null) {
			for (OnApplicationEventListener onApplicationEventListener : onApplicationEventListenerList) {
				onApplicationEventListener.onApplicationEvent(event);
			}
		}

	}
}

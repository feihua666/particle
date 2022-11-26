package com.particle.global.bootstrap.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * spring 的关闭bean
 * </p>
 *
 * @author yangwei
 * @since 2021-08-31 10:02
 */
@Slf4j
@Component
public class OnApplicationShutdownPortalBean implements DisposableBean {

	@Autowired(required = false)
	List<OnApplicationShutdownListener> onApplicationShutdownListenerList;

	/**
	 * 应用关闭调用
	 * @throws Exception
	 */
	@Override
	public void destroy() throws Exception {
		log.debug("app shutdown，listener count={}", Optional.ofNullable(onApplicationShutdownListenerList).map(List::size).orElse(0));
		if (onApplicationShutdownListenerList != null) {
			for (OnApplicationShutdownListener onApplicationShutdownListener : onApplicationShutdownListenerList) {
				onApplicationShutdownListener.shutdown();
			}
		}
		log.debug("app shutdown destroy finished.");

	}
}

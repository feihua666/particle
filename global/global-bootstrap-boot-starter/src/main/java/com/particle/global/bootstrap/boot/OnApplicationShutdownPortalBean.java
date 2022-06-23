package com.particle.global.bootstrap.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
		log.info("应用关闭，调用关闭监听");
		if (onApplicationShutdownListenerList != null) {
			log.info("应用关闭，调用关闭监听，监听数量={}",onApplicationShutdownListenerList.size());
			for (OnApplicationShutdownListener onApplicationShutdownListener : onApplicationShutdownListenerList) {
				onApplicationShutdownListener.shutdown();
			}
		}else {
			log.info("应用关闭，调用关闭监听，无可用监听");
		}
	}
}

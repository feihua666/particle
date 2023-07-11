package com.particle.global.security.security;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <p>
 * spring context holder 帮助类
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 17:38
 */
public class ApplicationContextForSecurityHelper implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextForSecurityHelper.applicationContext = applicationContext;
	}

	protected static <T> T getBean(Class<T> targetClz) {
		T beanInstance = null;
		//优先按type查
		try {
			beanInstance = (T)applicationContext.getBean(targetClz);
		} catch (Exception e) {
		}
		//按name查
		if (beanInstance == null) {
			String simpleName = targetClz.getSimpleName();
			//首字母小写
			simpleName = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
			beanInstance = (T)applicationContext.getBean(simpleName);
		}
		if (beanInstance == null) {
			throw new RuntimeException("Component " + targetClz + " can not be found in Spring Container");
		}
		return beanInstance;
	}

	public static Object getBean(String claz) {
		return ApplicationContextForSecurityHelper.applicationContext.getBean(claz);
	}

	public static <T> T getBean(String name, Class<T> requiredType) {
		return ApplicationContextForSecurityHelper.applicationContext.getBean(name, requiredType);
	}

	public static <T> T getBean(Class<T> requiredType, Object... params) {
		return ApplicationContextForSecurityHelper.applicationContext.getBean(requiredType, params);
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}

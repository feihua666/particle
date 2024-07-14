package com.particle.global.tool.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 以静态变量保存Spring ApplicationContext,
 * 可在任何代码任何地方任何时候取出ApplicaitonContext.
 * @author yangwei
 * @since 2022-04-13 19:52
 */
@Component(SpringContextHolder.springContextHolderName)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

	public final static String springContextHolderName = "springContextHolder";

	private static ApplicationContext applicationContext = null;

	private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}
	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBeanByName(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}
	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}
	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 * 在groovy脚本中不太好识别重载方式，这里单独写一个
	 */
	public static <T> T getBeanByType(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}

	/**
	 * 获取多个bean
	 * @param requiredType
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> getBeans(Class<T> requiredType) {
		assertContextInjected();
		Map<String, T> beansOfType = applicationContext.getBeansOfType(requiredType);
		if (beansOfType != null) {
			Set<Map.Entry<String, T>> entries = beansOfType.entrySet();
			List<T> result = new ArrayList<>(entries.size());
			for (Map.Entry<String, T> stringTEntry : entries) {
				result.add(stringTEntry.getValue());
			}
			return result;
		}
		return null;
	}

	/**
	 * 清除SpringContextHolder中的ApplicationContext为Null.
	 */
	public static void clearHolder() {
		if (logger.isDebugEnabled()){
			logger.debug("clear ApplicationContext:" + applicationContext);
		}
		applicationContext = null;
	}
	/**
	 * 实现ApplicationContextAware接口, 注入Context到静态变量中.
	 */
	@Autowired
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextHolder.applicationContext = applicationContext;
	}

	/**
	 * 实现DisposableBean接口, 在Context关闭时清理静态变量.
	 */
	@Override
	public void destroy() throws Exception {
		SpringContextHolder.clearHolder();
	}

	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void assertContextInjected() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext is not be injected,please config SpringContextHolder in applicationContext.xml");
		}
	}
}
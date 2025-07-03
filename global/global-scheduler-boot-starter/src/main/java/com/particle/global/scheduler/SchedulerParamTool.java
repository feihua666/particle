package com.particle.global.scheduler;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.particle.global.tool.thread.ThreadContextTool;


/**
 * <p>
 * 任务计划线程工具
 * 添加目的主要是为了任务计划手动执行时的参数传递
 * </p>
 *
 * @author yangwei
 * @since 2023-07-05 14:37
 */
public class SchedulerParamTool {

	private static String currentSchedulerParamKey = "currentSchedulerParamKey";


	/**
	 * 获取值，一般用来获取字符串
	 * @return
	 */
	public static String get() {
		return ((String) ThreadContextTool.get(currentSchedulerParamKey));
	}

	/**
	 * 设置字符串值
	 * @param str
	 */
	public static void set(String str) {
		ThreadContextTool.put(currentSchedulerParamKey,str);
	}

	public static void clear() {
		ThreadContextTool.remove(currentSchedulerParamKey);
	}
	/**
	 * 如果 {@link SchedulerParamTool#set(java.lang.String)} 方法设置的是一个字符器且可以转为对象，那么可以使用该方法
	 * @param beanClass
	 * @param <T>
	 * @return
	 */
	public static  <T> T getBean(Class<T> beanClass) {
		String str = get();
		if (StrUtil.isEmpty(str)) {
			return null;
		}
		return JSONUtil.toBean(str, beanClass);
	}
}

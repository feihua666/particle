package com.particle.global.notification.notify;

import com.particle.global.tool.spring.SpringContextHolder;

/**
 * <p>
 * 通知工具，方便调用，而不用省去频繁注入的麻烦
 * 依赖 {@link NotifyHelper}
 * </p>
 *
 * @author yangwei
 * @since 2021-08-18 22:35
 */
public class NotifyTool {

	private static volatile NotifyHelper notifyHelper;

	/**
	 * 获取notifyHelper帮忙类
	 * @return
	 */
	public static NotifyHelper getNotifyHelper() {
		if (notifyHelper == null) {
			synchronized (NotifyTool.class){
				if(notifyHelper == null){
					notifyHelper = SpringContextHolder.getBean(NotifyHelper.class);
				}
			}
		}
		return notifyHelper;
	}

	public static void notify(NotifyParam notifyParam){
		getNotifyHelper().notify(notifyParam);
	}

}

package com.particle.global.notification.sms;

import com.particle.global.notification.notify.NotifyParam;
import com.particle.global.tool.sms.SmsAccount;

/**
 * <p>
 * 短信通知处监听器，提取该接口的好处是可以实现针对不同的厂商做兼容
 * </p>
 *
 * @author yangwei
 * @since 2023-05-24 13:25
 */
public interface ISmsNotifyHandlerListener {

	/**
	 * 是否支持厂商账号
	 * @param smsAccount
	 * @return
	 */
	boolean support(SmsAccount smsAccount);

	/**
	 * 发送
	 * @param notifyParam
	 */
	void doNotify(NotifyParam notifyParam);
}

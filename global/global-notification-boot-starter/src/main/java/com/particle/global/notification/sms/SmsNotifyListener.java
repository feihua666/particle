package com.particle.global.notification.sms;

import com.particle.global.notification.GlobalNotificationProperties;
import com.particle.global.notification.notify.AbstractNotifyListener;
import com.particle.global.notification.notify.NotifyParam;
import com.particle.global.tool.sms.SmsAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 短信通知实现
 * </p>
 *
 * @author yangwei
 * @since 2023-05-22 17:19
 */
@Slf4j
public class SmsNotifyListener extends AbstractNotifyListener {

	@Autowired
	private GlobalNotificationProperties globalNotificationProperties;
	@Autowired(required = false)
	private List<ISmsNotifyHandlerListener> smsNotifyHandlerListeners;

	@Override
	protected String supportType() {
		return NotifyParam.Type.sms.name();
	}

	@Override
	public void doNotify(NotifyParam notifyParam) {
		NotifyParam.SmsParam smsParam = notifyParam.getSmsParam();
		if (smsParam == null) {
			return;
		}
		SmsAccount smsAccount = smsParam.getSmsAccount();
		if (smsAccount == null) {
			smsAccount = globalNotificationProperties.getSms();
		}
		if (smsAccount == null) {
			throw new RuntimeException("no smsAccount was found");
		}
		if (smsNotifyHandlerListeners != null) {
			for (ISmsNotifyHandlerListener smsNotifyHandlerListener : smsNotifyHandlerListeners) {
				if (smsNotifyHandlerListener.support(smsAccount)) {
					smsNotifyHandlerListener.doNotify(notifyParam);
				}
			}
		}else {
			if (smsAccount == null) {
				throw new RuntimeException("no smsNotifyHandlerListeners was found");
			}
		}
	}
}

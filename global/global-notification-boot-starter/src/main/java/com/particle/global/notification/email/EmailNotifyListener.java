package com.particle.global.notification.email;

import cn.hutool.extra.mail.GlobalMailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.particle.global.notification.GlobalNotificationProperties;
import com.particle.global.notification.notify.AbstractNotifyListener;
import com.particle.global.notification.notify.NotifyParam;
import com.particle.global.tool.email.EmailAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 邮件通知实现
 * </p>
 *
 * @author yangwei
 * @since 2023-05-22 17:19
 */
@Slf4j
public class EmailNotifyListener extends AbstractNotifyListener {

	@Autowired
	private GlobalNotificationProperties globalNotificationProperties;

	@Override
	protected String supportType() {
		return NotifyParam.Type.email.name();
	}

	@Override
	public void doNotify(NotifyParam notifyParam) {
		NotifyParam.EmailParam emailParam = notifyParam.getEmailParam();
		if (emailParam == null) {
			log.warn("email notify ignored. because of emailParam is null");
			return;
		}
		EmailAccount mailAccount = emailParam.getEmailAccount();
		if (mailAccount == null) {
			mailAccount = globalNotificationProperties.getEmail();
		}
		if (mailAccount == null) {
			mailAccount = EmailAccount.createByMailAccount(GlobalMailAccount.INSTANCE.getAccount());
		}
		// 没有邮件账号，发送不到，可以使用 yml配置或发送通知时指定邮件账号
		if (mailAccount == null) {
			throw new RuntimeException("no mailAccount was found. maybe you can config in yml or pass a EmailAccount instance param");
		}
		emailParam.setEmailAccount(mailAccount);
		MailUtil.send(
				mailAccount,
				notifyParam.getToUser(),
				emailParam.getToCcUser(),
				emailParam.getToBccUser(),
				notifyParam.getTitle(),
				notifyParam.getContent(),
				notifyParam.getIsHtml());
	}
}

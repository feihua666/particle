package com.particle.global.notification;

import com.particle.global.notification.alert.AlertAccount;
import com.particle.global.tool.email.EmailAccount;
import com.particle.global.tool.sms.SmsAccount;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 全局通知配置
 * </p>
 *
 * @author yangwei
 * @since 2023-05-23 18:18
 */
@Data
@ConfigurationProperties(prefix = "particle.notification")
public class GlobalNotificationProperties {

	/**
	 * 邮箱发送配置
	 */
	private EmailAccount email;
	/**
	 * 短信发送配置
	 */
	private SmsAccount sms;
	/**
	 * 报警
	 */
	private AlertAccount alert;

}

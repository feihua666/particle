package com.particle.global.notification.sms.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 单独针对三方的短信实现配置，如果不需要方便排除
 * </p>
 *
 * @author yangwei
 * @since 2023-05-25 10:14
 */
@Configuration
public class GlobalNotificationThirdSmsAutoConfiguration {

	@Bean
	public JuheSmsNotifyHandlerListener juheSmsNotifyHandlerListener(){
		return new JuheSmsNotifyHandlerListener();
	}
}

package com.particle.global.captcha.endpoint;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * <p>
 * 主要是动态验证码通知时，根据场景值获取通知标识配置
 * </p>
 *
 * @author yangwei
 * @since 2023-05-25 09:28
 */
@Data
@ConfigurationProperties(prefix = "particle.dynamic-captcha")
public class DynamicCaptchaNotifyProperties {
	/**
	 * key为场景值 即：{@link CaptchaGenCommand#captchaScene}
	 * value 为 {@link DynamicCaptchaGenCommand#notifyIdentifier}
	 */
	private Map<String,String> notifyIdentifiers;


	/**
	 * 获取 notifyIdentifier
	 * @param captchaScene 场景值
	 * @param defaultNotifyIdentifier 默认的，如果获取不到将返回默认的
	 * @return
	 */
	public String getNotifyIdentifier(String captchaScene, String defaultNotifyIdentifier) {
		if (notifyIdentifiers == null) {
			return defaultNotifyIdentifier;
		}
		if (!notifyIdentifiers.containsKey(captchaScene)) {
			return defaultNotifyIdentifier;
		}
		return notifyIdentifiers.get(captchaScene);
	}
}

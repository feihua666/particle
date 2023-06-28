package com.particle.global.notification.alert;

import com.particle.global.tool.proxy.ProxyConfig;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 报警通知配置，结尾以 account 结尾，延用了 email和sms一致风格
 * </p>
 *
 * @author yangwei
 * @since 2023-06-28 16:02
 */
@Data
public class AlertAccount implements Serializable {

	/**
	 * 代理配置
	 */
	private ProxyConfig proxyConfig;
	/**
	 * 企业微信webhooks
	 * 企业微信群机器人
	 */
	private List<String> wxcpWebhooks;
}

package com.particle.global.tool.email;

import cn.hutool.extra.mail.MailAccount;
import com.particle.global.tool.proxy.ProxyConfig;
import lombok.Data;

import java.util.Properties;

/**
 * <p>
 * 邮件账号
 * </p>
 *
 * @author yangwei
 * @since 2023-05-24 13:17
 */
@Data
public class EmailAccount extends MailAccount {

	/**
	 * 代理
	 */
	private ProxyConfig proxy;

	public static EmailAccount createByMailAccount(MailAccount mailAccount) {
		EmailAccount emailAccount = new EmailAccount();
		return emailAccount;
	}

	/**
	 * 重写主要使用代理设置
	 * @return
	 */
	@Override
	public Properties getSmtpProps() {
		Properties properties = super.getSmtpProps();
		ProxyConfig proxy = this.proxy;
		if (proxy == null) {
			proxy = ProxyConfig.proxyConfig();
			if (proxy != null) {
				boolean useProxy = proxy.getUseProxy()!=null && proxy.getUseProxy();
				if (!useProxy) {
					proxy = null;
				}
			}
			if (proxy != null) {
				boolean asDefault = proxy.getAsDefault()!=null && proxy.getAsDefault();
				if (!asDefault) {
					proxy = null;
				}
			}
		}

		if (proxy != null) {
			properties.put("mail.smtp.proxy.host", proxy.getProxyAddress());
			properties.put("mail.smtp.proxy.port", proxy.getProxyPort());
			properties.put("mail.smtp.proxy.auth", true);
			properties.put("mail.smtp.proxy.user", proxy.getProxyUsername());
			properties.put("mail.smtp.proxy.password", proxy.getProxyPassword());
		}

		return properties;
	}
}

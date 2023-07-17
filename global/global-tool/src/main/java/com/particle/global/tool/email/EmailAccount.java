package com.particle.global.tool.email;

import cn.hutool.extra.mail.MailAccount;
import com.particle.global.tool.proxy.ProxyConfig;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

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
		ProxyConfig proxy = ProxyConfig.finalProxyConfig(this.proxy);
		if (proxy != null) {
			// 默认使用http代理
			if (Strings.isEmpty(proxy.getProxyType()) || ProxyConfig.ProxyType.http.name().equals(proxy.getProxyType())) {
				properties.put("mail.smtp.proxy.host", proxy.getProxyAddress());
				properties.put("mail.smtp.proxy.port", proxy.getProxyPort());
			}else {
				// 否则使用socket 代理
				properties.put("mail.smtp.socks.host", proxy.getProxyAddress());
				properties.put("mail.smtp.socks.port", proxy.getProxyPort());
			}
			properties.put("mail.smtp.proxy.user", proxy.getProxyUsername());
			properties.put("mail.smtp.proxy.password", proxy.getProxyPassword());
		}

		return properties;
	}
}

package com.particle.global.tool.email;

import cn.hutool.extra.mail.MailAccount;
import com.particle.global.tool.proxy.ProxyConfig;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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
	/**
	 * 设置默认证器,目前在socket代理需要认证时，还没有找到怎么设置代理认证，可以使用该设置强制全局认证，但这样可能有并发问题（如果在一个环境只有一个socket代理是没问题的）
	 */
	private Boolean setDefaultAuthenticatorForProxy;

	public static EmailAccount createByMailAccount(MailAccount mailAccount) {
		return MailAccountMapping.instance.toEmailAccount(mailAccount);
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
				properties.put("mail.smtp.proxy.user", proxy.getProxyUsername());
				properties.put("mail.smtp.proxy.password", proxy.getProxyPassword());
			}else {
				// 否则使用socket 代理
				properties.put("mail.smtp.socks.host", proxy.getProxyAddress());
				properties.put("mail.smtp.socks.port", proxy.getProxyPort());
				properties.put("mail.smtp.socks.username", proxy.getProxyUsername());
				properties.put("mail.smtp.socks.password", proxy.getProxyPassword());
			}
			if (setDefaultAuthenticatorForProxy != null && setDefaultAuthenticatorForProxy) {
				MyJavaAuthenticator authenticator = new MyJavaAuthenticator(proxy.getProxyUsername(), proxy.getProxyPassword());
				java.net.Authenticator.setDefault(authenticator);
			}
		}

		return properties;
	}
	static class MyJavaAuthenticator extends java.net.Authenticator {
		private String user = "";
		private String password = "";

		public MyJavaAuthenticator(String user, String password) {
			this.user = user;
			this.password = password;
		}

		@Override
		protected java.net.PasswordAuthentication getPasswordAuthentication() {
			return new java.net.PasswordAuthentication(user, password.toCharArray());
		}

	}

	@Mapper
	interface  MailAccountMapping{
		MailAccountMapping instance = Mappers.getMapper( MailAccountMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param mailAccount
		 */
		EmailAccount toEmailAccount(MailAccount mailAccount);
	}
}

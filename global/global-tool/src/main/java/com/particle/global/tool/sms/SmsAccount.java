package com.particle.global.tool.sms;

import com.particle.global.tool.proxy.ProxyConfig;
import com.particle.global.tool.str.NetPathTool;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 短信账号，用于短信通知使用，设置一个通用账号
 * 主要是参考 {@link com.particle.global.tool.email.EmailAccount} 账号一样
 * 主到 global-tool 包中方便项目使用，保证都能够引用的到
 * </p>
 *
 * @author yangwei
 * @since 2023-05-24 13:04
 */
@Data
public class SmsAccount implements Serializable {

	/**
	 * 请求地址 不要以 / 结尾
	 * 一般对接第三方需要有一个请求地址
	 * 如：http://example.com
	 */
	private String domain;
	/**
	 * 用户账号
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 渠道，一个字符串标识是哪一家的
	 */
	private String channel;


	/**
	 * 代理
	 */
	private ProxyConfig proxy;

	public String concatDomain(String urlSuffix) {
		return NetPathTool.concat(domain, urlSuffix);
	}
}

package com.particle.global.tool.proxy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * <p>
 * 代理配置对象
 * </p>
 *
 * @author yangwei
 * @since 2023-05-24 14:54
 */
@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "particle.proxy")
public class ProxyConfig implements InitializingBean, Serializable {

	private static ProxyConfig proxyConfig = null;

	/**
	 * 是否使用代理
	 */

	private Boolean useProxy;
	/**
	 * 代理地址
	 */
	private String proxyAddress;
	/**
	 * 代理端口
	 */
	private String proxyPort;
	/**
	 * 代理用户
	 */
	private String proxyUsername;
	/**
	 * 代理密码
	 */
	private String proxyPassword;

	/**
	 * 是否便衣全局默认的代理
	 */
	private Boolean asDefault;

	// spring 初始化
	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.useProxy != null) {
			proxyConfig = this;
		}
	}

	/**
	 * 以静态方法获取代理配置，支持spring自动注入配置
	 * @return
	 */
	public static ProxyConfig proxyConfig(){
		return proxyConfig;
	}

	/**
	 * 静态初始化
	 */
	static {
		String useProxy = System.getProperty("particle.proxy.useProxy");
		String proxyAddress = System.getProperty("particle.proxy.proxyAddress");
		String proxyPort = System.getProperty("particle.proxy.proxyPort");
		String proxyUsername = System.getProperty("particle.proxy.proxyUsername");
		String proxyPassword = System.getProperty("particle.proxy.proxyPassword");
		String asDefault = System.getProperty("particle.proxy.asDefault");
		if (useProxy != null) {
			ProxyConfig proxyConfig = new ProxyConfig();
			proxyConfig.setUseProxy(Boolean.valueOf(useProxy));
			proxyConfig.setProxyAddress(proxyAddress);
			proxyConfig.setProxyPort(proxyPort);
			proxyConfig.setProxyUsername(proxyUsername);
			proxyConfig.setProxyPassword(proxyPassword);
			if (asDefault != null) {
				proxyConfig.setAsDefault(Boolean.valueOf(asDefault));
			}
			ProxyConfig.proxyConfig = proxyConfig;
		}
	}
}

package com.particle.global.wxjava.cp;

import com.particle.global.wxjava.cp.config.WxCpConfiguration;
import com.particle.global.wxjava.cp.config.WxCpProperties;
import com.particle.global.wxjava.cp.handler.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>
 * 企业微信自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-06-28 15:24
 */
@Configuration
@Import(WxCpConfiguration.class)
@EnableConfigurationProperties(WxCpProperties.class)
@ConditionalOnProperty(prefix = "particle.wxcp", name = "enabled", havingValue = "true", matchIfMissing = false)
public class WxCpAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public ContactChangeHandler contactChangeHandler() {
		return new ContactChangeHandler();
	}

	@Bean
	@ConditionalOnMissingBean
	public EnterAgentHandler enterAgentHandler() {
		return new EnterAgentHandler();
	}
	@Bean
	@ConditionalOnMissingBean
	public LocationHandler locationHandler() {
		return new LocationHandler();
	}
	@Bean
	@ConditionalOnMissingBean
	public LogHandler logHandler() {
		return new LogHandler();
	}
	@Bean
	@ConditionalOnMissingBean
	public MenuClickHandler menuClickHandler() {
		return new MenuClickHandler();
	}
	@Bean
	@ConditionalOnMissingBean
	public MenuHandler menuHandler() {
		return new MenuHandler();
	}
	@Bean
	@ConditionalOnMissingBean
	public MsgHandler msgHandler() {
		return new MsgHandler();
	}
	@Bean
	@ConditionalOnMissingBean
	public ScanHandler scanHandler() {
		return new ScanHandler();
	}
	@Bean
	@ConditionalOnMissingBean
	public SubscribeHandler subscribeHandler() {
		return new SubscribeHandler();
	}
	@Bean
	@ConditionalOnMissingBean
	public UnsubscribeHandler unsubscribeHandler() {
		return new UnsubscribeHandler();
	}
}

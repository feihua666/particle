package com.particle.global.oss.client.local;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 * local配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-28 09:51:58
 */
@EnableConfigurationProperties({GlobalLocalOssProperties.class})
public class GlobalLocalConfiguration {

	@Bean
	public GlobalLocalOssClient globalLocalOssClient(GlobalLocalOssProperties globalLocalOssProperties){
		return new GlobalLocalOssClient(globalLocalOssProperties);
	}

}

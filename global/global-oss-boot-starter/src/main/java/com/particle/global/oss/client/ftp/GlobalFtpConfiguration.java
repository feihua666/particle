package com.particle.global.oss.client.ftp;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 * ftp 配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-28 09:51:58
 */
@EnableConfigurationProperties({GlobalFtpOssProperties.class})
public class GlobalFtpConfiguration {

	@Bean
	public GlobalFtpOssClient globalFtpOssClient(GlobalFtpOssProperties globalFtpOssProperties){
		return new GlobalFtpOssClient(globalFtpOssProperties);
	}

}

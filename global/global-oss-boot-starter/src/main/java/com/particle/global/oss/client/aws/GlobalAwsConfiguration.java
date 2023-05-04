package com.particle.global.oss.client.aws;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 * aws配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-26 18:25
 */
@EnableConfigurationProperties({GlobalAwsOssProperties.class})
public class GlobalAwsConfiguration {

	@Bean
	public GlobalAwsOssClient globalAwsOssClient(GlobalAwsOssProperties globalAwsOssProperties){
		return new GlobalAwsOssClient(globalAwsOssProperties);
	}

}

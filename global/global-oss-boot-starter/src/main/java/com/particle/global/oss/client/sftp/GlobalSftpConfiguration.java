package com.particle.global.oss.client.sftp;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 * sftp 配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-28 09:51:58
 */
@EnableConfigurationProperties({GlobalSftpOssProperties.class})
public class GlobalSftpConfiguration {

	@Bean
	public GlobalSftpOssClient globalSftpOssClient(GlobalSftpOssProperties globalSftpOssProperties){
		return new GlobalSftpOssClient(globalSftpOssProperties);
	}

}

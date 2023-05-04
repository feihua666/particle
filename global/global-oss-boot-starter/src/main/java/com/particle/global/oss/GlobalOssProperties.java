package com.particle.global.oss;

import com.particle.global.oss.client.aws.GlobalAwsOssProperties;
import com.particle.global.oss.client.ftp.GlobalFtpOssProperties;
import com.particle.global.oss.client.local.GlobalLocalOssProperties;
import com.particle.global.oss.client.sftp.GlobalSftpOssProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

import static com.particle.global.oss.service.DefaultGlobalOssClientServiceImpl.defaultClientKey;

/**
 * <p>
 * 全局对象存储属性配置
 * </p>
 *
 * @author yangwei
 * @since 2023-04-27 23:05
 */
@Data
@ConfigurationProperties("particle.oss")
public class GlobalOssProperties {
	/**
	 * 默认的 client key
	 */
	private String defaultClient = defaultClientKey;

	/**
	 * 上传成功后，是否拼接 端点路径
	 */
	private Boolean concatEndpoint = false;
	/**
	 * aws客户端配置
	 * key = client key
	 */
	private Map<String, GlobalAwsOssProperties> aws;
	/**
	 * ftp客户端配置
	 */
	private Map<String, GlobalFtpOssProperties> ftp;
	/**
	 * sftp客户端配置
	 */
	private Map<String, GlobalSftpOssProperties> sftp;
	/**
	 * 本地客户端配置
	 */
	private Map<String, GlobalLocalOssProperties> local;
}

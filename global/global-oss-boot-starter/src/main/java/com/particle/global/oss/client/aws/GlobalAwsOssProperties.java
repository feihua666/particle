package com.particle.global.oss.client.aws;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * aws 配置
 * </p>
 *
 * @author yangwei
 * @since 2023-04-26 18:11
 */
@Data
@ConfigurationProperties("particle.oss.aws")
public class GlobalAwsOssProperties {

	/**
	 * 对象存储服务的URL
	 */
	private String endpoint;

	/**
	 * 内网地址，内网地址主要用来上传
	 */
	private String innerEndpoint;

	/**
	 * 区域
	 */
	private String region;

	/**
	 * 默认的桶
	 */
	private String bucketName;

	/**
	 * Access key
	 */
	private String accessKey;

	/**
	 * Secret key
	 */
	private String secretKey;

	/**
	 * 最大线程数，默认：100
	 */
	private Integer maxConnections = 100;


	/**
	 * s3Client serviceConfiguration 使用
	 */
	private Boolean accelerateModeEnabled = false;
	private Boolean checksumValidationEnabled = false;
	private Boolean multiRegionEnabled = false;
	private Boolean chunkedEncodingEnabled = false;
	/**
	 * true path-style nginx 反向代理和S3默认支持 pathStyle模式 {http://endpoint/bucketname}
	 * false supports virtual-hosted-style 阿里云等需要配置为 virtual-hosted-style 模式{http://bucketname.endpoint}
	 * 只是url的显示不一样
	 */
	private Boolean pathStyleAccessEnabled = true;
	private Boolean useArnRegionEnabled = false;



	private Boolean fipsEnabled = false;
	private Boolean dualstackEnabled = false;
}

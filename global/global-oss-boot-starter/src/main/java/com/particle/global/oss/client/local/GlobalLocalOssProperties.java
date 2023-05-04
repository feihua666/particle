package com.particle.global.oss.client.local;

import com.particle.global.oss.endpoint.OssController;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * local 配置
 * </p>
 *
 * @author yangwei
 * @since 2023-04-28 09:54:58
 */
@Data
@ConfigurationProperties("particle.oss.local")
public class GlobalLocalOssProperties {

	/**
	 * 对象访问访问前缀
	 * 如：http://localhost:8080/ + {@link OssController#API_DOWNLOAD_PREFIX}
	 */
	private String endpoint;

	/**
	 * 数据存储路径,好文使用绝对地址：
	 * window: D:\\data
	 * linux: /data
	 */
	private String basePath;

	/**
	 * 默认的桶,沿用了aws桶的概念，实际上是一个 {@link GlobalLocalOssProperties#basePath}的子一级目录
	 * 如：user，请使用小写符合文件夹命令规则
	 */
	private String bucketName;

}

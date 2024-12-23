package com.particle.global.oss.client.ftp;

import cn.hutool.extra.ftp.FtpMode;
import com.particle.global.oss.client.PoolProperties;
import com.particle.global.oss.client.local.GlobalLocalOssProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * ftp 配置
 * </p>
 *
 * @author yangwei
 * @since 2023-04-28 09:54:58
 */
@Data
@ConfigurationProperties("particle.oss.ftp")
public class GlobalFtpOssProperties extends GlobalLocalOssProperties {
	/**
	 * 主机
	 */
	private String host;
	/**
	 * 端口
	 */
	private int port;
	/**
	 * 用户名
	 */
	private String user;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 编码
	 */
	private String charset = "utf-8";

	/**
	 * FTP连接模式,默认被动
	 */
	private FtpMode mode = FtpMode.Passive;

	/**
	 * 连接超时时长，单位毫秒，默认5秒
	 */
	private long connectionTimeout = 5 * 1000;
	/**
	 * Socket连接超时时长，单位毫秒,默认1分钟
	 */
	private long socketTimeout = 1 * 60 * 1000;

	/**
	 * 是否使用池化，并发场景需要设置为true
	 */
	private Boolean usePool = true;
	/**
	 * 池配置
	 */
	private PoolProperties pool = new PoolProperties();
}

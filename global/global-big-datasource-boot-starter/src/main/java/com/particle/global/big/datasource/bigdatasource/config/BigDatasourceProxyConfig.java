package com.particle.global.big.datasource.bigdatasource.config;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

/**
 * <p>
 * 大数据源代理配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-03-28 19:57
 */
@Data
public class BigDatasourceProxyConfig extends DTO {
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
}

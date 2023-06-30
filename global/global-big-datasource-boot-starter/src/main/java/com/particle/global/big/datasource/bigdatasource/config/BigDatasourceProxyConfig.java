package com.particle.global.big.datasource.bigdatasource.config;

import com.particle.global.dto.basic.DTO;
import com.particle.global.tool.proxy.ProxyConfig;
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
	 * 代表配置
	 */
	private ProxyConfig proxyConfig;
}

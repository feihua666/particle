package com.particle.dataquery.domain.datasource.value;

import com.particle.global.dto.basic.Value;
import com.particle.global.tool.proxy.ProxyConfig;
import lombok.Data;

/**
 * <p>
 * 数据查询数据源代理配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-29 00:37:49
 */
@Data
public class DataQueryDatasourceProxyConfig extends Value {

	/**
	 * 代理配置
	 */
	private ProxyConfig proxyConfig;
}

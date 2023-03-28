package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import com.particle.global.dto.basic.Value;
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

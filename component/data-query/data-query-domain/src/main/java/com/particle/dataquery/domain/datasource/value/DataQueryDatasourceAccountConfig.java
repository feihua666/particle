package com.particle.dataquery.domain.datasource.value;

import lombok.Data;

/**
 * <p>
 * 数据查询数据源账号密码配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-29 00:37:42
 */
@Data
public class DataQueryDatasourceAccountConfig extends DataQueryDatasourceProxyConfig {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
}

package com.particle.global.big.datasource.bigdatasource.impl.jdbc.config;

import com.particle.global.big.datasource.bigdatasource.config.BigDatasourceAccountConfig;
import lombok.Data;

/**
 * <p>
 * jdbc大数据源配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 13:50
 */
@Data
public class JdbcBigDatasourceConfig extends BigDatasourceAccountConfig {

	/**
	 * 驱动类名
	 */
	private String driverClassName;
	/**
	 * 地址
	 */
	private String url;


	public static JdbcBigDatasourceConfig create(String driverClassName, String url, String username, String password) {
		JdbcBigDatasourceConfig jdbcBigDatasourceConfig = new JdbcBigDatasourceConfig();
		jdbcBigDatasourceConfig.setUrl(driverClassName);
		jdbcBigDatasourceConfig.setUrl(url);
		jdbcBigDatasourceConfig.setUsername(username);
		jdbcBigDatasourceConfig.setPassword(password);

		return jdbcBigDatasourceConfig;
	}
}

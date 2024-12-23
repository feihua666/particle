package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import lombok.Data;

/**
 * <p>
 * jdbc数据源配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-17 23:24
 */
@Data
public class DataQueryDatasourceJdbcConfig extends DataQueryDatasourceAccountConfig {

	private String driverClassName;
	/**
	 * 地址
	 */
	private String url;

	public static DataQueryDatasourceJdbcConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceJdbcConfig dataQueryDatasourceJdbcConfig = JSONUtil.toBean(jsonStr, DataQueryDatasourceJdbcConfig.class);
		return dataQueryDatasourceJdbcConfig;
	}
}

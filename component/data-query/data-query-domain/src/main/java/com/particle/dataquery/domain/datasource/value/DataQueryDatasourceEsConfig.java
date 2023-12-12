package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import lombok.Data;

/**
 * <p>
 * es数据源配置
 * </p>
 *
 * @author yangwei
 * @since 2023-12-12 10:49:03
 */
@Data
public class DataQueryDatasourceEsConfig extends DataQueryDatasourceAccountConfig {
	/**
	 * 地址,支持集群，多个以逗号分隔如：http://localhost:9200
	 */
	private String uris;

	public static DataQueryDatasourceEsConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceEsConfig dataQueryDatasourceJdbcConfig = JSONUtil.toBean(jsonStr, DataQueryDatasourceEsConfig.class);
		return dataQueryDatasourceJdbcConfig;
	}
}

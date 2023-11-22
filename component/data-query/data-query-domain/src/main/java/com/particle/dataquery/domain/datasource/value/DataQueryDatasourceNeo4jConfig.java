package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import lombok.Data;

/**
 * <p>
 * neo4j数据源配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-17 23:24
 */
@Data
public class DataQueryDatasourceNeo4jConfig extends DataQueryDatasourceAccountConfig {
	/**
	 * 地址
	 */
	private String uri;

	public static DataQueryDatasourceNeo4jConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceNeo4jConfig dataQueryDatasourceJdbcConfig = JSONUtil.toBean(jsonStr, DataQueryDatasourceNeo4jConfig.class);
		return dataQueryDatasourceJdbcConfig;
	}
}

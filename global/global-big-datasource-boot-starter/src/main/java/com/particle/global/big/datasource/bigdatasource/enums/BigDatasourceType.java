package com.particle.global.big.datasource.bigdatasource.enums;

/**
 * <p>
 * 大数据源类型
 * </p>
 *
 * @author yangwei
 * @since 2023-03-09 11:30
 */
public enum BigDatasourceType {
	/**
	 * jdbc数据源
	 */
	datasource_jdbc,
	/**
	 * redis数据源
	 */
	datasource_redis,
	/**
	 * es数据源
	 */
	datasource_es,
	/**
	 * neo4j数据源
	 */
	datasource_neo4j,
	/**
	 * http数据源
	 */
	datasource_http;
}

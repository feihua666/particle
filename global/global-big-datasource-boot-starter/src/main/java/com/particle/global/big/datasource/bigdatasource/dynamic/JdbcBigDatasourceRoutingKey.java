package com.particle.global.big.datasource.bigdatasource.dynamic;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;

/**
 * <p>
 * 动态大数据源子路由键
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 10:39
 */
public interface JdbcBigDatasourceRoutingKey<K> extends DynamicBigDatasourceRoutingKey<K> {
	/**
	 * 子路由键,该值应该是 {@link DynamicRoutingDataSource} 的 dataSourceName
	 * 主要是为支持动态多数据源 {@link DynamicRoutingDataSource}
	 * @return
	 */
	String subKey();
}

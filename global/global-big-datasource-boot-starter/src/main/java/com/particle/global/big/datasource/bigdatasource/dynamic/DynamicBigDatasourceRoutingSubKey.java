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
public interface DynamicBigDatasourceRoutingSubKey {
	/**
	 * 子路由键
	 * 主要是为支持动态多数据源 {@link DynamicRoutingDataSource}
	 * @return
	 */
	String subKey();
}

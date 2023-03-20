package com.particle.global.big.datasource.bigdatasource.dynamic;

import com.particle.global.big.datasource.bigdatasource.BigDatasource;

/**
 * <p>
 * 动态数据源路由器
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 10:37
 */
public interface DynamicBigDatasourceRouter {

	/**
	 * 路由大数据源
	 * @param routingKey
	 * @return
	 */
	BigDatasource routing(DynamicBigDatasourceRoutingKey routingKey);
}

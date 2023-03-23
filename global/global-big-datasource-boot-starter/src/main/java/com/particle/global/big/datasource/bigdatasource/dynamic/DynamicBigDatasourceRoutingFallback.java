package com.particle.global.big.datasource.bigdatasource.dynamic;

import com.particle.global.big.datasource.bigdatasource.BigDatasource;

/**
 * <p>
 * 在路由失败时处理，一般情况可能没初始化数据源，提供一次补救措施
 * </p>
 *
 * @author yangwei
 * @since 2023-03-22 23:25
 */
public interface DynamicBigDatasourceRoutingFallback {

	/**
	 * 在路由失败时可以支持动态加载数据源
	 * @param routingKey
	 * @return 返回 null 表示不处理
	 */
	BigDatasource routedFailedFallback(DynamicBigDatasourceRoutingKey routingKey);
}

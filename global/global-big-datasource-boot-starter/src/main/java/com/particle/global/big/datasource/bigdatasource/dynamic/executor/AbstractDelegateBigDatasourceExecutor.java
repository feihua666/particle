package com.particle.global.big.datasource.bigdatasource.dynamic.executor;

import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRouter;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKeyHolder;

/**
 * <p>
 * 代理基类
 * </p>
 *
 * @author yangwei
 * @since 2023-03-12 22:36
 */
public abstract class AbstractDelegateBigDatasourceExecutor {


	protected DynamicBigDatasourceRouter dynamicBigDatasourceRouter;

	public AbstractDelegateBigDatasourceExecutor(DynamicBigDatasourceRouter dynamicBigDatasourceRouter) {
		this.dynamicBigDatasourceRouter = dynamicBigDatasourceRouter;
	}

	/**
	 * 路由大数据源
	 * @return
	 */
	protected BigDatasource routing(DynamicBigDatasourceRoutingKey routingKey) {
		return dynamicBigDatasourceRouter.routing(routingKey != null? routingKey : DynamicBigDatasourceRoutingKeyHolder.get());
	}
}

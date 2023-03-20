package com.particle.global.big.datasource.bigdatasource.dynamic.impl;

import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRouter;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;

import java.util.Map;

/**
 * <p>
 * 默认动态大数据源路由器实现
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 10:42
 */
public class DefaultDynamicBigDatasourceRouterImpl implements DynamicBigDatasourceRouter {

	private Map<DynamicBigDatasourceRoutingKey, BigDatasource> bigDatasourceMap;

	public DefaultDynamicBigDatasourceRouterImpl(Map<DynamicBigDatasourceRoutingKey, BigDatasource> bigDatasourceMap) {
		this.bigDatasourceMap = bigDatasourceMap;
	}

	@Override
	public BigDatasource routing(DynamicBigDatasourceRoutingKey routingKey) {

		BigDatasource routedBigDatasource = null;

		if (bigDatasourceMap != null) {
			for (DynamicBigDatasourceRoutingKey dynamicBigDatasourceRoutingKey : bigDatasourceMap.keySet()) {
				if (routingKey.match(dynamicBigDatasourceRoutingKey)) {
					routedBigDatasource = bigDatasourceMap.get(dynamicBigDatasourceRoutingKey);
					break;
				}
			}
		}
		// 路由完成后处理一些逻辑
		routed(routedBigDatasource);
		if (routedBigDatasource == null) {
			routedBigDatasource = routedFailedFallback(routingKey);
		}
		if (routedBigDatasource == null) {
			throw new RuntimeException("routing bigDatasource failed. none bigDatasource is available for routingKey " + routingKey);
		}
		return routedBigDatasource;
	}

	/**
	 * 路由完成后处理一些逻辑
	 * @param routedBigDatasource
	 */
	private void routed(BigDatasource routedBigDatasource) {
		if (routedBigDatasource == null) {
			routedFailed();
		}else {
			routedSuccess(routedBigDatasource);
		}
	}
	/**
	 * 路由成功后处理，预留
	 * @param routedBigDatasource
	 */
	protected void routedSuccess(BigDatasource routedBigDatasource) {

	}

	/**
	 * 路由失败后处理，预留
	 */
	protected void routedFailed(){

	}

	/**
	 * 路由失败后，备用处理
	 * @param routingKey
	 * @return
	 */
	protected BigDatasource routedFailedFallback(DynamicBigDatasourceRoutingKey routingKey) {
		return null;
	}
}

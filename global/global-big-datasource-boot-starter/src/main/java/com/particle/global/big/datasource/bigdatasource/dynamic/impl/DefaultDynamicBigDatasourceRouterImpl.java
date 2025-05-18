package com.particle.global.big.datasource.bigdatasource.dynamic.impl;

import com.baomidou.dynamic.datasource.exception.CannotFindDataSourceException;
import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.*;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceNotFoundException;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.JdbcBigDatasource;

import java.util.List;
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

	private List<DynamicBigDatasourceRoutingFallback> routingFallbackList;

	public DefaultDynamicBigDatasourceRouterImpl(Map<DynamicBigDatasourceRoutingKey, BigDatasource> bigDatasourceMap) {
		this.bigDatasourceMap = bigDatasourceMap;
	}
	public DefaultDynamicBigDatasourceRouterImpl(Map<DynamicBigDatasourceRoutingKey, BigDatasource> bigDatasourceMap,List<DynamicBigDatasourceRoutingFallback> routingFallbackList) {

		this.bigDatasourceMap = bigDatasourceMap;
		this.routingFallbackList = routingFallbackList;
	}
	@Override
	public BigDatasource routing(DynamicBigDatasourceRoutingKey routingKey) {

		BigDatasource routedBigDatasource = doRouting(routingKey);
		// 路由完成后处理一些逻辑
		routed(routedBigDatasource);
		if (routedBigDatasource == null) {
			routedBigDatasource = routedFailedFallback(routingKey);
			// 尝试重新路由,因为 routedBigDatasource 为空不代表没有加载，可能是由于并发没有加载之前已经存在了
			if (routedBigDatasource == null) {
				routedBigDatasource = doRouting(routingKey);
			}
		}
		if (routedBigDatasource == null) {
			throw new BigDatasourceNotFoundException("routing bigDatasource failed. none bigDatasource is available for routingKey " + routingKey);
		}
		return routedBigDatasource;
	}

	/**
	 * 路由数据源
	 * @param routingKey
	 * @return
	 */
	private BigDatasource doRouting(DynamicBigDatasourceRoutingKey routingKey) {
		BigDatasource routedBigDatasource = null;

		if (bigDatasourceMap != null) {
			for (DynamicBigDatasourceRoutingKey dynamicBigDatasourceRoutingKey : bigDatasourceMap.keySet()) {
				if (routingKey.match(dynamicBigDatasourceRoutingKey)) {
					routedBigDatasource = bigDatasourceMap.get(dynamicBigDatasourceRoutingKey);
					// 这里判断一下路由的jdbc大数据源是否内部已经包含了子数据源
					if (routedBigDatasource instanceof JdbcBigDatasource && routingKey instanceof JdbcBigDatasourceRoutingKey) {
						try {
							if (!((JdbcBigDatasource) routedBigDatasource).existDs(((JdbcBigDatasourceRoutingKey) routingKey).subKey())) {
								routedBigDatasource = null;
							}
						} catch (CannotFindDataSourceException e) {
							routedBigDatasource = null;
						}
					}
					break;
				}
			}
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
		if (routingFallbackList != null) {
			for (DynamicBigDatasourceRoutingFallback dynamicBigDatasourceRoutingFallback : routingFallbackList) {
				BigDatasource bigDatasource = dynamicBigDatasourceRoutingFallback.routedFailedFallback(routingKey);
				if (bigDatasource != null) {
					routedBigDatasourceSuccess(routingKey,bigDatasource);
					return bigDatasource;
				}
			}
		}
		return null;
	}

	/**
	 * 路由降级成功后处理，尝试添加到多数据源中
	 * @param routingKey
	 * @param bigDatasource
	 */
	protected void routedBigDatasourceSuccess(DynamicBigDatasourceRoutingKey routingKey, BigDatasource bigDatasource) {
		synchronized(DynamicBigDatasource.class) {
			bigDatasourceMap.put(routingKey, bigDatasource);
		}
	}
}

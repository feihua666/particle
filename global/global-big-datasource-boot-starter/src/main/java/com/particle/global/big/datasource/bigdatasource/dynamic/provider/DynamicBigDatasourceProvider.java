package com.particle.global.big.datasource.bigdatasource.dynamic.provider;

import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;

import java.util.Map;

/**
 * <p>
 * 动态多数据源加载接口
 * </p>
 *
 * @author yangwei
 * @since 2023-03-17 21:46
 */
public interface DynamicBigDatasourceProvider {
	/**
	 * 加载接口
	 * @return
	 */
	Map<DynamicBigDatasourceRoutingKey, BigDatasource> loadDataSources();
}

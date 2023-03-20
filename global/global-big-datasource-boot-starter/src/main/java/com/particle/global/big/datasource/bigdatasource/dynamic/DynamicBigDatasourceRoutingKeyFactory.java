package com.particle.global.big.datasource.bigdatasource.dynamic;

import com.particle.global.big.datasource.bigdatasource.dynamic.impl.DefaultDynamicBigDatasourceRoutingKeyImpl;
import com.particle.global.big.datasource.bigdatasource.dynamic.impl.DefaultDynamicBigDatasourceRoutingSubKeyImpl;

/**
 * <p>
 * 动态大数据源路由键工厂类
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 12:54
 */
public class DynamicBigDatasourceRoutingKeyFactory {


	/**
	 * 动态大数据源路由键
	 * @param key
	 * @return
	 */
	public static DynamicBigDatasourceRoutingKey of(String key) {
		return DefaultDynamicBigDatasourceRoutingKeyImpl.create(key);
	}

	public static DynamicBigDatasourceRoutingKey of(String key,String subKey) {
		return DefaultDynamicBigDatasourceRoutingSubKeyImpl.create(key, subKey);
	}
}

package com.particle.global.big.datasource.bigdatasource.dynamic;

/**
 * <p>
 * 动态大数据源路由键
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 10:39
 */
public interface DynamicBigDatasourceRoutingKey<K> {


	/**
	 * 匹配是否支持
	 * @param key
	 * @return
	 */
	boolean match(DynamicBigDatasourceRoutingKey<K> key);

	/**
	 * 转为字符串标识
	 * @return
	 */
	String toStringKey();
}

package com.particle.global.big.datasource.bigdatasource.dynamic;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.particle.global.tool.thread.ThreadContextTool;

/**
 * <p>
 * 动态大数据源路由键保持器
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 10:47
 */
public class DynamicBigDatasourceRoutingKeyHolder {
	private static String dynamicBigDatasourceRoutingKeyHolderThreadLocalKey = "dynamicBigDatasourceRoutingKeyHolderThreadLocalKey";

	/**
	 * 获取当前的key
	 * @return
	 */
	public static DynamicBigDatasourceRoutingKey get(){
		DynamicBigDatasourceRoutingKey dynamicBigDatasourceRoutingKey = (DynamicBigDatasourceRoutingKey) ThreadContextTool.get(dynamicBigDatasourceRoutingKeyHolderThreadLocalKey);
		if(dynamicBigDatasourceRoutingKey instanceof DynamicBigDatasourceRoutingSubKey){
			DynamicDataSourceContextHolder.peek();
		}
		return dynamicBigDatasourceRoutingKey;
	}

	/**
	 * 清空
	 */
	public static void clear(){
		ThreadContextTool.remove(dynamicBigDatasourceRoutingKeyHolderThreadLocalKey);
		DynamicDataSourceContextHolder.clear();
	}

	/**
	 * 设置key
	 * @param routingKey
	 */
	public static void set(DynamicBigDatasourceRoutingKey routingKey) {
		ThreadContextTool.put(dynamicBigDatasourceRoutingKeyHolderThreadLocalKey,routingKey);
		if(routingKey instanceof DynamicBigDatasourceRoutingSubKey){
			DynamicDataSourceContextHolder.push(((DynamicBigDatasourceRoutingSubKey) routingKey).subKey());
		}
	}

	public static void set(String routingKey) {
		set(DynamicBigDatasourceRoutingKeyFactory.of(routingKey));
	}

	public static void set(String routingKey,String subRoutingKey) {
		set(DynamicBigDatasourceRoutingKeyFactory.of(routingKey,subRoutingKey));
	}
}

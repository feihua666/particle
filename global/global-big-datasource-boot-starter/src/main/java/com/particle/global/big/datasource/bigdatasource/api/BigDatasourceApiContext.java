package com.particle.global.big.datasource.bigdatasource.api;

import com.particle.global.dto.basic.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 大数据源接口上下文
 * 方便收集一些数据
 * </p>
 *
 * @author yangwei
 * @since 2023-08-22 15:21
 */
public class BigDatasourceApiContext extends DTO {

	private Map<Object,Object> mapData;

	public static BigDatasourceApiContext create() {
		BigDatasourceApiContext bigDatasourceApiContext = new BigDatasourceApiContext();
		return bigDatasourceApiContext;
	}

	/**
	 * 设置值
	 * @param key
	 * @param value
	 */
	public void putData(Object key, Object value) {
		if (mapData == null) {
			mapData = new HashMap<>();
		}
		mapData.put(key, value);
	}

	/**
	 * 获取值
	 * @param key
	 * @return
	 */
	public Object getData(Object key){
		if (mapData == null) {
			return null;
		}
		return mapData.get(key);
	}

}

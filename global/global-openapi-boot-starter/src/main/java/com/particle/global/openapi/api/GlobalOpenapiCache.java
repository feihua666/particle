package com.particle.global.openapi.api;

/**
 * <p>
 * 全局开放接口缓存，主要是防止重复请求判断
 * </p>
 *
 * @author yangwei
 * @since 2023-08-03 13:52
 */
public interface GlobalOpenapiCache {

	/**
	 * 缓存
	 * @param key
	 * @param value
	 * @param cacheTime 缓存时间，单位毫秒
	 */
	void put(String key, Object value,Long cacheTime);

	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	Object get(String key);

	/**
	 * 删除缓存
	 * @param key
	 */
	void remove(String key);
}

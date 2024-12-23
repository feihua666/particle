package com.particle.global.openapi.api.impl;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import com.particle.global.openapi.api.GlobalOpenapiCache;

/**
 * <p>
 * 基于内存的存储
 * </p>
 *
 * @author yangwei
 * @since 2023-08-04 11:22
 */
public class InMemoryGlobalOpenapiCacheImpl implements GlobalOpenapiCache {

	/**
	 * 缓存1小时
	 */
	private TimedCache<String, Object> objectTimedCache = CacheUtil.newTimedCache(60 * 60 * 1000);

	@Override
	public void put(String key, Object value, Long cacheTime) {
		objectTimedCache.put(key,value,cacheTime);
	}

	@Override
	public Object get(String key) {
		return objectTimedCache.get(key);
	}

	@Override
	public void remove(String key) {
		objectTimedCache.remove(key);
	}

}

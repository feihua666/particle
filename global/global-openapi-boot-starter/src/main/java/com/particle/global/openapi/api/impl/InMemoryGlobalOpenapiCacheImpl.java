package com.particle.global.openapi.api.impl;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.WeakCache;
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

	private WeakCache<String, CacheObj> objectTimedCache = CacheUtil.newWeakCache(10 * 60 * 1000);

	@Override
	public void put(String key, Object value, Long cacheTime) {
		objectTimedCache.put(key,CacheObj.create(value,cacheTime));
	}

	@Override
	public Object get(String key) {
		CacheObj cacheObj = objectTimedCache.get(key);
		if (cacheObj == null) {
			return null;
		}
		// 已过期
		if (cacheObj.expireAt < System.currentTimeMillis()) {
			objectTimedCache.remove(key);
			return null;
		}
		return cacheObj.value;
	}

	private static class CacheObj{
		private Object value;
		/**
		 * 过期时间戳，单位毫秒
		 */
		private Long expireAt;

		public static CacheObj create(Object value, Long cacheTime) {
			CacheObj cacheObj = new CacheObj();
			cacheObj.value = value;
			cacheObj.expireAt = cacheTime + System.currentTimeMillis();
			return cacheObj;
		}
	}
}

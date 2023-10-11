package com.particle.global.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.Nullable;

/**
 * <p>
 * 缓存帮助类，目前该帮助类，只考虑到了字典翻译手动清除的场景
 * </p>
 *
 * @author yangwei
 * @since 2023-10-09 09:47:14
 */

public class CacheHelper {

	public static final KeyGenerator simpleKeyGenerator = new SimpleKeyGenerator();

	@Autowired
	private CacheManager cacheManager;

	/**
	 * spring cache 默认是SimpleKeyGenerator，它并没有默认注入的spring容器中，如果用户自定义了，这里会有值
	 */
	@Autowired(required = false)
	private KeyGenerator keyGenerator;

	/**
	 * 该方法是有限的功能，仅支持默认的一个 cacheManager，不支持注解上指定 cacheManager
	 * 删除指定的参数缓存，请按缓存的方法来传递参数
	 * @param cacheName 缓存名称，这一般在使用使用注解时指定
	 * @param params 要清除哪个方法的缓存，就按哪个方法的参数传参
	 */
	public Boolean removeCacheByParam(String cacheName, Object... params){
		Cache cache = cacheManager.getCache(cacheName);
		if (cache != null) {
			if (keyGenerator == null) {
				keyGenerator = simpleKeyGenerator;
			}
			Object generate = keyGenerator.generate(null, null, params);
			return cache.evictIfPresent(generate);
		}
		return null;
	}

	/**
	 * 直接根据缓存key删除
	 * @param cacheName
	 * @param key
	 */
	public Boolean removeCacheByKey(String cacheName, Object key){
		Cache cache = cacheManager.getCache(cacheName);
		if (cache != null) {
			return cache.evictIfPresent(key);
		}
		return null;
	}

	/**
	 * 清空缓存
	 * @param cacheName
	 */
	public Boolean clearCache(String cacheName){
		Cache cache = cacheManager.getCache(cacheName);
		if (cache != null) {
			return cache.invalidate();
		}
		return null;
	}

	/**
	 * 设置缓存
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public void putCache(String cacheName, Object key, @Nullable Object value) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache != null) {
			cache.put(key,value);
		}
	}

	/**
	 * 获取缓存
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Cache.ValueWrapper get(String cacheName,Object key){
		Cache cache = cacheManager.getCache(cacheName);
		if (cache != null) {
			return cache.get(key);
		}
		return null;
	}


	/**
	 * 获取缓存
	 * @param cacheName
	 * @param key
	 * @param type
	 * @return
	 * @param <T>
	 */
	public <T> T get(String cacheName, Object key, @Nullable Class<T> type){
		Cache cache = cacheManager.getCache(cacheName);
		if (cache != null) {
			return cache.get(key,type);
		}
		return null;
	}
}

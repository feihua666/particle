package com.particle.global.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.particle.global.cache.config.JdbcCacheConfig;
import com.particle.global.cache.jdbccache.JdbcCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * 缓存帮助类，目前该帮助类，只考虑到了字典翻译手动清除的场景
 * </p>
 *
 * @author yangwei
 * @since 2023-10-09 09:47:14
 */

public class CacheHelper {

	public static final KeyGenerator customSimpleKeyGenerator = new CustomSimpleKeyGenerator();

	/**
	 * springboot默认使用这个，如：使用 Cacheable注解，就是默认使用的该内存缓存
	 * 如果自定义注入了其它的cacheManager，那么这里就不是默认的了，是自己注入的
	 */
	@Autowired
	private CacheManager cacheManager;

	/**
	 * 已经创建的cacheManager，这里记录，方便清除缓存他用
	 */
	private List<CacheManager> createdCacheManagerList = new ArrayList<>();

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
		return removeCacheByParam(cacheManager, cacheName, params);
	}
	/**
	 * 该方法是有限的功能，仅支持默认的一个 cacheManager，不支持注解上指定 cacheManager
	 * 删除指定的参数缓存，请按缓存的方法来传递参数
	 * @param cacheName 缓存名称，这一般在使用使用注解时指定
	 * @param params 要清除哪个方法的缓存，就按哪个方法的参数传参
	 */
	public Boolean removeCacheByParam(CacheManager cacheManager,String cacheName, Object... params){
		Cache cache = cacheManager.getCache(cacheName);
		if (cache != null) {
			if (keyGenerator == null) {
				keyGenerator = customSimpleKeyGenerator;
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
		return removeCacheByKey(cacheManager, cacheName, key);
	}
	/**
	 * 直接根据缓存key删除
	 * @param cacheName
	 * @param key
	 */
	public Boolean removeCacheByKey(CacheManager cacheManager,String cacheName, Object key){
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
		return clearCache(cacheManager, cacheName);
	}
	/**
	 * 清空缓存
	 * @param cacheName
	 */
	public Boolean clearCache(CacheManager cacheManager,String cacheName){
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
		putCache(cacheManager, cacheName, key, value);
	}
	/**
	 * 设置缓存
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public void putCache(CacheManager cacheManager,String cacheName, Object key, @Nullable Object value) {
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
		return get(cacheManager, cacheName, key);
	}
	/**
	 * 获取缓存
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Cache.ValueWrapper get(CacheManager cacheManager,String cacheName,Object key){
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
		return get(cacheManager, cacheName, key, type);
	}
	/**
	 * 获取缓存
	 * @param cacheName
	 * @param key
	 * @param type
	 * @return
	 * @param <T>
	 */
	public <T> T get(CacheManager cacheManager,String cacheName, Object key, @Nullable Class<T> type){
		Cache cache = cacheManager.getCache(cacheName);
		if (cache != null) {
			return cache.get(key,type);
		}
		return null;
	}

	/**
	 * 创建jdbcCacheManager
	 * @param jdbcTemplate
	 * @param objectMapper
	 * @param defaultTtl
	 * @return
	 */
	public JdbcCacheManager createJdbcCacheManager(JdbcTemplate jdbcTemplate, ObjectMapper objectMapper, long defaultTtl) {
		JdbcCacheManager jdbcCacheManager = new JdbcCacheManager(jdbcTemplate,objectMapper,defaultTtl);
		createdCacheManagerList.add(jdbcCacheManager);
		return jdbcCacheManager;
	}

	/**
	 * 创建concurrentMapCacheManager
	 * springboot默认使用这个，如：使用 Cacheable注解，就是默认使用的该内存缓存，此处创建一个，可以自己使用
	 * @return
	 */
	public ConcurrentMapCacheManager  createConcurrentMapCacheManager() {
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
		createdCacheManagerList.add(cacheManager);
		return cacheManager;
	}
}

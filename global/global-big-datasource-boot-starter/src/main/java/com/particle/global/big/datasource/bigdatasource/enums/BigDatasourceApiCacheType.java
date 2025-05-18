package com.particle.global.big.datasource.bigdatasource.enums;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.particle.global.cache.CacheHelper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * <p>
 * 大数据源接口缓存类型
 * </p>
 *
 * @author yangwei
 * @since 2025-05-18 21:41:46
 */
public enum BigDatasourceApiCacheType {
	/**
	 * 使用内存缓存
	 * 参考 {@link CacheHelper#createConcurrentMapCacheManager()}
	 * */
	memory,
	/**
	 * 使用jdbc 缓存
	 * 参考 {@link CacheHelper#createJdbcCacheManager(JdbcTemplate, ObjectMapper, long)}
	 */
	jdbcCache;
}

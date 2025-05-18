package com.particle.global.big.datasource.bigdatasource.api.config;

import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiCacheType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

/**
 * <p>
 * 大数据源缓存配置
 * </p>
 *
 * @author yangwei
 * @since 2025-05-18 21:37:47
 */
@Slf4j
@Data
public class BigDatasourceApiCacheConfig {

	/**
	 * 内存缓存，缓存时间2小时，不使用groovy脚本
	 */
	public static BigDatasourceApiCacheConfig memoryBigDatasourceApiCacheConfig = BigDatasourceApiCacheConfig.create(BigDatasourceApiCacheType.memory, Duration.ofHours(2).toSeconds(),"");
	/**
	 * 缓存类型，使用哪种类型
	 */
	private BigDatasourceApiCacheType cacheType;

	/**
	 * 缓存时间，单位秒
	 */
	private long ttl;

	/**
	 * 缓存key的groovy脚本
	 * 可以写一个脚本来创建缓存key，必须返回字符串类型，如果不指定将自动生成key
	 */
	private String cacheKeyGroovyScript;

	public static BigDatasourceApiCacheConfig create(BigDatasourceApiCacheType cacheType,long ttl,String cacheKeyGroovyScript)
	{
		BigDatasourceApiCacheConfig bigDatasourceApiCacheConfig = new BigDatasourceApiCacheConfig();
		bigDatasourceApiCacheConfig.setCacheType(cacheType);
		bigDatasourceApiCacheConfig.setTtl(ttl);
		bigDatasourceApiCacheConfig.setCacheKeyGroovyScript(cacheKeyGroovyScript);
		return bigDatasourceApiCacheConfig;
	}
}

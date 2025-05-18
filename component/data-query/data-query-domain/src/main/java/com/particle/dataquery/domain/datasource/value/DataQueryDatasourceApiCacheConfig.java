package com.particle.dataquery.domain.datasource.value;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.particle.component.light.share.dict.CacheType;
import com.particle.global.dto.basic.Value;
import com.particle.global.tool.script.GroovyTool;
import lombok.Data;

import javax.script.ScriptException;

/**
 * <p>
 * 缓存配置
* 该配置大致与{@link com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiCacheConfig} 相对应
 * </p>
 *
 * @author yangwei
 * @since 2025-05-18 22:45:22
 */
@Data
public class DataQueryDatasourceApiCacheConfig extends Value {

	/**
	 * 缓存类型，使用哪种类型
	 * 参考 {@link BigDatasourceApiCacheType#name()}
	 */
	private CacheType cacheType;

	/**
	 * 缓存时间，单位秒
	 */
	private long ttl;

	/**
	 * 缓存key的groovy脚本
	 * 可以写一个脚本来创建缓存key，必须返回字符串类型，如果不指定将自动生成key
	 */
	private String cacheKeyGroovyScript;

	/**
	 * 编译预热
	 * @throws ScriptException
	 */
	public void warmUpLight() throws ScriptException {
		if (StrUtil.isNotEmpty(cacheKeyGroovyScript)) {
			GroovyTool.compile(cacheKeyGroovyScript,true);
		}
	}
	public static DataQueryDatasourceApiCacheConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceApiCacheConfig dataQueryDatasourceApiCacheConfig = JSONUtil.toBean(jsonStr, DataQueryDatasourceApiCacheConfig.class);
		return dataQueryDatasourceApiCacheConfig;
	}

	public static DataQueryDatasourceApiCacheConfig create(CacheType cacheType,long ttl,String cacheKeyGroovyScript) {
		DataQueryDatasourceApiCacheConfig dataQueryDatasourceApiCacheConfig = new DataQueryDatasourceApiCacheConfig();
		dataQueryDatasourceApiCacheConfig.cacheType = cacheType;
		dataQueryDatasourceApiCacheConfig.ttl = ttl;
		dataQueryDatasourceApiCacheConfig.cacheKeyGroovyScript = cacheKeyGroovyScript;
		return dataQueryDatasourceApiCacheConfig;
	}
}

package com.particle.global.big.datasource.bigdatasource.executor;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiCacheConfig;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiCacheType;
import com.particle.global.cache.CacheHelper;
import com.particle.global.cache.GlobalCacheValueWrapper;
import com.particle.global.exception.Assert;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.spring.SpringContextHolder;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.script.Bindings;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 默认的缓存实现
 * </p>
 *
 * @author yangwei
 * @since 2023/10/11 10:20
 */
public class DefaultBigDatasourceApiExecutorExeCacheImpl implements IBigDatasourceApiExecutorExeCache{

    private static final String cacheName = DefaultBigDatasourceApiExecutorExeCacheImpl.class.getSimpleName();

    @Autowired
    private CacheHelper cacheHelper;

    private Map<String,CacheManager> cacheManagerMap = new HashMap<>();

    @Override
    public CacheValue get(BigDatasourceApi bigDatasourceApi, Object command, String queryString) {
        CacheManager cacheManager = getCacheManager(bigDatasourceApi);
        Cache cache = cacheManager.getCache(cacheName);
        Object cacheKey = getKey(bigDatasourceApi, command, queryString);

        Cache.ValueWrapper valueWrapper = cache.get(cacheKey);
        if (valueWrapper != null) {
            Object o = valueWrapper.get();
            // 兼容一下历史缓存
            if (o instanceof GlobalCacheValueWrapper) {
                if (((GlobalCacheValueWrapper) o).isExpired()) {
                    o = null;
                }else{
                    o = ((GlobalCacheValueWrapper) o).getData();
                }
            }
            return CacheValue.create(o, true);
        }
        return CacheValue.create(null, false);

    }

    @Override
    public void put(BigDatasourceApi bigDatasourceApi, Object command, String queryString, Object o) {
        CacheManager cacheManager = getCacheManager(bigDatasourceApi);
        Cache cache = cacheManager.getCache(cacheName);
        Object cacheKey = getKey(bigDatasourceApi, command, queryString);
        GlobalCacheValueWrapper globalCacheValueWrapper = GlobalCacheValueWrapper.create(o, LocalDateTime.now().plusSeconds(bigDatasourceApi.cacheConfig().getTtl()));
        cache.put(cacheKey,globalCacheValueWrapper);
    }

    /**
     * 生成key
     * @param bigDatasourceApi
     * @param command
     * @param queryString
     * @return
     */
    @SneakyThrows
    private Object getKey(BigDatasourceApi bigDatasourceApi, Object command, String queryString) {
        Assert.notEmpty(bigDatasourceApi.identifier(),"bigDatasourceApi identifier can not be empty");
        BigDatasourceApiCacheConfig bigDatasourceApiCacheConfig = bigDatasourceApi.cacheConfig();
        String cacheKeyGroovyScript = bigDatasourceApiCacheConfig.getCacheKeyGroovyScript();
        if (StrUtil.isNotEmpty(cacheKeyGroovyScript)) {
            Bindings bindings = GroovyTool.createBindings();
            bindings.put("identifier", bigDatasourceApi.identifier());
            bindings.put("command", command);
            bindings.put("queryString", queryString);
            Object o = GroovyTool.compileAndEval(cacheKeyGroovyScript, bindings, true);
            Assert.isTrue(o instanceof String,"cache key must return a string value!");
            return (String) o;
        }
        return CacheHelper.customSimpleKeyGenerator.generate(null, null,bigDatasourceApi.identifier(), command, queryString);
    }

    /**
     * 获取缓存
     * @param bigDatasourceApi
     * @return
     */
    private CacheManager getCacheManager(BigDatasourceApi bigDatasourceApi) {
        BigDatasourceApiCacheConfig bigDatasourceApiCacheConfig = bigDatasourceApi.cacheConfig();
        String key = bigDatasourceApi.identifier() + bigDatasourceApiCacheConfig.getCacheType() + bigDatasourceApiCacheConfig.getTtl();
        if (BigDatasourceApiCacheType.memory == bigDatasourceApiCacheConfig.getCacheType()) {
            return cacheManagerMap.computeIfAbsent(key, (k) -> cacheHelper.createConcurrentMapCacheManager());
        }else if (BigDatasourceApiCacheType.jdbcCache == bigDatasourceApiCacheConfig.getCacheType()) {
            return cacheManagerMap.computeIfAbsent(key, (k) -> {
                JdbcTemplate jdbcTemplate = SpringContextHolder.getBean(JdbcTemplate.class);
                ObjectMapper objectMapper = SpringContextHolder.getBean(ObjectMapper.class);
                return cacheHelper.createJdbcCacheManager(jdbcTemplate, objectMapper, bigDatasourceApiCacheConfig.getTtl());
            });
        }
        return null;
    }

}

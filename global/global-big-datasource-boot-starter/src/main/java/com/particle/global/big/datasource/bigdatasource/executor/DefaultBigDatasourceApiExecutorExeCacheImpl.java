package com.particle.global.big.datasource.bigdatasource.executor;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.cache.CacheHelper;
import com.particle.global.exception.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;

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

    @Override
    public CacheValue get(BigDatasourceApi bigDatasourceApi, Object command, String queryString) {
        Cache.ValueWrapper valueWrapper = cacheHelper.get(cacheName, getKey(bigDatasourceApi,command,queryString));
        if (valueWrapper != null) {
            return CacheValue.create(valueWrapper.get(), true);
        }
        return CacheValue.create(null, false);

    }

    @Override
    public void put(BigDatasourceApi bigDatasourceApi, Object command, String queryString, Object o) {
        cacheHelper.putCache(cacheName,getKey(bigDatasourceApi,command,queryString),o);
    }

    /**
     * 生成key
     * @param bigDatasourceApi
     * @param command
     * @param queryString
     * @return
     */
    private Object getKey(BigDatasourceApi bigDatasourceApi, Object command, String queryString) {
        Assert.notEmpty(bigDatasourceApi.identifier(),"bigDatasourceApi identifier can not be empty");
        return CacheHelper.simpleKeyGenerator.generate(null, null,bigDatasourceApi.identifier(), command, queryString);
    }
}

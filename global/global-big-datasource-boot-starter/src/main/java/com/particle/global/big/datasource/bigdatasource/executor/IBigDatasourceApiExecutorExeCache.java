package com.particle.global.big.datasource.bigdatasource.executor;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import lombok.Data;

/**
 * <p>
 * 在执行时缓存
 * </p>
 *
 * @author yangwei
 * @since 2023/10/11 09:36
 */
public interface IBigDatasourceApiExecutorExeCache {

    /**
     * 获取缓存
     * @param bigDatasourceApi
     * @param command
     * @param queryString
     * @return
     */
    CacheValue get(BigDatasourceApi bigDatasourceApi, Object command, String queryString);

    /**
     * 设置缓存
     * @param bigDatasourceApi
     * @param command
     * @param queryString
     * @param o
     */
    void put(BigDatasourceApi bigDatasourceApi, Object command, String queryString, Object o);

    @Data
    public static class CacheValue{
        private Object data;
        private Boolean isCacheHit;

        public static CacheValue create(Object data, Boolean isCacheHit) {
            CacheValue cacheValue = new CacheValue();
            cacheValue.data = data;
            cacheValue.isCacheHit = isCacheHit;
            return cacheValue;
        }
    }
}

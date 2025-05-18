package com.particle.component.light.share.dict;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 缓存类型 字典项
 * 该字段一般与 {@link com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiCacheConfig} 配置一致
 * </p>
 *
 * @author yw
 * @since 2025-05-18 22:55:59
 */
public enum CacheType implements IDictItem {

    /**
     * 内存缓存
     */
    memory
    ,
    /**
     * jdbc缓存
     */
    jdbcCache
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.cache_type.groupCode();
    }

    /**
     * 缓存类型 字典组
     */
    public enum Group implements IDictGroup {
        cache_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}


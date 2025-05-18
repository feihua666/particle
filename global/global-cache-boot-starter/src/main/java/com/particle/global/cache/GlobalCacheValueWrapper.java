package com.particle.global.cache;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 自定义一个缓存对象包装，因为某些缓存不支持过期时间，所以自定义一个
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GlobalCacheValueWrapper implements Serializable {
    /**
     * 缓存数据
     */
    private Object data;
    /**
     * 创建时间
     */
    private LocalDateTime createAt;
    /**
     * 缓存时间，单位秒
     */
    private Long ttl;
    /**
     * 过期时间
     */
    private LocalDateTime expireAt;

    public static GlobalCacheValueWrapper create(Object data, LocalDateTime createAt, Long ttl,LocalDateTime expireAt) {
        GlobalCacheValueWrapper globalCacheValueWrapper = new GlobalCacheValueWrapper();
        globalCacheValueWrapper.data = (data);
        globalCacheValueWrapper.createAt = (createAt);
        globalCacheValueWrapper.ttl = (ttl);
        globalCacheValueWrapper.expireAt = (expireAt);
        return globalCacheValueWrapper;
    }

    /**
     * 有时可能只使用过期时间和数据，为了方便这里添加一个创建方法
     * @param data
     * @param expireAt
     * @return
     */
    public static GlobalCacheValueWrapper create(Object data,LocalDateTime expireAt) {
        return create(data, null, null, expireAt);
    }

    /**
     * 是否过期
     * @return
     */
    public Boolean isExpired() {
        if (expireAt != null) {
            return expireAt.isBefore(LocalDateTime.now());
        }
        if (createAt != null && ttl != null) {
            LocalDateTime expireAt = createAt.plusSeconds(ttl);
            return expireAt.isBefore(LocalDateTime.now());
        }
        return false;
    }
}

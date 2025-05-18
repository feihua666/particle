package com.particle.global.cache.jdbccache;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * <p>
 * 数据库缓存实现
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 11:19
 */
public class JdbcCache implements Cache {
    /**
     * 缓存名称
     */
    private final String name;
    // 原生缓存实现
    private final JdbcTemplate jdbcTemplate;
    /**
     * 缓存存活时间，单位秒
     */
    private final long ttl;
    /**
     * json序列化对象
     */
    private ObjectMapper objectMapper;
    /**
     * 主要用于 key 转化 参考了 {@link org.springframework.data.redis.cache.RedisCache#convertKey(Object)} 中使用了
     */
    private ConversionService conversionService;

    public JdbcCache(String name, JdbcTemplate jdbcTemplate,ObjectMapper objectMapper,ConversionService conversionService, long ttl) {
        this.name = name;
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
        this.conversionService = conversionService;
        this.ttl = ttl;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return jdbcTemplate;
    }

    @Override
    public ValueWrapper get(Object key) {
        String cacheKey = serializeKey(key);
        return jdbcTemplate.query(
                "SELECT cache_value_json, cache_value_blob, cache_value_type,cache_value_is_basic_type FROM global_cache " +
                        "WHERE cache_name = ? AND cache_key = ? AND (cache_create_at + INTERVAL cache_ttl SECOND) > NOW()",
                rs -> {
                    if (rs.next()) {
                        Object value = null;
                        String type = rs.getString(3);
                        int isBasicType = rs.getInt(4);
                        String json = rs.getString(1);
                        if (json != null) {
                            if (isBasicType == 1) {
                                value = Convert.convertByClassName(type, json);
                            }else{
                                try {
                                    value = objectMapper.readValue(json, Object.class);
                                } catch (JsonProcessingException e) {
                                    throw new RuntimeException("Failed to deserialize JSON cache value", e);
                                }
                            }

                        } else {
                            byte[] blob = rs.getBytes(2);
                            if (blob != null) {
                                value = deserialize(blob);
                            }
                        }
                        return (value != null) ? new SimpleValueWrapper(value) : null;
                    }
                    return null;
                },
                this.name, cacheKey
        );
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        ValueWrapper wrapper = get(key);
        return wrapper == null ? null : type.cast(wrapper.get());
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        // 简单实现，建议添加完整逻辑
        ValueWrapper wrapper = get(key);
        if (wrapper != null) {
            return (T) wrapper.get();
        }
        try {
            T value = valueLoader.call();
            put(key, value);
            return value;
        } catch (Exception e) {
            throw new ValueRetrievalException(key, valueLoader, e);
        }
    }

    @Override
    public void put(Object key, Object value) {
        // 不缓存空值
        if (value == null) {
            return;
        }
        String cacheKey = serializeKey(key);
        String id = UUID.randomUUID().toString(true);
        LocalDateTime now = LocalDateTime.now();
        String type = value.getClass().getName();
        int isBasicType = ObjectUtil.isBasicType(value) ? 1 : 0;

        try {
            String jsonValue = objectMapper.writeValueAsString(value);
            jdbcTemplate.update(
                    "INSERT INTO global_cache (id, cache_name, cache_key, cache_value_json, cache_value_type,cache_value_is_basic_type,cache_create_at, cache_ttl, create_at) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                            "ON DUPLICATE KEY UPDATE " +
                            "cache_value_json = VALUES(cache_value_json), " +
                            "cache_value_type = VALUES(cache_value_type), " +
                            "cache_value_is_basic_type = VALUES(cache_value_is_basic_type), " +
                            "cache_create_at = VALUES(cache_create_at), " +
                            "cache_ttl = VALUES(cache_ttl)",
                    id,
                    this.name,
                    cacheKey,
                    jsonValue,
                    type,
                    isBasicType,
                    now,
                    ttl,
                    now
            );
        } catch (IOException e) {
            // JSON序列化失败时降级到BLOB
            byte[] blobValue = serializeValue(value);
            jdbcTemplate.update(
                    "INSERT INTO global_cache (id, cache_name, cache_key, cache_value_blob,cache_value_type,cache_value_is_basic_type, cache_create_at, cache_ttl, create_at) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                            "ON DUPLICATE KEY UPDATE " +
                            "cache_value_blob = VALUES(cache_value_blob), " +
                            "cache_value_type = VALUES(cache_value_type), " +
                            "cache_value_is_basic_type = VALUES(cache_value_is_basic_type), " +
                            "cache_create_at = VALUES(cache_create_at), " +
                            "cache_ttl = VALUES(cache_ttl)",
                    id,
                    this.name,
                    cacheKey,
                    blobValue,
                    type,
                    isBasicType,
                    now,
                    ttl,
                    now
            );
        }
    }

    @Override
    public void evict(Object key) {
        String cacheKey = serializeKey(key);
        jdbcTemplate.update(
                "DELETE FROM global_cache WHERE cache_name = ? AND cache_key = ?",
                name, cacheKey
        );
    }

    @Override
    public void clear() {
        jdbcTemplate.update(
                "DELETE FROM global_cache WHERE cache_name = ?",
                name
        );
    }

    /**
     * 定期清理过期缓存的方法
     */
    public void cleanUpExpiredEntries() {
        jdbcTemplate.update(
                "DELETE FROM global_cache WHERE  cache_name = ? and (cache_create_at + INTERVAL cache_ttl SECOND) <= NOW()",
                name
        );
    }

    private String serializeKey(Object key) {
        return convertKey(key);
    }

    private byte[] serializeValue(Object value) {
        return SerializationUtils.serialize(value);
    }

    private Object deserialize(byte[] bytes) {
        return SerializationUtils.deserialize(bytes);
    }

    /**
     * 以下都参考了 {@link org.springframework.data.redis.cache.RedisCache}
     */
    /**
     * 参考了 {@link org.springframework.data.redis.cache.RedisCache#convertKey(Object)}
     * @param key
     * @return
     */
    protected String convertKey(Object key) {

        if (key instanceof String stringKey) {
            return stringKey;
        }

        TypeDescriptor source = TypeDescriptor.forObject(key);

        if (conversionService.canConvert(source, TypeDescriptor.valueOf(String.class))) {
            try {
                return conversionService.convert(key, String.class);
            } catch (ConversionFailedException ex) {

                // May fail if the given key is a collection
                if (isCollectionLikeOrMap(source)) {
                    return convertCollectionLikeOrMapKey(key, source);
                }

                throw ex;
            }
        }

        if (hasToStringMethod(key)) {
            return key.toString();
        }

        throw new IllegalStateException(("Cannot convert cache key %s to String; Please register a suitable Converter"
                + " via 'JdbcCacheManager.configureKeyConverters(...)' or override '%s.toString()'")
                .formatted(source, key.getClass().getName()));
    }

    private boolean hasToStringMethod(Object target) {
        return hasToStringMethod(target.getClass());
    }

    private boolean hasToStringMethod(Class<?> type) {

        Method toString = ReflectionUtils.findMethod(type, "toString");

        return toString != null && !Object.class.equals(toString.getDeclaringClass());
    }

    private boolean isCollectionLikeOrMap(TypeDescriptor source) {
        return source.isArray() || source.isCollection() || source.isMap();
    }

    private String convertCollectionLikeOrMapKey(Object key, TypeDescriptor source) {

        if (source.isMap()) {

            int count = 0;

            StringBuilder target = new StringBuilder("{");

            for (Map.Entry<?, ?> entry : ((Map<?, ?>) key).entrySet()) {
                target.append(convertKey(entry.getKey())).append("=").append(convertKey(entry.getValue()));
                target.append(++count > 1 ? ", " : "");
            }

            target.append("}");

            return target.toString();

        } else if (source.isCollection() || source.isArray()) {

            StringJoiner stringJoiner = new StringJoiner(",");

            Collection<?> collection = source.isCollection() ? (Collection<?>) key
                    : Arrays.asList(ObjectUtils.toObjectArray(key));

            for (Object collectedKey : collection) {
                stringJoiner.add(convertKey(collectedKey));
            }

            return "[" + stringJoiner + "]";
        }

        throw new IllegalArgumentException("Cannot convert cache key [%s] to String".formatted(key));
    }


    /**
     * 以上都参考了 {@link org.springframework.data.redis.cache.RedisCache}
     */

}

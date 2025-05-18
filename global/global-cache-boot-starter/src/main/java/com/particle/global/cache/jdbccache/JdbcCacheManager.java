package com.particle.global.cache.jdbccache;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

/**
 * <p>
 * 基于jdbc的缓存实现
 * 主要是为了满足在数据查询数据源接口中使用，将三方数据缓存到数据库中，以减少费用
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 11:18
 */
public class JdbcCacheManager implements CacheManager {
    private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<>();
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    /**
     * 主要用于 key 转化 参考了 {@link org.springframework.data.redis.cache.RedisCache#convertKey(Object)} 中使用了
     */
    private ConversionService conversionService;
    /**
     * 缓存存活时间，单位秒
     */
    private final long defaultTtl;

    public JdbcCacheManager(JdbcTemplate jdbcTemplate,ObjectMapper objectMapper, long defaultTtl) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper.copy();
        this.objectMapper.activateDefaultTyping(
                this.objectMapper.getPolymorphicTypeValidator(),
                // ObjectMapper.DefaultTyping.NON_FINAL,
                ObjectMapper.DefaultTyping.EVERYTHING,
                JsonTypeInfo.As.PROPERTY
        );
        this.defaultTtl = defaultTtl;

        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        registerDefaultConverters(conversionService);
        this.conversionService = conversionService;
    }

    @Override
    public Cache getCache(String name) {

        return cacheMap.computeIfAbsent(name, cacheName -> new JdbcCache(cacheName, jdbcTemplate,objectMapper,conversionService, defaultTtl));
    }

    public Cache getCache(String name,int ttl) {
        return cacheMap.computeIfAbsent(name, cacheName -> new JdbcCache(cacheName, jdbcTemplate,objectMapper,conversionService, ttl));
    }

    @Override
    public Collection<String> getCacheNames() {
        // 从数据库中查询所有不同的缓存名称
        List<String> cacheNames = jdbcTemplate.queryForList(
                "SELECT DISTINCT cache_name FROM global_cache",
                String.class
        );

        return cacheNames;
        // 同时合并内存中已创建但可能还未存入数据库的缓存名称
        // Set<String> allCacheNames = new HashSet<>(cacheNames);
        // allCacheNames.addAll(cacheMap.keySet());

        // return Collections.unmodifiableSet(allCacheNames);
    }
    /**
     * 参考 {@link org.springframework.data.redis.cache.RedisCacheConfiguration#registerDefaultConverters(ConverterRegistry)}
     * @param registry
     */
    private static void registerDefaultConverters(ConverterRegistry registry) {

        Assert.notNull(registry, "ConverterRegistry must not be null");

        registry.addConverter(String.class, byte[].class, source -> source.getBytes(StandardCharsets.UTF_8));
        registry.addConverter(SimpleKey.class, String.class, SimpleKey::toString);
    }
    /**
     * 注册额外的key转换器
     * {@link org.springframework.data.redis.cache.RedisCache}
     * @param registryConsumer
     */
    public void configureKeyConverters(Consumer<ConverterRegistry> registryConsumer) {
        registryConsumer.accept((ConverterRegistry) conversionService);
    }
}

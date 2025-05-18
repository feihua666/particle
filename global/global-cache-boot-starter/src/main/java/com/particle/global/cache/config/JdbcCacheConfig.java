package com.particle.global.cache.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.particle.global.cache.jdbccache.JdbcCacheManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.Duration;

/**
 * <p>
 * 基于jdbc 缓存的配置
 * 内部类 没有 @Configuration 也会配置内部类里面的bean
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:04
 */
// @Configuration(proxyBeanMethods = false)
public class JdbcCacheConfig {
    public static final String jdbcCacheManagerName = "jdbcCacheManager";

    /**
     * 如果满足jdbc使用jdbc
     * 一旦注入了，默认情况下，springboot默认的缓存实现是ConcurrentMapCacheManager 会失效，这里暂不注入，如要使用请手动调用
     */
    // @Configuration(proxyBeanMethods = false)
    // @ConditionalOnClass(JdbcTemplate.class)
    static class JdbcStoreConfig{
        /**
         * jdbc缓存管理器
         * @param jdbcTemplate
         * @param objectMapper 该参数用于序列化，默认使用springboot的ObjectMapper，主要是springmvc中使用的，
         *                     相关配置已在 {@link com.particle.global.web.mvc.http.jackson2.CustomJackson2ObjectMapperBuilderCustomizer} 中配置了
         * @return
         */
        @Bean
        @ConditionalOnBean(JdbcTemplate.class)
        public JdbcCacheManager jdbcCacheManager(JdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
            return new JdbcCacheManager(jdbcTemplate, objectMapper, Duration.ofDays(7L).toSeconds());
        }
    }
}

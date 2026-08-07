package com.particle.global.crawler.storage.data;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 内存结构化数据存储
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
public class MemoryDataStorage implements DataStorage {

    private final Map<String, Object> storage = new ConcurrentHashMap<>();

    @Override
    public void save(String key, Object value) {
        if (key == null || value == null) {
            log.warn("保存数据失败: key或value为null");
            return;
        }
        storage.put(key, value);
        log.debug("保存内存数据: key={}", key);
    }

    @Override
    public <T> T load(String key, Class<T> clazz) {
        return (T) storage.get(key);
    }

}

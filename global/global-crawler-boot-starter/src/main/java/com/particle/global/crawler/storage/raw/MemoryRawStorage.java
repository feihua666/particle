package com.particle.global.crawler.storage.raw;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 内存原始数据存储
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
public class MemoryRawStorage implements RawStorage {

    private final Map<String, String> storage = new ConcurrentHashMap<>();

    @Override
    public void save(String key, String value) {
        if (key == null || value == null) {
            log.warn("保存数据失败: key或value为null");
            return;
        }
        storage.put(key, value);
        log.debug("保存内存数据: key={}", key);
    }

    @Override
    public String load(String key) {
        return storage.get(key);
    }
}

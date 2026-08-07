package com.particle.global.crawler.storage.raw;

/**
 * 原始数据存储接口
 * 用于存储抓取的原始内容（HTML、JSON、XML 等文本格式）
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
public interface RawStorage {

    /**
     * 保存原始数据
     *
     * @param key  数据键
     * @param value 数据值
     */
    void save(String key, String value);

    /**
     * 获取原始数据
     *
     * @param key 数据键
     * @return 数据值
     */
    String load(String key);
}

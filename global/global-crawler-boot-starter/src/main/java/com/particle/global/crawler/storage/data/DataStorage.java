package com.particle.global.crawler.storage.data;

/**
 * 结构化数据存储接口
 * <p>
 * 用于存储 Pipeline 处理后的结构化数据，数据必须是可序列化为 JSON 的对象
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
public interface DataStorage {

    /**
     * 保存结构化数据
     *
     * @param key   数据键
     * @param value 数据值（必须是可序列化为 JSON 的对象，如 Map、List、POJO）
     */
    void save(String key, Object value);

    /**
     * 获取结构化数据
     *
     * @param key 数据键
     * @param <T> 返回类型
     * @return 数据值
     */
    <T> T load(String key, Class<T> clazz);
}

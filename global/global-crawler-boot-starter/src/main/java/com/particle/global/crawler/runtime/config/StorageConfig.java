package com.particle.global.crawler.runtime.config;

import com.particle.global.crawler.common.enums.StorageType;
import lombok.Data;

/**
 * Storage 配置
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
public class StorageConfig {
    /**
     * 原始数据存储路径
     */
    private String rawStoragePath;

    /**
     * 结构化数据存储路径
     */
    private String dataStoragePath;

    /**
     * 原始数据存储类型
     */
    private StorageType rawStorageType;

    /**
     * 结构化数据存储类型
     */
    private StorageType dataStorageType;

    public static StorageConfig defaultConfig() {
        StorageConfig config = new StorageConfig();
        config.setRawStoragePath(null);
        config.setDataStoragePath(null);
        config.setRawStorageType(StorageType.MEMORY);
        config.setDataStorageType(StorageType.MEMORY);
        return config;
    }

    /**
     * 合并配置（当前配置优先）
     */
    public StorageConfig merge(StorageConfig defaults) {
        if (defaults == null) {
            return this;
        }
        if (this.rawStoragePath == null) {
            this.rawStoragePath = defaults.rawStoragePath;
        }
        if (this.dataStoragePath == null) {
            this.dataStoragePath = defaults.dataStoragePath;
        }
        if (this.rawStorageType == null) {
            this.rawStorageType = defaults.rawStorageType;
        }
        if (this.dataStorageType == null) {
            this.dataStorageType = defaults.dataStorageType;
        }
        return this;
    }
}

package com.particle.global.crawler.runtime.session.auth;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 单个存储项（KV）
 * <p>
 * 用于 LocalStorage 和 SessionStorage 的键值对存储
 * </p>
 *
 * @author yangwei
 * @since 2026/05/13 21:35
 */
@Data
public class StorageItem {
    
    /**
     * 存储项名称
     */
    private String name;
    
    /**
     * 存储项值
     */
    private String value;

    public StorageItem() {
    }

    public StorageItem(String name, String value) {
        this.name = name;
        this.value = value;
    }
}

package com.particle.global.crawler.runtime.session.auth;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 某个域名下的 Storage（LocalStorage / SessionStorage）
 * <p>
 * 按域名分组的存储信息，包含该域名下的所有存储项
 * </p>
 *
 * @author yangwei
 * @since 2026/05/13 21:35
 */
@Data
public class OriginStorage {

    /**
     * 域名来源，如 https://example.com
     */
    private String origin;

    /**
     * 存储项列表
     */
    private List<StorageItem> items = new ArrayList<>();

    public OriginStorage() {
    }

    public OriginStorage(String origin) {
        this.origin = origin;
    }

    /**
     * 添加存储项
     *
     * @param name  存储项名称
     * @param value 存储项值
     * @return 当前对象（支持链式调用）
     */
    public OriginStorage add(String name, String value) {
        items.add(new StorageItem(name, value));
        return this;
    }

    /**
     * 按 name 查找存储项的值
     *
     * @param name 存储项名称
     * @return 存储项值（可能为空）
     */
    public Optional<String> get(String name) {
        return items.stream()
                .filter(i -> i.getName().equals(name))
                .map(StorageItem::getValue)
                .findFirst();
    }
}

package com.particle.global.crawler.driver.browser;


import com.particle.global.crawler.runtime.session.auth.CookieItem;
import com.particle.global.crawler.runtime.session.auth.OriginStorage;
import com.particle.global.crawler.runtime.session.auth.StorageItem;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Playwright StorageState 结构
 * <p>
 * 对应 Playwright storageState() 返回的 JSON 格式
 * </p>
 *
 * @author yangwei
 * @since 2026/05/13 21:35
 */
@Data
public class PlaywrightStorageState {

    /**
     * Cookies 列表（复用 CookieItem）
     */
    private List<CookieItem> cookies;

    /**
     * Origins 列表（复用 OriginStorage，但注意字段名：Playwright 用 localStorage）
     */
    private List<PlaywrightOrigin> origins;

    public PlaywrightStorageState() {
        this.cookies = new ArrayList<>();
        this.origins = new ArrayList<>();
    }

    // ========== 内部类：PlaywrightOrigin ==========

    @Data
    public static class PlaywrightOrigin {

        /**
         * 域名来源，如 https://example.com
         */
        private String origin;

        /**
         * LocalStorage 数据（复用 OriginStorage 的结构）
         * 注意：Playwright JSON 中的字段名是 "localStorage"
         */
        private List<StorageItem> localStorage;

        public PlaywrightOrigin() {
            this.localStorage = new ArrayList<>();
        }

        /**
         * 转换为 OriginStorage
         */
        public OriginStorage toOriginStorage() {
            OriginStorage storage = new OriginStorage(this.origin);
            if (this.localStorage != null) {
                for (StorageItem item : this.localStorage) {
                    storage.add(item.getName(), item.getValue());
                }
            }
            return storage;
        }

        /**
         * 从 OriginStorage 转换
         */
        public static PlaywrightOrigin fromOriginStorage(OriginStorage storage) {
            PlaywrightOrigin origin = new PlaywrightOrigin();
            origin.setOrigin(storage.getOrigin());
            if (storage.getItems() != null) {
                origin.setLocalStorage(new ArrayList<>(storage.getItems()));
            }
            return origin;
        }
    }
}

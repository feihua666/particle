package com.particle.global.crawler.runtime.session.auth;

import cn.hutool.core.util.URLUtil;
import com.particle.global.crawler.driver.browser.PlaywrightStorageState;
import com.particle.global.tool.http.UrlTool;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.servlet.RequestTool;
import lombok.Data;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Session 认证信息
 * <p>
 * 参考 Playwright StorageState 设计
 * 包含 Cookie、LocalStorage，覆盖主流网站的认证持久化需求
 * </p>
 *
 * @author yangwei
 * @since 2026/5/13 21:35
 */
@Data
public class SessionAuthInfo {
    /**
     * Cookie 列表
     */
    private List<CookieItem> cookies = new ArrayList<>();

    /**
     * LocalStorage（按域名分组）
     */
    private List<OriginStorage> localStorage = new ArrayList<>();

    /**
     * SessionStorage（按域名分组，可选）
     * 注意：SessionStorage 页面关闭即失效，大多数场景不需要持久化
     * SessionStorage 是 Tab 级别，不是浏览器级别，仅当前 Tab，不同 Tab 隔离
     * 所以没有用，这里列出来当注释吧
     */

    // private List<OriginStorage> sessionStorage = new ArrayList<>();

    /**
     * 自定义请求头（Token、签名等）
     */

    private Map<String, String> extraHeaders = new HashMap<>();

    /**
     * 判断认证信息是否为空（没有任何有效认证数据）
     *
     * @return true 表示没有任何认证数据
     */
    public boolean isEmpty() {
        if (cookies != null && !cookies.isEmpty()) {
            return false;
        }
        if (localStorage != null && !localStorage.isEmpty()) {
            return false;
        }
        if (extraHeaders != null && !extraHeaders.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 根据域名过滤认证信息
     *
     * @param domain 需要保留的域名
     * @return 过滤后的认证信息（只包含指定域名的数据）
     */
    public SessionAuthInfo filterByDomain(String domain) {
        return this.filterByDomains(Set.of(domain));
    }
    /**
     * 根据域名列表过滤认证信息
     *
     * @param domains 需要保留的域名列表
     * @return 过滤后的认证信息（只包含指定域名的数据）
     */
    public SessionAuthInfo filterByDomains(Set<String> domains) {
        if (domains == null || domains.isEmpty()) {
            return new SessionAuthInfo();
        }

        SessionAuthInfo filtered = new SessionAuthInfo();

        // 过滤 Cookies
        if (this.cookies != null) {
            filtered.setCookies(this.cookies.stream()
                    .filter(cookie -> domains.contains(cookie.getDomain()))
                    .collect(Collectors.toList()));
        }

        // 过滤 LocalStorage
        if (this.localStorage != null) {
            filtered.setLocalStorage(this.localStorage.stream()
                    .filter(storage -> domains.contains(UrlTool.extractHostWithPort(storage.getOrigin())))
                    .collect(Collectors.toList()));
        }

        // 保留 extraHeaders（认证相关的 header 通常是域名无关的）
        if (this.extraHeaders != null) {
            filtered.setExtraHeaders(new HashMap<>(this.extraHeaders));
        }

        return filtered;
    }
    /**
     * 合并另一个认证信息（当前优先）
     * <p>
     * 规则：
     * - 相同域名的 Cookie 合并，当前覆盖旧的
     * - 相同域名的 LocalStorage 合并，当前覆盖旧的
     * - Header 合并，当前覆盖旧的
     * </p>
     *
     * @param other 另一个认证信息
     * @return 当前对象（链式调用）
     */
    public SessionAuthInfo merge(SessionAuthInfo other) {
        if (other == null) return this;

        // 1. 合并 Cookies
        Map<String, CookieItem> cookieMap = new LinkedHashMap<>();
        List<CookieItem> otherCookies = other.getCookies() != null ? other.getCookies() : List.of();
        List<CookieItem> thisCookies  = this.cookies != null ? this.cookies : List.of();

        for (CookieItem c : otherCookies) cookieMap.put(cookieKey(c), c);
        for (CookieItem c : thisCookies)  cookieMap.put(cookieKey(c), c); // 当前覆盖
        this.cookies = new ArrayList<>(cookieMap.values());

        // 2. 合并 LocalStorage
        Map<String, OriginStorage> storageMap = new LinkedHashMap<>();
        List<OriginStorage> otherStorage = other.getLocalStorage() != null ? other.getLocalStorage() : List.of();
        List<OriginStorage> thisStorage  = this.localStorage != null ? this.localStorage : List.of();

        for (OriginStorage s : otherStorage) storageMap.put(s.getOrigin(), s);
        for (OriginStorage s : thisStorage) {
            if (storageMap.containsKey(s.getOrigin())) {
                // 同 origin 合并 items，当前覆盖
                OriginStorage existing = storageMap.get(s.getOrigin());
                Map<String, StorageItem> itemMap = new LinkedHashMap<>();
                if (existing.getItems() != null) existing.getItems().forEach(i -> itemMap.put(i.getName(), i));
                if (s.getItems() != null)        s.getItems().forEach(i -> itemMap.put(i.getName(), i));
                existing.setItems(new ArrayList<>(itemMap.values()));
            } else {
                storageMap.put(s.getOrigin(), s);
            }
        }
        this.localStorage = new ArrayList<>(storageMap.values());

        // 3. 合并 Headers
        Map<String, String> headerMap = new LinkedHashMap<>();
        Optional.ofNullable(other.getExtraHeaders()).ifPresent(headerMap::putAll);
        Optional.ofNullable(this.extraHeaders).ifPresent(headerMap::putAll);
        this.extraHeaders = headerMap;

        return this;
    }

    private String cookieKey(CookieItem c) {
        return c.getDomain() + "|" + c.getName() + "|"
                + (c.getPath() != null ? c.getPath() : "/");
    }


    /**
     * 过滤掉已过期的 Cookie
     */
    public SessionAuthInfo filterExpiredCookies() {
        this.cookies = cookies.stream()
                .filter(c -> !c.hasExpired())
                .collect(Collectors.toList());
        return this;
    }

    /**
     * 是否有有效的认证信息
     */
    public boolean hasAuth() {
        boolean hasCookies = cookies != null && !cookies.isEmpty();
        boolean hasStorage = localStorage != null && !localStorage.isEmpty();
        boolean hasHeaders = extraHeaders != null && !extraHeaders.isEmpty();
        return hasCookies || hasStorage || hasHeaders;
    }


    /**
     * 从 Playwright storageState JSON 字符串转换
     */
    public static SessionAuthInfo fromPlaywrightJson(String json) {
        try {
            PlaywrightStorageState state = JsonTool.getObjectMapper().readValue(json, PlaywrightStorageState.class);

            SessionAuthInfo info = new SessionAuthInfo();

            // 直接复用 CookieItem（字段完全一致）
            info.setCookies(state.getCookies());

            // 转换 origins → localStorage
            if (state.getOrigins() != null) {
                List<OriginStorage> localStorage = state.getOrigins().stream()
                        .map(PlaywrightStorageState.PlaywrightOrigin::toOriginStorage)
                        .collect(Collectors.toList());
                info.setLocalStorage(localStorage);
            }

            return info;
        } catch (Exception e) {
            throw new RuntimeException("解析 Playwright storageState 失败", e);
        }
    }

    /**
     * 转换为 Playwright storageState JSON 字符串
     */
    public String toPlaywrightJson() {
        try {
            PlaywrightStorageState state = new PlaywrightStorageState();

            // 直接复用 CookieItem
            state.setCookies(this.getCookies());

            // 转换 localStorage → origins
            if (this.getLocalStorage() != null) {
                List<PlaywrightStorageState.PlaywrightOrigin> origins = this.getLocalStorage().stream()
                        .map(PlaywrightStorageState.PlaywrightOrigin::fromOriginStorage)
                        .collect(Collectors.toList());
                state.setOrigins(origins);
            }

            return JsonTool.getObjectMapper().writeValueAsString(state);
        } catch (Exception e) {
            throw new RuntimeException("转换为 Playwright storageState 失败", e);
        }
    }
}

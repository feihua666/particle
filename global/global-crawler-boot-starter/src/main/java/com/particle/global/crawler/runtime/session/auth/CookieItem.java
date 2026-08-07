package com.particle.global.crawler.runtime.session.auth;

import lombok.Data;

/**
 * Cookie 信息
 * <p>
 * 对应 Playwright Cookie / Selenium Cookie
 * </p>
 *
 * @author yangwei
 * @since 2026/05/13 21:35
 */
@Data
public class CookieItem {

    /**
     * Cookie 名称
     */
    private String name;

    /**
     * Cookie 值
     */
    private String value;

    /**
     * 域名，如 .example.com
     */
    private String domain;

    /**
     * 路径，如 /
     */
    private String path;

    /**
     * 过期时间（Unix 时间戳，秒）
     * -1 表示 Session Cookie（浏览器关闭即失效）
     */
    private double expires;

    /**
     * 是否 HttpOnly
     */
    private boolean httpOnly;

    /**
     * 是否 Secure
     */
    private boolean secure;

    /**
     * SameSite 策略
     * Strict / Lax / None
     */
    private String sameSite;

    /**
     * 是否是 Session Cookie（无固定过期时间）
     */
    public boolean checkIsSessionCookie() {
        return expires < 0;
    }

    /**
     * 是否已过期
     */
    public boolean hasExpired() {
        if (checkIsSessionCookie()) return false;
        return System.currentTimeMillis() / 1000 > expires;
    }
}

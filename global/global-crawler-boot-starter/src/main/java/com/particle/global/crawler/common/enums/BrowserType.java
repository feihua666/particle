package com.particle.global.crawler.common.enums;

/**
 * 浏览器类型枚举
 * <p>
 * 定义支持的浏览器类型，用于 Playwright 和 Selenium 驱动配置。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
public enum BrowserType {

    /**
     * Chrome 浏览器
     */
    CHROME("chromium", "Chrome"),

    /**
     * Firefox 浏览器
     */
    FIREFOX("firefox", "Firefox"),

    /**
     * Safari 浏览器（仅 macOS）
     * 不建议使用
     * 在 macOS 上可以运行完整的 Safari，在其他系统上运行的是 WebKit 引擎的版本
     */
    WEBKIT("webkit", "Safari"),

    /**
     * Edge 浏览器
     * 不建议使用
     */
    EDGE("chromium", "Edge");

    private final String engine;
    private final String displayName;

    BrowserType(String engine, String displayName) {
        this.engine = engine;
        this.displayName = displayName;
    }

    /**
     * 获取浏览器引擎类型
     * <p>
     * Playwright 使用：chromium, firefox, webkit
     * Selenium 使用：chrome, firefox, safari, edge
     * </p>
     *
     * @return 引擎类型
     */
    public String getEngine() {
        return engine;
    }

    /**
     * 获取显示名称
     *
     * @return 显示名称
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 根据引擎名称获取浏览器类型
     *
     * @param engine 引擎名称
     * @return 浏览器类型，未找到返回 CHROME
     */
    public static BrowserType fromEngine(String engine) {
        if (engine == null) {
            return CHROME;
        }
        for (BrowserType type : values()) {
            if (type.engine.equalsIgnoreCase(engine) || type.name().equalsIgnoreCase(engine)) {
                return type;
            }
        }
        return CHROME;
    }

    /**
     * 获取 Playwright 引擎名称
     *
     * @return Playwright 引擎名称
     */
    public String toPlaywrightEngine() {
        return engine;
    }

    /**
     * 获取 Selenium 浏览器名称
     *
     * @return Selenium 浏览器名称
     */
    public String toSeleniumBrowser() {
        return switch (this) {
            case CHROME -> "chrome";
            case FIREFOX -> "firefox";
            case WEBKIT -> "safari";
            case EDGE -> "msedge";
        };
    }
}

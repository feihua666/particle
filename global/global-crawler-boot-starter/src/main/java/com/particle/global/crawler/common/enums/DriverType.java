package com.particle.global.crawler.common.enums;

/**
 * Driver 类型枚举
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
public enum DriverType {

    HTTP("http", "HTTP 驱动"),
    PLAYWRIGHT("playwright", "Playwright 浏览器驱动"),
    SELENIUM("selenium", "Selenium 浏览器驱动");

    private final String code;
    private final String description;

    DriverType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    private String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}

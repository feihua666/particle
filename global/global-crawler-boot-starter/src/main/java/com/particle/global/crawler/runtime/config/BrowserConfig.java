package com.particle.global.crawler.runtime.config;

import com.particle.global.crawler.runtime.config.browser.PlaywrightOptions;
import com.particle.global.crawler.runtime.config.browser.SeleniumOptions;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 浏览器配置（统一容器）
 * <p>
 * 包含 Playwright 和 Selenium 的各自配置。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BrowserConfig{


    /**
     * Playwright 专用选项
     */
    private PlaywrightOptions playwright = new PlaywrightOptions();

    /**
     * Selenium 专用选项
     */
    private SeleniumOptions selenium = new SeleniumOptions();

    /**
     * 合并配置（当前配置优先）
     */
    public BrowserConfig merge(BrowserConfig defaults) {
        if (defaults == null) {
            return this;
        }
        // 合并 Playwright 配置
        if (this.playwright != null && defaults.playwright != null) {
            this.playwright.merge(defaults.playwright);
        } else if (this.playwright == null) {
            this.playwright = defaults.playwright;
        }
        // 合并 Selenium 配置
        if (this.selenium != null && defaults.selenium != null) {
            this.selenium.merge(defaults.selenium);
        } else if (this.selenium == null) {
            this.selenium = defaults.selenium;
        }
        return this;
    }

    public static BrowserConfig defaultConfig() {
        BrowserConfig config = new BrowserConfig();
        config.setPlaywright(PlaywrightOptions.defaultOptions());
        config.setSelenium(SeleniumOptions.defaultOptions());
        return config;
    }
}

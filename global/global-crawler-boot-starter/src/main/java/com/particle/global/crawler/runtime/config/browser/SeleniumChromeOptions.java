package com.particle.global.crawler.runtime.config.browser;

import lombok.Data;

import java.util.Map;

/**
 * Selenium Chrome 选项
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
public class SeleniumChromeOptions {
    /**
     * Chrome 二进制路径
     */
    private String binary;

    /**
     * 额外的 Chrome 参数
     */
    private String[] arguments;

    /**
     * 预启动的 Chrome 扩展
     */
    private String[] extensions;

    /**
     * 实验性选项
     */
    private Map<String, Object> experimentalOptions;

    /**
     * 合并配置（当前配置优先）
     */
    public SeleniumChromeOptions merge(SeleniumChromeOptions defaults) {
        if (defaults == null) {
            return this;
        }
        if (this.binary == null) {
            this.binary = defaults.binary;
        }
        if (this.arguments == null) {
            this.arguments = defaults.arguments;
        }
        if (this.extensions == null) {
            this.extensions = defaults.extensions;
        }
        if (this.experimentalOptions == null) {
            this.experimentalOptions = defaults.experimentalOptions;
        }
        return this;
    }
    
    public static SeleniumChromeOptions defaultOptions() {
        SeleniumChromeOptions options = new SeleniumChromeOptions();
        options.setBinary(null);
        options.setArguments(null);
        options.setExtensions(null);
        return options;
    }
}

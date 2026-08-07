package com.particle.global.crawler.runtime.config.browser;

import lombok.Data;

import java.util.Map;

/**
 * Selenium Firefox 选项
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
public class SeleniumFirefoxOptions {
    /**
     * Firefox 二进制路径
     */
    private String binary;

    /**
     * 额外的 Firefox 参数
     */
    private String[] arguments;

    /**
     * Profile 路径
     */
    private String profile;

    /**
     * 偏好设置
     */
    private Map<String, Object> preferences;

    /**
     * 合并配置（当前配置优先）
     */
    public SeleniumFirefoxOptions merge(SeleniumFirefoxOptions defaults) {
        if (defaults == null) {
            return this;
        }
        if (this.binary == null) {
            this.binary = defaults.binary;
        }
        if (this.arguments == null) {
            this.arguments = defaults.arguments;
        }
        if (this.profile == null) {
            this.profile = defaults.profile;
        }
        if (this.preferences == null) {
            this.preferences = defaults.preferences;
        }
        return this;
    }
    
    public static  SeleniumFirefoxOptions defaultOptions(){
        SeleniumFirefoxOptions options = new SeleniumFirefoxOptions();
        options.setBinary(null);
        options.setArguments(null);
        options.setProfile(null);
        return options;
    }
}

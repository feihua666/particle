package com.particle.global.crawler.runtime.config.browser;

import com.particle.global.crawler.common.enums.BrowserType;
import lombok.Data;

/**
 * Playwright 专用选项
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
public class PlaywrightOptions extends CommonOptions {

    /**
     * Playwright 浏览器缓存目录（可选）
     * <p>
     * 如果配置了此路径，Playwright 将从该目录加载已下载的浏览器驱动。
     * 默认路径：~/.cache/ms-playwright
     * </p>
     */
    private String browsersPath;

    /**
     * 开发者选项
     */
    private DevOptions devOptions = new DevOptions();

    public static PlaywrightOptions defaultOptions() {
        PlaywrightOptions options = new PlaywrightOptions();

        CommonOptions commonOptions = CommonOptions.defaultOptions();
        options.fillByCommonOptions(commonOptions);

        options.setExecutablePath(null);
        options.setBrowsersPath(null);
        options.setDevOptions(DevOptions.defaultOptions());
        return options;
    }

    /**
     * 合并配置（当前配置优先）
     */
    public PlaywrightOptions merge(PlaywrightOptions defaults) {
        if (defaults == null) {
            return this;
        }
        // 合并父类 CommonOptions
        super.merge(defaults);
        // 合并子类字段
        if (this.browsersPath == null) {
            this.browsersPath = defaults.browsersPath;
        }
        if (this.devOptions != null && defaults.devOptions != null) {
            this.devOptions.merge(defaults.devOptions);
        } else if (this.devOptions == null) {
            this.devOptions = defaults.devOptions;
        }
        return this;
    }
}

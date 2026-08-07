package com.particle.global.crawler.runtime.config.browser;

import com.particle.global.crawler.common.enums.BrowserType;
import lombok.Data;

/**
 * 浏览器配置（统一容器）
 * <p>
 * 包含 Playwright 和 Selenium 的各自配置。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
public class CommonOptions {

    /**
     * 是否无头模式
     */
    private Boolean isHeadless;

    /**
     * 页面加载超时时间（毫秒）
     */
    private Integer timeout;

    /**
     * 浏览器类型：chrome, firefox, webkit, edge
     */
    private BrowserType browserType;

    /**
     * 浏览器窗口大小，格式：width
     */
    private Integer viewportWidth;
    /**
     * 浏览器窗口大小，格式：height
     */
    private Integer viewportHeight;

    /**
     * 浏览器可执行文件路径（可选）
     */
    private String executablePath;

    /**
     * 用户数据目录（可选）
     */
    private String userDataDir;

    /**
     * 用户数据目录下的Profile目录名
     * 例如：Default、Profile 1、Profile 2
     */
    private String userDataDirProfile;
    /**
     * 下载路径（可选）
     */
    private String downloadPath;

    /**
     * 是否启用反检测
     */
    private Boolean isStealth;

    protected void fillByCommonOptions(CommonOptions commonOptions) {

        this.setIsHeadless(commonOptions.getIsHeadless());
        this.setTimeout(commonOptions.getTimeout());
        this.setBrowserType(commonOptions.getBrowserType());
        this.setViewportWidth(commonOptions.getViewportWidth());
        this.setViewportHeight(commonOptions.getViewportHeight());
        this.setExecutablePath(commonOptions.getExecutablePath());
        this.setUserDataDir(commonOptions.getUserDataDir());
        this.setDownloadPath(commonOptions.getDownloadPath());
        this.setIsStealth(commonOptions.getIsStealth());
    }

    /**
     * 合并配置（当前配置优先）
     */
    public CommonOptions merge(CommonOptions defaults) {
        if (defaults == null) {
            return this;
        }
        if (this.isHeadless == null) {
            this.isHeadless = defaults.isHeadless;
        }
        if (this.timeout == null) {
            this.timeout = defaults.timeout;
        }
        if (this.browserType == null) {
            this.browserType = defaults.browserType;
        }
        if (this.viewportWidth == null) {
            this.viewportWidth = defaults.viewportWidth;
        }
        if (this.executablePath == null) {
            this.executablePath = defaults.executablePath;
        }
        if (this.userDataDir == null) {
            this.userDataDir = defaults.userDataDir;
        }
        if (this.downloadPath == null) {
            this.downloadPath = defaults.downloadPath;
        }
        if (this.isStealth == null) {
            this.isStealth = defaults.isStealth;
        }
        return this;
    }

    public static CommonOptions defaultOptions() {
        CommonOptions options = new CommonOptions();
        options.setIsHeadless(true);
        options.setTimeout(30000);
        options.setBrowserType(BrowserType.CHROME);
        options.setViewportWidth(1920);
        options.setViewportHeight(1080);
        options.setExecutablePath(null);
        options.setUserDataDir(null);
        options.setDownloadPath(null);
        options.setIsStealth(true);

        return options;
    }
}

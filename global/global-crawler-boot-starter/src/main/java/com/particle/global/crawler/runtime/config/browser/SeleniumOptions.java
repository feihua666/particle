package com.particle.global.crawler.runtime.config.browser;

import lombok.Data;

/**
 * Selenium 专用选项
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
public class SeleniumOptions extends CommonOptions {
    /**
     * 隐式等待时间（毫秒）
     */
    private Integer implicitWait;

    /**
     * 页面加载超时时间（毫秒）
     */
    private Integer pageLoadTimeout;

    /**
     * 脚本超时时间（毫秒）
     */
    private Integer scriptTimeout;

    /**
     * Chrome 特定选项
     */
    private SeleniumChromeOptions chromeOptions = new SeleniumChromeOptions();

    /**
     * Firefox 特定选项
     */
    private SeleniumFirefoxOptions firefoxOptions = new SeleniumFirefoxOptions();

    /**
     * 是否禁用 GPU（Chrome）
     */
    private Boolean isDisableGpu;

    /**
     * 是否禁用软件渲染列表
     */
    private Boolean isDisableSoftwareRasterizer;

    /**
     * 是否禁用扩展
     */
    private Boolean isDisableExtensions;

    /**
     * 是否禁用弹出窗口阻止
     */
    private Boolean isDisablePopupBlocking;

    /**
     * 是否禁用自动化信息栏
     */
    private Boolean isDisableAutomationInfoBar;

    /**
     * 是否使用自动化扩展
     */
    private Boolean isUseAutomationExtension;

    /**
     * 合并配置（当前配置优先）
     */
    public SeleniumOptions merge(SeleniumOptions defaults) {
        if (defaults == null) {
            return this;
        }
        // 合并父类 CommonOptions
        super.merge(defaults);
        // 合并子类字段
        if (this.implicitWait == null) {
            this.implicitWait = defaults.implicitWait;
        }
        if (this.pageLoadTimeout == null) {
            this.pageLoadTimeout = defaults.pageLoadTimeout;
        }
        if (this.scriptTimeout == null) {
            this.scriptTimeout = defaults.scriptTimeout;
        }
        if (this.chromeOptions != null && defaults.chromeOptions != null) {
            this.chromeOptions.merge(defaults.chromeOptions);
        } else if (this.chromeOptions == null) {
            this.chromeOptions = defaults.chromeOptions;
        }
        if (this.firefoxOptions != null && defaults.firefoxOptions != null) {
            this.firefoxOptions.merge(defaults.firefoxOptions);
        } else if (this.firefoxOptions == null) {
            this.firefoxOptions = defaults.firefoxOptions;
        }
        if (this.isDisableGpu == null) {
            this.isDisableGpu = defaults.isDisableGpu;
        }
        if (this.isDisableSoftwareRasterizer == null) {
            this.isDisableSoftwareRasterizer = defaults.isDisableSoftwareRasterizer;
        }
        if (this.isDisableExtensions == null) {
            this.isDisableExtensions = defaults.isDisableExtensions;
        }
        if (this.isDisablePopupBlocking == null) {
            this.isDisablePopupBlocking = defaults.isDisablePopupBlocking;
        }
        if (this.isDisableAutomationInfoBar == null) {
            this.isDisableAutomationInfoBar = defaults.isDisableAutomationInfoBar;
        }
        if (this.isUseAutomationExtension == null) {
            this.isUseAutomationExtension = defaults.isUseAutomationExtension;
        }
        return this;
    }
    
    public static SeleniumOptions defaultOptions() {
        SeleniumOptions options = new SeleniumOptions();

        CommonOptions commonOptions = CommonOptions.defaultOptions();
        options.fillByCommonOptions(commonOptions);
        
        options.setImplicitWait(0);
        options.setPageLoadTimeout(30000);
        options.setScriptTimeout(30000);
        options.setChromeOptions(SeleniumChromeOptions.defaultOptions());
        options.setFirefoxOptions(SeleniumFirefoxOptions.defaultOptions());
        options.setIsDisableGpu(true);
        options.setIsDisableSoftwareRasterizer(false);
        options.setIsDisableExtensions(true);
        options.setIsDisablePopupBlocking(true);
        options.setIsDisableAutomationInfoBar(true);
        options.setIsUseAutomationExtension(false);
        
        return options;
    }
}

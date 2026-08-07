package com.particle.global.crawler.runtime;

import com.particle.global.crawler.runtime.config.*;
import com.particle.global.tool.proxy.ProxyConfig;
import lombok.Data;

/**
 * 运行时选项（每次执行时可覆盖）
 * <p>
 * 用于在每次执行 Pipeline 时动态指定选项，优先级高于 CrawlerProperties。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
public class CrawlRuntimeOptions {

    /**
     * 代理设置
     */
    private ProxyConfig proxy;

    /**
     * Session 配置
     */
    private SessionConfig session = new SessionConfig();

    /**
     * Driver 类型
     */
    private DriverConfig deriver = new DriverConfig();

    /**
     * HTTP 配置
     */
    private HttpConfig http = new HttpConfig();

    /**
     * 浏览器配置
     */
    private BrowserConfig browser = new BrowserConfig();

    /**
     * 合并默认选项
     * <p>
     * 将默认选项中的配置合并到当前选项，当前选项优先。
     * </p>
     *
     * @param defaults 默认选项
     * @return 合并后的选项
     */
    public CrawlRuntimeOptions merge(CrawlRuntimeOptions defaults) {
        if (defaults == null) {
            return this;
        }

        // 合并代理配置
        if (this.proxy != null && defaults.getProxy() != null) {
            this.proxy.merge(defaults.getProxy());
        } else if (this.proxy == null) {
            this.proxy = defaults.getProxy();
        }

        // 合并 session 配置
        if (this.session != null && defaults.getSession() != null) {
            this.session.merge(defaults.getSession());
        }

        // 合并 Driver 配置
        if (this.deriver != null && defaults.getDeriver() != null) {
            this.deriver.merge(defaults.getDeriver());
        } else if (this.deriver == null) {
            this.deriver = defaults.getDeriver();
        }

        // 合并 HTTP 配置
        if (this.http != null && defaults.getHttp() != null) {
            this.http.merge(defaults.getHttp());
        } else if (this.http == null) {
            this.http = defaults.getHttp();
        }

        // 合并浏览器配置
        if (this.browser != null && defaults.getBrowser() != null) {
            this.browser.merge(defaults.getBrowser());
        } else if (this.browser == null) {
            this.browser = defaults.getBrowser();
        }

        return this;
    }

    public static CrawlRuntimeOptions defaultOptions() {
        CrawlRuntimeOptions crawlRuntimeOptions = new CrawlRuntimeOptions();
        crawlRuntimeOptions.setProxy(null);
        crawlRuntimeOptions.setSession(SessionConfig.defaultConfig());
        crawlRuntimeOptions.setDeriver(DriverConfig.defaultConfig());
        crawlRuntimeOptions.setHttp(HttpConfig.defaultConfig());
        crawlRuntimeOptions.setBrowser(BrowserConfig.defaultConfig());
        return crawlRuntimeOptions;
    }
}

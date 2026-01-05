package com.particle.global.crawler.tool;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>
 * Playwright 浏览器工具类
 * </p>
 *
 * @author yw
 * @since 2026-01-04
 */
public class CrawlerPlaywrightTool {

    /**
     * 创建 Playwright 实例
     */
    public static Playwright create() {
        return Playwright.create();
    }

    /**
     * 安全关闭 Playwright
     */
    public static void close(Playwright playwright) {
        if (playwright != null) {
            playwright.close();
        }
    }

    /**
     * 创建 launchPersistentContextOptions
     */
    public static BrowserType.LaunchPersistentContextOptions newLaunchPersistentContextOptions() {
        BrowserType.LaunchPersistentContextOptions launchPersistentContextOptions = new BrowserType.LaunchPersistentContextOptions();
        launchPersistentContextOptions.setHeadless(false)
                .setViewportSize(1920,1080);
        return launchPersistentContextOptions;
    }
    /**
     * chrome 相关
     */
    public static class Chrome {

        public static BrowserContext newContext(
                Playwright playwright,
                BrowserType.LaunchPersistentContextOptions options,
                String userDataDir
        ) {
            Path userDataDirPath = Paths.get(userDataDir);
            BrowserContext context = playwright.chromium().launchPersistentContext(userDataDirPath, options);

            return context;
        }

        /**
         * 添加 Chrome 浏览器的防检测脚本
         * 必须在 launchPersistentContext() 之后、newPage() 之前调用
         */
        public static void injectAntiDetect(BrowserContext context) {
            context.addInitScript("""
        // webdriver
        Object.defineProperty(navigator, 'webdriver', { get: () => undefined });

        // languages
        Object.defineProperty(navigator, 'languages', {
            get: () => ['zh-CN', 'zh', 'en-US']
        });

        // plugins
        Object.defineProperty(navigator, 'plugins', {
            get: () => [{ name: 'Chrome PDF Plugin' }]
        });

        // chrome runtime
        window.chrome = {
            runtime: {}
        };

        // platform
        Object.defineProperty(navigator, 'platform', {
            get: () => 'Win32'
        });

        // hardware
        Object.defineProperty(navigator, 'hardwareConcurrency', {
            get: () => 8
        });

        Object.defineProperty(navigator, 'deviceMemory', {
            get: () => 8
        });
    """);
        }

    }

    /**
     * Firefox 相关
     */
    public static class Firefox {

        public static BrowserContext newContext(
                Playwright playwright,
                BrowserType.LaunchPersistentContextOptions options,
                String userDataDir
        ) {

            Path userDataDirPath = Paths.get(userDataDir);
            BrowserContext context =
                    playwright.firefox().launchPersistentContext(userDataDirPath, options);

            return context;
        }

        /**
         * 添加 Firefox 浏览器的防检测脚本
         * 必须在 launchPersistentContext() 之后、newPage() 之前调用
         */
        public static void injectAntiDetect(BrowserContext context) {
            context.addInitScript(
                    """
                    Object.defineProperty(navigator, 'webdriver', { get: () => undefined });
                    Object.defineProperty(navigator, 'languages', { get: () => ['zh-CN','zh','en-US'] });
                    Object.defineProperty(navigator, 'plugins', { get: () => [1,2,3] });

                    const originalQuery = navigator.permissions.query.bind(navigator.permissions);
                    navigator.permissions.query = (parameters) => {
                        if (parameters && parameters.name === 'notifications') {
                            return Promise.resolve({ state: Notification.permission });
                        }
                        return originalQuery(parameters);
                    };

                    const getParameter = WebGLRenderingContext.prototype.getParameter;
                    if(getParameter){
                        WebGLRenderingContext.prototype.getParameter = function(parameter) {
                            if (parameter === 37445) return 'Mozilla';
                            if (parameter === 37446) return 'Mozilla GPU';
                            return getParameter.call(this, parameter);
                        }
                    }
                    
                    """
            );
        }
    }
}

package com.particle.global.crawler.playwright;

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
        BrowserType.LaunchPersistentContextOptions options = new BrowserType.LaunchPersistentContextOptions();
        options.setHeadless(false)
                .setViewportSize(null);

        return options;
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
            context.addInitScript(
                    """
                    Object.defineProperty(navigator, 'webdriver', { get: () => undefined }); 
                    """
            );
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
                    """
            );
        }
    }
}

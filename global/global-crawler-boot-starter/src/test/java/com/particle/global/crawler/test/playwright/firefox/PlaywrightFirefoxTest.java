package com.particle.global.crawler.test.playwright.firefox;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.particle.global.crawler.test.Test;
import com.particle.global.crawler.tool.CrawlerPlaywrightTool;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 简单测试 firefox
 * 启动浏览器，访问一个网页
 * </p>
 *
 * @author yangwei
 * @since 2025/11/11 10:56
 */
@Slf4j
public class PlaywrightFirefoxTest {

    public static void main(String[] args) {
        Playwright playwright = CrawlerPlaywrightTool.create();
        // 临时启动时路径
        String userDataDir = "/Users/yw/temp/selenium-firefox";
        String userAgent = Test.chromeAgent;
        try {
            BrowserType.LaunchPersistentContextOptions launchPersistentContextOptions = CrawlerPlaywrightTool.newLaunchPersistentContextOptions();
            launchPersistentContextOptions
                    .setUserAgent(userAgent);

            BrowserContext context =
                    CrawlerPlaywrightTool.Firefox.newContext(
                            playwright,
                            launchPersistentContextOptions,
                            userDataDir
                    );
            // 注入防检测脚本
            CrawlerPlaywrightTool.Firefox.injectAntiDetect(context);

            Page page = context.newPage();
            page.navigate("https://baidu.com");
            page.waitForTimeout(5000);

            context.close();
        } finally {
            CrawlerPlaywrightTool.close(playwright);
        }
    }
}

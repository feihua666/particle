package com.particle.global.crawler.examples.playwright.chrome;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.particle.global.crawler.constants.CrawlTestConstants;
import com.particle.global.crawler.playwright.CrawlerPlaywrightTool;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 简单测试 chrome
 * 启动浏览器，访问一个网页
 * </p>
 *
 * @author yangwei
 * @since 2025/11/11 10:56
 */
@Slf4j
public class PlaywrightChromeExample {

    public static void main(String[] args) {
        Playwright playwright = CrawlerPlaywrightTool.create();

        // 临时启动时路径
        String userDataDir = "/Users/yw/temp/playwright-chrome";
        String userAgent = CrawlTestConstants.chromeAgent;
        try {
            BrowserType.LaunchPersistentContextOptions launchPersistentContextOptions = CrawlerPlaywrightTool.newLaunchPersistentContextOptions();
            launchPersistentContextOptions.setChannel("chrome");
            // launchPersistentContextOptions.setUserAgent(userAgent);
            BrowserContext context =
                    CrawlerPlaywrightTool.Chrome.newContext(
                            playwright,
                            launchPersistentContextOptions,
                            userDataDir
                    );
            // 注入防检测脚本
            CrawlerPlaywrightTool.Chrome.injectAntiDetect(context);

            // 会开一个新 tap 页
            // Page page = context.newPage();
            Page page = context.pages().get(0);
            page.navigate("https://baidu.com");

            log.info("当前页面标题：{}", page.title());

            context.close();
        } finally {
            CrawlerPlaywrightTool.close(playwright);
        }
    }
}

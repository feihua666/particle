package com.particle.global.crawler.examples.playwright.firefox;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.particle.global.crawler.constants.CrawlTestConstants;
import com.particle.global.crawler.playwright.CrawlerPlaywrightTool;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
public class PlaywrightFirefoxExample {

    public static void main(String[] args) {
        Playwright playwright = CrawlerPlaywrightTool.create();
        // 临时启动时路径
        String userDataDir = "/Users/yw/temp/playwright-firefox";
        String userAgent = CrawlTestConstants.chromeAgent;
        try {
            BrowserType.LaunchPersistentContextOptions options = CrawlerPlaywrightTool.newLaunchPersistentContextOptions();
            options.setUserAgent(userAgent);
            options.setArgs(List.of(
                    /**
                     * 禁止 Firefox 复用已有的 Firefox 进程 / 实例
                     * 默认情况下，Firefox 的行为是：
                     * 如果系统里已经有一个 Firefox 在运行
                     * 新启动的 Firefox 不会新建进程
                     * 而是把“打开页面”的请求交给已有实例
                     * 这在自动化里是灾难级行为。
                     */
                    "--no-remote",
                    /**
                     * 禁用 Firefox 的“上次会话崩溃提示弹窗”
                     * 这个弹窗长这样：
                     * Firefox 上次意外关闭，是否恢复会话？
                     */
                    "--disable-session-crashed-bubble"
            ));
            // 保持 会话
            options.setFirefoxUserPrefs(new java.util.HashMap<String, Object>() {{
                put("privacy.clearOnShutdown.cookies", false);
                put("privacy.clearOnShutdown.offlineApps", false);
            }});

            BrowserContext context = CrawlerPlaywrightTool.Firefox.newContext(playwright, options, userDataDir);
            // 注入防检测脚本
            CrawlerPlaywrightTool.Firefox.injectAntiDetect(context);

            // 会开一个新窗口
            // Page page = context.newPage();
            Page page = context.pages().get(0);
            page.navigate("https://baidu.com");
            log.info("当前页面标题：{}", page.title());

            page.waitForTimeout(1000);

            context.close();
        } finally {
            CrawlerPlaywrightTool.close(playwright);
        }
    }
}

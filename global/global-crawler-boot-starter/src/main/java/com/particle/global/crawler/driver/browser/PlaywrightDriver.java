package com.particle.global.crawler.driver.browser;

import com.microsoft.playwright.*;
import com.particle.global.crawler.driver.CrawlDriver;
import com.particle.global.crawler.exception.DriverException;
import com.particle.global.crawler.runtime.session.auth.SessionAuthInfo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Playwright 浏览器驱动实现
 * <p>
 * 基于 Playwright 的浏览器驱动实现，支持 Chromium、Firefox、WebKit 等浏览器。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
public class PlaywrightDriver implements CrawlDriver {

    private Page page;
    private final BrowserContext context;
    private final Browser browser;
    private final Playwright playwright;


    public PlaywrightDriver(Page page, BrowserContext context,Browser browser, Playwright playwright) {
        this.page = page;
        this.context = context;
        this.browser = browser;
        this.playwright = playwright;
    }

    @Override
    public void open(String url) {
        log.debug("Playwright 打开页面: {}", url);
        page.navigate(url);
    }

    @Override
    public void click(String selector, int timeout) {
        log.debug("点击元素: selector={}, timeout={}", selector, timeout);
        Locator locator = page.locator(selector);
        locator.click(new Locator.ClickOptions().setTimeout(timeout));
    }

    @Override
    public void input(String selector, String text, int timeout) {
        log.debug("输入文本: selector={}, text={}, timeout={}", selector, text, timeout);
        Locator locator = page.locator(selector);
        locator.fill(text);
    }

    @Override
    public List<String> extractText(String selector) {
        log.debug("提取文本: selector={}", selector);
        Locator locator = page.locator(selector);
        return locator.allTextContents();
    }

    @Override
    public List<String> extractAttr(String selector, String attrName) {
        log.debug("提取属性: selector={}, attr={}", selector, attrName);
        Locator locator = page.locator(selector);
        int count = locator.count();
        List<String> results = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            String value = locator.nth(i).getAttribute(attrName);
            if (value != null) {
                results.add(value);
            }
        }
        return results;
    }

    @Override
    public List<String> extractHtml(String selector) {
        log.debug("提取 HTML: selector={}", selector);

        Locator locator = page.locator(selector);
        int count = locator.count();
        List<String> results = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            results.add(locator.nth(i).innerHTML());
        }
        return results;
    }


    @Override
    public Object executeScript(String script) {
        return page.evaluate(script);
    }

    @Override
    public Object executeScript(String script, Object... args) {
        return page.evaluate(script, args);
    }

    @Override
    public String getPageSource() {
        return page.content();
    }

    @Override
    public String getCurrentUrl() {
        return page.url();
    }

    @Override
    public String getTitle() {
        return page.title();
    }

    @Override
    public void pressKey(String key) {
        log.debug("按下按键: {}", key);
        page.keyboard().press(key);
    }

    @Override
    public void hover(String selector) {
        log.debug("鼠标悬停: selector={}", selector);
        page.locator(selector).hover();
    }

    @Override
    public void close() {
        log.debug("PlaywrightDriver 释放");

        try {
            if (page != null) {
                page.close();
                log.debug("Playwright Page 已关闭");
            }
        } catch (Exception e) {
            log.warn("关闭 Page 时发生错误", e);
        }

        // 持久化上下文模式：关闭 context
        try {
            if (context != null && browser == null) {
                context.close();
                log.debug("Playwright BrowserContext 已关闭");
            }
        } catch (Exception e) {
            log.warn("关闭 BrowserContext 时发生错误", e);
        }

        // 临时浏览器模式：关闭 browser
        try {
            if (browser != null) {
                browser.close();
                log.debug("Playwright Browser 已关闭");
            }
        } catch (Exception e) {
            log.warn("关闭 Browser 时发生错误", e);
        }

        try {
            if (playwright != null) {
                playwright.close();
                log.debug("Playwright 实例已关闭");
            }
        } catch (Exception e) {
            log.warn("关闭 Playwright 时发生错误", e);
        }
    }

    public Page getPage() {
        return page;
    }

    @Override
    public Object getCurrentPage() {
        return page;
    }

    @Override
    public void setCurrentPage(Object page) {
        if (page instanceof Page) {
            this.page = (Page) page;
        }
    }

    public BrowserContext getContext() {
        return context;
    }


    @Override
    public Object newTab(String url) {
        Page newPage = context != null ? context.newPage() : page.context().newPage();
        if (url != null && !url.isEmpty()) {
            newPage.navigate(url);
        }
        // 自动切换为新创建的 Page
        this.page = newPage;
        return newPage;
    }

    @Override
    public void closeTab() {
        // 获取所有标签页
        java.util.List<Page> pages = context != null ? context.pages() : page.context().pages();

        if (pages.isEmpty()) {
            log.debug("没有可关闭的标签页");
            return;
        }

        // 关闭当前标签页
        page.close();
        log.debug("关闭当前标签页");

        // 如果还有其他标签页，切换到最后一个
        java.util.List<Page> remainingPages = context != null ? context.pages() : page.context().pages();
        if (!remainingPages.isEmpty()) {
            this.page = remainingPages.get(remainingPages.size() - 1);
            log.debug("切换到剩余的最后个标签页");
        }
    }


    @SneakyThrows
    @Override
    public void applyAuthInfo(SessionAuthInfo sessionAuthInfo) {
        if (sessionAuthInfo == null || !sessionAuthInfo.hasAuth()) {
            log.debug("认证信息为空，跳过应用");
            return;
        }

        Path tempFile = null;
        try {
            BrowserContext browserContext = context != null ? context : page.context();

            // 1. 获取当前完整的 StorageState
            String currentStorageStateJson = browserContext.storageState();
            SessionAuthInfo currentAuth = SessionAuthInfo.fromPlaywrightJson(currentStorageStateJson);

            // 2. 合并
            currentAuth.merge(sessionAuthInfo);

            // 3. 使用唯一文件名（避免并发冲突）
            String mergedJson = currentAuth.toPlaywrightJson();
            tempFile = Files.createTempFile("auth-" + UUID.randomUUID().toString(), ".json");
            Files.writeString(tempFile, mergedJson);

            // 4. 应用
            browserContext.setStorageState(tempFile);

            log.debug("认证信息合并成功");

        }finally {
            // 5. 确保删除临时文件
            if (tempFile != null) {
                try {
                    Files.deleteIfExists(tempFile);
                } catch (IOException e) {
                    log.warn("删除临时文件失败: {}", tempFile, e);
                }
            }
        }
    }
    @Override
    public SessionAuthInfo getAuthInfo() {
        BrowserContext browserContext = context != null ? context : page.context();
        // 使用 Playwright 原生方法获取 StorageState
        String storageStateJson = browserContext.storageState();
        // 转换为 SessionAuthInfo
        return SessionAuthInfo.fromPlaywrightJson(storageStateJson);
    }
}

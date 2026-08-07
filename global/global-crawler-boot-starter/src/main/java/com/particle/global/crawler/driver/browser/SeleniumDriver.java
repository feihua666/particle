package com.particle.global.crawler.driver.browser;

import com.particle.global.crawler.driver.CrawlDriver;
import com.particle.global.crawler.exception.DriverException;
import com.particle.global.crawler.runtime.session.auth.CookieItem;
import com.particle.global.crawler.runtime.session.auth.OriginStorage;
import com.particle.global.crawler.runtime.session.auth.SessionAuthInfo;
import com.particle.global.crawler.runtime.session.auth.StorageItem;
import com.particle.global.tool.http.UrlTool;
import com.particle.global.tool.json.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Selenium WebDriver 实现
 * <p>
 * 基于 Selenium WebDriver 的浏览器驱动实现，支持 Chrome、Firefox、Edge 等浏览器。
 * </p>
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
public class SeleniumDriver implements CrawlDriver {

    private WebDriver driver;
    /**
     * 内存中的认证缓存（按域名）
     * 每次 open 后自动更新
     * 因为 selenium 不支持直接设置认证信息到存储，如果获取认证信息必须先打开页面，这里采用折中方案，在每次 open 后，将当前页面的认证信息缓存到内存中
     * 而后由 session 管理是否存储认证信息
     * 由于是每次打开获取认证信息，所以有一些比如使用 页面 click action 等操作跳转的页面是没有办法获取认证信息，所以可能需要开发这类action需要用户处理
     */
    private final Map<String, SessionAuthInfo> authCache = new ConcurrentHashMap<>();


    public SeleniumDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void open(String url) {
        log.debug("Selenium 打开页面: {}", url);
        driver.get(url);
        cacheCurrentAuth(url);
    }

    @Override
    public void click(String selector, int timeout) {
        log.debug("Selenium 点击元素: selector={}, timeout={}", selector, timeout);
        WebElement element = findElementWithTimeout(selector, timeout);
        element.click();
    }

    @Override
    public void input(String selector, String text, int timeout) {
        log.debug("Selenium 输入文本: selector={}, text={}, timeout={}", selector, text, timeout);
        WebElement element = findElementWithTimeout(selector, timeout);
        element.clear();
        element.sendKeys(text);
    }

    @Override
    public List<String> extractText(String selector) {
        log.debug("Selenium 提取文本: selector={}", selector);
        List<WebElement> elements = driver.findElements(By.cssSelector(selector));
        List<String> texts = new ArrayList<>();
        for (WebElement element : elements) {
            texts.add(element.getText());
        }
        return texts;
    }

    @Override
    public List<String> extractAttr(String selector, String attrName) {
        log.debug("Selenium 提取属性: selector={}, attr={}", selector, attrName);
        List<WebElement> elements = driver.findElements(By.cssSelector(selector));
        List<String> attrs = new ArrayList<>();
        for (WebElement element : elements) {
            String attr = element.getAttribute(attrName);
            attrs.add(attr != null ? attr : "");
        }
        return attrs;
    }

    @Override
    public List<String> extractHtml(String selector) {
        log.debug("Selenium 提取 HTML: selector={}", selector);
        List<WebElement> elements = driver.findElements(By.cssSelector(selector));
        List<String> htmls = new ArrayList<>();
        for (WebElement element : elements) {
            htmls.add(element.getAttribute("innerHTML"));
        }
        return htmls;
    }

    @Override
    public Object executeScript(String script) {
        return executeScript(script, (Object[]) null);
    }

    @Override
    public Object executeScript(String script, Object... args) {
        if (driver instanceof JavascriptExecutor) {
            return ((JavascriptExecutor) driver).executeScript(script, args);
        }
        throw new DriverException("当前浏览器驱动不支持 JavaScript 执行");
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public void pressKey(String key) {
        log.debug("按下按键: {}", key);
        Actions actions = new Actions(driver);

        // 将字符串转换为 Selenium 的 Keys 枚举
        Keys seleniumKey = convertToSeleniumKey(key);
        if (seleniumKey != null) {
            actions.sendKeys(seleniumKey).perform();
        } else {
            // 如果不是特殊键，直接发送字符
            actions.sendKeys(key).perform();
        }
    }

    @Override
    public void hover(String selector) {
        log.debug("鼠标悬停: selector={}", selector);
        WebElement element = driver.findElement(By.cssSelector(selector));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    /**
     * 将按键名称转换为 Selenium Keys 枚举
     */
    private Keys convertToSeleniumKey(String key) {
        return switch (key.toUpperCase()) {
            case "ENTER" -> Keys.ENTER;
            case "RETURN" -> Keys.RETURN;
            case "TAB" -> Keys.TAB;
            case "ESCAPE", "ESC" -> Keys.ESCAPE;
            case "BACK_SPACE", "BACKSPACE" -> Keys.BACK_SPACE;
            case "DELETE" -> Keys.DELETE;
            case "SPACE" -> Keys.SPACE;
            case "ARROW_UP", "UP" -> Keys.ARROW_UP;
            case "ARROW_DOWN", "DOWN" -> Keys.ARROW_DOWN;
            case "ARROW_LEFT", "LEFT" -> Keys.ARROW_LEFT;
            case "ARROW_RIGHT", "RIGHT" -> Keys.ARROW_RIGHT;
            case "F1" -> Keys.F1;
            case "F2" -> Keys.F2;
            case "F3" -> Keys.F3;
            case "F4" -> Keys.F4;
            case "F5" -> Keys.F5;
            case "F6" -> Keys.F6;
            case "F7" -> Keys.F7;
            case "F8" -> Keys.F8;
            case "F9" -> Keys.F9;
            case "F10" -> Keys.F10;
            case "F11" -> Keys.F11;
            case "F12" -> Keys.F12;
            default -> null;
        };
    }

    @Override
    public Object getCurrentPage() {
        return driver;
    }

    @Override
    public void setCurrentPage(Object page) {
        // Selenium 不支持直接设置 WebDriver，需要通过窗口切换
        if (page instanceof String) {
            switchToWindow((String) page);
        }
    }

    @Override
    public Object newTab(String url) {
        // 使用 JavaScript 打开新标签页
        ((JavascriptExecutor) driver).executeScript("window.open('', '_blank');");

        // 切换到新标签页
        Set<String> handles = driver.getWindowHandles();
        String newHandle = handles.toArray(new String[0])[handles.size() - 1];
        driver.switchTo().window(newHandle);

        // 如果指定了 URL，导航到该 URL
        if (url != null && !url.isEmpty()) {
            driver.get(url);
        }

        return newHandle;
    }

    @Override
    public void closeTab() {
        // 获取所有窗口句柄
        Set<String> handles = driver.getWindowHandles();

        if (handles.isEmpty()) {
            log.debug("没有可关闭的标签页");
            return;
        }

        // 获取当前窗口句柄
        String currentHandle = driver.getWindowHandle();

        // 关闭当前窗口
        driver.close();
        log.debug("关闭当前标签页");

        // 如果还有其他窗口，切换到第一个
        Set<String> remainingHandles = driver.getWindowHandles();
        if (!remainingHandles.isEmpty()) {
            String newHandle = remainingHandles.iterator().next();
            driver.switchTo().window(newHandle);
            log.debug("切换到剩余的第一个标签页");
        }
    }

    @Override
    public void close() {
        if (driver != null) {
            driver.quit();  // 应该用 quit() 而不是 close()
            log.debug("Selenium WebDriver 已关闭");
        }
    }

    /**
     * 切换到指定窗口
     *
     * @param windowHandle 窗口句柄
     */
    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }


    /**
     * 查找元素（带超时）
     */
    private WebElement findElementWithTimeout(String selector, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeout));
        return wait.until(d -> d.findElement(By.cssSelector(selector)));
    }

    /**
     * 应用认证信息
     * 由于 selenium 限制，想要应用认证信息，必须先导航到对应的页面，并且只能使用脚本设置，建议使用 playwright
     * @param sessionAuthInfo 认证信息
     */
    @Override
    public void applyAuthInfo(SessionAuthInfo sessionAuthInfo) {
        if (sessionAuthInfo == null || !sessionAuthInfo.hasAuth()) {
            log.debug("认证信息为空，跳过应用");
            return;
        }

        String originalUrl = driver.getCurrentUrl();
        Set<String> processedDomains = new HashSet<>();

        // 1. 收集所有需要处理的域名/Origin
        Set<String> domainsToProcess = new LinkedHashSet<>();

        // 从 Cookies 收集域名
        if (sessionAuthInfo.getCookies() != null && !sessionAuthInfo.getCookies().isEmpty()) {
            for (CookieItem cookie : sessionAuthInfo.getCookies()) {
                if (cookie.hasExpired()) continue;
                String domain = cookie.getDomain();
                if (domain != null && !domain.isEmpty()) {
                    if (domain.startsWith(".")) {
                        domain = domain.substring(1);
                    }
                    domainsToProcess.add(domain);
                }
            }
        }

        // 从 LocalStorage 收集 Origin
        if (sessionAuthInfo.getLocalStorage() != null && !sessionAuthInfo.getLocalStorage().isEmpty()) {
            for (OriginStorage storage : sessionAuthInfo.getLocalStorage()) {
                String origin = storage.getOrigin();
                if (origin != null && !origin.isEmpty()) {
                    domainsToProcess.add(origin);
                }
            }
        }

        log.debug("需要处理 {} 个域名: {}", domainsToProcess.size(), domainsToProcess);

        // 2. 对每个域名进行导航并注入认证信息
        for (String domain : domainsToProcess) {
            if (processedDomains.contains(domain)) {
                continue;
            }

            try {
                // 构建 URL
                String url = domain.startsWith("http") ? domain : "https://" + domain;
                log.debug("导航到: {}", url);
                driver.get(url);
                processedDomains.add(domain);

                // 2.1 注入该域名的 Cookies
                if (sessionAuthInfo.getCookies() != null && !sessionAuthInfo.getCookies().isEmpty()) {
                    for (CookieItem cookie : sessionAuthInfo.getCookies()) {
                        if (cookie.hasExpired()) continue;

                        String cookieDomain = cookie.getDomain();
                        if (cookieDomain != null && cookieDomain.startsWith(".")) {
                            cookieDomain = cookieDomain.substring(1);
                        }

                        if (domain.equals(cookieDomain)) {
                            Cookie.Builder cookieBuilder = new Cookie.Builder(cookie.getName(), cookie.getValue());
                            if (cookie.getDomain() != null && !cookie.getDomain().isEmpty()) {
                                cookieBuilder.domain(cookie.getDomain());
                            }
                            if (cookie.getPath() != null && !cookie.getPath().isEmpty()) {
                                cookieBuilder.path(cookie.getPath());
                            }
                            if (!cookie.checkIsSessionCookie() && cookie.getExpires() > 0) {
                                cookieBuilder.expiresOn(new Date((long) (cookie.getExpires() * 1000)));
                            }
                            if (cookie.isHttpOnly()) {
                                cookieBuilder.isHttpOnly(true);
                            }
                            if (cookie.isSecure()) {
                                cookieBuilder.isSecure(true);
                            }

                            driver.manage().addCookie(cookieBuilder.build());
                            log.debug("已添加 Cookie: {}={} for domain: {}",
                                    cookie.getName(), cookie.getValue(), domain);
                        }
                    }
                }

                // 2.2 注入该域名的 LocalStorage
                if (sessionAuthInfo.getLocalStorage() != null && !sessionAuthInfo.getLocalStorage().isEmpty()) {
                    for (OriginStorage storage : sessionAuthInfo.getLocalStorage()) {
                        String origin = storage.getOrigin();
                        String originHost = UrlTool.extractHostWithPort(origin);

                        if (domain.contains(originHost) && storage.getItems() != null && !storage.getItems().isEmpty()) {
                            Map<String, String> data = new HashMap<>();
                            for (StorageItem item : storage.getItems()) {
                                data.put(item.getName(), item.getValue());
                            }

                            String script = buildLocalStorageScript(data);
                            executeScript(script);
                            log.debug("已添加 LocalStorage: origin={}, 条目数={}", origin, data.size());
                        }
                    }
                }

            } catch (Exception e) {
                log.debug("处理域名 {} 失败: {}", domain, e.getMessage());
            }
        }

        log.info("认证信息应用成功，已处理 {} 个域名", processedDomains.size());

        // 恢复原页面
        if (originalUrl != null && !originalUrl.isEmpty()) {
            try {
                driver.get(originalUrl);
            } catch (Exception e) {
                log.debug("恢复原页面失败: {}", originalUrl, e);
            }
        }
    }
    /**
     * 构建设置 LocalStorage 的 JavaScript 脚本
     */
    private String buildLocalStorageScript(java.util.Map<String, String> data) {
        StringBuilder script = new StringBuilder();
        script.append("(() => {\n");
        script.append("  const data = ").append(JsonTool.toJsonStr(data)).append(";\n");
        script.append("  for (const [key, value] of Object.entries(data)) {\n");
        script.append("    localStorage.setItem(key, value);\n");
        script.append("  }\n");
        script.append("})();\n");
        return script.toString();
    }

    /**
     * 获取已经存储的认证信息
     * 由于 selenium 限制，想要获取认证信息，必须先导航到对应的页面，并且只能使用脚本获取，建议使用 playwright
     * @return
     */
    @Override
    public SessionAuthInfo getAuthInfo() {
        // 返回所有缓存的认证信息的合并结果
        SessionAuthInfo mergedAuthInfo = new SessionAuthInfo();

        if (authCache.isEmpty()) {
            log.debug("缓存中无认证信息");
            return mergedAuthInfo;
        }

        // 合并所有域名的认证信息
        for (Map.Entry<String, SessionAuthInfo> entry : authCache.entrySet()) {
            SessionAuthInfo cached = entry.getValue();
            if (cached != null && cached.hasAuth()) {
                mergedAuthInfo.merge(cached);
                log.debug("合并认证信息: domain={}, cookies={}, localStorage={}",
                        entry.getKey(),
                        cached.getCookies() != null ? cached.getCookies().size() : 0,
                        cached.getLocalStorage() != null ? cached.getLocalStorage().size() : 0);
            }
        }

        log.debug("获取所有缓存认证信息完成: 总计 {} 个域名, {} 个 Cookie, {} 个 LocalStorage",
                authCache.size(),
                mergedAuthInfo.getCookies().size(),
                mergedAuthInfo.getLocalStorage().size());

        return mergedAuthInfo;
    }

    /**
     * 获取当前页面的认证信息
     *
     * @return 认证信息
     */
    public SessionAuthInfo getCurrentPageAuthInfo() {
        SessionAuthInfo authInfo = new SessionAuthInfo();

        // 1. 获取 Cookies
        Set<Cookie> seleniumCookies = driver.manage().getCookies();
        if (seleniumCookies != null && !seleniumCookies.isEmpty()) {
            java.util.List<CookieItem> cookies = seleniumCookies.stream()
                .map(c -> {
                    CookieItem cookie = new CookieItem();
                    cookie.setName(c.getName());
                    cookie.setValue(c.getValue());
                    cookie.setDomain(c.getDomain());
                    cookie.setPath(c.getPath());

                    if (c.getExpiry() != null) {
                        cookie.setExpires(c.getExpiry().getTime() / 1000.0);
                    } else {
                        cookie.setExpires(-1);
                    }

                    cookie.setHttpOnly(c.isHttpOnly());
                    cookie.setSecure(c.isSecure());
                    // Selenium Cookie 没有 sameSite 属性

                    return cookie;
                })
                .collect(java.util.stream.Collectors.toList());
            authInfo.setCookies(cookies);
        }

        // 2. 获取 LocalStorage
        // 注意：由于浏览器安全策略（同源策略），Selenium 只能获取当前页面的 LocalStorage
        // 无法跨域获取其他域名的 LocalStorage。如果需要完整的多域名 LocalStorage，
        // 建议使用 PlaywrightDriver，它通过 storageState() 原生支持多域名。
        java.util.Map<String, String> localStorageData =
                (java.util.Map<String, String>) executeScript("(() => {\n" +
                        "  const data = {};\n" +
                        "  for (let i = 0; i < localStorage.length; i++) {\n" +
                        "    const key = localStorage.key(i);\n" +
                        "    data[key] = localStorage.getItem(key);\n" +
                        "  }\n" +
                        "  return data;\n" +
                        "})()");

        if (localStorageData != null && !localStorageData.isEmpty()) {
            String currentOrigin = (String) executeScript("return window.location.origin");
            OriginStorage storage = new OriginStorage(currentOrigin);
            localStorageData.forEach((key, value) -> {
                storage.add(key, value);
            });
            authInfo.setLocalStorage(java.util.Collections.singletonList(storage));
        }

        log.debug("获取认证信息成功: {} 个 Cookie, {} 个 LocalStorage",
            authInfo.getCookies().size(),
            authInfo.getLocalStorage().size());

        return authInfo;
    }

    /**
     * 自动缓存当前页面的认证信息
     */
    private void cacheCurrentAuth(String url) {
        log.debug("自动缓存认证: url={}", url);
        String domain = UrlTool.extractHostWithPort(url);
        // 获取当前页面认证
        SessionAuthInfo authInfo = getCurrentPageAuthInfo();
        if (authInfo != null && authInfo.hasAuth()) {
            authCache.put(domain, authInfo);

        }
    }
}

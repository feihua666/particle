package com.particle.global.crawler.driver.http;

import com.particle.global.crawler.driver.CrawlDriver;
import com.particle.global.crawler.exception.DriverException;
import com.particle.global.crawler.runtime.config.HttpConfig;
import com.particle.global.crawler.runtime.session.auth.CookieItem;
import com.particle.global.crawler.runtime.session.auth.SessionAuthInfo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;

/**
 * HTTP 驱动实现（基于 Java 11+ HttpClient + Jsoup）
 * <p>
 * 基于 Java 标准库 HttpClient，配合 Jsoup 进行 HTML 解析，适用于静态页面的抓取。
 * </p>
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
public class HttpDriver implements CrawlDriver {

    // ==================== 共享数据（static） ====================
    private static CookieManager sharedCookieManager;

    static {
        sharedCookieManager = new CookieManager();
        sharedCookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(sharedCookieManager);
    }
    // ==================== 实例数据 ====================
    private static HttpClient httpClient;
    private Document document;
    private String currentUrl;
    private String currentTitle;
    private int currentStatusCode;
    private Map<String, String> responseHeaders;
    private Map<String, String> extraHeaders;

    public HttpDriver(HttpConfig httpConfig) {
        HttpClient.Builder builder = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(httpConfig.getTimeout()))
                .followRedirects(httpConfig.getIsFollowRedirects() ?
                        HttpClient.Redirect.NORMAL : HttpClient.Redirect.NEVER);

        httpClient = builder.build();
        // 实例数据初始化
        this.extraHeaders = new HashMap<>();
        this.responseHeaders = new HashMap<>();
    }

    @SneakyThrows
    @Override
    public void open(String url) {
        log.debug("HTTP GET 请求: {}", url);
        this.currentUrl = url;

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();

        // 添加自定义请求头
        if (extraHeaders != null && !extraHeaders.isEmpty()) {
            for (Map.Entry<String, String> entry : extraHeaders.entrySet()) {
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
            log.debug("添加请求头: {}", extraHeaders.keySet());
        }

        HttpRequest request = requestBuilder.build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        this.currentStatusCode = response.statusCode();
        this.currentUrl = response.uri().toString();
        this.responseHeaders = new HashMap<>();

        // 保存响应头
        response.headers().map().forEach((name, values) -> {
            if (values != null && !values.isEmpty()) {
                responseHeaders.put(name, values.get(0));
            }
        });

        String html = response.body();
        this.document = Jsoup.parse(html, url);
        this.currentTitle = document.title();

        log.debug("HTTP GET 请求成功: {} - {}, title={}", currentStatusCode, url, currentTitle);
    }

    @Override
    public void click(String selector, int timeout) {
        throw new DriverException("HTTP 驱动不支持点击操作，请使用浏览器驱动（Playwright/Selenium）");
    }

    @Override
    public void input(String selector, String text, int timeout) {
        throw new DriverException("HTTP 驱动不支持输入操作，请使用浏览器驱动（Playwright/Selenium）");
    }

    @Override
    public List<String> extractText(String selector) {
        if (document == null) {
            log.warn("未打开任何页面");
            return Collections.emptyList();
        }

        Elements elements = document.select(selector);
        List<String> texts = new ArrayList<>();
        for (Element element : elements) {
            texts.add(element.text());
        }
        return texts;
    }

    @Override
    public List<String> extractAttr(String selector, String attrName) {
        if (document == null) {
            log.warn("未打开任何页面");
            return Collections.emptyList();
        }

        Elements elements = document.select(selector);
        List<String> attrs = new ArrayList<>();
        for (Element element : elements) {
            attrs.add(element.attr(attrName));
        }
        return attrs;
    }

    @Override
    public List<String> extractHtml(String selector) {
        if (document == null) {
            log.warn("未打开任何页面");
            return Collections.emptyList();
        }

        Elements elements = document.select(selector);
        List<String> htmls = new ArrayList<>();
        for (Element element : elements) {
            htmls.add(element.html());
        }
        return htmls;
    }


    @Override
    public Object executeScript(String script) {
        throw new DriverException("HTTP 驱动不支持 JavaScript 执行，请使用浏览器驱动（Playwright/Selenium）");
    }

    @Override
    public Object executeScript(String script, Object... args) {
        throw new DriverException("HTTP 驱动不支持 JavaScript 执行，请使用浏览器驱动（Playwright/Selenium）");
    }

    @Override
    public String getPageSource() {
        if (document == null) {
            log.warn("未打开任何页面");
            return null;
        }
        return document.html();
    }

    @Override
    public String getCurrentUrl() {
        return currentUrl;
    }

    @Override
    public String getTitle() {
        return currentTitle;
    }

    @Override
    public void pressKey(String key) {
        throw new DriverException("HTTP 驱动不支持键盘操作，请使用浏览器驱动（Playwright/Selenium）");
    }

    @Override
    public void hover(String selector) {
        throw new DriverException("HTTP 驱动不支持鼠标悬停操作，请使用浏览器驱动（Playwright/Selenium）");
    }

    @Override
    public void closeTab() {
        throw new DriverException("HTTP 驱动不支持多标签页，请使用浏览器驱动（Playwright/Selenium）");
    }

    @Override
    public Object getCurrentPage() {
        return document;
    }

    @Override
    public void setCurrentPage(Object page) {
        throw new DriverException("HTTP 驱动不支持多标签页，请使用浏览器驱动（Playwright/Selenium）");
    }

    @Override
    public Object newTab(String url) {
        throw new DriverException("HTTP 驱动不支持多标签页，请使用浏览器驱动（Playwright/Selenium）");
    }

    @Override
    public void close() {
        this.document = null;
        this.currentUrl = null;
        this.currentTitle = null;
        log.debug("HTTP 驱动已关闭");
    }

    // ==================== 认证信息管理 ====================

    @Override
    public void applyAuthInfo(SessionAuthInfo sessionAuthInfo) {
        if (sessionAuthInfo == null || !sessionAuthInfo.hasAuth()) {
            log.debug("认证信息为空，跳过应用");
            return;
        }

        // 1. 应用 Cookies
        if (sessionAuthInfo.getCookies() != null && !sessionAuthInfo.getCookies().isEmpty()) {
            // 添加新 Cookies
            for (CookieItem cookie : sessionAuthInfo.getCookies()) {
                if (cookie.hasExpired()) {
                    log.debug("跳过已过期的 Cookie: {}", cookie.getName());
                    continue;
                }

                HttpCookie httpCookie = new HttpCookie(cookie.getName(), cookie.getValue());
                if (cookie.getDomain() != null && !cookie.getDomain().isEmpty()) {
                    httpCookie.setDomain(cookie.getDomain());
                }
                if (cookie.getPath() != null && !cookie.getPath().isEmpty()) {
                    httpCookie.setPath(cookie.getPath());
                }
                if (!cookie.checkIsSessionCookie() && cookie.getExpires() > 0) {
                    long maxAge = (long) (cookie.getExpires() - System.currentTimeMillis() / 1000);
                    httpCookie.setMaxAge(maxAge > 0 ? maxAge : 0);
                }
                httpCookie.setHttpOnly(cookie.isHttpOnly());
                httpCookie.setSecure(cookie.isSecure());

                String domain = cookie.getDomain();
                if (domain != null && domain.startsWith(".")) {
                    domain = domain.substring(1);
                }
                try {
                    URI uri = new URI("https", domain, "/", null);
                    sharedCookieManager.getCookieStore().add(uri, httpCookie);
                } catch (Exception e) {
                    log.debug("添加 Cookie 失败: {}", cookie.getName(), e);
                }
            }
            log.debug("已应用 {} 个 Cookie", sessionAuthInfo.getCookies().size());
        }

        // 2. 应用 Extra Headers
        if (sessionAuthInfo.getExtraHeaders() != null && !sessionAuthInfo.getExtraHeaders().isEmpty()) {
            if (this.extraHeaders == null) {
                this.extraHeaders = new HashMap<>();
            }
            this.extraHeaders.putAll(sessionAuthInfo.getExtraHeaders());
            log.debug("已应用 {} 个 Extra Headers", sessionAuthInfo.getExtraHeaders().size());
        }

        log.debug("认证信息应用成功");
    }

    @Override
    public SessionAuthInfo getAuthInfo() {
        SessionAuthInfo authInfo = new SessionAuthInfo();

        // 1. 获取 Cookies
        List<HttpCookie> httpCookies = sharedCookieManager.getCookieStore().getCookies();

        if (httpCookies != null && !httpCookies.isEmpty()) {
            List<CookieItem> cookies = new ArrayList<>();
            for (HttpCookie httpCookie : httpCookies) {
                CookieItem cookie = new CookieItem();
                cookie.setName(httpCookie.getName());
                cookie.setValue(httpCookie.getValue());
                cookie.setDomain(httpCookie.getDomain());
                cookie.setPath(httpCookie.getPath());
                cookie.setHttpOnly(httpCookie.isHttpOnly());
                cookie.setSecure(httpCookie.getSecure());

                long maxAge = httpCookie.getMaxAge();
                if (maxAge > 0) {
                    cookie.setExpires(System.currentTimeMillis() / 1000 + maxAge);
                } else {
                    cookie.setExpires(-1);
                }

                cookies.add(cookie);
            }
            authInfo.setCookies(cookies);
            log.debug("获取到 {} 个 Cookie", cookies.size());
        }

        // 2. 获取 Extra Headers
        if (extraHeaders != null && !extraHeaders.isEmpty()) {
            authInfo.setExtraHeaders(new HashMap<>(extraHeaders));
            log.debug("获取到 {} 个 Extra Headers", extraHeaders.size());
        }

        return authInfo;
    }

}

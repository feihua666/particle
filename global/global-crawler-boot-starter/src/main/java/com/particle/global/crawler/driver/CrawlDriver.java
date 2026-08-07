package com.particle.global.crawler.driver;

import com.particle.global.crawler.runtime.session.auth.SessionAuthInfo;

import java.util.List;
import java.util.Set;

/**
 * 爬虫驱动接口
 * <p>
 * 定义所有浏览器/HTTP 驱动的统一接口。
 * 支持：
 * - HTTP 驱动（Jsoup）
 * - Playwright 驱动（浏览器自动化）
 * - Selenium 驱动（可选保留）
 * </p>
 *
 * <p>实现类：</p>
 * <ul>
 *   <li>{@link com.particle.global.crawler.driver.browser.PlaywrightDriver} - Playwright 浏览器驱动</li>
 *   <li>{@link com.particle.global.crawler.driver.http.HttpDriver} - HTTP 驱动（Jsoup）</li>
 *   <li>{@link com.particle.global.crawler.driver.browser.SeleniumDriver} - Selenium 驱动</li>
 * </ul>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
public interface CrawlDriver {

    /**
     * 应用认证信息
     *
     * @param sessionAuthInfo 认证信息
     */
    void applyAuthInfo(SessionAuthInfo sessionAuthInfo);
    /**
     * 获取认证信息
     *
     * @return 认证信息
     */
    SessionAuthInfo getAuthInfo();
    /**
     * 打开 URL
     *
     * @param url 目标 URL
     */
    void open(String url);

    /**
     * 点击元素
     *
     * @param selector CSS 选择器
     * @param timeout  超时时间（毫秒）
     */
    void click(String selector, int timeout);

    /**
     * 输入文本
     *
     * @param selector CSS 选择器
     * @param text     要输入的文本
     * @param timeout  超时时间（毫秒）
     */
    void input(String selector, String text, int timeout);

    /**
     * 提取文本列表
     *
     * @param selector CSS 选择器
     * @return 文本列表
     */
    List<String> extractText(String selector);

    /**
     * 提取属性列表
     *
     * @param selector CSS 选择器
     * @param attrName 属性名（如 "href", "src", "text"）
     * @return 属性值列表
     */
    List<String> extractAttr(String selector, String attrName);

    /**
     * 提取 HTML
     *
     * @param selector CSS 选择器
     * @return HTML 列表
     */
    List<String> extractHtml(String selector);

    /**
     * 执行 JavaScript
     *
     * @param script JavaScript 脚本
     * @return 执行结果
     */
    Object executeScript(String script);

    /**
     * 执行带参数的 JavaScript
     *
     * @param script JavaScript 脚本
     * @param args   参数
     * @return 执行结果
     */
    Object executeScript(String script, Object... args);

    /**
     * 获取当前页面源码
     *
     * @return HTML 源码
     */
    String getPageSource();

    /**
     * 获取当前 URL
     *
     * @return 当前 URL
     */
    String getCurrentUrl();

    /**
     * 获取页面标题
     *
     * @return 页面标题
     */
    String getTitle();

    /**
     * 按下键盘按键
     * <p>
     * 模拟按下指定的键盘按键，如 Enter、Tab、Escape 等。
     * </p>
     *
     * @param key 按键名称（如 "Enter", "Tab", "Escape"）
     */
    void pressKey(String key);

    /**
     * 鼠标悬停在元素上
     * <p>
     * 将鼠标悬停在指定的 CSS 选择器所代表的元素上。
     * </p>
     *
     * @param selector CSS 选择器
     */
    void hover(String selector);

    /**
     * 获取当前 Page 对象（仅浏览器驱动支持）
     * <p>
     * 用于支持多标签页切换，Action 执行时可通过此方法获取当前操作的 Page。
     * </p>
     *
     * @return Page 对象，HTTP 驱动返回 null
     */
    Object getCurrentPage();

    /**
     * 设置当前 Page 对象（仅浏览器驱动支持）
     * <p>
     * 用于切换到其他标签页或新打开的页面。
     * </p>
     *
     * @param page Page 对象
     */
    void setCurrentPage(Object page);

    /**
     * 新建标签页（仅浏览器驱动支持）
     * <p>
     * 创建一个新的标签页，导航到指定 URL，并自动切换到该标签页。如果URL为空，则创建空白标签页。
     * </p>
     *
     * @param url 目标 URL
     * @return 新创建的 Page 对象
     */
    Object newTab(String url);

    /**
     * 关闭当前标签页（仅浏览器驱动支持）
     * <p>
     * 关闭当前活动的标签页，如果有其他标签页则自动切换到第一个。
     * </p>
     */
    void closeTab();

    /**
     * 关闭驱动
     */
    void close();
}

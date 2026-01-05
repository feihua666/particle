package com.particle.global.crawler.tool;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 常用工具
 * </p>
 *
 * @author yangwei
 * @since 2025/12/23 20:15
 */
@Slf4j
public class CrawlerWebDriverTool {

    /**
     * 打开url
     * @param driver
     * @param url
     */
    public static void openUrl(WebDriver driver,String url) {
        driver.get(url);
    }
    /**
     * 新建标签页
     * @param driver
     */
    public static void newTab(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
    }
    /**
     * 切换到最后一个标签页
     * @param driver
     */
    public static void switchToLastTab(WebDriver driver) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

    }
    /**
     * 获取页面标题
     * @param driver
     * @return
     */
    public static String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    /**
     * 等待全局,这属于一个配置项，设置后每次对浏览器的操作都会等待，直到时间到或要操作的元素可以操作
     * 注意：和 sleep()方法不同，sleep()方法会阻塞当前线程，而 waitGlobal()方法不会阻塞当前线程,也不一定会实际等待设置的时间
     * @param driver
     * @param millis 毫秒，建议 2000 毫秒
     */
    public static void waitGlobal(WebDriver driver, long millis) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(millis));
    }
    /**
     * 强制等待
     * @param millis
     */
    public static void waitForce(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    /**
     * cssSelector
     * @param cssSelector
     * @return
     */
    public static By cssLocator(String cssSelector) {
        return By.cssSelector(cssSelector);
    }
    /**
     * 等待元素出现
     * @param driver
     * @param millis 毫秒，建议 2000 毫秒
     * @param locator
     * @return
     */
    public static WebElement findElementWithWait(WebDriver driver, long millis, By locator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofMillis(millis));
        return webDriverWait.until(webDriver -> {
            ExpectedCondition<WebElement> webElementExpectedCondition = ExpectedConditions.presenceOfElementLocated(locator);
            try {
                return webElementExpectedCondition.apply(webDriver);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 通过cssSelector获取元素
     * @param driver
     * @param locator
     * @return
     */
    public static WebElement findElement(FirefoxDriver driver, By locator) {
        long start = System.currentTimeMillis();
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        } catch (Exception e) {
            log.error("findElementByCssSelector error,cssSelector={}",locator.toString(),e);
            return  null;
        }finally {
            long end = System.currentTimeMillis();
            log.debug("findElementByCssSelector time={}ms,cssSelector={}",end-start,locator.toString());
        }
        return element;
    }
    /**
     * 关闭浏览器
     * @param driver
     */
    public static void closeDriver(WebDriver  driver) {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * chrome 相关工具
     */
    public static class Chrome{
        /**
         * 初始化Selenium WebDriver
         */
        public static ChromeDriver setUpDriver(String path,ChromeOptions chromeOptions) {
            System.setProperty("webdriver.chrome.driver", path);
            ChromeDriver driver = new ChromeDriver(chromeOptions);
            return driver;
        }
        /**
         * 新建chrome选项
         * @return
         */
        public static ChromeOptions newOptions() {
            return new ChromeOptions();
        }

        /**
         * 反检测配置
         * 返爬虫反检测配置，适用于简单返爬虫场景
         * @param userDataDir 用户数据目录建议使用真实的路径 如：/Users/xxx/Library/Application Support/Google/Chrome
         * @param profileDir 用户数据目录建议使用真实的路径，如：Default
         * @param userAgent 浏览器UA
         * @return
         */
        public static ChromeOptions antiDetectOptions(String userDataDir,String profileDir,String userAgent) {
            ChromeOptions options = new ChromeOptions();

            // ===== 基础稳定性 =====
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");

            // ===== 拟人化 =====
            options.addArguments("--start-maximized");
            options.addArguments("--lang=zh-CN");

            // 使用真实用户数据目录（非常重要）
            if (StrUtil.isNotEmpty(userDataDir)) {
                options.addArguments("--user-data-dir=" + userDataDir);
            }

            if (StrUtil.isNotEmpty(profileDir)) {
                options.addArguments("--profile-directory=" + profileDir);
            }
            // ===== 反自动化标识 =====
            options.addArguments("--disable-blink-features=AutomationControlled");

            // User-Agent（与你 Test.chromeAgent 保持一致）
            if (StrUtil.isNotEmpty(userAgent)) {
                options.addArguments("--user-agent=" + userAgent);
            }

            // ===== 实验性 =====
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

            return options;
        }

        /**
         * 注入反检测脚本
         * 必须在 driver.get(url) 之前调用
         * @param driver
         */
        public static void injectAntiDetectJS(WebDriver driver) {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // webdriver
            js.executeScript(
                    "Object.defineProperty(navigator, 'webdriver', {get: () => undefined});"
            );

            // chrome
            js.executeScript(
                    "window.navigator.chrome = { runtime: {} };"
            );

            // plugins
            js.executeScript(
                    "Object.defineProperty(navigator, 'plugins', {get: () => [1,2,3,4,5]});"
            );

            // languages
            js.executeScript(
                    "Object.defineProperty(navigator, 'languages', {get: () => ['zh-CN','zh','en-US']});"
            );

            // permissions
            js.executeScript(
                    "const originalQuery = navigator.permissions.query;" +
                            "navigator.permissions.query = (parameters) => (" +
                            " parameters.name === 'notifications' ?" +
                            " Promise.resolve({ state: Notification.permission }) :" +
                            " originalQuery(parameters)" +
                            ");"
            );

            // WebGL 指纹（重要）
            js.executeScript(
                    "const getParameter = WebGLRenderingContext.prototype.getParameter;" +
                            "WebGLRenderingContext.prototype.getParameter = function(parameter) {" +
                            " if (parameter === 37445) return 'Intel Inc.';" +
                            " if (parameter === 37446) return 'Intel Iris OpenGL Engine';" +
                            " return getParameter.call(this, parameter);" +
                            "};"
            );

            // hairline fix（部分站点）
            js.executeScript(
                    "Object.defineProperty(screen, 'availTop', {get: () => 0});"
            );
        }

    }

    /**
     * firefox 相关工具
     */
    public static class Firefox{
        /**
         * 初始化Selenium WebDriver
         */
        public static FirefoxDriver setUpDriver(String path,FirefoxOptions chromeOptions) {
            System.setProperty("webdriver.gecko.driver", path);
            FirefoxDriver driver = new FirefoxDriver(chromeOptions);
            return driver;
        }
        /**
         * 新建chrome选项
         * @return
         */
        public static FirefoxOptions newOptions(FirefoxProfile profile) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            if (profile != null) {
                firefoxOptions.setProfile(profile);
            }
            return firefoxOptions;
        }

        /**
         * 新建 firefox profile 配置
         * @param profileDir
         * @param userAgent
         * @return
         */
        public static FirefoxProfile newProfile(String profileDir,String userAgent) {
            FirefoxProfile profile = null;
            if (StrUtil.isNotEmpty(profileDir)) {
                profile = new FirefoxProfile(new File(profileDir));
            }else {
                profile = new FirefoxProfile();
            }

            if (StrUtil.isNotEmpty(userAgent)) {
                profile.setPreference("general.useragent.override", userAgent);
                return profile;
            }
            return profile;
        }
        /**
         * Firefox 专用反检测脚本注入
         * 必须在 driver.get(url) 之前调用
         */
        public static void injectAntiDetectJSForFirefox(WebDriver driver) {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // ===== webdriver =====
            js.executeScript(
                    "Object.defineProperty(navigator, 'webdriver', {" +
                            "  get: () => undefined" +
                            "});"
            );

            // ===== languages =====
            js.executeScript(
                    "Object.defineProperty(navigator, 'languages', {" +
                            "  get: () => ['zh-CN', 'zh', 'en-US']" +
                            "});"
            );

            // ===== plugins（Firefox 本身不暴露真实插件列表）=====
            js.executeScript(
                    "Object.defineProperty(navigator, 'plugins', {" +
                            "  get: () => [1, 2, 3]" +
                            "});"
            );

            // ===== permissions.query（Firefox 安全写法）=====
            js.executeScript(
                    "const originalQuery = navigator.permissions.query.bind(navigator.permissions);" +
                            "navigator.permissions.query = (parameters) => {" +
                            "  if (parameters && parameters.name === 'notifications') {" +
                            "    return Promise.resolve({ state: Notification.permission });" +
                            "  }" +
                            "  return originalQuery(parameters);" +
                            "};"
            );

            // ===== WebGL 指纹（Firefox 必修）=====
            js.executeScript(
                    "const getParameter = WebGLRenderingContext.prototype.getParameter;" +
                            "WebGLRenderingContext.prototype.getParameter = function(parameter) {" +
                            "  if (parameter === 37445) return 'Mozilla';" +   // UNMASKED_VENDOR_WEBGL
                            "  if (parameter === 37446) return 'Mozilla GPU';" + // UNMASKED_RENDERER_WEBGL
                            "  return getParameter.call(this, parameter);" +
                            "};"
            );

            // ===== timezone 偏差（Firefox 常被查）=====
            js.executeScript(
                    "Object.defineProperty(Intl.DateTimeFormat().resolvedOptions(), 'timeZone', {" +
                            "  get: () => 'Asia/Shanghai'" +
                            "});"
            );

            // ===== screen 修正（防 headless）=====
            js.executeScript(
                    "Object.defineProperty(screen, 'availTop', { get: () => 0 });" +
                            "Object.defineProperty(screen, 'availLeft', { get: () => 0 });"
            );
        }

    }
}

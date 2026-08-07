package com.particle.global.crawler.webdriver;

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
    public static List<WebElement> findElements(WebDriver driver, By locator) {
        long start = System.currentTimeMillis();
        List<WebElement> elements = null;
        try {
            elements = driver.findElements(locator);
        } catch (Exception e) {
            log.error("findElementsByCssSelector error,cssSelector={}",locator.toString(),e);
            return  null;
        }finally {
            long end = System.currentTimeMillis();
            log.error("findElementsByCssSelector time={}ms,cssSelector={}",end-start,locator.toString());
        }
        return elements;
    }
    /**
     * 通过cssSelector获取元素
     * @param driver
     * @param locator
     * @return
     */
    public static WebElement findElement(WebDriver driver, By locator) {
        long start = System.currentTimeMillis();
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        } catch (Exception e) {
            log.error("findElementByCssSelector error,cssSelector={}",locator.toString(),e);
            return  null;
        }finally {
            long end = System.currentTimeMillis();
            log.error("findElementByCssSelector time={}ms,cssSelector={}",end-start,locator.toString());
        }
        return element;
    }
    /**
     * 通过cssSelector获取元素
     * @param driver
     * @param locator
     * @return
     */
    public static WebElement findElement(WebElement driver, By locator) {
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
         * 真实的路径 浏览器输入 chrome://version/ 查看
         * @param userDataDir 用户数据目录建议使用真实的路径 如：/Users/xxx/Library/Application Support/Google/Chrome
         * @param profileDir 用户数据目录建议使用真实的路径，如：Default
         * @param userAgent 浏览器UA
         * @return
         */
        public static ChromeOptions newOptions(String userDataDir,String profileDir,String userAgent) {
            ChromeOptions options = new ChromeOptions();

            // 使用真实用户数据目录（非常重要）
            if (StrUtil.isNotEmpty(userDataDir)) {
                options.addArguments("--user-data-dir=" + userDataDir);
            }

            if (StrUtil.isNotEmpty(profileDir)) {
                options.addArguments("--profile-directory=" + profileDir);
            }

            // User-Agent（与你 Test.chromeAgent 保持一致）
            if (StrUtil.isNotEmpty(userAgent)) {
                options.addArguments("--user-agent=" + userAgent);
            }

            return options;
        }

        /**
         * 注入反检测脚本
         * 必须在 driver.get(url) 之后调用
         * @param driver
         */
        public static void injectAntiDetectJS(WebDriver driver) {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // webdriver
            js.executeScript(
                    "Object.defineProperty(navigator, 'webdriver', {get: () => undefined});"
            );
        }

    }

    /**
     * firefox 相关工具
     */
    public static class Firefox{
        /**
         * 初始化Selenium WebDriver
         *
         * 关于窗口最大化：
         * 当你 使用固定 profile 时：
         * Firefox 会记住上一次窗口状态
         * 如果上次不是最大化,本次也不是
         * <code>
         *     WebDriver driver = new FirefoxDriver(options);
         *     driver.manage().window().maximize();
         * </code>
         */
        public static FirefoxDriver setUpDriver(String path,FirefoxOptions firefoxOptions) {
            System.setProperty("webdriver.gecko.driver", path);
            FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
            return driver;
        }
        /**
         * 新建chrome选项
         *
         * 注意：options.setProfile(...) 本质上还是 FirefoxProfile 对象
         * 这就是你“每次登录都丢”的根因
         *
         * 唯一可靠方式：-profile <真实路径>
         *  <code>
         *      FirefoxOptions options = new FirefoxOptions();
         *      options.addArguments("-profile");
         *      options.addArguments("/absolute/path/to/your/profile");
         *
         *       WebDriver driver = new FirefoxDriver(options);
         *  </code>
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
         *
         * 浏览器 输入 about:support 查看 profile 路径
         *
         * 注意：使用 FirefoxProfile 对象（99% 的坑）
         * 官方原话（简化版）：FirefoxProfile is copied to a temporary directory before use
         * 这就是你“每次登录都丢”的根因
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
         * 必须在 driver.get(url) 之后调用
         */
        public static void injectAntiDetectJSForFirefox(WebDriver driver) {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 经测试，在打开页面后调用，在网页控制台中访问 window.navigator.webdriver 返回 undefined，如果不调用这个注入会返回 true
            js.executeScript(
                    "Object.defineProperty(navigator, 'webdriver', {" +
                            "  get: () => undefined" +
                            "});"
            );
        }

    }
}

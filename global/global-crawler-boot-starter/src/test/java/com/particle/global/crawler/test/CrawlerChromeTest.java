package com.particle.global.crawler.test;

import com.google.common.collect.Lists;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 简单测试
 * </p>
 *
 * @author yangwei
 * @since 2025/11/11 10:56
 */
public class CrawlerChromeTest {
    private static ChromeDriver driver;

    public static void main(String[] args) {
        setupDriver();
        openUrl("https://www.creditchina.gov.cn");
        // openUrl("https://wenshu.court.gov.cn/");
        // closeDriver();
    }

    /**
     * 初始化Selenium WebDriver
     */
    public static void setupDriver() {
        // 设置ChromeDriver路径
        System.setProperty("webdriver.chrome.driver", "/Users/yw/fh/chromedriver-mac-x64/chromedriver");

        // 配置浏览器选项
        ChromeOptions options = new ChromeOptions();
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36";

        // 核心配置：设置调试器地址
        // 需要先打开浏览器
        // '/Applications/Google Chrome.app/Contents/MacOS/Google Chrome' --remote-debugging-port=9222 --user-data-dir="/Users/yw/temp/aaa"
        // options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        // 其他优化选项
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-web-security");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");
        options.addArguments("--user-data-dir=/Users/yw/temp/bbb");

        options.addArguments("--user-agent=" + userAgent);

        options.setExperimentalOption("excludeSwitches", Lists.newArrayList("enable-automation","useAutomationExtension"));
        options.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(options);

        // 此外，也可以通过CDP命令覆盖WebDriver属性
        Map<String, Object> sourceMap = new HashMap<>();
        sourceMap.put("source", "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
        driver.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", sourceMap);
        Map<String, Object> userAgentMap = new HashMap<>();
        userAgentMap.put("userAgent", userAgent);
        driver.executeCdpCommand("Network.setUserAgentOverride", userAgentMap);
        // 设置隐式等待
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    public static void openUrl(String url) {
        driver.get(url);
    }
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}

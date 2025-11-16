package com.particle.global.crawler.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 简单测试
 * 启动浏览器，访问一个网页
 * </p>
 *
 * @author yangwei
 * @since 2025/11/11 10:56
 */
public class SimpleTest {
    private static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        setupDriver();
        openUrl("https://www.baidu.com");
        Thread.sleep(5000);
        closeDriver();
    }

    /**
     * 初始化Selenium WebDriver
     */
    public static void setupDriver() {
        // 设置ChromeDriver路径
        System.setProperty("webdriver.chrome.driver", "/Users/yw/fh/chromedriver-mac-x64/chromedriver");

        // 配置浏览器选项
        ChromeOptions options = new ChromeOptions();

        // 无头模式（不显示浏览器窗口）
        // options.addArguments("--headless");

        // 其他优化选项
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        // 设置User-Agent
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

        driver = new ChromeDriver(options);

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

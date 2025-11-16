package com.particle.global.crawler.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * <p>
 * 测试使用 WebDriverManager 自动下载对应版本的驱动
 * 默认下载的驱动目录为：~/.cache/selenium
 * </p>
 *
 * @author yangwei
 * @since 2025/11/11 11:43
 */
public class WebDriverManagerTest {
    public static void main(String[] args) {
        // chromedriverTest();
        firefoxdriverTest();
    }

    /**
     * 使用 chromedriver
     */
    public static void chromedriverTest() {
        // 自动设置并启动ChromeDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // 接下来就可以使用driver进行你的操作了
        driver.get("https://www.baidu.com");
        System.out.println(driver.getTitle());

        // ... 你的其他自动化逻辑

        // 操作完成后，关闭浏览器
        driver.quit();
    }

    /**
     * 使用FirefoxDriver
     */
    public static void firefoxdriverTest() {
        // 自动设置并启动GeckoDriver
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();

        // 接下来就可以使用driver进行你的操作了
        driver.get("https://www.baidu.com");
        System.out.println(driver.getTitle());

        // ... 你的其他自动化逻辑

        // 操作完成后，关闭浏览器
        driver.quit();
    }
}

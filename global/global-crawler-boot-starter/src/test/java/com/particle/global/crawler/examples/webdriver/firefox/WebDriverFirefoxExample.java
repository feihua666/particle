package com.particle.global.crawler.examples.webdriver.firefox;

import com.particle.global.crawler.constants.CrawlTestConstants;
import com.particle.global.crawler.webdriver.CrawlerWebDriverTool;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

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
public class WebDriverFirefoxExample {
    private static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

        String firefoxDriverPath = "/Users/yw/fh/geckodriver-v0.36.0-macos/geckodriver";

        // 浏览器 输入 about:support 查看
        String realUserDataDir = "/Users/yw/Library/Application Support/Firefox/Profiles/";


        String userDataDir = "/Users/yw/temp/selenium-firefox/";
        String profileDir = "6q05cfzp.default-release-1";
        // copy 有问题，如果复制的文件是一个软链接，那么复制的时候会出错，建议手动复制
        // cp -R "/Users/yw/Library/Application Support/Firefox/Profiles/6q05cfzp.default-release-1" "/Users/yw/temp/selenium-firefox"
        // FileUtil.copy(realProfileDir, profileDir, true);
        String userAgent = CrawlTestConstants.firefoxAgent;
        // firefox 的 profile 太大 导致内存溢出，先注释掉，不添加
        // FirefoxProfile profile = profile(userDataDir + profileDir,userAgent);
        FirefoxProfile profile = profile(null,userAgent);
        FirefoxOptions options = options(null);

        // 设置参数启动，保持 session 一致
        options.addArguments("-profile");
        options.addArguments(userDataDir);

        driver = CrawlerWebDriverTool.Firefox.setUpDriver(firefoxDriverPath,options);
        CrawlerWebDriverTool.waitGlobal(driver,1 * 2000);


        CrawlerWebDriverTool.openUrl(driver,"https://baidu.com");
        CrawlerWebDriverTool.Firefox.injectAntiDetectJSForFirefox(driver);
        Thread.sleep(5000);

        log.info("当前页面标题：{}",CrawlerWebDriverTool.getPageTitle(driver));
        CrawlerWebDriverTool.closeDriver(driver);
    }

    /**
     * 配置 Firefox 浏览器选项
     * 适用于 Selenium / 爬虫 / 自动化测试场景
     */
    public static FirefoxOptions options(FirefoxProfile profile) {
        FirefoxOptions options = CrawlerWebDriverTool.Firefox.newOptions(profile);

        // 用途: 启用无头模式，在后台运行浏览器
        // options.addArguments("-headless");

        return options;
    }

    /**
     * 配置 Firefox 浏览器 Profile
     * 适用于 Selenium / 爬虫 / 自动化测试场景
     */
    public static FirefoxProfile profile(String profileDir,String userAgent) {
        FirefoxProfile profile = CrawlerWebDriverTool.Firefox.newProfile(profileDir,userAgent);

        // 本来是控制在网页端调用 navigator.webdriver 时 让他返回false，但目前来看，这个方法已经无效了，即设置后也会返回 true
        // profile.setPreference("dom.webdriver.enabled", false);

        return profile;
    }



}

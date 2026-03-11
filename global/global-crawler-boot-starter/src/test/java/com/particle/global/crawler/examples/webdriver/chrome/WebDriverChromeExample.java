package com.particle.global.crawler.examples.webdriver.chrome;

import com.particle.global.crawler.tool.CrawlerWebDriverTool;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * <p>
 * 简单测试 chrome
 * 启动浏览器，访问一个网页
 * </p>
 *
 * @author yangwei
 * @since 2025/11/11 10:56
 */
@Slf4j
public class WebDriverChromeExample {
    private static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        // ChromeDriver 路径
        String chromeDriverPath = "/Users/yw/fh/chromedriver-mac-x64/chromedriver";


        // 真实的路径 浏览器输入 chrome://version/ 查看
        String realUserDataDir = "/Users/yw/Library/Application Support/Google/Chrome/";
        // 临时启动时路径
        String userDataDir = "/Users/yw/temp/selenium-chrome";
        String profileDir = "Default";
        // 如果需要真实的用户配置，可以复制到一个其他路径下面，否则会影响自己正常访问
        // 该 工具有问题，如果复制的文件是一个软链接，那么复制的时候会出错，建议手动复制
        // cp -R "/Users/yw/Library/Application Support/Google/Chrome" "/Users/yw/temp/selenium-chrome"
        // FileUtil.copy(realUserDataDir,userDataDir,true);

        // user agent 如果不设置，会使用默认的，默认的一般是真实的
        // String userAgent = Test.chromeAgent;
        String userAgent = null;

        ChromeOptions options = options(userDataDir,profileDir,userAgent);
        // 可以 隐藏正受软件自动控制 的信息栏
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});


        // 实例化 WebDriver
        driver = CrawlerWebDriverTool.Chrome.setUpDriver(chromeDriverPath,options);
        // 设置全局等待
        CrawlerWebDriverTool.waitGlobal(driver,1 * 2000);

        Thread.sleep(2000);
        // 打开新标签页
        // CrawlerWebDriverTool.newTab(driver);
        // 切换到新打开的标签页
        // CrawlerWebDriverTool.switchToLastTab(driver);
        // 打开新窗口
        // driver.switchTo().newWindow(WindowType.WINDOW);

        // 打开地址
        CrawlerWebDriverTool.openUrl(driver,"https://baidu.com");

        // 注入反检测脚本
        CrawlerWebDriverTool.Chrome.injectAntiDetectJS(driver);
        log.info("当前页面标题：{}",CrawlerWebDriverTool.getPageTitle(driver));
        // 关闭浏览器
        CrawlerWebDriverTool.closeDriver(driver);
    }
    /**
     * 配置 Chrome 浏览器选项
     * 此方法提供了一系列常用的 Chrome 浏览器配置选项，
     * 可根据不同使用场景（本地 / Docker / 爬虫 / CI）进行启用或禁用
     *
     * @return ChromeOptions 配置好的 Chrome 选项对象
     */
    public static ChromeOptions options(String userDataDir,String profileDir,String userAgent) {
        ChromeOptions options = CrawlerWebDriverTool.Chrome.newOptions( userDataDir,profileDir,userAgent);

        return options;
    }


}

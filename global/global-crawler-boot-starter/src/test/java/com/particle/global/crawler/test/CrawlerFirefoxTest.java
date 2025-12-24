package com.particle.global.crawler.test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 简单测试
 * </p>
 *
 * @author yangwei
 * @since 2025/11/11 10:56
 */
public class CrawlerFirefoxTest {
    private static FirefoxDriver driver;

    public static void main(String[] args) {
        setupDriver();
        openUrl("https://jd.com");
        // openUrl("https://wenshu.court.gov.cn/");
        // closeDriver();
    }

    /**
     * 初始化Selenium WebDriver - Firefox版本
     */
    public static void setupDriver() {
        // 设置Firefox驱动(geckodriver)路径 [citation:7]
        System.setProperty("webdriver.gecko.driver", "/Users/yw/fh/geckodriver-v0.36.0-macos/geckodriver");

        // 配置Firefox浏览器选项
        FirefoxOptions options = new FirefoxOptions();
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:144.0) Gecko/20100101 Firefox/144.0";

        // 创建并配置FirefoxProfile
        FirefoxProfile profile = new FirefoxProfile();

        // 设置User-Agent [citation:3]
        profile.setPreference("general.useragent.override", userAgent);

        // 其他Firefox特定配置
        profile.setPreference("dom.webdriver.enabled", false);
        profile.setPreference("useAutomationExtension", false);
        // 其他重要的隐私和安全设置
        profile.setPreference("privacy.trackingprotection.enabled", false);
        profile.setPreference("privacy.resistFingerprinting", false);
        profile.setPreference("browser.privatebrowsing.autostart", false);
        // 禁用各种追踪和检测
        profile.setPreference("privacy.trackingprotection.enabled", false);
        profile.setPreference("privacy.resistFingerprinting", false);
        profile.setPreference("browser.send_pings", false);
        profile.setPreference("browser.uitour.enabled", false);
        profile.setPreference("dom.disable_beforeunload", true);
        profile.setPreference("dom.disable_open_during_load", false);
        // 将profile添加到options
        options.setProfile(profile);

        // Firefox专用的参数配置
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-web-security");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");

        // 设置用户数据目录（Firefox的路径可能与Chrome不同）
        // options.addArguments("--profile /Users/yw/temp/firefox_profile");

        driver = new FirefoxDriver(options);
        // 通过 JavaScript 进一步隐藏 webdriver 属性
        hideWebDriverProperty();
        // 设置隐式等待
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * 使用 JavaScript 隐藏 webdriver 属性
     */
    private static void hideWebDriverProperty() {
        try {
            // 方法1：直接删除属性
            driver.executeScript(
                    "delete navigator.__proto__.webdriver;" +
                            "delete navigator.webdriver;"
            );

            // 方法2：重定义属性为 undefined
            driver.executeScript(
                    "Object.defineProperty(navigator, 'webdriver', {" +
                            "   get: () => undefined," +
                            "   configurable: true" +
                            "});"
            );

            // 方法3：覆盖原型链上的属性
            driver.executeScript(
                    "Object.defineProperty(navigator, 'webdriver', {" +
                            "   value: false," +
                            "   writable: false," +
                            "   enumerable: false," +
                            "   configurable: false" +
                            "});"
            );

        } catch (Exception e) {
            System.err.println("隐藏 webdriver 属性失败: " + e.getMessage());
        }
        // applyStealthTechniques(driver);
    }

    /**
     * 测试 navigator.webdriver 属性
     */
    public static void testWebDriverProperty() {
        try {
            Object result = driver.executeScript("return navigator.webdriver;");
            System.out.println("navigator.webdriver = " + result);

            // 检查其他自动化特征
            Object chrome = driver.executeScript("return window.chrome;");
            Object runtime = driver.executeScript("return chrome && chrome.runtime;");

            System.out.println("window.chrome: " + chrome);
            System.out.println("chrome.runtime: " + runtime);

        } catch (Exception e) {
            System.err.println("测试 webdriver 属性时出错: " + e.getMessage());
        }
    }
    public static void applyStealthTechniques(FirefoxDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 综合隐藏脚本
        String stealthScript =
                "// 隐藏 webdriver 属性\n" +
                        "Object.defineProperty(navigator, 'webdriver', {\n" +
                        "   get: () => undefined,\n" +
                        "   configurable: true\n" +
                        "});\n" +
                        "\n" +
                        "// 删除 webdriver 属性\n" +
                        "delete navigator.wrappedJSObject.webdriver;\n" +
                        "\n" +
                        "// 覆盖 permissions API\n" +
                        "const originalQuery = window.navigator.permissions.query;\n" +
                        "window.navigator.permissions.query = (parameters) => (\n" +
                        "   parameters.name === 'notifications' ?\n" +
                        "       Promise.resolve({ state: Notification.permission }) :\n" +
                        "       originalQuery(parameters)\n" +
                        ");\n" +
                        "\n" +
                        "// 覆盖 plugins 属性\n" +
                        "Object.defineProperty(navigator, 'plugins', {\n" +
                        "   get: () => [1, 2, 3, 4, 5],\n" +
                        "   configurable: true\n" +
                        "});\n" +
                        "\n" +
                        "// 覆盖 languages 属性\n" +
                        "Object.defineProperty(navigator, 'languages', {\n" +
                        "   get: () => ['zh-CN', 'zh', 'en'],\n" +
                        "   configurable: true\n" +
                        "});\n" +
                        "\n" +
                        "// 覆盖 chrome 运行时\n" +
                        "window.chrome = {\n" +
                        "   runtime: {}\n" +
                        "};";

        try {
            js.executeScript(stealthScript);
            System.out.println("已应用高级隐藏技术");
        } catch (Exception e) {
            System.err.println("应用隐藏技术失败: " + e.getMessage());
        }
    }
    public static void openUrl(String url) {
        driver.get(url);
        // 每次页面加载后重新隐藏 webdriver 属性
        hideWebDriverProperty();

    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}

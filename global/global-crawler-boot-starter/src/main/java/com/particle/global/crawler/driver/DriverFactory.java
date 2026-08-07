package com.particle.global.crawler.driver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Playwright;
import com.particle.global.crawler.common.enums.BrowserType;
import com.particle.global.crawler.common.enums.DriverType;
import com.particle.global.crawler.driver.browser.PlaywrightDriver;
import com.particle.global.crawler.driver.browser.SeleniumDriver;
import com.particle.global.crawler.driver.http.HttpDriver;
import com.particle.global.crawler.runtime.CrawlRuntimeOptions;
import com.particle.global.crawler.runtime.config.browser.PlaywrightOptions;
import com.particle.global.crawler.runtime.config.browser.SeleniumOptions;
import com.particle.global.tool.str.FilePathTool;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Driver 工厂（无状态）
 * <p>
 * 负责根据不同的驱动类型创建对应的 CrawlDriver 实例。
 * 支持 HTTP、Playwright 和 Selenium 三种驱动类型。
 * </p>
 *
 * @author particle
 * @since 1.0
 */
@Slf4j
public class DriverFactory {

    /**
     * 根据驱动类型创建对应的 CrawlDriver 实例
     *
     * @param options 运行时配置选项
     * @return 创建的 CrawlDriver 实例
     * @throws IllegalArgumentException 当传入不支持的驱动类型时抛出
     */
    public CrawlDriver create(CrawlRuntimeOptions options) {
        DriverType driverType = options.getDeriver().getDriverType();
        return switch (driverType) {
            case HTTP -> createHttpDriver(options);
            case PLAYWRIGHT -> createPlaywrightDriver(options);
            case SELENIUM -> createSeleniumDriver(options);
        };
    }

    // ----------------------------------------------------------------
    // HTTP
    // ----------------------------------------------------------------

    /**
     * 创建 HTTP Driver
     *
     * @param options 运行时配置选项
     * @return HTTP Driver 实例
     */
    private HttpDriver createHttpDriver(CrawlRuntimeOptions options) {
        log.debug("创建 HTTP Driver");
        return new HttpDriver(options.getHttp());
    }

    // ----------------------------------------------------------------
    // Playwright
    // ----------------------------------------------------------------

    /**
     * 创建 Playwright Driver
     * <p>
     * 根据配置决定使用持久化模式还是临时模式：
     * - 如果配置了 userDataDir，则使用持久化上下文模式
     * - 否则使用临时浏览器模式
     * </p>
     *
     * @param options 运行时配置选项
     * @return Playwright Driver 实例
     * @throws RuntimeException 创建失败时抛出
     */
    private PlaywrightDriver createPlaywrightDriver(CrawlRuntimeOptions options) {
        log.debug("创建 Playwright Driver");

        PlaywrightOptions playwrightOpts = options.getBrowser().getPlaywright();
        BrowserType browserType = playwrightOpts.getBrowserType();

        try {
            // 配置了 browsersPath 则指定 Playwright 浏览器缓存目录
            String browsersPath = playwrightOpts.getBrowsersPath();
            if (browsersPath != null && !browsersPath.isEmpty()) {
                log.info("使用自定义浏览器缓存目录: {}", browsersPath);
                System.setProperty("playwright.browsers.path", browsersPath);
            }

            Playwright playwright = Playwright.create();
            String userDataDir = playwrightOpts.getUserDataDir();
            String userDataDirProfile = playwrightOpts.getUserDataDirProfile();

            if (userDataDir != null && !userDataDir.isEmpty()) {
                return createPlaywrightPersistentDriver(playwright, playwrightOpts, browserType, userDataDir,userDataDirProfile);
            } else {
                return createPlaywrightTempDriver(playwright, playwrightOpts, browserType);
            }

        } catch (Exception e) {
            throw new RuntimeException("创建 Playwright Driver 失败", e);
        }
    }

    /**
     * 创建持久化上下文的 Playwright Driver
     * <p>
     * 使用 launchPersistentContext 创建持久化浏览器上下文，保持会话状态。
     * browser() 在持久化模式下返回 null，直接使用 BrowserContext 管理。
     * </p>
     *
     * @param playwright Playwright 实例
     * @param opts Playwright 配置选项
     * @param browserType 浏览器类型
     * @param userDataDir 用户数据目录路径
     * @return Playwright Driver 实例
     */
    private PlaywrightDriver createPlaywrightPersistentDriver(Playwright playwright,
                                                              PlaywrightOptions opts,
                                                              BrowserType browserType,
                                                              String userDataDir,String userDataDirProfile) {
        log.info("Playwright 持久化模式, userDataDir={}", userDataDir);

        com.microsoft.playwright.BrowserType.LaunchPersistentContextOptions persistentOpts =
                new com.microsoft.playwright.BrowserType.LaunchPersistentContextOptions()
                        .setHeadless(opts.getIsHeadless())
                        .setTimeout(opts.getTimeout());

        if (userDataDirProfile != null && !userDataDirProfile.isEmpty()) {
            persistentOpts.setArgs(List.of("--profile-directory=" + userDataDirProfile));
        }
        if(opts.getViewportWidth() != null && opts.getViewportHeight() != null){
            persistentOpts.setViewportSize(opts.getViewportWidth(), opts.getViewportHeight());
        }


        // executablePath 从 CommonOptions 继承，Playwright/Selenium 统一入口
        String executablePath = opts.getExecutablePath();
        if (executablePath != null && !executablePath.isEmpty()) {
            log.info("使用本地浏览器: {}", executablePath);
            persistentOpts.setExecutablePath(Path.of(executablePath));
        }

        Path userDataDirPath = Paths.get(userDataDir);
        BrowserContext context = switch (browserType) {
            case CHROME, EDGE -> playwright.chromium().launchPersistentContext(userDataDirPath, persistentOpts);
            case FIREFOX      -> playwright.firefox().launchPersistentContext(userDataDirPath, persistentOpts);
            case WEBKIT       -> playwright.webkit().launchPersistentContext(userDataDirPath, persistentOpts);
        };

        // 反检测在 context 上注入，newPage 之前，对所有后续页面生效
        if (Boolean.TRUE.equals(opts.getIsStealth())) {
            injectStealth(context, browserType);
        }

        com.microsoft.playwright.Page page = context.pages().isEmpty()
                ? context.newPage()
                : context.pages().get(0);

        // 持久化模式：传 context，browser 传 null
        return new PlaywrightDriver(page, context,null, playwright);
    }

    /**
     * 创建临时浏览器的 Playwright Driver
     * <p>
     * 使用 launch 创建临时浏览器实例，关闭后不保留任何状态。
     * </p>
     *
     * @param playwright Playwright 实例
     * @param opts Playwright 配置选项
     * @param browserType 浏览器类型
     * @return Playwright Driver 实例
     */
    private PlaywrightDriver createPlaywrightTempDriver(Playwright playwright,
                                                        PlaywrightOptions opts,
                                                        BrowserType browserType) {
        log.debug("Playwright 临时模式");

        com.microsoft.playwright.BrowserType.LaunchOptions launchOpts =
                new com.microsoft.playwright.BrowserType.LaunchOptions()
                        .setHeadless(opts.getIsHeadless())
                        .setTimeout(opts.getTimeout());

        String executablePath = opts.getExecutablePath();
        if (executablePath != null && !executablePath.isEmpty()) {
            log.info("使用本地浏览器: {}", executablePath);
            launchOpts.setExecutablePath(Path.of(executablePath));
        }

        Browser browser = switch (browserType) {
            case CHROME, EDGE -> playwright.chromium().launch(launchOpts);
            case FIREFOX      -> playwright.firefox().launch(launchOpts);
            case WEBKIT       -> playwright.webkit().launch(launchOpts);
        };

        Browser.NewContextOptions contextOpts = new Browser.NewContextOptions();

        if (opts.getViewportWidth() != null && opts.getViewportHeight() != null) {
            contextOpts.setViewportSize(opts.getViewportWidth(), opts.getViewportHeight());
        }

        // 先建 context，在 context 上注入反检测，再开 page
        BrowserContext context = browser.newContext(contextOpts);

        if (Boolean.TRUE.equals(opts.getIsStealth())) {
            injectStealth(context, browserType);
        }

        com.microsoft.playwright.Page page = context.newPage();

        // 临时模式：传 browser（PlaywrightDriver.close() 里 browser != null 走 browser.close()）
        // context 由 browser.close() 连带关闭，不需要单独传
        return new PlaywrightDriver(page,context, browser, playwright);
    }

    /**
     * 在 BrowserContext 上注入反检测脚本
     * <p>
     * 必须在 newPage() 之前调用，对所有后续页面生效。
     * 不同浏览器类型注入不同的反检测脚本。
     * </p>
     *
     * @param context 浏览器上下文
     * @param browserType 浏览器类型
     */
    private void injectStealth(BrowserContext context, BrowserType browserType) {
        String script = switch (browserType) {
            case FIREFOX -> """
                    Object.defineProperty(navigator, 'webdriver', { get: () => undefined });
                    Object.defineProperty(navigator, 'plugins', { get: () => [1, 2, 3, 4, 5] });
                    """;
            default -> """
                    Object.defineProperty(navigator, 'webdriver', { get: () => undefined });
                    """;
        };
        context.addInitScript(script);
    }

    // ----------------------------------------------------------------
    // Selenium
    // ----------------------------------------------------------------

    /**
     * 创建 Selenium Driver
     *
     * @param options 运行时配置选项
     * @return Selenium Driver 实例
     * @throws RuntimeException 创建失败时抛出
     */
    private CrawlDriver createSeleniumDriver(CrawlRuntimeOptions options) {
        log.debug("创建 Selenium Driver");

        SeleniumOptions seleniumOpts = options.getBrowser().getSelenium();
        BrowserType browserType = seleniumOpts.getBrowserType();

        try {
            WebDriver webDriver = switch (browserType) {
                case CHROME -> createSeleniumChromeDriver(seleniumOpts);
                case FIREFOX -> createSeleniumFirefoxDriver(seleniumOpts);
                case EDGE -> createSeleniumEdgeDriver(seleniumOpts);
                case WEBKIT -> createSeleniumSafariDriver(seleniumOpts);
            };
            SeleniumOptions seleniumOptions = options.getBrowser().getSelenium();
            if (seleniumOptions != null && seleniumOptions.getViewportWidth() != null && seleniumOptions.getViewportHeight() != null) {
                webDriver.manage().window().setSize(
                        new Dimension(seleniumOptions.getViewportWidth(), seleniumOptions.getViewportHeight())
                );
            }
            return new SeleniumDriver(webDriver);
        } catch (Exception e) {
            throw new RuntimeException("创建 Selenium Driver 失败", e);
        }
    }

    /**
     * 创建 Chrome Driver
     *
     * @param opts Selenium 配置选项
     * @return Chrome WebDriver 实例
     */
    private WebDriver createSeleniumChromeDriver(SeleniumOptions opts) {
        log.info("初始化 Chrome Driver");
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        if (Boolean.TRUE.equals(opts.getIsHeadless())) {
            chromeOptions.addArguments("--headless=new");
        }
        if (Boolean.TRUE.equals(opts.getIsDisableGpu())) {
            chromeOptions.addArguments("--disable-gpu");
        }
        if (Boolean.TRUE.equals(opts.getIsDisableExtensions())) {
            chromeOptions.addArguments("--disable-extensions");
        }
        if (Boolean.TRUE.equals(opts.getIsDisableAutomationInfoBar())) {
            chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        }
        if (Boolean.FALSE.equals(opts.getIsUseAutomationExtension())) {
            chromeOptions.setExperimentalOption("useAutomationExtension", false);
        }

        // executablePath 从 CommonOptions 继承
        String executablePath = opts.getExecutablePath();
        if (executablePath != null && !executablePath.isEmpty()) {
            chromeOptions.setBinary(executablePath);
        }

        // userDataDir 从 CommonOptions 继承
        String userDataDir = opts.getUserDataDir();
        if (userDataDir != null && !userDataDir.isEmpty()) {
            log.info("Chrome 使用持久化目录: {}", userDataDir);

            chromeOptions.addArguments("--user-data-dir=" + userDataDir);
            String userDataDirProfile = opts.getUserDataDirProfile();
            if (userDataDirProfile != null && !userDataDirProfile.isEmpty()) {
                log.info("Chrome 使用持久化目录下的 profile: {}", userDataDirProfile);
                chromeOptions.addArguments("--profile-directory=" + userDataDirProfile);
            }

        }

        return new ChromeDriver(chromeOptions);
    }

    /**
     * 创建 Firefox Driver
     *
     * @param opts Selenium 配置选项
     * @return Firefox WebDriver 实例
     */
    private WebDriver createSeleniumFirefoxDriver(SeleniumOptions opts) {
        log.info("初始化 Firefox Driver");
        WebDriverManager.firefoxdriver().browserVersion("149").setup();

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (Boolean.TRUE.equals(opts.getIsHeadless())) {
            firefoxOptions.addArguments("-headless");
        }

        String executablePath = opts.getExecutablePath();
        if (executablePath != null && !executablePath.isEmpty()) {
            firefoxOptions.setBinary(executablePath);
        }


        String userDataDir = opts.getUserDataDir();
        if (userDataDir != null && !userDataDir.isEmpty()) {
            log.info("Firefox 使用持久化目录: {}", userDataDir);
            // Firefox 用 profile 目录方式（该方式会复制到一个临时目录）
            // FirefoxProfile profile = new FirefoxProfile(new File(userDataDir));
            // firefoxOptions.setProfile(profile);

            String userDataDirProfile = opts.getUserDataDirProfile();
            String finallyUserDataDir = FilePathTool.concat(userDataDir, userDataDirProfile);
            firefoxOptions.addArguments("-profile", finallyUserDataDir);

        }

        return new FirefoxDriver(firefoxOptions);
    }

    /**
     * 创建 Edge Driver
     *
     * @param opts Selenium 配置选项
     * @return Edge WebDriver 实例
     */
    private WebDriver createSeleniumEdgeDriver(SeleniumOptions opts) {
        log.info("初始化 Edge Driver");
        WebDriverManager.edgedriver().setup();

        EdgeOptions edgeOptions = new EdgeOptions();
        if (Boolean.TRUE.equals(opts.getIsHeadless())) {
            edgeOptions.addArguments("--headless=new");
        }
        if (Boolean.TRUE.equals(opts.getIsDisableGpu())) {
            edgeOptions.addArguments("--disable-gpu");
        }

        String executablePath = opts.getExecutablePath();
        if (executablePath != null && !executablePath.isEmpty()) {
            edgeOptions.setBinary(executablePath);
        }

        String userDataDir = opts.getUserDataDir();
        if (userDataDir != null && !userDataDir.isEmpty()) {
            log.info("Edge 使用持久化目录: {}", userDataDir);
            edgeOptions.addArguments("--user-data-dir=" + userDataDir);
            String userDataDirProfile = opts.getUserDataDirProfile();
            if (userDataDirProfile != null && !userDataDirProfile.isEmpty()) {
                log.info("Edge 使用持久化目录下的 profile: {}", userDataDirProfile);
                edgeOptions.addArguments("--profile-directory=" + userDataDirProfile);
            }
        }

        return new EdgeDriver(edgeOptions);
    }

    /**
     * 创建 Safari Driver
     *
     * @param opts Selenium 配置选项
     * @return Safari WebDriver 实例
     */
    private WebDriver createSeleniumSafariDriver(SeleniumOptions opts) {
        log.info("初始化 Safari Driver");
        SafariOptions safariOptions = new SafariOptions();
        if (Boolean.TRUE.equals(opts.getIsHeadless())) {
            log.warn("Safari 不支持无头模式，将忽略该配置");
        }
        return new SafariDriver(safariOptions);
    }
}

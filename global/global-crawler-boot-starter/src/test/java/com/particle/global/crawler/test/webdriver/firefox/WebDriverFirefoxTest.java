package com.particle.global.crawler.test.webdriver.firefox;

import com.particle.global.crawler.test.Test;
import com.particle.global.crawler.tool.CrawlerWebDriverTool;
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
public class WebDriverFirefoxTest {
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
        String userAgent = Test.firefoxAgent;
        // firefox 的 profile 太大 导致内存溢出，先注释掉，不添加
        // FirefoxProfile profile = profile(userDataDir + profileDir,userAgent);
        FirefoxProfile profile = profile(null,userAgent);
        FirefoxOptions options = options(profile);
        driver = CrawlerWebDriverTool.Firefox.setUpDriver(firefoxDriverPath,options);
        CrawlerWebDriverTool.waitGlobal(driver,1 * 2000);

        CrawlerWebDriverTool.openUrl(driver,"https://baidu.com");
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

        // =========================
        // 基础浏览器配置
        // =========================

        // 用途: 启用无头模式，在后台运行浏览器
        // 默认值: false（显示浏览器窗口）
        // 影响: 不显示UI，适合服务器、CI、Docker环境；本地调试不便
        // options.addArguments("-headless");

        // 用途: 设置浏览器窗口宽度
        // 默认值: 无头模式下约 800px
        // 影响: 避免网站返回移动端或响应式异常布局
        options.addArguments("--width=1920");

        // 用途: 设置浏览器窗口高度
        // 默认值: 无头模式下约 600px
        // 影响: 保证页面布局与桌面端一致，减少元素定位问题
        options.addArguments("--height=1080");

        return options;
    }

    /**
     * 配置 Firefox 浏览器 Profile
     * 适用于 Selenium / 爬虫 / 自动化测试场景
     */
    public static FirefoxProfile profile(String profileDir,String userAgent) {
        FirefoxProfile profile = CrawlerWebDriverTool.Firefox.newProfile(profileDir,userAgent);

        // =========================
        // 自动化与反检测相关配置
        // =========================

        // 用途: 禁用 WebDriver 自动化标识
        // 默认值: true（navigator.webdriver = true）
        // 影响: 降低被简单反爬脚本识别为自动化浏览器的概率
        profile.setPreference("dom.webdriver.enabled", false);

        // 用途: 禁用 Firefox 自动化扩展
        // 默认值: 启用
        // 影响: 减少自动化特征暴露，但无法完全规避高级反爬
        profile.setPreference("useAutomationExtension", false);


        // =========================
        // 页面交互与弹窗控制
        // =========================

        // 用途: 禁用网页通知弹窗
        // 默认值: 首次访问站点时询问用户
        // 影响: 避免通知弹窗遮挡页面，影响元素点击和定位
        profile.setPreference("dom.webnotifications.enabled", false);

        // 用途: 禁用推送通知功能
        // 默认值: 启用
        // 影响: 防止页面加载过程中触发推送权限请求
        profile.setPreference("dom.push.enabled", false);

        // 用途: 禁用地理位置请求
        // 默认值: 访问相关API时弹出询问
        // 影响: 避免地理位置授权弹窗阻塞页面加载
        profile.setPreference("geo.enabled", false);


        // =========================
        // 性能与资源加载配置
        // =========================

        // 用途: 禁用图片加载以提高页面加载速度
        // 默认值: 加载所有图片
        // 影响: 显著减少流量和加载时间，但无法进行截图或图像分析
        profile.setPreference("permissions.default.image", 2);

        // 用途: 禁用内置 PDF 预览功能
        // 默认值: 浏览器内直接打开 PDF
        // 影响: PDF 文件将直接下载，避免占用浏览器标签页
        profile.setPreference("pdfjs.disabled", true);


        // =========================
        // Firefox 特有优化（可选）
        // =========================

        // 用途: 启用磁盘和内存缓存以提升性能
        // 默认值: 启用
        // 影响: 页面加载更快，降低重复网络请求
        profile.setPreference("browser.cache.disk.enable", true);
        profile.setPreference("browser.cache.memory.enable", true);

        // 用途: 禁用浏览器自动更新
        // 默认值: 启用
        // 影响: 避免更新干扰自动化任务
        profile.setPreference("app.update.enabled", false);

        // 用途: 自动下载文件到指定目录，禁用下载确认弹窗
        // 默认值: 弹窗提示用户选择
        // 影响: 自动保存文件，提高自动化效率
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", "/tmp");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/octet-stream");

        return profile;
    }



}

package com.particle.global.crawler.test.webdriver.chrome;

import com.particle.global.crawler.test.Test;
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
public class WebDriverChromeTest {
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

        // user agent 如果不设置，会使用默认默认的，默认的一般是真实的
        // String userAgent = Test.chromeAgent;
        String userAgent = null;
        // 反检测配置
        ChromeOptions options = CrawlerWebDriverTool.Chrome.antiDetectOptions(userDataDir,profileDir, userAgent);
        // ChromeOptions options = options();
        // 实例化 WebDriver
        driver = CrawlerWebDriverTool.Chrome.setUpDriver(chromeDriverPath,options);
        // 设置全局等待
        CrawlerWebDriverTool.waitGlobal(driver,1 * 2000);

        // 注入反检测脚本
        // CrawlerWebDriverTool.Chrome.injectAntiDetectJS(driver);
        Thread.sleep(2000);
        // 打开新标签页
        // CrawlerWebDriverTool.newTab(driver);
        // 切换到新打开的标签页
        // CrawlerWebDriverTool.switchToLastTab(driver);
        // 打开新窗口
        // driver.switchTo().newWindow(WindowType.WINDOW);

        // 打开地址
        CrawlerWebDriverTool.openUrl(driver,"https://baidu.com");
        Thread.sleep(5000);

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
    public static ChromeOptions options() {
        ChromeOptions options = CrawlerWebDriverTool.Chrome.newOptions();

        // =========================
        // 基础浏览器配置
        // =========================

        // 基础浏览器配置
        // 用途: 禁用沙盒模式，提高在 Docker 等容器环境中的运行稳定性
        // 默认值: 启用沙盒模式
        // 影响: 避免在受限权限环境中 Chrome 无法启动（Docker 中几乎必需）
        options.addArguments("--no-sandbox");

        // 基础浏览器配置
        // 用途: 禁用 /dev/shm 共享内存使用
        // 默认值: 使用 /dev/shm
        // 影响: 避免 Docker 容器中因共享内存不足导致 Chrome 崩溃
        options.addArguments("--disable-dev-shm-usage");

        // 基础浏览器配置
        // 用途: 禁用 GPU 硬件加速
        // 默认值: 启用 GPU 加速（系统支持时）
        // 影响: 提高无头模式和 Linux 环境下的稳定性
        options.addArguments("--disable-gpu");

        // 基础浏览器配置
        // 用途: 设置固定窗口大小
        // 默认值: 系统默认窗口大小
        // 影响: 避免响应式布局变化，便于截图与元素定位
        options.addArguments("--window-size=1920,1080");

        // 基础浏览器配置
        // 用途: 启用无头模式运行（不显示浏览器窗口）
        // 默认值: 显示浏览器窗口
        // 影响: 节省资源，适用于服务器 / CI 环境；调试不便
        // options.addArguments("--headless=new");


        // =========================
        // 自动化与反检测相关配置
        // =========================

        // 自动化控制配置
        // 用途: 隐藏 AutomationControlled 自动化特征
        // 默认值: 启用自动化特征检测
        // 影响: navigator.webdriver 等特征更隐蔽，降低被基础反爬检测概率
        options.addArguments("--disable-blink-features=AutomationControlled");

        // 自动化控制配置
        // 用途: 禁用自动化扩展加载
        // 默认值: 启用自动化扩展
        // 影响: 减少 Selenium 自动化特征暴露
        options.setExperimentalOption("useAutomationExtension", false);

        // 自动化控制配置
        // 用途: 从 Chrome 启动参数中排除 enable-automation
        // 默认值: 包含 enable-automation
        // 影响: 隐藏 “Chrome 正受自动化控制” 提示和相关特征
        options.setExperimentalOption(
                "excludeSwitches",
                new String[]{"enable-automation"}
        );


        // =========================
        // 页面行为与安全限制
        // =========================

        // 页面行为配置（高风险）
        // 用途: 禁用同源策略和网页安全限制
        // 默认值: 启用网页安全限制
        // 影响: 允许跨域访问；存在严重安全风险，仅限调试或特殊场景
        // 爬虫环境通常不建议开启
        options.addArguments("--disable-web-security");

        // 页面行为配置
        // 用途: 禁用 VizDisplayCompositor 渲染特性
        // 默认值: 启用
        // 影响: 降低某些 GPU / 渲染相关崩溃概率
        options.addArguments("--disable-features=VizDisplayCompositor");


        // =========================
        // 性能与后台行为优化
        // =========================

        // 性能优化配置
        // 用途: 禁用后台标签页定时器节流
        // 默认值: 启用定时器节流
        // 影响: 后台页面定时器保持正常运行
        options.addArguments("--disable-background-timer-throttling");

        // 性能优化配置
        // 用途: 禁用被遮挡窗口的后台处理
        // 默认值: 启用
        // 影响: 防止窗口不可见时功能被限制
        options.addArguments("--disable-backgrounding-occluded-windows");

        // 性能优化配置
        // 用途: 禁用渲染器后台降级处理
        // 默认值: 启用
        // 影响: 页面在后台时仍保持完整渲染逻辑
        options.addArguments("--disable-renderer-backgrounding");

        // 性能优化配置
        // 用途: 禁用 IPC 洪水保护机制
        // 默认值: 启用 IPC 洪水保护
        // 影响: 避免高频通信场景下被误判并断开连接
        options.addArguments("--disable-ipc-flooding-protection");

        // 性能优化配置
        // 用途: 禁用后台网络请求
        // 默认值: 启用后台网络请求
        // 影响: 减少非必要流量，提高爬虫效率
        options.addArguments("--disable-background-networking");


        // =========================
        // 缓存与内存管理
        // =========================

        // 缓存配置
        // 用途: 设置磁盘缓存最大为 4GB
        // 默认值: Chrome 自动管理缓存大小
        // 影响: 控制磁盘占用，长时间运行时更可控
        options.addArguments("--disk-cache-size=4294967296");

        // 内存管理配置
        // 用途: 关闭内存压力通知
        // 默认值: 启用内存压力通知
        // 影响: 防止 Chrome 因内存压力改变调度行为
        options.addArguments("--memory-pressure-off");


        // =========================
        // 浏览器标识配置
        // =========================

        // 浏览器标识配置
        // 用途: 设置自定义 User-Agent
        // 默认值: Chrome 原生 User-Agent（含自动化特征）
        // 影响: 伪装真实用户浏览器，降低被反爬识别概率
        options.addArguments("--user-agent=" + Test.chromeAgent);


        // =========================
        // 代理与隐私模式（可选）
        // =========================

        // 代理配置
        // 用途: 设置代理服务器访问目标网站
        // 默认值: 不使用代理
        // 影响: 提高匿名性，适用于反爬或多 IP 场景
        // options.addArguments("--proxy-server=http://proxy-server:port");

        // 隐私模式
        // 用途: 启用无痕浏览模式
        // 默认值: 普通浏览模式
        // 影响: 不保存 Cookie、缓存和历史记录
        // options.addArguments("--incognito");


        // =========================
        // 日志配置
        // =========================

        // 日志配置
        // 用途: 设置 Chrome 日志级别
        // 默认值: 0
        // 影响: 控制日志输出详细程度（0=默认，1=INFO，2=WARNING，3=ERROR，4=FATAL）
        options.addArguments("--log-level=0");

        // 日志配置
        // 用途: 启用静默模式，减少控制台输出
        // 默认值: 输出详细日志
        // 影响: 控制台更干净，略微提升性能
        options.addArguments("--silent");

        return options;
    }


}

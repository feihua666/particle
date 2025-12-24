package com.particle.global.crawler.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 测试抓取jd一家店的商品信息
 * </p>
 *
 * @author yangwei
 * @since 2025/11/11 10:56
 */
public class CrawlerFirefoxJd {
    private static FirefoxDriver driver;

    public static void main(String[] args) throws InterruptedException {


        setupDriver();
        // 热水器
        // openUrl("https://pro.jd.com/mall/active/QpoRB642zrAgURWfuU2ZNpGRqrG/index.html");
        // 饮水机
        openUrl("https://pro.jd.com/mall/active/CfM1F6ajftBnKbuBngwRc9iVFWD/index.html");

        Thread.sleep(1000);
        String pageSource = driver.getPageSource();
        // 查找具有 jsonsrv="1" 和 linksrv 属性的 div 元素
        List<WebElement> hotSpots = driver.findElements(By.cssSelector("div[linksrv]"));

        System.out.println("找到 " + hotSpots.size() + " 个热区元素");
        List<String> detailUrls = new java.util.ArrayList<>();
        for (int i = 0; i < hotSpots.size(); i++) {
            WebElement divLinksrvElement = hotSpots.get(i);
            System.out.println("元素 " + i + ":");
            String linksrvAttr = divLinksrvElement.getAttribute("linksrv");
            System.out.println("  linksrv 属性: " + linksrvAttr);
            System.out.println("  style 属性: " + divLinksrvElement.getAttribute("style"));
            System.out.println("  class 属性: " + divLinksrvElement.getAttribute("class"));

            // 解析 linksrv 属性并提取 suburl
            if (linksrvAttr != null && !linksrvAttr.isEmpty()) {
                // 简单解析 JSON 字符串获取 suburl
                String suburl = extractSuburl(linksrvAttr);
                if (suburl != null) {
                    System.out.println("  提取的 suburl: " + suburl);

                    // 构造完整 URL 并打开新页面
                    String fullUrl = constructFullUrl("", suburl);
                    System.out.println("  完整 URL: " + fullUrl);
                    detailUrls.add(fullUrl);

                }
            }
        }
        List<Map<String, List<String>>> skuIdsAll = new ArrayList<>();
        for (String detailUrl : detailUrls) {
            List<String> skuIds = detailInfo(detailUrl);
            Map skuIdsAllMap = new HashMap<>();
            skuIdsAllMap.put("detailUrl", detailUrl);
            skuIdsAllMap.put("skuIds", skuIds);
            skuIdsAll.add(skuIdsAllMap);
            sleepDefault();
            System.out.println("找到 " + skuIds.size() + " 同类元素");
        }

        FileUtil.appendUtf8String(JSONUtil.toJsonStr(skuIdsAll), "/Users/yw/temp/skuIdsAll.json");
        for (Map skuIdsAllMap : skuIdsAll) {
            List<String> skuIds = (List<String>) skuIdsAllMap.get("skuIds");
            String detailUrl = (String) skuIdsAllMap.get("detailUrl");
            for (String skuId : skuIds) {
                String otherDetailUrl = replaceSkuIdWithRegex(detailUrl, skuId);
                detailInfo(otherDetailUrl);
                sleepDefault();
            }
        }

        // openUrl("https://wenshu.court.gov.cn/");
        // closeDriver();
    }
    private static String replaceSkuIdWithRegex(String originalUrl, String newSkuId) {
        // 匹配京东商品URL中的SKUID（通常是数字）
        // 正则解释：匹配 / 后跟数字的部分，直到 .html 或 ? 或 #
        Pattern pattern = Pattern.compile("(?<=/)\\d+(?=\\.html|\\?|#|$)");
        Matcher matcher = pattern.matcher(originalUrl);

        if (matcher.find()) {
            return matcher.replaceFirst(newSkuId);
        } else {
            // 如果没有找到SKUID，尝试其他模式
            return null;
        }
    }
    public static List<String> detailInfo(String detailUrl) {

        // 打开新页面
        driver.get(detailUrl);

        // 查找目标元素
        List<WebElement> targetElements = driver.findElements(By.cssSelector("div.itemInfo-wrap"));
        System.out.println("  在新页面中找到 " + targetElements.size() + " 个 itemInfo-wrap 元素");
        SkuDetail skuDetail = new SkuDetail();
        skuDetail.setSkuDetailUrl(detailUrl);
        List<String> notSelectedSkuIds = new java.util.ArrayList<>();
        for (WebElement targetElement : targetElements) {
            List<WebElement> skuTitleNames = targetElement.findElements(By.cssSelector("span.sku-title-name"));
            if (skuTitleNames.size() > 0) {
                System.out.println("  找到 " + skuTitleNames.size() + " 个 span.sku-title-name 元素");
                for (WebElement skuTitleName : skuTitleNames) {
                    System.out.println("    Text: " + skuTitleName.getText());
                    skuDetail.setSkuTitle(skuTitleName.getText());
                }
            }

            List<WebElement> finalPriceElements = targetElement.findElements(By.cssSelector("#J_FinalPrice"));
            if (finalPriceElements.size() > 0) {
                System.out.println("  找到 " + finalPriceElements.size() + " 个 #J_FinalPrice 元素");
                for (WebElement finalPriceElement : finalPriceElements) {
                    System.out.println("    Text: " + finalPriceElement.getText());
                    skuDetail.setSkuFinalPrice(finalPriceElement.getText());
                }
            }
            List<WebElement> jdPriceElements = targetElement.findElements(By.cssSelector(".p-price"));
            if (finalPriceElements.size() > 0) {
                System.out.println("  找到 " + jdPriceElements.size() + " 个 .p-price 元素");
                for (WebElement jdPriceElement : jdPriceElements) {
                    System.out.println("    Text: " + jdPriceElement.getText());
                    skuDetail.setSkuPrice(jdPriceElement.getText());
                }
            }
            List<WebElement> commentCountElements = targetElement.findElements(By.cssSelector("#comment-count"));
            if (commentCountElements.size() > 0) {
                System.out.println("  找到 " + commentCountElements.size() + " 个 #comment-count 元素");
                for (WebElement commentCountElement : commentCountElements) {
                    System.out.println("    Text: " + commentCountElement.getText());
                    skuDetail.setCommentCount(commentCountElement.getText());
                }
            }
            List<WebElement> itemSelectedElements = targetElement.findElements(By.cssSelector("div#choose-attrs div.item.selected"));
            if (itemSelectedElements.size() > 0) {
                System.out.println("  找到 " + itemSelectedElements.size() + " 个 div#choose-attrs div.item.selected 元素");
                for (WebElement itemSelectedElement : itemSelectedElements) {
                    System.out.println("    Text: " + itemSelectedElement.getText());
                    System.out.println("    data-sku: " + itemSelectedElement.getAttribute("data-sku"));
                    skuDetail.setSkuName(itemSelectedElement.getText());
                    skuDetail.setSkuId( itemSelectedElement.getAttribute("data-sku"));
                }
            }
            List<WebElement> itemNotSelectedElements = targetElement.findElements(By.cssSelector("div#choose-attrs div.item"));
            if (itemNotSelectedElements.size() > 0) {
                System.out.println("  找到 " + itemNotSelectedElements.size() + " 个 div#choose-attrs div.item 元素");
                for (WebElement itemNotSelectedElement : itemNotSelectedElements) {
                    System.out.println("    Text: " + itemNotSelectedElement.getText());
                    System.out.println("    data-sku: " + itemNotSelectedElement.getAttribute("data-sku"));
                    String aClass = itemNotSelectedElement.getAttribute("class");
                    if (aClass != null && !aClass.contains("selected")) {
                        String notSelectedSkuId = itemNotSelectedElement.getAttribute("data-sku");
                        notSelectedSkuIds.add(notSelectedSkuId);
                    }
                }
            }

        }
        FileUtil.appendUtf8Lines(Lists.newArrayList(JSONUtil.toJsonStr(skuDetail)), "/Users/yw/temp/jd-sku-detail.json");
        return notSelectedSkuIds;
    }
    public  static void sleep(long min, long max) {
        try {
            long sleep = RandomUtil.randomLong(min, max);

            System.out.println("Sleeping for " + sleep + " seconds...");
            Thread.sleep(sleep * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void sleepDefault() {
        sleep(3, 11);
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

    /**
     * 从 linksrv JSON 字符串中提取 suburl
     */
    private static String extractSuburl(String linksrvJson) {
        try {
            JSONObject jsonObject = JSONUtil.parseObj(linksrvJson);
            if (!StrUtil.equals("skudetail", jsonObject.getStr("des"))) {
                System.err.println("linksrv JSON 中缺少 skudetail 字段");
                return null;
            }

            return jsonObject.getStr("suburl");
        } catch (Exception e) {
            System.err.println("解析 suburl 失败: " + e.getMessage());
            return null;
        }

    }

    /**
     * 构造完整 URL
     */
    private static String constructFullUrl(String baseUrl, String suburl) {
        if (suburl.startsWith("//")) {
            // 如果是协议相对 URL，添加当前协议
            if (baseUrl.startsWith("https://")) {
                return "https:" + suburl;
            } else {
                return "http:" + suburl;
            }
        } else if (suburl.startsWith("/")) {
            // 如果是绝对路径，添加主机部分
            int hostEndIndex = baseUrl.indexOf('/', 8); // 跳过 http:// 或 https:// 部分
            if (hostEndIndex != -1) {
                String host = baseUrl.substring(0, hostEndIndex);
                return host + suburl;
            } else {
                return baseUrl + suburl;
            }
        } else if (suburl.startsWith("http://") || suburl.startsWith("https://")) {
            // 如果已经是完整 URL，直接返回
            return suburl;
        } else {
            // 相对路径
            if (baseUrl.endsWith("/")) {
                return baseUrl + suburl;
            } else {
                return baseUrl + "/" + suburl;
            }
        }
    }

}

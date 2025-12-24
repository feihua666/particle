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
 * 测试抓取tm一家店的商品信息
 * </p>
 *
 * @author yangwei
 * @since 2025/11/11 10:56
 */
public class CrawlerFirefoxTm {
    private static FirefoxDriver driver;

    public static void main(String[] args) throws InterruptedException {


        setupDriver();

        openUrl("https://www.tmall.com/");

        Thread.sleep(1000);
        // 热水器
        openUrl("https://midea.tmall.com/category-889413972-1339496479.htm?spm=a1z10.15-b-s.w4010-22480969661.157.600f76e52dHql1&search=y&catName=%C8%C8%CB%AE%C6%F7#bd");

        String pageSource = driver.getPageSource();
        // 查找具有 jsonsrv="1" 和 linksrv 属性的 div 元素
        List<WebElement> hotSpots = driver.findElements(By.cssSelector("div.J_TItems .item"));

        System.out.println("找到 " + hotSpots.size() + " 个热区元素");
        List<String> detailUrls = new ArrayList<>();
        for (int i = 0; i < hotSpots.size(); i++) {
            WebElement divLinksrvElement = hotSpots.get(i);
            System.out.println("元素 " + i + ":");
            WebElement itemElement = divLinksrvElement.findElement(By.cssSelector("a.J_TGoldData"));
            String href = itemElement.getAttribute("href");
            String suburl = href;
            if (suburl != null) {
                System.out.println("  提取的 suburl: " + suburl);

                // 构造完整 URL 并打开新页面
                String fullUrl = constructFullUrl("", suburl);
                System.out.println("  完整 URL: " + fullUrl);
                detailUrls.add(fullUrl);

            }
        }
        for (String detailUrl : detailUrls) {
            List<WebElement> notSelectedWebElements  = detailInfo(detailUrl,true);
            for (WebElement notSelectedWebElement : notSelectedWebElements) {
                System.out.println("  点击元素 " + notSelectedWebElement.getText());
                boolean clickElement = clickElement(notSelectedWebElement);
                if (clickElement) {
                    sleep1s();
                    detailInfo(detailUrl, false);
                }

            }
            sleepDefault();
        }

        // openUrl("https://wenshu.court.gov.cn/");
        // closeDriver();
    }

    public static List<WebElement> detailInfo(String detailUrl,boolean isOpenUrl) {

        if (isOpenUrl) {
            // 打开新页面
            driver.get(detailUrl);
        }


        // 查找目标元素
        List<WebElement> targetElements = driver.findElements(By.cssSelector("div#tbpcDetail_SkuPanelBody"));
        System.out.println("  在新页面中找到 " + targetElements.size() + " 个 div#tbpcDetail_SkuPanelBody 元素");
        SkuDetail skuDetail = new SkuDetail();
        skuDetail.setSkuDetailUrl(detailUrl);
        long start = System.currentTimeMillis();
        WebElement containerElement = findElementByCssSelector(driver,"div#ice-container");
        long end = System.currentTimeMillis();
        System.out.println("  查找 div#ice-container 元素耗时：" + (end - start) + "ms");

        start = System.currentTimeMillis();
        // WebElement tabDetailItemTitleElement = findElementByCssSelector(containerElement,"div#page-content-area","div#left-content-area","div.tabDetailItemTitle--bJtPXTNu");
        // end = System.currentTimeMillis();
        // System.out.println("  查找 div.tabDetailItemTitle--bJtPXTNu 元素耗时：" + (end - start) + "ms");
        // if (tabDetailItemTitleElement != null) {
        //     skuDetail.setCommentCount(tabDetailItemTitleElement.getText());
        // }
        start = System.currentTimeMillis();
        WebElement mainPicImageElement = findElementByCssSelector(driver,"img#mainPicImageEl");
        end = System.currentTimeMillis();
        System.out.println("  查找 img#mainPicImageEl 元素耗时：" + (end - start) + "ms");
        if (mainPicImageElement != null) {
            skuDetail.setSkuMainImgUrl(mainPicImageElement.getAttribute("src"));
        }


        List<WebElement> notSelectedWebElements = new ArrayList<>();
        for (WebElement targetElement : targetElements) {
            List<WebElement> skuTitleNames = targetElement.findElements(By.cssSelector("span[class^='MainTitle--']"));
            if (skuTitleNames.size() > 0) {
                System.out.println("  找到 " + skuTitleNames.size() + " 个 span[class^='MainTitle--']");
                for (WebElement skuTitleName : skuTitleNames) {
                    System.out.println("    Text: " + skuTitleName.getText());
                    skuDetail.setSkuTitle(skuTitleName.getText());
                }
            }

            List<WebElement> finalPriceElements = targetElement.findElements(By.cssSelector("div[class^='highlightPrice--']"));
            if (finalPriceElements.size() > 0) {
                System.out.println("  找到 " + finalPriceElements.size() + " 个 div[class^='highlightPrice--'] 元素");
                for (WebElement finalPriceElement : finalPriceElements) {
                    System.out.println("    Text: " + finalPriceElement.getText());
                    skuDetail.setSkuFinalPrice(finalPriceElement.getText());
                }
            }
            List<WebElement> jdPriceElements = targetElement.findElements(By.cssSelector("div[class^='subPrice--']"));
            if (finalPriceElements.size() > 0) {
                System.out.println("  找到 " + jdPriceElements.size() + " 个 div[class^='subPrice--'] 元素");
                for (WebElement jdPriceElement : jdPriceElements) {
                    System.out.println("    Text: " + jdPriceElement.getText());
                    skuDetail.setSkuPrice(jdPriceElement.getText());
                }
            }
            List<WebElement> summaryWrapElements = targetElement.findElements(By.cssSelector("div[class^='summaryWrap--']"));
            if (finalPriceElements.size() > 0) {
                System.out.println("  找到 " + summaryWrapElements.size() + " 个 div[class^='summaryWrap--'] 元素");
                for (WebElement summaryWrapElement : summaryWrapElements) {
                    System.out.println("    Text: " + summaryWrapElement.getText());
                    skuDetail.setSaleCount(summaryWrapElement.getText());
                }
            }
            // List<WebElement> commentCountElements = targetElement.findElements(By.cssSelector("#comment-count"));
            // if (commentCountElements.size() > 0) {
            //     System.out.println("  找到 " + commentCountElements.size() + " 个 #comment-count 元素");
            //     for (WebElement commentCountElement : commentCountElements) {
            //         System.out.println("    Text: " + commentCountElement.getText());
            //         skuDetail.setCommentCount(commentCountElement.getText());
            //     }
            // }
            List<WebElement> itemSelectedElements = targetElement.findElements(By.cssSelector("div[class^='skuValueWrap--'] div[class^='valueItem--'][class*='isSelected--']"));
            if (itemSelectedElements.size() > 0) {
                System.out.println("  找到 " + itemSelectedElements.size() + " 个 div[class^='skuValueWrap--'] div[class^='valueItem--'][class*='isSelected--'] 元素");
                for (WebElement itemSelectedElement : itemSelectedElements) {
                    System.out.println("    Text: " + itemSelectedElement.getText());
                    System.out.println("    data-vid: " + itemSelectedElement.getAttribute("data-vid"));
                    skuDetail.setSkuName(itemSelectedElement.getText());
                    skuDetail.setSkuId( itemSelectedElement.getAttribute("data-vid"));
                }
            }
            List<WebElement> itemNotSelectedElements = targetElement.findElements(By.cssSelector("div[class^='skuValueWrap--'] div[class^='valueItem--']"));
            if (itemNotSelectedElements.size() > 0) {
                System.out.println("  找到 " + itemNotSelectedElements.size() + " 个 div[class^='skuValueWrap'] div[class^='valueItem--'] 元素");
                for (WebElement itemNotSelectedElement : itemNotSelectedElements) {
                    System.out.println("    Text: " + itemNotSelectedElement.getText());
                    System.out.println("    data-vid: " + itemNotSelectedElement.getAttribute("data-vid"));
                    String aClass = itemNotSelectedElement.getAttribute("class");
                    if (aClass != null && !aClass.contains("isSelected")) {
                        notSelectedWebElements.add(itemNotSelectedElement);
                    }
                }
            }

        }
        FileUtil.appendUtf8Lines(Lists.newArrayList(JSONUtil.toJsonStr(skuDetail)), "/Users/yw/temp/tm-sku-detail.json");
        return notSelectedWebElements;
    }

    public static boolean clickElement(WebElement element) {
        try {
            element.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static WebElement findElementByCssSelector(FirefoxDriver driver,String cssSelector) {
        long start = System.currentTimeMillis();
        WebElement element = null;
        try {
            element = driver.findElement(By.cssSelector(cssSelector));
        } catch (Exception e) {
            System.err.println("error cssSelector=" + cssSelector);
            return  null;
        }finally {
            long end = System.currentTimeMillis();
            System.out.println("  findElementByCssSelector " + cssSelector + " 耗时: " + (end - start) + "ms");
        }

        return element;
    }
    public static WebElement findElementByCssSelector(FirefoxDriver driver,String ...cssSelectors) {
        WebElement element = findElementByCssSelector(driver, cssSelectors[0]);
        for (int i = 1; i < cssSelectors.length; i++) {
            element = findElementByCssSelector(element, cssSelectors[i]);
            if (element == null) {
                return null;
            }
        }
        return element;
    }
    public static WebElement findElementByCssSelector(WebElement webElement,String cssSelector) {
        long start = System.currentTimeMillis();
        WebElement element = null;
        try {
            element = webElement.findElement(By.cssSelector(cssSelector));
        } catch (Exception e) {
            System.err.println("error cssSelector=" + cssSelector);
            e.printStackTrace();
            return  null;
        }finally {
            long end = System.currentTimeMillis();
            System.out.println("  findElementByCssSelector " + cssSelector + " 耗时: " + (end - start) + "ms");
        }

        return element;
    }
    public static WebElement findElementByCssSelector(WebElement webElement,String ...cssSelectors) {
        WebElement element = webElement;
        for (String cssSelector : cssSelectors) {
            element = findElementByCssSelector(element, cssSelector);
            if (element == null) {
                return null;
            }
        }
        return element;
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
    public static void sleep1s() {
        sleep(1, 2);
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

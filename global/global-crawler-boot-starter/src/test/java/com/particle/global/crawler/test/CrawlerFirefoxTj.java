package com.particle.global.crawler.test;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.google.common.collect.Lists;
import com.particle.global.crawler.tool.CrawlerWebDriverTool;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 简单测试 firefox
 * 启动浏览器，访问一个网页
 * </p>
 *
 * @author yangwei
 * @since 2025/01/11
 */
@Slf4j
public class CrawlerFirefoxTj {
    private static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

        test3();


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
        FirefoxProfile profile = profile(userDataDir,userAgent);
        FirefoxOptions options = options(profile);
        driver = CrawlerWebDriverTool.Firefox.setUpDriver(firefoxDriverPath,options);
        CrawlerWebDriverTool.waitGlobal(driver,1 * 2000);

        CrawlerWebDriverTool.openUrl(driver,"https://sales.tungee.com/home");

        test();

        log.info("当前页面标题：{}",CrawlerWebDriverTool.getPageTitle(driver));
        // CrawlerWebDriverTool.closeDriver(driver);
    }
    public static List<Map<String, Object>>  readNames(String fileName) {
        List<Map<String, Object>> listResult = new ArrayList<>();
        ExcelReader reader = ExcelUtil.getReader("/Users/yw/temp/tj/" + fileName + ".xlsx");
        reader.setSheet(1);
        List<List<Object>> listList = reader.read(1);
        for (List<Object> list : listList) {
            String entName = list.get(0).toString();
            String userName = list.get(1).toString();

            Map<String, Object> map = new HashMap<>();
            map.put("entName",entName);
            map.put("userName",userName);
            map.put("fileName",fileName);

            listResult.add(map);
        }

        return listResult;
    }
    public static void test() {

        List<Map<String, Object>> listResult = new ArrayList<>();
        listResult.addAll(readNames("1"));
        listResult.addAll(readNames("2"));

        String resultFile = "/Users/yw/temp/tj/result.json";
        if (!FileUtil.exist(resultFile)) {
            String jsonStr = JSONUtil.toJsonPrettyStr(listResult);
            FileUtil.writeUtf8String(jsonStr, resultFile);
        }
        String s = FileUtil.readUtf8String(resultFile);
        List<Map> list = JSONUtil.toList(s, Map.class);
        try {
            Iterator<Map<String, Object>> iterator = listResult.iterator();
            while (iterator.hasNext()) {
                Map<String, Object> stringObjectMap = iterator.next();
                String entName = stringObjectMap.get("entName").toString();
                String userName = stringObjectMap.get("userName").toString();
                boolean isSkip = false;
                Map<String, Object> objectMapTemp = null;
                for (Map<String, Object> objectMap : list) {
                    if (objectMap.get("entName").equals(entName) && objectMap.get("userName").equals(userName)) {
                        objectMapTemp = objectMap;
                        if ("1".equals(objectMap.get("finish"))) {
                            isSkip = true;
                        }
                    }
                }
                if (isSkip) {
                    continue;
                }
                test2(stringObjectMap,true);
                objectMapTemp.put("finish", stringObjectMap.get("finish"));
                String jsonStr = JSONUtil.toJsonPrettyStr(stringObjectMap);
                FileUtil.appendUtf8Lines(Lists.newArrayList(jsonStr), "/Users/yw/temp/tj/result-"+ entName + "-" + userName +".json");

                String listjsonStr = JSONUtil.toJsonPrettyStr(list);
                FileUtil.writeUtf8String(listjsonStr, "/Users/yw/temp/tj/result.json");
                iterator.remove();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            String listjsonStr = JSONUtil.toJsonPrettyStr(list);
            FileUtil.writeUtf8String(listjsonStr, "/Users/yw/temp/tj/result.json");
        }

    }
    public static void test2(Map<String, Object> stringObjectMap,boolean useGd) {
        String entName = stringObjectMap.get("entName").toString();
        String userName = stringObjectMap.get("userName").toString();
        String encodedEntName = URLUtil.encode(entName);
        String searchUrl = "https://sales.tungee.com/find-enterprise/results?key=" + encodedEntName + "&page=1";
        CrawlerWebDriverTool.openUrl(driver,searchUrl);
        sleepDefault();
        // String searchHtml = driver.getPageSource();
        stringObjectMap.put("searchUrl",searchUrl);
        // stringObjectMap.put("searchHtml",searchHtml);
        // 按企业名称查出出来的第一个元素
        WebElement element = CrawlerWebDriverTool.findElement(driver,By.cssSelector("tr.ant-table-row:nth-child(1) > td:nth-child(2) > div:nth-child(2) > section:nth-child(1) > div:nth-child(2) > div:nth-child(1) > h3:nth-child(1) > a:nth-child(1)"));
        if (StrUtil.equals(element.getText(),entName)) {
            int oldCount = driver.getWindowHandles().size();
            Set<String> before = driver.getWindowHandles();

            element.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(d -> d.getWindowHandles().size() > oldCount);
            Set<String> after = driver.getWindowHandles();
            after.removeAll(before);
            String newWindow = after.iterator().next();
            driver.close();
            driver.switchTo().window(newWindow);


            sleepDefault();


            // long start = System.currentTimeMillis();
            // String detailHtmlLoaded = driver.getPageSource();
            // String detailHtmlLUrl = driver.getCurrentUrl();
            // stringObjectMap.put("detailHtmlLUrl",detailHtmlLUrl);
            // stringObjectMap.put("detailHtmlLoaded",detailHtmlLoaded);
            // long end = System.currentTimeMillis();
            // log.error("加载详情页面耗时：{}",end - start);


            // 如果没有解锁，先解锁
            WebElement element1 = CrawlerWebDriverTool.findElement(driver,By.cssSelector(".ant-btn-primary"));
            if (element1 != null && StrUtil.equals("解 锁",element1.getText())) {
                element1.click();
                WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
                Map<String, Integer> times = new HashMap<>();
                wait1.until(d ->
                        {
                            try {
                                times.put("times", times.getOrDefault("times", 0) + 1);
                                WebElement element3 =  CrawlerWebDriverTool.findElement(d,By.cssSelector(".ant-btn-primary"));
                                if (times.get("times") > 10) {
                                    if (element3 != null && StrUtil.equals("解 锁",element3.getText())) {
                                        element3.click();
                                        sleep(1,1);
                                    }
                                }
                                return element3 != null && StrUtil.equals("添加到CRM",element3.getText());
                            } catch (StaleElementReferenceException e) {
                                WebElement element3 =  CrawlerWebDriverTool.findElement(d,By.cssSelector(".ant-btn-primary"));
                                return element3 != null && StrUtil.equals("添加到CRM",element3.getText());
                            }
                        }
                );
                sleep(0,1);
            }

            // 显示全部联系方式
            WebElement element4 = CrawlerWebDriverTool.findElement(driver,By.cssSelector(".XmMVC > a:nth-child(1)"));
            if (element4 != null && StrUtil.contains(element4.getText(),"查看全部")
                    && StrUtil.contains(element4.getText(),"联系方式")) {
                // element4.click();
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element4);
                WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
                try {
                    wait2.until(d -> {
                        while (true) {
                            Actions actions = new Actions(driver);
                            actions.keyDown(Keys.CONTROL)
                                    .sendKeys(Keys.END)  // 滚动到底部
                                    .keyUp(Keys.CONTROL)
                                    .perform();

                            // 方法3：使用鼠标滚轮
                            actions.moveToElement(driver.findElement(By.cssSelector("#enterprise-details-scroll-bar-box + div div.ScrollbarsCustom-Scroller")))
                                    .click()
                                    .sendKeys(Keys.PAGE_DOWN)
                                    .perform();

                            WebElement element5 =  CrawlerWebDriverTool.findElement(d,By.cssSelector("._9xFbM"));
                            return element5 != null && StrUtil.contains(element5.getText(),"没有更多联系方式了");
                        }
                    });
                } catch (Exception e) {
                    log.error("显示全部联系方式异常 entName={}：{}",entName,e.getMessage());
                }
            }

            // 确认法定代表人是不是要找的人，如果是要找的人，保存页面就可以了
            WebElement element2 = CrawlerWebDriverTool.findElement(driver,By.cssSelector(".U6udV > div  [title=\"" + userName + "\"]"));
            if (element2 != null) {
                sleep(1,2);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                // String detailHtml = driver.getPageSource();
                String detailHtml = (String) js.executeScript("return document.documentElement.outerHTML;");
                stringObjectMap.put("detailHtml",detailHtml);
            }else{
                // 如果不是要找的人，尝试找股东
                if (useGd) {
                    stringObjectMap.put("useGd", "1");
                    WebElement element6 = CrawlerWebDriverTool.findElement(driver,By.cssSelector("._2eNU3 + div [title=\"" + userName + "\"]"));
                    if (element6 != null) {
                        WebElement element3 =  CrawlerWebDriverTool.findElement(element6,By.cssSelector("a"));
                        element3.click();
                        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(60));
                        wait3.until(d -> {
                            WebElement element5 =  CrawlerWebDriverTool.findElement(d,By.cssSelector(".ant-modal-title"));
                            return element5 != null;
                        });
                        int page = 1;
                        while (true) {
                            List<WebElement> elements = CrawlerWebDriverTool.findElements(driver,By.cssSelector(".ant-modal-body table tr"));
                            for (WebElement elementTr : elements) {
                                WebElement elementsTd3 =  CrawlerWebDriverTool.findElement(elementTr,By.cssSelector("td:nth-child(3)"));

                                if (elementsTd3 != null && StrUtil.equals(userName, elementsTd3.getText())) {

                                    // String detailHtmlPage = driver.getPageSource();
                                    // stringObjectMap.put("detailHtmlPage" + page,detailHtmlPage);
                                    stringObjectMap.put("detailHtmlPageNo", page + "");
                                    Map<String, Object> gdMap = new HashMap<>();
                                    stringObjectMap.put("gdMap", gdMap);
                                    WebElement elementsTd2 =  CrawlerWebDriverTool.findElement(elementTr,By.cssSelector("td:nth-child(2)"));
                                    WebElement element5 =  CrawlerWebDriverTool.findElement(elementsTd2,By.cssSelector("a"));
                                    if (element5 != null) {
                                        String text = element5.getText();
                                        gdMap.put("entName",text);
                                        gdMap.put("userName",userName);
                                        test2(gdMap,false);

                                        break;
                                    }

                                }
                            }

                            WebElement element7 = CrawlerWebDriverTool.findElement(driver,By.cssSelector(".ant-modal-body .ant-pagination-next"));
                            boolean hasNext = (element7 != null && !element7.getAttribute("class").contains("ant-pagination-disabled"));
                            if (hasNext) {
                                page++;
                                element7.click();
                                sleep(1, 2);
                            }else{
                                break;
                            }
                        }
                    }
                }
            }



        }else{
            stringObjectMap.put("errorMsg", "未搜索到企业");
        }
        stringObjectMap.put("finish", "1");
    }

    public static void test3() {
        String resultFile = "/Users/yw/temp/tj/result.json";
        String s = FileUtil.readUtf8String(resultFile);
        List<Map> list = JSONUtil.toList(s, Map.class);
        // 将下面的for循环改成并行
        list.parallelStream().forEach(map -> {
            String entName = (String) map.get("entName");
            String userName = (String) map.get("userName");

            String filePath = "/Users/yw/temp/tj/result-" + entName + "-" + userName + ".json";
            String readUtf8String = FileUtil.readUtf8String(filePath);
            JSONObject jsonObject = JSONUtil.parseObj(readUtf8String);

            // 将 userName 除第一个字符外，其它字符都替换成 *,如：张三四 改为 张**
            String prefix = StrUtil.sub(userName, 0, 1);
            for (String string : StrUtil.sub(userName, 1, userName.length()).split("")) {
                prefix += "*";
            }
            userName = prefix;
            String phone = null;
            JSONObject gdMap = jsonObject.getJSONObject("gdMap");
            if (gdMap != null) {
                String detailHtml1 = jsonObject.getStr("detailHtml");
                if (detailHtml1 != null) {
                    phone = parseHtml(detailHtml1, userName);
                    map.put("phoneMatchGd", phone);
                }
            }
            if (phone == null) {
                String detailHtml = jsonObject.getStr("detailHtml");
                if (detailHtml != null) {
                    phone = parseHtml(detailHtml, userName);
                    map.put("phoneMatch", phone);
                }

            }
            if (phone == null) {
                String detailHtml = jsonObject.getStr("detailHtmlLoaded");
                if (detailHtml != null) {
                    phone = parseHtml(detailHtml, userName);
                    map.put("phoneMatch", phone);
                }

            }
            log.info("entName:userName={}:{}", entName, userName);
            map.put("phone", phone);

        });

        String listjsonStr = JSONUtil.toJsonPrettyStr(list);
        FileUtil.writeUtf8String(listjsonStr, resultFile);
    }

    public static String parseHtml(String html,String userName) {

        long start = System.currentTimeMillis();
        String phone = null;
        // 预测匹配
        Document document = Jsoup.parse(html);
        Element element = document.selectFirst("#contact_item_guide > div:nth-child(1)");
        phone = getPhone(element, userName);
        if (phone == null) {

            Element element3 = document.selectFirst("#enterprise-details-scroll-bar-box + div div.ScrollbarsCustom-Scroller ._3D0bx");
            if (element3 != null) {
                Elements elements = element3.children();
                for (Element element0 : elements) {
                    phone = getPhone(element0, userName);
                }
            }

        }

        long end = System.currentTimeMillis();
        log.info("parseHtml：{}",end - start);
        return phone;
    }
    public static String getPhone(Element element,String userName) {
        long start = System.currentTimeMillis();
        String phone = null;
        if(element != null) {
            Element element2 = element.selectFirst(" > div:nth-child(2)");
            if(element2 != null) {
                if (StrUtil.contains(element2.text(),userName)) {
                    Element element1 = element.selectFirst(" > div:nth-child(1)");
                    String phoneText = element1.text().trim();
                    // 使用正则提取手机号
                    // 匹配格式：134****9878 或 13513086888
                    Pattern pattern = Pattern.compile("1[3-9]\\d{2}\\*{4}\\d{4}|1[3-9]\\d{9}");
                    Matcher matcher = pattern.matcher(phoneText);

                    if (matcher.find()) {
                        phone  = matcher.group();
                    }
                }
            }
        }
        long end = System.currentTimeMillis();
        log.info("getPhone：{}",end - start);
        return phone;
    }
    public  static void sleep(long minSecond, long maxSecond) {
        long sleep;
        if (minSecond == maxSecond) {
            sleep = minSecond;
        }else {
            sleep = RandomUtil.randomLong(minSecond, maxSecond);
        }



        System.out.println("Sleeping for " + sleep + " seconds...");
        CrawlerWebDriverTool.waitForce(sleep * 1000);
    }
    public static void sleepDefault() {
        sleep(1, 3);
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

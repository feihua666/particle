# 全局爬虫组件

全局爬虫组件提供现代化的爬虫架构，基于 Action-Pipeline-Driver 设计模式，支持多引擎驱动和链式 DSL 构建。

## 功能特性

1. **Pipeline 构建器**：提供流式 API 链式构建爬虫流程
2. **多引擎支持**：支持 HTTP（Jsoup）、Playwright、Selenium 三种驱动
3. **Action 插件化**：所有操作抽象为 Action，支持自定义扩展
4. **智能 Driver 工厂**：根据配置自动创建对应驱动实例
5. **完整浏览器控制**：支持点击、输入、按键、滚动、悬停等操作
6. **灵活数据提取**：支持文本、HTML、属性、列表、标题提取

## 核心概念

### 调用链

```
PipelineBuilder → CrawlPipeline → CrawlRuntime → RuntimeContext → Driver → ActionResult
```

### 三层架构

1. **Pipeline 层**：通过 PipelineBuilder 链式构建 Action 序列
2. **Runtime 层**：CrawlRuntime 执行 Pipeline，管理运行时上下文
3. **Driver 层**：根据配置创建 HTTP/Playwright/Selenium 驱动

## 快速开始

### 1. 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-crawler-boot-starter</artifactId>
</dependency>
```

### 2. 使用示例

#### 方式一：使用 PipelineBuilder（推荐）

```java
// 简单爬虫
CrawlPipeline pipeline = PipelineBuilder.create("新闻爬虫")
    .open("https://news.ycombinator.com")
    .extractText(".titleline a", "titles")
    .build();

List<ActionResult> results = crawlRuntime.execute(pipeline);
List<String> titles = results.get(1).getData();

// 复杂爬虫
CrawlPipeline pipeline = PipelineBuilder.create("商品爬虫")
    .open("https://example.com/products")
    .click(".next-page")
    .scrollToBottom()
    .extractList(".product-item", as -> {
        as.text("title", ".name");
        as.attr("image", ".img", "src");
        as.href("link", ".detail-link");
    })
    .enter("#search-input")  // 在输入框按回车
    .delay(1000)
    .close()
    .build();
```

#### 方式二：直接使用 Driver

```java
// HTTP 驱动（静态页面）
HttpDriver driver = new HttpDriver();
driver.open("https://example.com");
List<String> titles = driver.extractText(".title");
String pageTitle = driver.getTitle();

// Playwright 驱动（动态页面）
PlaywrightOptions options = new PlaywrightOptions();
options.setIsHeadless(false);
options.setBrowserType(BrowserType.CHROME);
CrawlDriver driver = DriverFactory.create(DriverType.PLAYWRIGHT, runtimeOptions);
driver.open("https://example.com");
driver.click(".button");
driver.pressKey("Enter");  // 按回车键
List<String> texts = driver.extractText(".content");
driver.close();
```

## 架构设计

### 核心组件

- **PipelineBuilder**：链式 DSL 构建器，提供流畅的 API
- **CrawlPipeline**：封装的 Action 序列
- **CrawlRuntime**：统一运行时入口，执行 Pipeline
- **RuntimeContext**：运行时上下文，管理变量和状态
- **DriverFactory**：无状态工厂，根据类型创建驱动
- **CrawlDriver**：驱动接口（HTTP、Playwright、Selenium）
- **CrawlAction**：动作接口（浏览器操作、数据提取、流程控制）

### 设计原则

1. **链式构建**：PipelineBuilder 提供流式 API，代码可读性强
2. **驱动解耦**：DriverFactory 无状态设计，按需创建驱动实例
3. **能力分离**：Action 按功能分类（Browser/Extract/Flow）
4. **Starter 定位**：作为 Spring Boot Starter 嵌入使用

### Action 类型

#### Browser Actions（浏览器操作）
- `BrowserOpenAction` - 打开页面
- `BrowserClickAction` - 点击元素
- `BrowserInputAction` - 输入文本
- `BrowserHoverAction` - 鼠标悬停
- `BrowserScrollAction` - 滚动页面
- `BrowserWaitAction` - 等待
- `BrowserPressKeyAction` - 按下键盘按键
- `BrowserEnterAction` - 在元素上按回车
- `BrowserNewPageAction` - 新建标签页
- `BrowserClosePageAction` - 关闭标签页
- `BrowserCloseAction` - 关闭浏览器

#### Extract Actions（数据提取）
- `ExtractTextAction` - 提取文本
- `ExtractHtmlAction` - 提取 HTML
- `ExtractAttrAction` - 提取属性
- `ExtractListAction` - 提取列表
- `ExtractTitleAction` - 提取页面标题

#### Flow Actions（流程控制）
- `DelayAction` - 延迟执行
- `RetryAction` - 重试机制

## PipelineBuilder API

### 浏览器操作

```java
PipelineBuilder.create()
    .open("https://example.com")           // 打开页面
    .click(".button")                       // 点击元素
    .click(".button", 5000)                 // 点击元素（指定超时）
    .input("#username", "admin")            // 输入文本
    .hover(".menu")                         // 鼠标悬停
    .scroll(500)                            // 滚动像素
    .scrollToBottom()                       // 滚动到底部
    .enter("#search")                       // 在元素上按回车
    .pressKey("Tab")                        // 按下指定按键
    .delay(1000)                            // 延迟1秒
    .close()                                // 关闭浏览器
```

### 数据提取

```java
PipelineBuilder.create()
    .extractText(".title")                  // 提取文本
    .extractText(".title", "myTitle")       // 提取文本并存储到变量
    .extractHtml(".content")                // 提取 HTML
    .extractAttr("a.link", "href")          // 提取属性
    .extractAttr("img", "src", "images")    // 提取属性并存储
    .extractTitle()                         // 提取页面标题
    .extractTitle("pageTitle")              // 提取标题并存储
    .extractList(".item", item -> {         // 提取列表
        item.text("name", ".name");
        item.attr("link", "a", "href");
        item.src("image", "img");
    })
```

## 驱动配置

### Playwright 配置

```java
PlaywrightOptions options = new PlaywrightOptions();
options.setBrowserType(BrowserType.CHROME);     // CHROME/FIREFOX/WEBKIT/EDGE
options.setIsHeadless(true);                     // 无头模式
options.setTimeout(30000);                       // 超时时间
options.setExecutablePath("/path/to/chrome");   // 本地浏览器路径
options.setUserDataDir("/path/to/userdata");    // 用户数据目录（持久化）
options.setBrowsersPath("/path/to/browsers");   // 浏览器缓存目录
options.setIsStealth(true);                      // 启用反检测
```

### Selenium 配置

```java
SeleniumOptions options = new SeleniumOptions();
options.setBrowserType(BrowserType.CHROME);
options.setIsHeadless(true);
options.setIsDisableGpu(true);                   // 禁用 GPU
options.setIsDisableExtensions(true);            // 禁用扩展
options.setExecutablePath("/path/to/chrome");
options.setUserDataDir("/path/to/userdata");
```

### HTTP 配置

```java
HttpConfig config = new HttpConfig();
config.setTimeout(30000);
config.setUserAgent("Mozilla/5.0...");
config.setIsFollowRedirects(true);
```

## 扩展示例

### 自定义 Action

```java
public class CustomScreenshotAction implements CrawlAction {
    
    private String filePath;
    
    public CustomScreenshotAction(String filePath) {
        this.filePath = filePath;
    }
    
    @Override
    public ActionType type() {
        return ActionType.CUSTOM;
    }
    
    @Override
    public ActionResult execute(RuntimeContext context) {
        try {
            CrawlDriver driver = context.getDriver();
            if (driver instanceof PlaywrightDriver) {
                ((PlaywrightDriver) driver).screenshotTo(filePath);
                return ActionResult.success().setMessage("截图保存: " + filePath);
            }
            return ActionResult.fail("当前驱动不支持截图");
        } catch (Exception e) {
            return ActionResult.fail(e);
        }
    }
}

// 使用
PipelineBuilder.create()
    .open("https://example.com")
    .add(new CustomScreenshotAction("/tmp/screenshot.png"))
    .build();
```

## 注意事项

- Playwright 首次使用会自动下载浏览器驱动，可配置跳过下载并使用本地浏览器
- HTTP Driver 不支持 JavaScript 渲染的动态页面
- Selenium 中的 WEBKIT 映射为 Safari 浏览器
- 使用持久化模式（userDataDir）可以保持登录状态和会话
- 反检测功能（isStealth）仅对 Playwright 有效
- Pipeline 执行完成后需要手动关闭浏览器（调用 close Action）

## 常见问题

### 1. Playwright 浏览器下载慢？

配置本地浏览器路径或设置 browsersPath：

```java
options.setExecutablePath("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
System.setProperty("playwright.browsers.path", "/custom/path");
```

### 2. 如何保持登录状态？

使用持久化用户数据目录：

```java
options.setUserDataDir("/path/to/chrome-profile");
```

### 3. 如何处理动态加载内容？

使用浏览器驱动并添加等待：

```java
PipelineBuilder.create()
    .open("https://example.com")
    .waitFor(2000)  // 等待2秒
    .extractText(".dynamic-content")
    .build();
```

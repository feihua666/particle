# 全局爬虫组件

全局爬虫组件提供网络爬虫相关依赖和工具，基于 Selenium WebDriver 实现，支持 Chrome 和 Firefox 浏览器自动化。

## 功能特性

1. **浏览器自动化**：基于 Selenium WebDriver 实现浏览器自动化操作
2. **多浏览器支持**：支持 Chrome 和 Firefox 浏览器
3. **驱动管理**：集成 WebDriverManager 实现驱动自动下载和管理
4. **反检测机制**：提供基础的反自动化检测隐藏技术
5. **测试工具**：提供简单易用的测试用例模板

## 核心组件

### WebDriver 支持
- ChromeDriver 支持
- FirefoxDriver 支持
- 浏览器选项配置工具

### 驱动管理
- WebDriverManager 自动驱动下载

### 反检测工具
- WebDriver 属性隐藏
- User-Agent 设置
- 浏览器指纹伪装

## 配置选项

### Chrome 驱动配置

使用 SimpleTest 测试时，需要下载驱动并配置驱动路径：

1. 打开下载地址找到对应平台版本和安装的 Chrome 版本，下载对应版本驱动
2. 下载地址：https://googlechromelabs.github.io/chrome-for-testing

配置示例：
```java
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
```

### Firefox 驱动配置

使用 CrawlerFirefoxTest 测试时，需要下载驱动并配置驱动路径：

1. 打开下载地址，找到火狐对应的版本，下载对应驱动
2. 下载地址：https://github.com/mozilla/geckodriver/releases

配置示例：
```java
System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver");
```

## 使用方法

### 添加依赖

```xml
<dependency>
    <groupId>com.particle</groupId>
    <artifactId>global-crawler-boot-starter</artifactId>
</dependency>
```

### 简单使用示例

```java
// 设置驱动路径
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

// 配置浏览器选项
ChromeOptions options = new ChromeOptions();
options.addArguments("--no-sandbox");
options.addArguments("--disable-dev-shm-usage");

// 创建 WebDriver 实例
WebDriver driver = new ChromeDriver(options);

// 访问网页
driver.get("https://www.example.com");

// 关闭浏览器
driver.quit();
```

## 自动配置

模块通过 Maven 依赖自动引入 Selenium WebDriver 和 WebDriverManager，无需额外配置即可使用。

## 依赖组件

- Selenium Java: https://www.selenium.dev
- WebDriverManager: https://github.com/bonigarcia/webdrivermanager

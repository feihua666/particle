package com.particle.global.crawler.runtime;

import com.particle.global.crawler.action.browser.BrowserOpenAction;
import com.particle.global.crawler.action.extract.ExtractTextAction;
import com.particle.global.crawler.action.store.DataStoreAction;
import com.particle.global.crawler.common.enums.BrowserType;
import com.particle.global.crawler.config.CrawlerProperties;
import com.particle.global.crawler.driver.DriverFactory;
import com.particle.global.crawler.pipeline.DefaultPipeline;
import com.particle.global.crawler.runtime.config.BrowserConfig;
import com.particle.global.crawler.runtime.session.CrawlSessionAuthInfoRepository;
import com.particle.global.crawler.runtime.session.CrawlSessionManager;
import com.particle.global.crawler.runtime.session.DefaultCrawlSessionManager;
import com.particle.global.crawler.runtime.session.MemoryCrawlSessionAuthInfoRepository;
import com.particle.global.crawler.storage.data.DataStorage;
import com.particle.global.crawler.storage.data.MemoryDataStorage;
import com.particle.global.crawler.storage.raw.MemoryRawStorage;
import com.particle.global.crawler.storage.raw.RawStorage;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * CrawlRuntime 完整测试 - Selenium Firefox
 */
public class CrawlRuntimeSeleniumFirefoxTest {

    @Test
    public void testSeleniumFirefoxCrawl() {
        // 配置浏览器
        BrowserConfig browserConfig = new BrowserConfig();
        browserConfig.getSelenium().setIsHeadless(false);
        browserConfig.getSelenium().setBrowserType(BrowserType.FIREFOX);
        browserConfig.getSelenium().setIsStealth(true);

        // 创建 Runtime Options
        CrawlRuntimeOptions options = new CrawlRuntimeOptions();
        options.getDeriver().setDriverType(com.particle.global.crawler.common.enums.DriverType.SELENIUM);
        options.setBrowser(browserConfig);

        // 创建 Runtime
        DriverFactory driverFactory = new DriverFactory();
        RuntimeExecutor runtimeExecutor = new RuntimeExecutor();
        CrawlSessionAuthInfoRepository authRepository = new MemoryCrawlSessionAuthInfoRepository();
        CrawlSessionManager sessionManager = new DefaultCrawlSessionManager(authRepository, driverFactory);
        RawStorage rawStorage = new MemoryRawStorage();
        DataStorage dataStorage = new MemoryDataStorage();
        CrawlerProperties properties = new CrawlerProperties();
        DefaultCrawlRuntime runtime = new DefaultCrawlRuntime(driverFactory, runtimeExecutor, sessionManager,rawStorage,dataStorage,properties);

        // 构建 Pipeline
        DefaultPipeline pipeline = new DefaultPipeline();
        pipeline.setName("Selenium Firefox爬取测试");
        pipeline.setActions(List.of(
                // 1. 导航到页面
                BrowserOpenAction.create("https://www.baidu.com"),

                // 2. 提取标题
                ExtractTextAction.create("title", "pageTitle"),

                // 3. 存储数据
                DataStoreAction.create("pageTitle")
        ));

        // 执行
        CrawlExecuteHandle result = runtime.execute(pipeline, options);

        // 验证
        Map<String, Object> variables = result.getRuntimeContext().getVariables();
        System.out.println("页面标题: " + variables.get("pageTitle"));
    }
}

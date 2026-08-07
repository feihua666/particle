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
 * CrawlRuntime 完整测试 - Playwright Chrome
 * 注意：需要在 IDEA 运行配置中设置环境变量 PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1
 */
public class CrawlRuntimePlaywrightFirefoxTest {

    @Test
    public void testPlaywrightFirefoxCrawl() {

        // 配置浏览器 - 使用 Chrome（更稳定）
        BrowserConfig browserConfig = new BrowserConfig();
        browserConfig.getPlaywright().setIsHeadless(false);
        browserConfig.getPlaywright().setBrowserType(BrowserType.FIREFOX);
        browserConfig.getPlaywright().setIsStealth(true);

        // 创建 Runtime Options
        CrawlRuntimeOptions options = new CrawlRuntimeOptions();
        options.getDeriver().setDriverType(com.particle.global.crawler.common.enums.DriverType.PLAYWRIGHT);
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
        pipeline.setName("Playwright Firefox爬取测试");
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

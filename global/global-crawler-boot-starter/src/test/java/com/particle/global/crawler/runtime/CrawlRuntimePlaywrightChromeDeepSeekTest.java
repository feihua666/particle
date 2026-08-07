package com.particle.global.crawler.runtime;

import com.particle.global.crawler.common.enums.BrowserType;
import com.particle.global.crawler.config.CrawlerProperties;
import com.particle.global.crawler.constants.CrawlTestConstants;
import com.particle.global.crawler.driver.DriverFactory;
import com.particle.global.crawler.pipeline.CrawlPipeline;
import com.particle.global.crawler.pipeline.PipelineBuilder;
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

/**
 * CrawlRuntime 完整测试 - Playwright Chrome
 * 注意：需要在 IDEA 运行配置中设置环境变量 PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1
 */
public class CrawlRuntimePlaywrightChromeDeepSeekTest {

    @Test
    public void testPlaywrightChromeCrawl() {
        // 配置浏览器 - 使用 Chrome（更稳定）
        BrowserConfig browserConfig = new BrowserConfig();
        browserConfig.getPlaywright().setIsHeadless(false);
        browserConfig.getPlaywright().setBrowserType(BrowserType.CHROME);
        browserConfig.getPlaywright().setIsStealth(true);
        browserConfig.getPlaywright().setUserDataDir(CrawlTestConstants.realChromeUserDataDir);
        browserConfig.getPlaywright().setUserDataDirProfile(CrawlTestConstants.realChromeUserDataDirProfile);

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

        CrawlPipeline pipeline1 = PipelineBuilder.create()
                .name("Playwright Chrome 爬取测试")
                .open("https://chat.deepseek.com/")

                .input("textarea[name=\"search\"]", "今天天气")
                .enter(null)
                .delay(1000 * 60)
                .build();

        // 执行
        CrawlExecuteHandle result = runtime.execute(pipeline1, options);

    }
}

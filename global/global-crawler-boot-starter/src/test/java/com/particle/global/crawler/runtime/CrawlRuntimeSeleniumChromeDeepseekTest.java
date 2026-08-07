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
 * CrawlRuntime 完整测试 - Selenium Chrome
 */
public class CrawlRuntimeSeleniumChromeDeepseekTest {

    @Test
    public void testSeleniumChromeCrawl() {

        // 配置浏览器
        BrowserConfig browserConfig = new BrowserConfig();
        browserConfig.getSelenium().setIsHeadless(false);
        browserConfig.getSelenium().setBrowserType(BrowserType.CHROME);
        browserConfig.getSelenium().setIsStealth(true);
        browserConfig.getSelenium().setUserDataDir(CrawlTestConstants.realChromeUserDataDir);
        browserConfig.getPlaywright().setUserDataDirProfile(CrawlTestConstants.realChromeUserDataDirProfile);

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

package com.particle.global.crawler;

import com.particle.global.crawler.config.CrawlerProperties;
import com.particle.global.crawler.driver.DriverFactory;
import com.particle.global.crawler.runtime.CrawlRuntime;
import com.particle.global.crawler.runtime.RuntimeExecutor;
import com.particle.global.crawler.runtime.DefaultCrawlRuntime;
import com.particle.global.crawler.runtime.session.CrawlSessionAuthInfoRepository;
import com.particle.global.crawler.runtime.session.CrawlSessionManager;
import com.particle.global.crawler.runtime.session.DefaultCrawlSessionManager;
import com.particle.global.crawler.runtime.session.MemoryCrawlSessionAuthInfoRepository;
import com.particle.global.crawler.storage.data.DataStorage;
import com.particle.global.crawler.storage.data.MemoryDataStorage;
import com.particle.global.crawler.storage.raw.MemoryRawStorage;
import com.particle.global.crawler.storage.raw.RawStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 爬虫自动配置
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Configuration
@EnableConfigurationProperties(CrawlerProperties.class)
public class CrawlerAutoConfiguration {

    /**
     * Driver 工厂
     */
    @Bean
    @ConditionalOnMissingBean
    public DriverFactory driverFactory() {
        return new DriverFactory();
    }

    /**
     * Pipeline 执行器
     */
    @Bean
    @ConditionalOnMissingBean
    public RuntimeExecutor pipelineExecutor() {
        return new RuntimeExecutor();
    }

    /**
     * Session 存储
     */
    @Bean
    @ConditionalOnMissingBean
    public CrawlSessionAuthInfoRepository crawlSessionAuthInfoRepository() {
        return new MemoryCrawlSessionAuthInfoRepository();
    }
    @Bean
    @ConditionalOnMissingBean
    public RawStorage rawStorage() {
        return new MemoryRawStorage();
    }
    @Bean
    @ConditionalOnMissingBean
    public DataStorage dataStorage() {
        return new MemoryDataStorage();
    }
    /**
     * Session 管理
     */
    @Bean
    @ConditionalOnMissingBean
    public CrawlSessionManager crawlSessionManager(CrawlSessionAuthInfoRepository crawlSessionAuthInfoRepository,
                                                   DriverFactory driverFactory) {
        return new DefaultCrawlSessionManager(crawlSessionAuthInfoRepository,driverFactory);
    }

    /**
     * 爬虫运行时（核心执行引擎）
     */
    @Bean
    @ConditionalOnMissingBean
    public CrawlRuntime crawlRuntime(DriverFactory driverFactory,
                                     RuntimeExecutor runtimeExecutor,
                                     CrawlSessionManager crawlSessionManager,
                                     RawStorage rawStorage,
                                     DataStorage dataStorage,
                                     CrawlerProperties properties) {
        return new DefaultCrawlRuntime(driverFactory,
                runtimeExecutor,
                crawlSessionManager,
                rawStorage,
                 dataStorage, properties);
    }
}

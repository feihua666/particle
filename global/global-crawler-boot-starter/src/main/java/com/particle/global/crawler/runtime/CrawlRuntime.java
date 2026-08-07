package com.particle.global.crawler.runtime;

import com.particle.global.crawler.config.CrawlerProperties;
import com.particle.global.crawler.pipeline.CrawlPipeline;

/**
 * 爬虫运行时接口
 * <p>
 * 定义爬虫执行的核心契约
 * </p>
 * @author yangwei
 * @since 2026-05-12 12:58:27
 */
public interface CrawlRuntime {

    /**
     * 执行 Pipeline
     *
     * @param pipeline Pipeline
     * @return 执行结果
     */
    CrawlExecuteHandle execute(CrawlPipeline pipeline);

    /**
     * 执行 Pipeline（带自定义选项）
     *
     * @param pipeline Pipeline
     * @param options  运行时选项
     * @return 执行结果
     */
    CrawlExecuteHandle execute(CrawlPipeline pipeline, CrawlRuntimeOptions options);

    /**
     * 获取配置属性
     *
     * @return 配置属性
     */
    CrawlerProperties getProperties();
}

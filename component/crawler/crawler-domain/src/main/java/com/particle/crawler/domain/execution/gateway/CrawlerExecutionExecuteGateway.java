package com.particle.crawler.domain.execution.gateway;

import com.particle.common.domain.gateway.IGateway;
import com.particle.crawler.domain.execution.value.ExecutionCrawlerResultDTO;

import java.util.Map;

/**
 * <p>
 * 爬虫执行 防腐层
 * </p>
 *
 * @author yw
 * @since 2026-05-12 16:43:06
 */
public interface CrawlerExecutionExecuteGateway extends IGateway {

    /**
     * 执行爬虫
     *
     * @param pipelineJson
     * @param variables 初始的参数
     * @param crawlRuntimeOptionsJson 运行时的选项配置，参考对象 {@link com.particle.global.crawler.runtime.CrawlRuntimeOptions}
     */
    ExecutionCrawlerResultDTO executeCrawler(String pipelineJson,
                                             Map<String,Object> variables,
                                             String crawlRuntimeOptionsJson);
}

package com.particle.crawler.infrastructure.execution.gateway.impl;

import com.particle.crawler.domain.enums.CrawlerExecutionStatus;
import com.particle.crawler.domain.execution.gateway.CrawlerExecutionExecuteGateway;
import com.particle.crawler.domain.execution.value.ExecutionCrawlerResultDTO;
import com.particle.global.crawler.pipeline.CrawlPipeline;
import com.particle.global.crawler.pipeline.PipelineBuilder;
import com.particle.global.crawler.runtime.CrawlExecuteHandle;
import com.particle.global.crawler.runtime.CrawlRuntime;
import com.particle.global.crawler.runtime.CrawlRuntimeOptions;
import com.particle.global.tool.json.JsonTool;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 * 爬虫执行 防腐层网关实现
 * </p>
 *
 * @author yangwei
 * @since 2026/5/12 16:45
 */
@Component
public class CrawlerExecutionExecuteGatewayImpl implements CrawlerExecutionExecuteGateway {

    private CrawlRuntime crawlRuntime;

    @SneakyThrows
    @Override
    public ExecutionCrawlerResultDTO executeCrawler(String pipelineJson, Map<String,Object> variables,String crawlRuntimeOptionsJson) {
        CrawlPipeline crawlPipeline = PipelineBuilder.fromJson(pipelineJson);
        CrawlRuntimeOptions crawlRuntimeOptions = CrawlRuntimeOptions.defaultOptions();
        if (crawlRuntimeOptionsJson != null && !crawlRuntimeOptionsJson.isEmpty()) {
            CrawlRuntimeOptions crawlRuntimeOptionsConfig = JsonTool.getObjectMapper().readValue(crawlRuntimeOptionsJson, CrawlRuntimeOptions.class);
            crawlRuntimeOptions = crawlRuntimeOptionsConfig.merge(crawlRuntimeOptions);
        }
        // 设置初始参数
        crawlPipeline.variables(variables);
        CrawlExecuteHandle crawlExecuteHandle = crawlRuntime.execute(crawlPipeline,crawlRuntimeOptions);

        Map<String, Object> resultData = crawlExecuteHandle.getResultData();
        CrawlerExecutionStatus crawlerExecutionStatus = CrawlerExecutionStatus.valueOf(crawlExecuteHandle.getStatus().name());

        return ExecutionCrawlerResultDTO.create(crawlerExecutionStatus, resultData);
    }

    @Autowired
    public void setCrawlRuntime(CrawlRuntime crawlRuntime) {
        this.crawlRuntime = crawlRuntime;
    }
}

package com.particle.crawler.domain.execution.value;

import com.particle.common.domain.ValueObjRoot;
import com.particle.crawler.domain.enums.CrawlerExecutionStatus;
import lombok.Getter;

import java.util.Map;

/**
 * <p>
 * 执行 爬虫的返回结果
 * </p>
 *
 * @author yangwei
 * @since 2026/5/13 15:30
 */
@Getter
public class ExecutionCrawlerResultDTO extends ValueObjRoot {

    private CrawlerExecutionStatus executionStatus;

    private Map<String,Object> resultData;


    public static ExecutionCrawlerResultDTO create(CrawlerExecutionStatus executionStatus, Map<String,Object> resultData) {
        ExecutionCrawlerResultDTO executionCrawlerResultDTO = new ExecutionCrawlerResultDTO();
        executionCrawlerResultDTO.executionStatus = executionStatus;
        executionCrawlerResultDTO.resultData = resultData;
        return executionCrawlerResultDTO;
    }
}

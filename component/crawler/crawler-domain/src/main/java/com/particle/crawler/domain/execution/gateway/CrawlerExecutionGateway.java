package com.particle.crawler.domain.execution.gateway;

import com.particle.crawler.domain.execution.CrawlerExecution;
import com.particle.crawler.domain.execution.CrawlerExecutionId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 爬虫执行实例 防腐层
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
public interface CrawlerExecutionGateway extends IBaseGateway<CrawlerExecutionId,CrawlerExecution> {
}

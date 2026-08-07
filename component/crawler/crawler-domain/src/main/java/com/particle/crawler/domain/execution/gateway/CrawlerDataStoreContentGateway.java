package com.particle.crawler.domain.execution.gateway;

import com.particle.crawler.domain.execution.CrawlerDataStoreContent;
import com.particle.crawler.domain.execution.CrawlerDataStoreContentId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 爬虫结构数据存储内容 防腐层
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
public interface CrawlerDataStoreContentGateway extends IBaseGateway<CrawlerDataStoreContentId,CrawlerDataStoreContent> {
}

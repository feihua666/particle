package com.particle.crawler.domain.execution.gateway;

import com.particle.crawler.domain.execution.CrawlerRawStoreContent;
import com.particle.crawler.domain.execution.CrawlerRawStoreContentId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 爬虫原始数据存储内容 防腐层
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
public interface CrawlerRawStoreContentGateway extends IBaseGateway<CrawlerRawStoreContentId,CrawlerRawStoreContent> {
}

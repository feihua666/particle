package com.particle.crawler.domain.execution.gateway;

import com.particle.crawler.domain.execution.CrawlerRawStore;
import com.particle.crawler.domain.execution.CrawlerRawStoreId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 爬虫原始数据存储 防腐层
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
public interface CrawlerRawStoreGateway extends IBaseGateway<CrawlerRawStoreId,CrawlerRawStore> {
}

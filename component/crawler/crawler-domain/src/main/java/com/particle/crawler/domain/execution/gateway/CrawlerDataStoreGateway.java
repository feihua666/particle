package com.particle.crawler.domain.execution.gateway;

import com.particle.crawler.domain.execution.CrawlerDataStore;
import com.particle.crawler.domain.execution.CrawlerDataStoreId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 爬虫结构数据存储 防腐层
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
public interface CrawlerDataStoreGateway extends IBaseGateway<CrawlerDataStoreId,CrawlerDataStore> {

}

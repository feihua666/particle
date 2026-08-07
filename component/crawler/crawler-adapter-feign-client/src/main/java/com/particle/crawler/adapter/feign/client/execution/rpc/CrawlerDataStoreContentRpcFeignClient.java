package com.particle.crawler.adapter.feign.client.execution.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 爬虫结构数据存储内容远程调用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@FeignClient(name = "${particle.feign-client.name.crawler:crawler}",path = "/rpc/crawler_data_store_content")
public interface CrawlerDataStoreContentRpcFeignClient {









}

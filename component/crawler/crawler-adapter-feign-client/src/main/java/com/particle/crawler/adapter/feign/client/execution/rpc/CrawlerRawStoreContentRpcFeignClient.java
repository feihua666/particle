package com.particle.crawler.adapter.feign.client.execution.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 爬虫原始数据存储内容远程调用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@FeignClient(name = "${particle.feign-client.name.crawler:crawler}",path = "/rpc/crawler_raw_store_content")
public interface CrawlerRawStoreContentRpcFeignClient {









}

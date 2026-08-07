package com.particle.crawler.adapter.feign.client.definition.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 爬虫定义历史远程调用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@FeignClient(name = "${particle.feign-client.name.crawler:crawler}",path = "/rpc/crawler_definition_history")
public interface CrawlerDefinitionHistoryRpcFeignClient {









}

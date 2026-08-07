package com.particle.crawler.adapter.feign.client.definition.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 爬虫定义远程调用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@FeignClient(name = "${particle.feign-client.name.crawler:crawler}",path = "/rpc/crawler_definition")
public interface CrawlerDefinitionRpcFeignClient {









}

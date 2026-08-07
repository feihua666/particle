package com.particle.crawler.adapter.feign.client.definition.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 爬虫项目远程调用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@FeignClient(name = "${particle.feign-client.name.crawler:crawler}",path = "/rpc/crawler_project")
public interface CrawlerProjectRpcFeignClient {









}

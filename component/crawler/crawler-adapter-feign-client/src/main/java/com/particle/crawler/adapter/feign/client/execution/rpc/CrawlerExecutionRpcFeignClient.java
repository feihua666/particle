package com.particle.crawler.adapter.feign.client.execution.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 爬虫执行实例远程调用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@FeignClient(name = "${particle.feign-client.name.crawler:crawler}",path = "/rpc/crawler_execution")
public interface CrawlerExecutionRpcFeignClient {









}

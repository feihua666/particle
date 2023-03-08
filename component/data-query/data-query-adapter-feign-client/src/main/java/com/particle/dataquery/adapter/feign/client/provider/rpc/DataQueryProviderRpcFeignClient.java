package com.particle.dataquery.adapter.feign.client.provider.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 数据查询供应商远程调用
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@FeignClient(name = "${particle.feign-client.name.data-query:data-query}",path = "/rpc/data_query_provider")
public interface DataQueryProviderRpcFeignClient {









}

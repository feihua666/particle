package com.particle.dataquery.adapter.feign.client.dataapi.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 数据查询数据接口远程调用
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@FeignClient(name = "${particle.feign-client.name.data-query:data-query}",path = "/rpc/data_query_data_api")
public interface DataQueryDataApiRpcFeignClient {









}

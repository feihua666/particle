package com.particle.dataquery.adapter.feign.client.datasource.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 数据查询数据源远程调用
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@FeignClient(name = "${particle.feign-client.name.data-query:data-query}",path = "/rpc/data_query_datasource")
public interface DataQueryDatasourceRpcFeignClient {









}

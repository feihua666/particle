package com.particle.dataquery.adapter.feign.client.datasource.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 数据查询数据源接口远程调用
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@FeignClient(name = "${particle.feign-client.name.data-query:data-query}",path = "/rpc/data_query_datasource_api")
public interface DataQueryDatasourceApiRpcFeignClient {









}

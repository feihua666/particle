package com.particle.openplatform.adapter.feign.client.openapi.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放接口批量查询记录远程调用
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_openapi_batch_query_record")
public interface OpenplatformOpenapiBatchQueryRecordRpcFeignClient {









}

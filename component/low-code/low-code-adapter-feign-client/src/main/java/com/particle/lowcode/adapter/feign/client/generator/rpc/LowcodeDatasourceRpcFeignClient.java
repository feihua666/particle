package com.particle.lowcode.adapter.feign.client.generator.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 低代码数据源远程调用
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@FeignClient(name = "${particle.feign-client.name.low-code:low-code}",path = "/rpc/lowcode-datasource")
public interface LowcodeDatasourceRpcFeignClient {









}

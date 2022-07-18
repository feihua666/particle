package com.particle.area.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 区域远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-14
 */
@FeignClient(name = "${particle.feign-client.name.area:area}",path = "/rpc")
public interface AreaRpcFeignClient {

}

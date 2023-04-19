package com.particle.func.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * <p>
 * 菜单功能远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@FeignClient(name = "${particle.feign-client.name.func:func}",path = "/rpc/func")
public interface FuncRpcFeignClient {






}

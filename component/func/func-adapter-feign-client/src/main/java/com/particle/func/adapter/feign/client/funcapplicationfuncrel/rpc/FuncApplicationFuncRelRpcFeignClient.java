package com.particle.func.adapter.feign.client.funcapplicationfuncrel.rpc;

import com.particle.global.dto.response.MultiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 功能应用功能关系远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@FeignClient(name = "${particle.feign-client.func.name:func-start}", contextId = "funcApplicationFuncRelRpcFeignClient", url = "${particle.feign-client.func.url:}", path = "/rpc/func_application_func_rel")
public interface FuncApplicationFuncRelRpcFeignClient {


	/**
	 * 获取应用下的功能菜单id
	 * @param funcApplicationId
	 * @return
	 */
	@GetMapping("/getFuncIdsByFuncApplicationId")
	public MultiResponse<Long> getFuncIdsByFuncApplicationId(@RequestParam Long funcApplicationId);








}

package com.particle.func.adapter.application.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.func.adapter.feign.client.application.rpc.FuncApplicationRpcFeignClient;
import com.particle.func.client.application.api.IFuncApplicationApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能应用远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Tag(name = "功能应用远程调用相关接口")
@RestController
@RequestMapping("/rpc/func_application")
public class FuncApplicationRpcController extends AbstractBaseRpcAdapter implements FuncApplicationRpcFeignClient  {

	@Autowired
	private IFuncApplicationApplicationService iFuncApplicationApplicationService;


}

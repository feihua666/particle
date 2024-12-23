package com.particle.lowcode.adapter.generator.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.lowcode.adapter.feign.client.generator.rpc.LowcodeModelItemRpcFeignClient;
import com.particle.lowcode.client.generator.api.ILowcodeModelItemApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码模型项目远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Tag(name = "低代码模型项目远程调用相关接口")
@RestController
@RequestMapping("/rpc/lowcode-model-item")
public class LowcodeModelItemRpcController extends AbstractBaseRpcAdapter implements LowcodeModelItemRpcFeignClient {

	@Autowired
	private ILowcodeModelItemApplicationService iLowcodeModelItemApplicationService;









}

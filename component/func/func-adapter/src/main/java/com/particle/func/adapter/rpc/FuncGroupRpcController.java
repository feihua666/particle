package com.particle.func.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.func.adapter.feign.client.rpc.FuncGroupRpcFeignClient;
import com.particle.func.client.api.IFuncGroupApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能组远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Tag(name = "功能组远程调用相关接口")
@RestController
@RequestMapping("/rpc/func-group")
public class FuncGroupRpcController extends AbstractBaseRpcAdapter implements FuncGroupRpcFeignClient {

	@Autowired
	private IFuncGroupApplicationService iFuncGroupApplicationService;









}

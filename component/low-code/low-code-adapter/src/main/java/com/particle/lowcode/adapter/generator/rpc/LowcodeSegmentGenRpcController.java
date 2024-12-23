package com.particle.lowcode.adapter.generator.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.lowcode.adapter.feign.client.generator.rpc.LowcodeSegmentGenRpcFeignClient;
import com.particle.lowcode.client.generator.api.ILowcodeSegmentGenApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码生成远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Tag(name = "低代码生成远程调用相关接口")
@RestController
@RequestMapping("/rpc/lowcode-segment-gen")
public class LowcodeSegmentGenRpcController extends AbstractBaseRpcAdapter implements LowcodeSegmentGenRpcFeignClient {

	@Autowired
	private ILowcodeSegmentGenApplicationService iLowcodeSegmentGenApplicationService;









}

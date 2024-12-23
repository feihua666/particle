package com.particle.lowcode.adapter.generator.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.lowcode.adapter.feign.client.generator.rpc.LowcodeSegmentTemplateRpcFeignClient;
import com.particle.lowcode.client.generator.api.ILowcodeSegmentTemplateApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码片段模板远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Tag(name = "低代码片段模板远程调用相关接口")
@RestController
@RequestMapping("/rpc/lowcode-segment-template")
public class LowcodeSegmentTemplateRpcController extends AbstractBaseRpcAdapter implements LowcodeSegmentTemplateRpcFeignClient {

	@Autowired
	private ILowcodeSegmentTemplateApplicationService iLowcodeSegmentTemplateApplicationService;









}

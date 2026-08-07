package com.particle.agi.adapter.model.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.agi.client.model.api.IAgiAiModelApplicationService;
import com.particle.agi.adapter.feign.client.model.rpc.AgiAiModelRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * AI模型远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Tag(name = "AI模型远程调用相关接口")
@RestController
@RequestMapping("/rpc/agi_ai_model")
public class AgiAiModelRpcController extends AbstractBaseRpcAdapter implements AgiAiModelRpcFeignClient  {

	@Autowired
	private IAgiAiModelApplicationService iAgiAiModelApplicationService;


}

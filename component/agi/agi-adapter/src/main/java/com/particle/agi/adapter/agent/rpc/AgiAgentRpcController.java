package com.particle.agi.adapter.agent.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.agi.client.agent.api.IAgiAgentApplicationService;
import com.particle.agi.adapter.feign.client.agent.rpc.AgiAgentRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 智能体远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Tag(name = "智能体远程调用相关接口")
@RestController
@RequestMapping("/rpc/agi_agent")
public class AgiAgentRpcController extends AbstractBaseRpcAdapter implements AgiAgentRpcFeignClient  {

	@Autowired
	private IAgiAgentApplicationService iAgiAgentApplicationService;


}
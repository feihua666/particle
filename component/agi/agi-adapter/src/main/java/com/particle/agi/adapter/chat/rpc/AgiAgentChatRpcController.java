package com.particle.agi.adapter.chat.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.agi.client.chat.api.IAgiAgentChatApplicationService;
import com.particle.agi.adapter.feign.client.chat.rpc.AgiAgentChatRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 智能体对话远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Tag(name = "智能体对话远程调用相关接口")
@RestController
@RequestMapping("/rpc/agi_agent_chat")
public class AgiAgentChatRpcController extends AbstractBaseRpcAdapter implements AgiAgentChatRpcFeignClient  {

	@Autowired
	private IAgiAgentChatApplicationService iAgiAgentChatApplicationService;


}
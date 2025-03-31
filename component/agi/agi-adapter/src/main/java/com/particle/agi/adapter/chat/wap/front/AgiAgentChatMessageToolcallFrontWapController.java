package com.particle.agi.adapter.chat.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.agi.client.chat.api.IAgiAgentChatMessageToolcallApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 智能体对话消息工具调用前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Tag(name = "智能体对话消息工具调用wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/agi_agent_chat_message_toolcall")
public class AgiAgentChatMessageToolcallFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IAgiAgentChatMessageToolcallApplicationService iAgiAgentChatMessageToolcallApplicationService;


}
package com.particle.agi.adapter.chat.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.agi.client.chat.api.IAgiAgentChatMessageMediaApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 智能体对话消息媒体前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Tag(name = "智能体对话消息媒体wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/agi_agent_chat_message_media")
public class AgiAgentChatMessageMediaFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IAgiAgentChatMessageMediaApplicationService iAgiAgentChatMessageMediaApplicationService;


}
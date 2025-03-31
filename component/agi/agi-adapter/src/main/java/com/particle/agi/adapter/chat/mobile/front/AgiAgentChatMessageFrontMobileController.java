package com.particle.agi.adapter.chat.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.agi.client.chat.api.IAgiAgentChatMessageApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 智能体对话消息前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Tag(name = "智能体对话消息移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/agi_agent_chat_message")
public class AgiAgentChatMessageFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IAgiAgentChatMessageApplicationService iAgiAgentChatMessageApplicationService;


}
package com.particle.agi.adapter.chat.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.agi.client.chat.api.IAgiAgentChatMessageMediaApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 智能体对话消息媒体后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Tag(name = "智能体对话消息媒体移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/agi_agent_chat_message_media")
public class AgiAgentChatMessageMediaAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IAgiAgentChatMessageMediaApplicationService iAgiAgentChatMessageMediaApplicationService;


}
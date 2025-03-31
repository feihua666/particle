package com.particle.agi.adapter.chat.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.agi.client.chat.api.IAgiAgentChatApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 智能体对话后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Tag(name = "智能体对话移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/agi_agent_chat")
public class AgiAgentChatAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IAgiAgentChatApplicationService iAgiAgentChatApplicationService;


}
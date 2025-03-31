package com.particle.agi.adapter.chat.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.agi.client.chat.api.IAgiAgentChatApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 智能体对话前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Tag(name = "智能体对话wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/agi_agent_chat")
public class AgiAgentChatFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IAgiAgentChatApplicationService iAgiAgentChatApplicationService;


}
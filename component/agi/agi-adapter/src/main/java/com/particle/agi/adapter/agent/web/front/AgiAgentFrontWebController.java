package com.particle.agi.adapter.agent.web.front;

import com.particle.agi.client.agent.api.representation.IAgiAgentRepresentationApplicationService;
import com.particle.agi.client.agent.dto.command.AgiAgentChatCommand;
import com.particle.agi.client.agent.dto.data.AgiAgentChatResponseVO;
import com.particle.agi.client.agent.dto.data.AgiAgentVO;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.agi.client.agent.api.IAgiAgentApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * <p>
 * 智能体前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Tag(name = "智能体pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/agi_agent")
public class AgiAgentFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IAgiAgentApplicationService iAgiAgentApplicationService;
	@Autowired
	private IAgiAgentRepresentationApplicationService iAgiAgentRepresentationApplicationService;


	@PreAuthorize("hasAuthority('front:web:agiAgent:detail')")
	@Operation(summary = "智能体详情展示")
	@GetMapping("/detail")
	public SingleResponse<AgiAgentVO> queryDetail(IdCommand detailCommand){
		return iAgiAgentRepresentationApplicationService.queryDetail(detailCommand);
	}


	@PreAuthorize("hasAuthority('front:web:agiAgent:chatStream')")
	@Operation(summary = "智能体流式对话")
	@PostMapping(value = "/chatStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<SingleResponse<AgiAgentChatResponseVO>> chatStream(@RequestBody AgiAgentChatCommand agiAgentChatCommand){
		Flux<SingleResponse<AgiAgentChatResponseVO>> chat = iAgiAgentApplicationService.chatStream(agiAgentChatCommand);
		return chat;
	}
}

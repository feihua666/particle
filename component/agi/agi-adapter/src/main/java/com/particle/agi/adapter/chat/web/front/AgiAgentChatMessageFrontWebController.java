package com.particle.agi.adapter.chat.web.front;

import com.particle.agi.client.chat.api.representation.IAgiAgentChatMessageRepresentationApplicationService;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessagePageQueryCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageVO;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.agi.client.chat.api.IAgiAgentChatMessageApplicationService;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 智能体对话消息前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Tag(name = "智能体对话消息pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/agi_agent_chat_message")
public class AgiAgentChatMessageFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IAgiAgentChatMessageApplicationService iAgiAgentChatMessageApplicationService;
	@Autowired
	private IAgiAgentChatMessageRepresentationApplicationService iAgiAgentChatMessageRepresentationApplicationService;

	@PreAuthorize("hasAuthority('front:web:agiAgentChatMessage:pageQuery')")
	@Operation(summary = "分页查询智能体对话消息")
	@GetMapping("/page")
	public PageResponse<AgiAgentChatMessageVO> pageQueryList(AgiAgentChatMessagePageQueryCommand agiAgentChatMessagePageQueryCommand){
		agiAgentChatMessagePageQueryCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.query.name());
		return iAgiAgentChatMessageRepresentationApplicationService.pageQuery(agiAgentChatMessagePageQueryCommand);
	}
}

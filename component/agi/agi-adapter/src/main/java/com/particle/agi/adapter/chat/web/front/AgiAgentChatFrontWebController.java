package com.particle.agi.adapter.chat.web.front;

import com.particle.agi.client.chat.api.representation.IAgiAgentChatRepresentationApplicationService;
import com.particle.agi.client.chat.dto.command.AgiAgentChatUpdateCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatPageQueryCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatVO;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.agi.client.chat.api.IAgiAgentChatApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 智能体对话前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Tag(name = "智能体对话pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/agi_agent_chat")
public class AgiAgentChatFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IAgiAgentChatApplicationService iAgiAgentChatApplicationService;
	@Autowired
	private IAgiAgentChatRepresentationApplicationService iAgiAgentChatRepresentationApplicationService;

	@PreAuthorize("hasAuthority('front:web:agiAgentChat:delete')")
	@Operation(summary = "删除智能体对话")
	@DeleteMapping("/delete")
	@OpLog(name = "删除智能体对话",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.delete)
	public SingleResponse<AgiAgentChatVO> delete(@RequestBody IdCommand deleteCommand){
		deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
		return iAgiAgentChatApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('front:web:agiAgentChat:update')")
	@Operation(summary = "更新智能体对话")
	@PutMapping("/update")
	@OpLog(name = "更新智能体对话",module = OpLogConstants.Module.agi,type = OpLogConstants.Type.update)
	public SingleResponse<AgiAgentChatVO> update(@RequestBody AgiAgentChatUpdateCommand agiAgentChatUpdateCommand){
		agiAgentChatUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
		return iAgiAgentChatApplicationService.update(agiAgentChatUpdateCommand);
	}

	@PreAuthorize("hasAuthority('front:web:agiAgentChat:update')")
	@Operation(summary = "智能体对话更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<AgiAgentChatVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iAgiAgentChatRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}
	@PreAuthorize("hasAuthority('front:web:agiAgentChat:pageQuery')")
	@Operation(summary = "分页查询智能体对话")
	@GetMapping("/page")
	public PageResponse<AgiAgentChatVO> pageQueryList(AgiAgentChatPageQueryCommand agiAgentChatPageQueryCommand){
		agiAgentChatPageQueryCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.query.name());
		return iAgiAgentChatRepresentationApplicationService.pageQuery(agiAgentChatPageQueryCommand);
	}
}

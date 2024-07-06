package com.particle.message.adapter.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.message.client.api.IMessageTemplateApplicationService;
import com.particle.message.client.api.representation.IMessageTemplateRepresentationApplicationService;
import com.particle.message.client.dto.command.MessageTemplateCreateCommand;
import com.particle.message.client.dto.data.MessageTemplateVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.message.client.dto.command.MessageTemplateUpdateCommand;
import com.particle.message.client.dto.command.representation.MessageTemplatePageQueryCommand;
import com.particle.message.client.dto.command.representation.MessageTemplateQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
/**
 * <p>
 * 消息模板后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Tag(name = "消息模板pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/message_template")
public class MessageTemplateAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IMessageTemplateApplicationService iMessageTemplateApplicationService;
	@Autowired
	private IMessageTemplateRepresentationApplicationService iMessageTemplateRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:messageTemplate:create')")
	@Operation(summary = "添加消息模板")
	@PostMapping("/create")
	@OpLog(name = "添加消息模板",module = OpLogConstants.Module.message,type = OpLogConstants.Type.create)
	public SingleResponse<MessageTemplateVO> create(@RequestBody MessageTemplateCreateCommand messageTemplateCreateCommand){
		return iMessageTemplateApplicationService.create(messageTemplateCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:messageTemplate:delete')")
	@Operation(summary = "删除消息模板")
	@DeleteMapping("/delete")
	@OpLog(name = "删除消息模板",module = OpLogConstants.Module.message,type = OpLogConstants.Type.delete)
	public SingleResponse<MessageTemplateVO> delete(@RequestBody IdCommand deleteCommand){
		deleteCommand.dcdo(DataConstraintConstants.data_object_message_message_template, DataConstraintContext.Action.delete.name());
		return iMessageTemplateApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:messageTemplate:update')")
	@Operation(summary = "更新消息模板")
	@PutMapping("/update")
	@OpLog(name = "更新消息模板",module = OpLogConstants.Module.message,type = OpLogConstants.Type.update)
	public SingleResponse<MessageTemplateVO> update(@RequestBody MessageTemplateUpdateCommand messageTemplateUpdateCommand){
		messageTemplateUpdateCommand.dcdo(DataConstraintConstants.data_object_message_message_template,DataConstraintContext.Action.update.name());
		return iMessageTemplateApplicationService.update(messageTemplateUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:messageTemplate:update')")
	@Operation(summary = "消息模板更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<MessageTemplateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iMessageTemplateRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:messageTemplate:detail')")
	@Operation(summary = "消息模板详情展示")
	@GetMapping("/detail")
	public SingleResponse<MessageTemplateVO> queryDetail(IdCommand detailCommand){
		return iMessageTemplateRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:messageTemplate:queryList')")
	@Operation(summary = "列表查询消息模板")
	@GetMapping("/list")
	public MultiResponse<MessageTemplateVO> queryList(MessageTemplateQueryListCommand messageTemplateQueryListCommand){
		messageTemplateQueryListCommand.dcdo(DataConstraintConstants.data_object_message_message_template,DataConstraintContext.Action.query.name());
		return iMessageTemplateRepresentationApplicationService.queryList(messageTemplateQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:messageTemplate:pageQuery')")
	@Operation(summary = "分页查询消息模板")
	@GetMapping("/page")
	public PageResponse<MessageTemplateVO> pageQueryList(MessageTemplatePageQueryCommand messageTemplatePageQueryCommand){
		messageTemplatePageQueryCommand.dcdo(DataConstraintConstants.data_object_message_message_template,DataConstraintContext.Action.query.name());
		return iMessageTemplateRepresentationApplicationService.pageQuery(messageTemplatePageQueryCommand);
	}

}
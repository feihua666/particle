package com.particle.message.adapter.web.admin;

import com.particle.message.client.api.IMessageApplicationService;
import com.particle.message.client.api.representation.IMessageRepresentationApplicationService;
import com.particle.message.client.dto.command.MessageCreateCommand;
import com.particle.message.client.dto.data.MessageVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.message.client.dto.command.MessageUpdateCommand;
import com.particle.message.client.dto.command.representation.MessagePageQueryCommand;
import com.particle.message.client.dto.command.representation.MessageQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * 消息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Api(tags = "消息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/message")
public class MessageAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IMessageApplicationService iMessageApplicationService;
	@Autowired
	private IMessageRepresentationApplicationService iMessageRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:message:create')")
	@ApiOperation("添加消息")
	@PostMapping("/create")
	@OpLog(name = "添加消息",module = OpLogConstants.Module.message,type = OpLogConstants.Type.create)
	public SingleResponse<MessageVO> create(@RequestBody MessageCreateCommand messageCreateCommand){
		return iMessageApplicationService.create(messageCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:message:delete')")
	@ApiOperation("删除消息")
	@DeleteMapping("/delete")
	@OpLog(name = "删除消息",module = OpLogConstants.Module.message,type = OpLogConstants.Type.delete)
	public SingleResponse<MessageVO> delete(@RequestBody IdCommand deleteCommand){
		return iMessageApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:message:update')")
	@ApiOperation("更新消息")
	@PutMapping("/update")
	@OpLog(name = "更新消息",module = OpLogConstants.Module.message,type = OpLogConstants.Type.update)
	public SingleResponse<MessageVO> update(@RequestBody MessageUpdateCommand messageUpdateCommand){
		return iMessageApplicationService.update(messageUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:message:update')")
	@ApiOperation("消息更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<MessageVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iMessageRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:message:detail')")
	@ApiOperation("消息详情展示")
	@GetMapping("/detail")
	public SingleResponse<MessageVO> queryDetail(IdCommand detailCommand){
		return iMessageRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:message:queryList')")
	@ApiOperation("列表查询消息")
	@GetMapping("/list")
	public MultiResponse<MessageVO> queryList(MessageQueryListCommand messageQueryListCommand){
		return iMessageRepresentationApplicationService.queryList(messageQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:message:pageQuery')")
	@ApiOperation("分页查询消息")
	@GetMapping("/page")
	public PageResponse<MessageVO> pageQueryList(MessagePageQueryCommand messagePageQueryCommand){
		return iMessageRepresentationApplicationService.pageQuery(messagePageQueryCommand);
	}

}
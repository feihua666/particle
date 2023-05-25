package com.particle.message.adapter.web.admin;

import com.particle.message.client.api.IMessageUserStateApplicationService;
import com.particle.message.client.api.representation.IMessageUserStateRepresentationApplicationService;
import com.particle.message.client.dto.command.MessageUserStateCreateCommand;
import com.particle.message.client.dto.data.MessageUserStateVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.message.client.dto.command.MessageUserStateUpdateCommand;
import com.particle.message.client.dto.command.representation.MessageUserStatePageQueryCommand;
import com.particle.message.client.dto.command.representation.MessageUserStateQueryListCommand;
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
 * 用户消息读取状态后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Api(tags = "用户消息读取状态pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/message_user_state")
public class MessageUserStateAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IMessageUserStateApplicationService iMessageUserStateApplicationService;
	@Autowired
	private IMessageUserStateRepresentationApplicationService iMessageUserStateRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:messageUserState:create')")
	@ApiOperation("添加用户消息读取状态")
	@PostMapping("/create")
	@OpLog(name = "添加用户消息读取状态",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
	public SingleResponse<MessageUserStateVO> create(@RequestBody MessageUserStateCreateCommand messageUserStateCreateCommand){
		return iMessageUserStateApplicationService.create(messageUserStateCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:messageUserState:delete')")
	@ApiOperation("删除用户消息读取状态")
	@DeleteMapping("/delete")
	@OpLog(name = "删除用户消息读取状态",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
	public SingleResponse<MessageUserStateVO> delete(@RequestBody IdCommand deleteCommand){
		return iMessageUserStateApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:messageUserState:update')")
	@ApiOperation("更新用户消息读取状态")
	@PutMapping("/update")
	@OpLog(name = "更新用户消息读取状态",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
	public SingleResponse<MessageUserStateVO> update(@RequestBody MessageUserStateUpdateCommand messageUserStateUpdateCommand){
		return iMessageUserStateApplicationService.update(messageUserStateUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:messageUserState:update')")
	@ApiOperation("用户消息读取状态更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<MessageUserStateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iMessageUserStateRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:messageUserState:detail')")
	@ApiOperation("用户消息读取状态详情展示")
	@GetMapping("/detail")
	public SingleResponse<MessageUserStateVO> queryDetail(IdCommand detailCommand){
		return iMessageUserStateRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:messageUserState:queryList')")
	@ApiOperation("列表查询用户消息读取状态")
	@GetMapping("/list")
	public MultiResponse<MessageUserStateVO> queryList(MessageUserStateQueryListCommand messageUserStateQueryListCommand){
		return iMessageUserStateRepresentationApplicationService.queryList(messageUserStateQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:messageUserState:pageQuery')")
	@ApiOperation("分页查询用户消息读取状态")
	@GetMapping("/page")
	public PageResponse<MessageUserStateVO> pageQueryList(MessageUserStatePageQueryCommand messageUserStatePageQueryCommand){
		return iMessageUserStateRepresentationApplicationService.pageQuery(messageUserStatePageQueryCommand);
	}

}
package com.particle.user.adapter.identifier.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.adapter.tool.PasswordTool;
import com.particle.user.client.identifier.api.IUserIdentifierApplicationService;
import com.particle.user.client.identifier.api.representation.IUserIdentifierRepresentationApplicationService;
import com.particle.user.client.identifier.dto.command.UserIdentifierCreateCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierUpdateCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPageQueryCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierQueryListCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 用户登录标识后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "用户登录标识pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/user-identifier")
public class UserIdentifierAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUserIdentifierApplicationService iUserIdentifierApplicationService;
	@Autowired
	private IUserIdentifierRepresentationApplicationService iUserIdentifierRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:userIdentifier:create')")
	@Operation(summary = "添加用户登录标识")
	@PostMapping("/create")
	@OpLog(name = "添加用户登录标识",module = OpLogConstants.Module.user,type = OpLogConstants.Type.create)
	public SingleResponse<UserIdentifierVO> create(@RequestBody UserIdentifierCreateCommand userIdentifierCreateCommand,@RequestBody UserIdentifierPwdCommand userIdentifierPwdCommand){
		PasswordTool.encodePassword(userIdentifierPwdCommand);
		return iUserIdentifierApplicationService.create(userIdentifierCreateCommand, userIdentifierPwdCommand);
	}


	/**
	 * 绑定用户登录标识不添加登录密码，仅做绑定，这在登录时将会使用唯一密码
	 * @param userIdentifierCreateCommand
	 * @return
	 */
	@PreAuthorize("hasAuthority('admin:web:userIdentifier:create')")
	@Operation(summary = "仅绑定用户登录标识")
	@PostMapping("/createBind")
	@OpLog(name = "仅绑定用户登录标识",module = OpLogConstants.Module.user,type = OpLogConstants.Type.create)
	public SingleResponse<UserIdentifierVO> createBind(@RequestBody UserIdentifierCreateCommand userIdentifierCreateCommand){
		return iUserIdentifierApplicationService.createBind(userIdentifierCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifier:delete')")
	@Operation(summary = "删除用户登录标识")
	@DeleteMapping("/delete")
	@OpLog(name = "删除用户登录标识",module = OpLogConstants.Module.user,type = OpLogConstants.Type.delete)
	public SingleResponse<UserIdentifierVO> delete(@RequestBody IdCommand deleteCommand){
		deleteCommand.dcdo(DataConstraintConstants.data_object_user_identifier, DataConstraintContext.Action.delete.name());
		return iUserIdentifierApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifier:update')")
	@Operation(summary = "更新用户登录标识")
	@PutMapping("/update")
	@OpLog(name = "更新用户登录标识",module = OpLogConstants.Module.user,type = OpLogConstants.Type.update)
	public SingleResponse<UserIdentifierVO> update(@RequestBody UserIdentifierUpdateCommand userIdentifierUpdateCommand){
		userIdentifierUpdateCommand.dcdo(DataConstraintConstants.data_object_user_identifier,DataConstraintContext.Action.update.name());
		return iUserIdentifierApplicationService.update(userIdentifierUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifier:update')")
	@Operation(summary = "用户登录标识更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<UserIdentifierVO> queryDetailForUpdate(IdCommand userIdentifierQueryDetailForUpdateCommand){
		return iUserIdentifierRepresentationApplicationService.queryDetailForUpdate(userIdentifierQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifier:detail')")
	@Operation(summary = "用户登录标识详情展示")
	@GetMapping("/detail")
	public SingleResponse<UserIdentifierVO> queryDetail(IdCommand userIdentifierQueryDetailCommand){
		return iUserIdentifierRepresentationApplicationService.queryDetail(userIdentifierQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifier:queryList')")
	@Operation(summary = "列表查询用户登录标识")
	@GetMapping("/list")
	public MultiResponse<UserIdentifierVO> queryList(UserIdentifierQueryListCommand userIdentifierQueryListCommand){
		userIdentifierQueryListCommand.dcdo(DataConstraintConstants.data_object_user_identifier,DataConstraintContext.Action.query.name());
		return iUserIdentifierRepresentationApplicationService.queryList(userIdentifierQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifier:pageQuery')")
	@Operation(summary = "分页查询用户登录标识")
	@GetMapping("/page")
	public PageResponse<UserIdentifierVO> pageQueryList(UserIdentifierPageQueryCommand userIdentifierPageQueryCommand){
		userIdentifierPageQueryCommand.dcdo(DataConstraintConstants.data_object_user_identifier,DataConstraintContext.Action.query.name());
		return iUserIdentifierRepresentationApplicationService.pageQuery(userIdentifierPageQueryCommand);
	}

}

package com.particle.user.adapter.identifier.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "用户登录标识pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/user-identifier")
public class UserIdentifierAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUserIdentifierApplicationService iUserIdentifierApplicationService;
	@Autowired
	private IUserIdentifierRepresentationApplicationService iUserIdentifierRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:userIdentifier:create')")
	@ApiOperation("添加用户登录标识")
	@PostMapping("/create")
	public SingleResponse<UserIdentifierVO> create(@RequestBody UserIdentifierCreateCommand userIdentifierCreateCommand,@RequestBody UserIdentifierPwdCommand userIdentifierPwdCommand){
		PasswordTool.encodePassword(userIdentifierPwdCommand);
		return iUserIdentifierApplicationService.create(userIdentifierCreateCommand, userIdentifierPwdCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifier:delete')")
	@ApiOperation("删除用户登录标识")
	@DeleteMapping("/delete")
	public SingleResponse<UserIdentifierVO> delete(@RequestBody IdCommand userIdentifierDeleteCommand){
		return iUserIdentifierApplicationService.delete(userIdentifierDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifier:update')")
	@ApiOperation("更新用户登录标识")
	@PutMapping("/update")
	public SingleResponse<UserIdentifierVO> update(@RequestBody UserIdentifierUpdateCommand userIdentifierUpdateCommand){
		return iUserIdentifierApplicationService.update(userIdentifierUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifier:update')")
	@ApiOperation("用户登录标识更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<UserIdentifierVO> queryDetailForUpdate(IdCommand userIdentifierQueryDetailForUpdateCommand){
		return iUserIdentifierRepresentationApplicationService.queryDetailForUpdate(userIdentifierQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifier:detail')")
	@ApiOperation("用户登录标识详情展示")
	@GetMapping("/detail")
	public SingleResponse<UserIdentifierVO> queryDetail(IdCommand userIdentifierQueryDetailCommand){
		return iUserIdentifierRepresentationApplicationService.queryDetail(userIdentifierQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifier:queryList')")
	@ApiOperation("列表查询用户登录标识")
	@GetMapping("/list")
	public MultiResponse<UserIdentifierVO> queryList(UserIdentifierQueryListCommand userIdentifierQueryListCommand){
		return iUserIdentifierRepresentationApplicationService.queryList(userIdentifierQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifier:pageQuery')")
	@ApiOperation("分页查询用户登录标识")
	@GetMapping("/page")
	public PageResponse<UserIdentifierVO> pageQueryList(UserIdentifierPageQueryCommand userIdentifierPageQueryCommand){
		return iUserIdentifierRepresentationApplicationService.pageQuery(userIdentifierPageQueryCommand);
	}

}
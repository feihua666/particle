package com.particle.user.adapter.identifier.web.admin;

import com.particle.user.client.identifier.api.IUserIdentifierPwdApplicationService;
import com.particle.user.client.identifier.api.representation.IUserIdentifierPwdRepresentationApplicationService;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCreateCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierPwdVO;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPwdQueryDetailForUpdateCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPwdQueryDetailCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdDeleteCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdUpdateCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPwdPageQueryCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPwdQueryListCommand;
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
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
/**
 * <p>
 * 用户密码后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "用户密码pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/user-identifier-pwd")
public class UserIdentifierPwdAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUserIdentifierPwdApplicationService iUserIdentifierPwdApplicationService;
	@Autowired
	private IUserIdentifierPwdRepresentationApplicationService iUserIdentifierPwdRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:userIdentifierPwd:create')")
	@ApiOperation("添加用户密码")
	@PostMapping("/create")
	public SingleResponse<UserIdentifierPwdVO> create(@RequestBody UserIdentifierPwdCreateCommand userIdentifierPwdCreateCommand){
		return iUserIdentifierPwdApplicationService.create(userIdentifierPwdCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifierPwd:delete')")
	@ApiOperation("删除用户密码")
	@DeleteMapping("/delete")
	public SingleResponse<UserIdentifierPwdVO> delete(@RequestBody UserIdentifierPwdDeleteCommand userIdentifierPwdDeleteCommand){
		return iUserIdentifierPwdApplicationService.delete(userIdentifierPwdDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifierPwd:update')")
	@ApiOperation("更新用户密码")
	@PutMapping("/update")
	public SingleResponse<UserIdentifierPwdVO> update(@RequestBody UserIdentifierPwdUpdateCommand userIdentifierPwdUpdateCommand){
		return iUserIdentifierPwdApplicationService.update(userIdentifierPwdUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifierPwd:update')")
	@ApiOperation("用户密码更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<UserIdentifierPwdVO> queryDetailForUpdate(UserIdentifierPwdQueryDetailForUpdateCommand userIdentifierPwdQueryDetailForUpdateCommand){
		return iUserIdentifierPwdRepresentationApplicationService.queryDetailForUpdate(userIdentifierPwdQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifierPwd:detail')")
	@ApiOperation("用户密码详情展示")
	@GetMapping("/detail")
	public SingleResponse<UserIdentifierPwdVO> queryDetail(UserIdentifierPwdQueryDetailCommand userIdentifierPwdQueryDetailCommand){
		return iUserIdentifierPwdRepresentationApplicationService.queryDetail(userIdentifierPwdQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifierPwd:queryList')")
	@ApiOperation("列表查询用户密码")
	@GetMapping("/list")
	public MultiResponse<UserIdentifierPwdVO> queryList(UserIdentifierPwdQueryListCommand userIdentifierPwdQueryListCommand){
		return iUserIdentifierPwdRepresentationApplicationService.queryList(userIdentifierPwdQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifierPwd:pageQuery')")
	@ApiOperation("分页查询用户密码")
	@GetMapping("/page")
	public PageResponse<UserIdentifierPwdVO> pageQueryList(UserIdentifierPwdPageQueryCommand userIdentifierPwdPageQueryCommand){
		return iUserIdentifierPwdRepresentationApplicationService.pageQuery(userIdentifierPwdPageQueryCommand);
	}

}
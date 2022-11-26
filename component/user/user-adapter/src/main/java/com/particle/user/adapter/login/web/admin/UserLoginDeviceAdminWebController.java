package com.particle.user.adapter.login.web.admin;

import com.particle.user.client.login.api.IUserLoginDeviceApplicationService;
import com.particle.user.client.login.api.representation.IUserLoginDeviceRepresentationApplicationService;
import com.particle.user.client.login.dto.command.UserLoginDeviceCreateCommand;
import com.particle.user.client.login.dto.data.UserLoginDeviceVO;
import com.particle.user.client.login.dto.command.representation.UserLoginDeviceQueryDetailForUpdateCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginDeviceQueryDetailCommand;
import com.particle.user.client.login.dto.command.UserLoginDeviceDeleteCommand;
import com.particle.user.client.login.dto.command.UserLoginDeviceUpdateCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginDevicePageQueryCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginDeviceQueryListCommand;
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
 * 用户登录设备后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Api(tags = "用户登录设备pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/user-login-device")
public class UserLoginDeviceAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUserLoginDeviceApplicationService iUserLoginDeviceApplicationService;
	@Autowired
	private IUserLoginDeviceRepresentationApplicationService iUserLoginDeviceRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:userLoginDevice:create')")
	@ApiOperation("添加用户登录设备")
	@PostMapping("/create")
	public SingleResponse<UserLoginDeviceVO> create(@RequestBody UserLoginDeviceCreateCommand userLoginDeviceCreateCommand){
		return iUserLoginDeviceApplicationService.create(userLoginDeviceCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginDevice:delete')")
	@ApiOperation("删除用户登录设备")
	@DeleteMapping("/delete")
	public SingleResponse<UserLoginDeviceVO> delete(@RequestBody UserLoginDeviceDeleteCommand userLoginDeviceDeleteCommand){
		return iUserLoginDeviceApplicationService.delete(userLoginDeviceDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginDevice:update')")
	@ApiOperation("更新用户登录设备")
	@PutMapping("/update")
	public SingleResponse<UserLoginDeviceVO> update(@RequestBody UserLoginDeviceUpdateCommand userLoginDeviceUpdateCommand){
		return iUserLoginDeviceApplicationService.update(userLoginDeviceUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginDevice:update')")
	@ApiOperation("用户登录设备更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<UserLoginDeviceVO> queryDetailForUpdate(UserLoginDeviceQueryDetailForUpdateCommand userLoginDeviceQueryDetailForUpdateCommand){
		return iUserLoginDeviceRepresentationApplicationService.queryDetailForUpdate(userLoginDeviceQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginDevice:detail')")
	@ApiOperation("用户登录设备详情展示")
	@GetMapping("/detail")
	public SingleResponse<UserLoginDeviceVO> queryDetail(UserLoginDeviceQueryDetailCommand userLoginDeviceQueryDetailCommand){
		return iUserLoginDeviceRepresentationApplicationService.queryDetail(userLoginDeviceQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginDevice:queryList')")
	@ApiOperation("列表查询用户登录设备")
	@GetMapping("/list")
	public MultiResponse<UserLoginDeviceVO> queryList(UserLoginDeviceQueryListCommand userLoginDeviceQueryListCommand){
		return iUserLoginDeviceRepresentationApplicationService.queryList(userLoginDeviceQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginDevice:pageQuery')")
	@ApiOperation("分页查询用户登录设备")
	@GetMapping("/page")
	public PageResponse<UserLoginDeviceVO> pageQueryList(UserLoginDevicePageQueryCommand userLoginDevicePageQueryCommand){
		return iUserLoginDeviceRepresentationApplicationService.pageQuery(userLoginDevicePageQueryCommand);
	}

}
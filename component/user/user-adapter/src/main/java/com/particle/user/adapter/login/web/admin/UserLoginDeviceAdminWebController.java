package com.particle.user.adapter.login.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.login.api.IUserLoginDeviceApplicationService;
import com.particle.user.client.login.api.representation.IUserLoginDeviceRepresentationApplicationService;
import com.particle.user.client.login.dto.command.UserLoginDeviceDeleteCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginDevicePageQueryCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginDeviceQueryDetailCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginDeviceQueryListCommand;
import com.particle.user.client.login.dto.data.UserLoginDeviceVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 用户登录设备后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Tag(name = "用户登录设备pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/user-login-device")
public class UserLoginDeviceAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUserLoginDeviceApplicationService iUserLoginDeviceApplicationService;
	@Autowired
	private IUserLoginDeviceRepresentationApplicationService iUserLoginDeviceRepresentationApplicationService;


	@PreAuthorize("hasAuthority('admin:web:userLoginDevice:delete')")
	@Operation(summary = "删除用户登录设备")
	@DeleteMapping("/delete")
	@OpLog(name = "删除用户登录设备",module = OpLogConstants.Module.user,type = OpLogConstants.Type.delete)
	public SingleResponse<UserLoginDeviceVO> delete(@RequestBody UserLoginDeviceDeleteCommand userLoginDeviceDeleteCommand){
		return iUserLoginDeviceApplicationService.delete(userLoginDeviceDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginDevice:detail')")
	@Operation(summary = "用户登录设备详情展示")
	@GetMapping("/detail")
	public SingleResponse<UserLoginDeviceVO> queryDetail(UserLoginDeviceQueryDetailCommand userLoginDeviceQueryDetailCommand){
		return iUserLoginDeviceRepresentationApplicationService.queryDetail(userLoginDeviceQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginDevice:queryList')")
	@Operation(summary = "列表查询用户登录设备")
	@GetMapping("/list")
	public MultiResponse<UserLoginDeviceVO> queryList(UserLoginDeviceQueryListCommand userLoginDeviceQueryListCommand){
		return iUserLoginDeviceRepresentationApplicationService.queryList(userLoginDeviceQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginDevice:pageQuery')")
	@Operation(summary = "分页查询用户登录设备")
	@GetMapping("/page")
	public PageResponse<UserLoginDeviceVO> pageQueryList(UserLoginDevicePageQueryCommand userLoginDevicePageQueryCommand){
		return iUserLoginDeviceRepresentationApplicationService.pageQuery(userLoginDevicePageQueryCommand);
	}

}

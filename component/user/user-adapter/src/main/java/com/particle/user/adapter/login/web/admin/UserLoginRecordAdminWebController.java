package com.particle.user.adapter.login.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.login.api.IUserLoginRecordApplicationService;
import com.particle.user.client.login.api.representation.IUserLoginRecordRepresentationApplicationService;
import com.particle.user.client.login.dto.command.UserLoginRecordDeleteCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordPageQueryCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordQueryDetailCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordQueryListCommand;
import com.particle.user.client.login.dto.data.UserLoginRecordVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 用户登录记录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Tag(name = "用户登录记录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/user-login-record")
public class UserLoginRecordAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUserLoginRecordApplicationService iUserLoginRecordApplicationService;
	@Autowired
	private IUserLoginRecordRepresentationApplicationService iUserLoginRecordRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:userLoginRecord:delete')")
	@Operation(summary = "删除用户登录记录")
	@DeleteMapping("/delete")
	public SingleResponse<UserLoginRecordVO> delete(@RequestBody UserLoginRecordDeleteCommand userLoginRecordDeleteCommand){
		return iUserLoginRecordApplicationService.delete(userLoginRecordDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginRecord:detail')")
	@Operation(summary = "用户登录记录详情展示")
	@GetMapping("/detail")
	public SingleResponse<UserLoginRecordVO> queryDetail(UserLoginRecordQueryDetailCommand userLoginRecordQueryDetailCommand){
		return iUserLoginRecordRepresentationApplicationService.queryDetail(userLoginRecordQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginRecord:queryList')")
	@Operation(summary = "列表查询用户登录记录")
	@GetMapping("/list")
	public MultiResponse<UserLoginRecordVO> queryList(UserLoginRecordQueryListCommand userLoginRecordQueryListCommand){
		return iUserLoginRecordRepresentationApplicationService.queryList(userLoginRecordQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginRecord:pageQuery')")
	@Operation(summary = "分页查询用户登录记录")
	@GetMapping("/page")
	public PageResponse<UserLoginRecordVO> pageQueryList(UserLoginRecordPageQueryCommand userLoginRecordPageQueryCommand){
		return iUserLoginRecordRepresentationApplicationService.pageQuery(userLoginRecordPageQueryCommand);
	}

}
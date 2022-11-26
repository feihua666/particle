package com.particle.user.adapter.login.web.admin;

import com.particle.user.client.login.api.IUserLoginRecordApplicationService;
import com.particle.user.client.login.api.representation.IUserLoginRecordRepresentationApplicationService;
import com.particle.user.client.login.dto.command.UserLoginRecordCreateCommand;
import com.particle.user.client.login.dto.data.UserLoginRecordVO;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordQueryDetailForUpdateCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordQueryDetailCommand;
import com.particle.user.client.login.dto.command.UserLoginRecordDeleteCommand;
import com.particle.user.client.login.dto.command.UserLoginRecordUpdateCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordPageQueryCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordQueryListCommand;
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
 * 用户登录记录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Api(tags = "用户登录记录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/user-login-record")
public class UserLoginRecordAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUserLoginRecordApplicationService iUserLoginRecordApplicationService;
	@Autowired
	private IUserLoginRecordRepresentationApplicationService iUserLoginRecordRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:userLoginRecord:create')")
	@ApiOperation("添加用户登录记录")
	@PostMapping("/create")
	public SingleResponse<UserLoginRecordVO> create(@RequestBody UserLoginRecordCreateCommand userLoginRecordCreateCommand){
		return iUserLoginRecordApplicationService.create(userLoginRecordCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginRecord:delete')")
	@ApiOperation("删除用户登录记录")
	@DeleteMapping("/delete")
	public SingleResponse<UserLoginRecordVO> delete(@RequestBody UserLoginRecordDeleteCommand userLoginRecordDeleteCommand){
		return iUserLoginRecordApplicationService.delete(userLoginRecordDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginRecord:update')")
	@ApiOperation("更新用户登录记录")
	@PutMapping("/update")
	public SingleResponse<UserLoginRecordVO> update(@RequestBody UserLoginRecordUpdateCommand userLoginRecordUpdateCommand){
		return iUserLoginRecordApplicationService.update(userLoginRecordUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginRecord:update')")
	@ApiOperation("用户登录记录更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<UserLoginRecordVO> queryDetailForUpdate(UserLoginRecordQueryDetailForUpdateCommand userLoginRecordQueryDetailForUpdateCommand){
		return iUserLoginRecordRepresentationApplicationService.queryDetailForUpdate(userLoginRecordQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginRecord:detail')")
	@ApiOperation("用户登录记录详情展示")
	@GetMapping("/detail")
	public SingleResponse<UserLoginRecordVO> queryDetail(UserLoginRecordQueryDetailCommand userLoginRecordQueryDetailCommand){
		return iUserLoginRecordRepresentationApplicationService.queryDetail(userLoginRecordQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginRecord:queryList')")
	@ApiOperation("列表查询用户登录记录")
	@GetMapping("/list")
	public MultiResponse<UserLoginRecordVO> queryList(UserLoginRecordQueryListCommand userLoginRecordQueryListCommand){
		return iUserLoginRecordRepresentationApplicationService.queryList(userLoginRecordQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userLoginRecord:pageQuery')")
	@ApiOperation("分页查询用户登录记录")
	@GetMapping("/page")
	public PageResponse<UserLoginRecordVO> pageQueryList(UserLoginRecordPageQueryCommand userLoginRecordPageQueryCommand){
		return iUserLoginRecordRepresentationApplicationService.pageQuery(userLoginRecordPageQueryCommand);
	}

}
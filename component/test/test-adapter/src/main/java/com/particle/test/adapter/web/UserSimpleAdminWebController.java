package com.particle.test.adapter.web;

import com.particle.test.client.api.IUserSimpleApplicationService;
import com.particle.test.client.dto.command.UserSimpleCreateCommand;
import com.particle.test.client.dto.data.UserSimpleVO;
import com.particle.test.client.dto.command.UserSimpleQueryDetailForUpdateCommand;
import com.particle.test.client.dto.command.UserSimpleQueryDetailCommand;
import com.particle.test.client.dto.command.UserSimpleDeleteCommand;
import com.particle.test.client.dto.command.UserSimpleUpdateCommand;
import com.particle.test.client.dto.command.UserSimplePageQueryCommand;
import com.particle.test.client.dto.command.UserSimpleQueryListCommand;
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
 * 简单用户后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Api(tags = "简单用户pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/user-simple")
public class UserSimpleAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUserSimpleApplicationService iUserSimpleApplicationService;

	@PreAuthorize("hasAuthority('admin:web:userSimple:create')")
	@ApiOperation("添加简单用户")
	@PostMapping("/create")
	public SingleResponse<UserSimpleVO> create(@RequestBody UserSimpleCreateCommand userSimpleCreateCommand){
		return iUserSimpleApplicationService.create(userSimpleCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userSimple:delete')")
	@ApiOperation("删除简单用户")
	@DeleteMapping("/delete")
	public SingleResponse<UserSimpleVO> delete(@RequestBody UserSimpleDeleteCommand userSimpleDeleteCommand){
		return iUserSimpleApplicationService.delete(userSimpleDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userSimple:update')")
	@ApiOperation("更新简单用户")
	@PutMapping("/update")
	public SingleResponse<UserSimpleVO> update(@RequestBody UserSimpleUpdateCommand userSimpleUpdateCommand){
		return iUserSimpleApplicationService.update(userSimpleUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userSimple:update')")
	@ApiOperation("简单用户更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<UserSimpleVO> queryDetailForUpdate(UserSimpleQueryDetailForUpdateCommand userSimpleQueryDetailForUpdateCommand){
		return iUserSimpleApplicationService.queryDetailForUpdate(userSimpleQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userSimple:detail')")
	@ApiOperation("简单用户详情展示")
	@GetMapping("/detail")
	public SingleResponse<UserSimpleVO> queryDetail(UserSimpleQueryDetailCommand userSimpleQueryDetailCommand){
		return iUserSimpleApplicationService.queryDetail(userSimpleQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userSimple:queryList')")
	@ApiOperation("列表查询简单用户")
	@GetMapping("/list")
	public MultiResponse<UserSimpleVO> queryList(UserSimpleQueryListCommand userSimpleQueryListCommand){
		return iUserSimpleApplicationService.queryList(userSimpleQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userSimple:pageQuery')")
	@ApiOperation("分页查询简单用户")
	@GetMapping("/page")
	public PageResponse<UserSimpleVO> pageQueryList(UserSimplePageQueryCommand userSimplePageQueryCommand){
		return iUserSimpleApplicationService.pageQuery(userSimplePageQueryCommand);
	}

}
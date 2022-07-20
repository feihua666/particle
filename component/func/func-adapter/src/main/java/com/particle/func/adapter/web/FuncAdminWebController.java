package com.particle.func.adapter.web;

import com.particle.func.client.api.IFuncApplicationService;
import com.particle.func.client.dto.command.FuncCreateCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.func.client.dto.command.FuncQueryDetailForUpdateCommand;
import com.particle.func.client.dto.command.FuncQueryDetailCommand;
import com.particle.func.client.dto.command.FuncDeleteCommand;
import com.particle.func.client.dto.command.FuncUpdateCommand;
import com.particle.func.client.dto.command.FuncPageQueryCommand;
import com.particle.func.client.dto.command.FuncQueryListCommand;
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
 * 菜单功能后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Api(tags = "菜单功能pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/func")
public class FuncAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IFuncApplicationService iFuncApplicationService;

	@PreAuthorize("hasAuthority('admin:web:func:create')")
	@ApiOperation("添加菜单功能")
	@PostMapping("/create")
	public SingleResponse<FuncVO> create(@RequestBody FuncCreateCommand funcCreateCommand){
		return iFuncApplicationService.create(funcCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:func:delete')")
	@ApiOperation("删除菜单功能")
	@DeleteMapping("/delete")
	public SingleResponse<FuncVO> delete(@RequestBody FuncDeleteCommand funcDeleteCommand){
		return iFuncApplicationService.delete(funcDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:func:update')")
	@ApiOperation("更新菜单功能")
	@PutMapping("/update")
	public SingleResponse<FuncVO> update(@RequestBody FuncUpdateCommand funcUpdateCommand){
		return iFuncApplicationService.update(funcUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:func:update')")
	@ApiOperation("菜单功能更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<FuncVO> queryDetailForUpdate(FuncQueryDetailForUpdateCommand funcQueryDetailForUpdateCommand){
		return iFuncApplicationService.queryDetailForUpdate(funcQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:func:detail')")
	@ApiOperation("菜单功能详情展示")
	@GetMapping("/detail")
	public SingleResponse<FuncVO> queryDetail(FuncQueryDetailCommand funcQueryDetailCommand){
		return iFuncApplicationService.queryDetail(funcQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:func:queryList')")
	@ApiOperation("列表查询菜单功能")
	@GetMapping("/list")
	public MultiResponse<FuncVO> queryList(FuncQueryListCommand funcQueryListCommand){
		return iFuncApplicationService.queryList(funcQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:func:pageQuery')")
	@ApiOperation("分页查询菜单功能")
	@GetMapping("/page")
	public PageResponse<FuncVO> pageQueryList(FuncPageQueryCommand funcPageQueryCommand){
		return iFuncApplicationService.pageQuery(funcPageQueryCommand);
	}

}
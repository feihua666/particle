package com.particle.func.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.client.api.IFuncApplicationService;
import com.particle.func.client.api.representation.IFuncRepresentationApplicationService;
import com.particle.func.client.dto.command.FuncCreateCommand;
import com.particle.func.client.dto.command.FuncMoveCommand;
import com.particle.func.client.dto.command.FuncUpdateCommand;
import com.particle.func.client.dto.command.representation.FuncPageQueryCommand;
import com.particle.func.client.dto.command.representation.FuncQueryListCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
	@Autowired
	private IFuncRepresentationApplicationService iFuncRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:func:create')")
	@ApiOperation("添加菜单功能")
	@PostMapping("/create")
	public SingleResponse<FuncVO> create(@RequestBody FuncCreateCommand funcCreateCommand){
		return iFuncApplicationService.create(funcCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:func:delete')")
	@ApiOperation("删除菜单功能")
	@DeleteMapping("/delete")
	public SingleResponse<FuncVO> delete(@RequestBody IdCommand funcDeleteCommand){
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
	public SingleResponse<FuncVO> queryDetailForUpdate(IdCommand funcQueryDetailForUpdateCommand){
		return iFuncRepresentationApplicationService.queryDetailForUpdate(funcQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:func:detail')")
	@ApiOperation("菜单功能详情展示")
	@GetMapping("/detail")
	public SingleResponse<FuncVO> queryDetail(IdCommand funcQueryDetailCommand){
		return iFuncRepresentationApplicationService.queryDetail(funcQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:func:queryList')")
	@ApiOperation("列表查询菜单功能")
	@GetMapping("/list")
	public MultiResponse<FuncVO> queryList(FuncQueryListCommand funcQueryListCommand){
		return iFuncRepresentationApplicationService.queryList(funcQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:func:pageQuery')")
	@ApiOperation("分页查询菜单功能")
	@GetMapping("/page")
	public PageResponse<FuncVO> pageQueryList(FuncPageQueryCommand funcPageQueryCommand){
		return iFuncRepresentationApplicationService.pageQuery(funcPageQueryCommand);
	}


	@PreAuthorize("hasAuthority('admin:web:func:moveNode')")
	@ApiOperation("移动菜单功能")
	@DeleteMapping("/moveNode")
	public SingleResponse<FuncVO> delete(@RequestBody FuncMoveCommand funcMoveNodeCommand){
		return iFuncApplicationService.moveNode(funcMoveNodeCommand);
	}
}
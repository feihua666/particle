package com.particle.func.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.func.client.api.IFuncApplicationService;
import com.particle.func.client.api.representation.IFuncRepresentationApplicationService;
import com.particle.func.client.dto.command.FuncCreateCommand;
import com.particle.func.client.dto.command.FuncMoveCommand;
import com.particle.func.client.dto.command.FuncUpdateCommand;
import com.particle.func.client.dto.command.representation.FuncPageQueryCommand;
import com.particle.func.client.dto.command.representation.FuncQueryListCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 菜单功能前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Tag(name = "菜单功能pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/func")
public class FuncFrontWebController extends AbstractBaseWebAdapter {


	@Autowired
	private IFuncApplicationService iFuncApplicationService;
	@Autowired
	private IFuncRepresentationApplicationService iFuncRepresentationApplicationService;

	@PreAuthorize("hasAuthority('front:web:func:create')")
	@Operation(summary = "添加菜单功能")
	@PostMapping("/create")
	@OpLog(name = "添加菜单功能",module = OpLogConstants.Module.func,type = OpLogConstants.Type.create)
	public SingleResponse<FuncVO> create(@RequestBody FuncCreateCommand funcCreateCommand){
		return iFuncApplicationService.create(funcCreateCommand);
	}

	@PreAuthorize("hasAuthority('front:web:func:delete')")
	@Operation(summary = "删除菜单功能")
	@DeleteMapping("/delete")
	@OpLog(name = "删除菜单功能",module = OpLogConstants.Module.func,type = OpLogConstants.Type.delete)
	public SingleResponse<FuncVO> delete(@RequestBody IdCommand funcDeleteCommand){
		return iFuncApplicationService.delete(funcDeleteCommand);
	}

	@PreAuthorize("hasAuthority('front:web:func:update')")
	@Operation(summary = "更新菜单功能")
	@PutMapping("/update")
	@OpLog(name = "更新菜单功能",module = OpLogConstants.Module.func,type = OpLogConstants.Type.update)
	public SingleResponse<FuncVO> update(@RequestBody FuncUpdateCommand funcUpdateCommand){
		return iFuncApplicationService.update(funcUpdateCommand);
	}

	@PreAuthorize("hasAuthority('front:web:func:update')")
	@Operation(summary = "菜单功能更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<FuncVO> queryDetailForUpdate(IdCommand funcQueryDetailForUpdateCommand){
		return iFuncRepresentationApplicationService.queryDetailForUpdate(funcQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('front:web:func:detail')")
	@Operation(summary = "菜单功能详情展示")
	@GetMapping("/detail")
	public SingleResponse<FuncVO> queryDetail(IdCommand funcQueryDetailCommand){
		return iFuncRepresentationApplicationService.queryDetail(funcQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('front:web:func:queryList')")
	@Operation(summary = "列表查询菜单功能")
	@GetMapping("/list")
	public MultiResponse<FuncVO> queryList(FuncQueryListCommand funcQueryListCommand){
		return iFuncRepresentationApplicationService.queryList(funcQueryListCommand);
	}

	@PreAuthorize("hasAuthority('front:web:func:pageQuery')")
	@Operation(summary = "分页查询菜单功能")
	@GetMapping("/page")
	public PageResponse<FuncVO> pageQueryList(FuncPageQueryCommand funcPageQueryCommand){
		return iFuncRepresentationApplicationService.pageQuery(funcPageQueryCommand);
	}


	@PreAuthorize("hasAuthority('front:web:func:moveNode')")
	@Operation(summary = "移动菜单功能")
	@PostMapping("/moveNode")
	public SingleResponse<FuncVO> moveNode(@RequestBody FuncMoveCommand funcMoveNodeCommand){
		return iFuncApplicationService.moveNode(funcMoveNodeCommand);
	}
}

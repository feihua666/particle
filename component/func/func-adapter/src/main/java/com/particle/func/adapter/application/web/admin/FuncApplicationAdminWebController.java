package com.particle.func.adapter.application.web.admin;

import com.particle.func.client.application.api.IFuncApplicationApplicationService;
import com.particle.func.client.application.api.representation.IFuncApplicationRepresentationApplicationService;
import com.particle.func.client.application.dto.command.FuncApplicationCreateCommand;
import com.particle.func.client.application.dto.data.FuncApplicationVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.client.application.dto.command.FuncApplicationUpdateCommand;
import com.particle.func.client.application.dto.command.representation.FuncApplicationPageQueryCommand;
import com.particle.func.client.application.dto.command.representation.FuncApplicationQueryListCommand;
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
 * 功能应用后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Api(tags = "功能应用pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/func_application")
public class FuncApplicationAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IFuncApplicationApplicationService iFuncApplicationApplicationService;
	@Autowired
	private IFuncApplicationRepresentationApplicationService iFuncApplicationRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:funcApplication:create')")
	@ApiOperation("添加功能应用")
	@PostMapping("/create")
	public SingleResponse<FuncApplicationVO> create(@RequestBody FuncApplicationCreateCommand funcApplicationCreateCommand){
		return iFuncApplicationApplicationService.create(funcApplicationCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcApplication:delete')")
	@ApiOperation("删除功能应用")
	@DeleteMapping("/delete")
	public SingleResponse<FuncApplicationVO> delete(@RequestBody IdCommand deleteCommand){
		return iFuncApplicationApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcApplication:update')")
	@ApiOperation("更新功能应用")
	@PutMapping("/update")
	public SingleResponse<FuncApplicationVO> update(@RequestBody FuncApplicationUpdateCommand funcApplicationUpdateCommand){
		return iFuncApplicationApplicationService.update(funcApplicationUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcApplication:update')")
	@ApiOperation("功能应用更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<FuncApplicationVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iFuncApplicationRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcApplication:detail')")
	@ApiOperation("功能应用详情展示")
	@GetMapping("/detail")
	public SingleResponse<FuncApplicationVO> queryDetail(IdCommand detailCommand){
		return iFuncApplicationRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcApplication:queryList')")
	@ApiOperation("列表查询功能应用")
	@GetMapping("/list")
	public MultiResponse<FuncApplicationVO> queryList(FuncApplicationQueryListCommand funcApplicationQueryListCommand){
		return iFuncApplicationRepresentationApplicationService.queryList(funcApplicationQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcApplication:pageQuery')")
	@ApiOperation("分页查询功能应用")
	@GetMapping("/page")
	public PageResponse<FuncApplicationVO> pageQueryList(FuncApplicationPageQueryCommand funcApplicationPageQueryCommand){
		return iFuncApplicationRepresentationApplicationService.pageQuery(funcApplicationPageQueryCommand);
	}

}
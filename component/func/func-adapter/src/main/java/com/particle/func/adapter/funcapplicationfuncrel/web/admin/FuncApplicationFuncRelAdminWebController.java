package com.particle.func.adapter.funcapplicationfuncrel.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.client.funcapplicationfuncrel.api.IFuncApplicationFuncRelApplicationService;
import com.particle.func.client.funcapplicationfuncrel.api.representation.IFuncApplicationFuncRelRepresentationApplicationService;
import com.particle.func.client.funcapplicationfuncrel.dto.command.FuncApplicationAssignFuncCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.command.FuncApplicationFuncRelCreateCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.command.FuncAssignFuncApplicationCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.command.representation.FuncApplicationFuncRelPageQueryCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.command.representation.FuncApplicationFuncRelQueryListCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.data.FuncApplicationFuncRelVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 功能应用功能关系后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Api(tags = "功能应用功能关系pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/func_application_func_rel")
public class FuncApplicationFuncRelAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IFuncApplicationFuncRelApplicationService iFuncApplicationFuncRelApplicationService;
	@Autowired
	private IFuncApplicationFuncRelRepresentationApplicationService iFuncApplicationFuncRelRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:funcApplicationFuncRel:create')")
	@ApiOperation("添加功能应用功能关系")
	@PostMapping("/create")
	public SingleResponse<FuncApplicationFuncRelVO> create(@RequestBody FuncApplicationFuncRelCreateCommand funcApplicationFuncRelCreateCommand){
		return iFuncApplicationFuncRelApplicationService.create(funcApplicationFuncRelCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcApplicationFuncRel:delete')")
	@ApiOperation("删除功能应用功能关系")
	@DeleteMapping("/delete")
	public SingleResponse<FuncApplicationFuncRelVO> delete(@RequestBody IdCommand deleteCommand){
		return iFuncApplicationFuncRelApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcApplicationFuncRel:detail')")
	@ApiOperation("功能应用功能关系详情展示")
	@GetMapping("/detail")
	public SingleResponse<FuncApplicationFuncRelVO> queryDetail(IdCommand detailCommand){
		return iFuncApplicationFuncRelRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcApplicationFuncRel:queryList')")
	@ApiOperation("列表查询功能应用功能关系")
	@GetMapping("/list")
	public MultiResponse<FuncApplicationFuncRelVO> queryList(FuncApplicationFuncRelQueryListCommand funcApplicationFuncRelQueryListCommand){
		return iFuncApplicationFuncRelRepresentationApplicationService.queryList(funcApplicationFuncRelQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcApplicationFuncRel:pageQuery')")
	@ApiOperation("分页查询功能应用功能关系")
	@GetMapping("/page")
	public PageResponse<FuncApplicationFuncRelVO> pageQueryList(FuncApplicationFuncRelPageQueryCommand funcApplicationFuncRelPageQueryCommand){
		return iFuncApplicationFuncRelRepresentationApplicationService.pageQuery(funcApplicationFuncRelPageQueryCommand);
	}



	@ApiOperation("功能分配功能应用")
	@PreAuthorize("hasAuthority('admin:web:funcApplicationFuncRel:funcAssignFuncApplication')")
	@PostMapping("/func/assign/funcApplication")
	@ResponseStatus(HttpStatus.CREATED)
	public Response funcAssignFuncApplication(@RequestBody FuncAssignFuncApplicationCommand cf) {
		return iFuncApplicationFuncRelApplicationService.funcAssignFuncApplication(cf);
	}

	@ApiOperation("根据功能ID查询已分配的功能应用id")
	@PreAuthorize("hasAuthority('admin:web:funcApplicationFuncRel:queryFuncApplicationIdsByFuncId')")
	@GetMapping("/queryFuncApplicationIdsByFuncId")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<Long> queryFuncApplicationIdsByFuncId(IdCommand funcIdCommand) {
		return iFuncApplicationFuncRelRepresentationApplicationService.queryFuncApplicationIdsByFuncId( funcIdCommand);
	}

	@ApiOperation("清空功能下的所有功能应用")
	@PreAuthorize("hasAuthority('admin:web:funcApplicationFuncRel:deleteByFuncId')")
	@DeleteMapping("/deleteByFuncId")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Response deleteByFuncId(@RequestBody IdCommand funcIdCommand) {
		return iFuncApplicationFuncRelApplicationService.deleteByFuncId(funcIdCommand);
	}


	@ApiOperation("功能应用分配功能")
	@PreAuthorize("hasAuthority('admin:web:funcApplicationFuncRel:funcApplicationAssignFunc')")
	@PostMapping("/funcApplication/assign/func")
	@ResponseStatus(HttpStatus.CREATED)
	public Response funcApplicationAssignFunc(@RequestBody FuncApplicationAssignFuncCommand cf) {
		return iFuncApplicationFuncRelApplicationService.funcApplicationAssignFunc(cf);
	}

	@ApiOperation("根据功能应用ID查询已分配的功能id")
	@PreAuthorize("hasAuthority('admin:web:funcApplicationFuncRel:queryFuncIdsByFuncApplicationId')")
	@GetMapping("/queryFuncIdsByFuncApplicationId")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<Long> queryFuncIdsByFuncApplicationId( IdCommand funcApplicationIdCommand) {
		return iFuncApplicationFuncRelRepresentationApplicationService.queryFuncIdsByFuncApplicationId( funcApplicationIdCommand);

	}

	@ApiOperation("清空功能应用下的所有功能")
	@PreAuthorize("hasAuthority('admin:web:funcApplicationFuncRel:deleteByFuncApplicationId')")
	@DeleteMapping("/deleteByFuncApplicationId")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Response deleteByFuncApplicationId(@RequestBody IdCommand funcIdCommand) {
		return iFuncApplicationFuncRelApplicationService.deleteByFuncApplicationId(funcIdCommand);
	}
}
package com.particle.func.adapter.application.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.func.client.application.api.IFuncApplicationApplicationService;
import com.particle.func.client.application.api.representation.IFuncApplicationRepresentationApplicationService;
import com.particle.func.client.application.dto.command.FuncApplicationCreateCommand;
import com.particle.func.client.application.dto.data.FuncApplicationVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.client.application.dto.command.FuncApplicationUpdateCommand;
import com.particle.func.client.application.dto.command.representation.FuncApplicationPageQueryCommand;
import com.particle.func.client.application.dto.command.representation.FuncApplicationQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "功能应用pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/func_application")
public class FuncApplicationAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IFuncApplicationApplicationService iFuncApplicationApplicationService;
	@Autowired
	private IFuncApplicationRepresentationApplicationService iFuncApplicationRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:funcApplication:create')")
	@Operation(summary = "添加功能应用")
	@PostMapping("/create")
	@OpLog(name = "添加功能应用",module = OpLogConstants.Module.func,type = OpLogConstants.Type.create)
	public SingleResponse<FuncApplicationVO> create(@RequestBody FuncApplicationCreateCommand funcApplicationCreateCommand){
		return iFuncApplicationApplicationService.create(funcApplicationCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcApplication:delete')")
	@Operation(summary = "删除功能应用")
	@DeleteMapping("/delete")
	@OpLog(name = "删除功能应用",module = OpLogConstants.Module.func,type = OpLogConstants.Type.delete)
	public SingleResponse<FuncApplicationVO> delete(@RequestBody IdCommand deleteCommand){
		deleteCommand.dcdo(DataConstraintConstants.data_object_func_application, DataConstraintContext.Action.delete.name());
		return iFuncApplicationApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcApplication:update')")
	@Operation(summary = "更新功能应用")
	@PutMapping("/update")
	@OpLog(name = "更新功能应用",module = OpLogConstants.Module.func,type = OpLogConstants.Type.update)
	public SingleResponse<FuncApplicationVO> update(@RequestBody FuncApplicationUpdateCommand funcApplicationUpdateCommand){
		funcApplicationUpdateCommand.dcdo(DataConstraintConstants.data_object_func_application,DataConstraintContext.Action.update.name());
		return iFuncApplicationApplicationService.update(funcApplicationUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcApplication:update')")
	@Operation(summary = "功能应用更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<FuncApplicationVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iFuncApplicationRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcApplication:detail')")
	@Operation(summary = "功能应用详情展示")
	@GetMapping("/detail")
	public SingleResponse<FuncApplicationVO> queryDetail(IdCommand detailCommand){
		return iFuncApplicationRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcApplication:queryList')")
	@Operation(summary = "列表查询功能应用")
	@GetMapping("/list")
	public MultiResponse<FuncApplicationVO> queryList(FuncApplicationQueryListCommand funcApplicationQueryListCommand){
		funcApplicationQueryListCommand.dcdo(DataConstraintConstants.data_object_func_application,DataConstraintContext.Action.query.name());
		return iFuncApplicationRepresentationApplicationService.queryList(funcApplicationQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcApplication:pageQuery')")
	@Operation(summary = "分页查询功能应用")
	@GetMapping("/page")
	public PageResponse<FuncApplicationVO> pageQueryList(FuncApplicationPageQueryCommand funcApplicationPageQueryCommand){
		funcApplicationPageQueryCommand.dcdo(DataConstraintConstants.data_object_func_application,DataConstraintContext.Action.query.name());
		return iFuncApplicationRepresentationApplicationService.pageQuery(funcApplicationPageQueryCommand);
	}

}
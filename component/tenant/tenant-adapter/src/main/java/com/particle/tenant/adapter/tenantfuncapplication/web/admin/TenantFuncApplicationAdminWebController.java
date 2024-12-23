package com.particle.tenant.adapter.tenantfuncapplication.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.tenantfuncapplication.api.ITenantFuncApplicationApplicationService;
import com.particle.tenant.client.tenantfuncapplication.api.representation.ITenantFuncApplicationRepresentationApplicationService;
import com.particle.tenant.client.tenantfuncapplication.dto.command.TenantAssignFuncApplicationCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.command.TenantFuncApplicationCreateCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.command.TenantFuncApplicationUpdateCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.command.representation.TenantFuncApplicationPageQueryCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.command.representation.TenantFuncApplicationQueryListCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.data.TenantFuncApplicationVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 租户功能应用后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Tag(name = "租户功能应用pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/tenant_func_application")
public class TenantFuncApplicationAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITenantFuncApplicationApplicationService iTenantFuncApplicationApplicationService;
	@Autowired
	private ITenantFuncApplicationRepresentationApplicationService iTenantFuncApplicationRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:tenantFuncApplication:create')")
	@Operation(summary = "添加租户功能应用")
	@PostMapping("/create")
	@OpLog(name = "添加租户功能应用",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.create)
	public SingleResponse<TenantFuncApplicationVO> create(@RequestBody TenantFuncApplicationCreateCommand tenantFuncApplicationCreateCommand){
		return iTenantFuncApplicationApplicationService.create(tenantFuncApplicationCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantFuncApplication:delete')")
	@Operation(summary = "删除租户功能应用")
	@DeleteMapping("/delete")
	@OpLog(name = "删除租户功能应用",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.delete)
	public SingleResponse<TenantFuncApplicationVO> delete(@RequestBody IdCommand deleteCommand){
		return iTenantFuncApplicationApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantFuncApplication:update')")
	@Operation(summary = "更新租户功能应用")
	@PutMapping("/update")
	@OpLog(name = "更新租户功能应用",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.update)
	public SingleResponse<TenantFuncApplicationVO> update(@RequestBody TenantFuncApplicationUpdateCommand tenantFuncApplicationUpdateCommand){
		return iTenantFuncApplicationApplicationService.update(tenantFuncApplicationUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantFuncApplication:update')")
	@Operation(summary = "租户功能应用更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<TenantFuncApplicationVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iTenantFuncApplicationRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantFuncApplication:detail')")
	@Operation(summary = "租户功能应用详情展示")
	@GetMapping("/detail")
	public SingleResponse<TenantFuncApplicationVO> queryDetail(IdCommand detailCommand){
		return iTenantFuncApplicationRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantFuncApplication:queryList')")
	@Operation(summary = "列表查询租户功能应用")
	@GetMapping("/list")
	public MultiResponse<TenantFuncApplicationVO> queryList(TenantFuncApplicationQueryListCommand tenantFuncApplicationQueryListCommand){
		return iTenantFuncApplicationRepresentationApplicationService.queryList(tenantFuncApplicationQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantFuncApplication:pageQuery')")
	@Operation(summary = "分页查询租户功能应用")
	@GetMapping("/page")
	public PageResponse<TenantFuncApplicationVO> pageQueryList(TenantFuncApplicationPageQueryCommand tenantFuncApplicationPageQueryCommand){
		return iTenantFuncApplicationRepresentationApplicationService.pageQuery(tenantFuncApplicationPageQueryCommand);
	}

	@Operation(summary = "租户分配功能应用")
	@PreAuthorize("hasAuthority('admin:web:tenantFuncApplication:tenantAssignFuncApplication')")
	@PostMapping("/tenant/assign/funcApplication")
	@ResponseStatus(HttpStatus.CREATED)
	@OpLog(name = "租户分配功能应用",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.relAsign)
	public Response tenantAssignFuncApplication(@RequestBody TenantAssignFuncApplicationCommand cf) {
		return iTenantFuncApplicationApplicationService.tenantAssignFuncApplication(cf);
	}


	@Operation(summary = "根据租户ID查询已分配的功能应用id")
	@PreAuthorize("hasAuthority('admin:web:tenantFuncApplication:queryFuncApplicationIdsByTenantId')")
	@GetMapping("/queryFuncApplicationIdsByTenantId")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<Long> queryFuncApplicationIdsByTenantId(IdCommand tenantIdCommand) {
		return iTenantFuncApplicationRepresentationApplicationService.queryFuncApplicationIdsByTenantId( tenantIdCommand);
	}
}

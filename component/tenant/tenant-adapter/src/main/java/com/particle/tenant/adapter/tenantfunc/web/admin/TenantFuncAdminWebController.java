package com.particle.tenant.adapter.tenantfunc.web.admin;

import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.Response;
import com.particle.tenant.client.tenantfunc.api.ITenantFuncApplicationService;
import com.particle.tenant.client.tenantfunc.api.representation.ITenantFuncRepresentationApplicationService;
import com.particle.tenant.client.tenantfunc.dto.command.TenantAssignFuncCommand;
import com.particle.tenant.client.tenantfunc.dto.command.TenantFuncCreateCommand;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncQueryFuncIdsByTenantIdCommand;
import com.particle.tenant.client.tenantfunc.dto.data.TenantFuncVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.tenant.client.tenantfunc.dto.command.TenantFuncUpdateCommand;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncPageQueryCommand;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
 * 租户功能菜单后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Api(tags = "租户功能菜单pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/tenant_func")
public class TenantFuncAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITenantFuncApplicationService iTenantFuncApplicationService;
	@Autowired
	private ITenantFuncRepresentationApplicationService iTenantFuncRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:tenantFunc:create')")
	@ApiOperation("添加租户功能菜单")
	@PostMapping("/create")
	@OpLog(name = "添加租户功能菜单",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.create)
	public SingleResponse<TenantFuncVO> create(@RequestBody TenantFuncCreateCommand tenantFuncCreateCommand){
		return iTenantFuncApplicationService.create(tenantFuncCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantFunc:delete')")
	@ApiOperation("删除租户功能菜单")
	@DeleteMapping("/delete")
	@OpLog(name = "删除租户功能菜单",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.delete)
	public SingleResponse<TenantFuncVO> delete(@RequestBody IdCommand deleteCommand){
		return iTenantFuncApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantFunc:update')")
	@ApiOperation("更新租户功能菜单")
	@PutMapping("/update")
	@OpLog(name = "更新租户功能菜单",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.update)
	public SingleResponse<TenantFuncVO> update(@RequestBody TenantFuncUpdateCommand tenantFuncUpdateCommand){
		return iTenantFuncApplicationService.update(tenantFuncUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantFunc:update')")
	@ApiOperation("租户功能菜单更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<TenantFuncVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iTenantFuncRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantFunc:detail')")
	@ApiOperation("租户功能菜单详情展示")
	@GetMapping("/detail")
	public SingleResponse<TenantFuncVO> queryDetail(IdCommand detailCommand){
		return iTenantFuncRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantFunc:queryList')")
	@ApiOperation("列表查询租户功能菜单")
	@GetMapping("/list")
	public MultiResponse<TenantFuncVO> queryList(TenantFuncQueryListCommand tenantFuncQueryListCommand){
		return iTenantFuncRepresentationApplicationService.queryList(tenantFuncQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantFunc:pageQuery')")
	@ApiOperation("分页查询租户功能菜单")
	@GetMapping("/page")
	public PageResponse<TenantFuncVO> pageQueryList(TenantFuncPageQueryCommand tenantFuncPageQueryCommand){
		return iTenantFuncRepresentationApplicationService.pageQuery(tenantFuncPageQueryCommand);
	}


	/**
	 * 租户分配功能菜单，也可以用做租户应用分配功能菜单，但租户id是必填的
	 * 目前后台管理的 租户应用分配功能菜单 用的就是这个接口
	 * 分配完成后，有可能会功能比以前的少，这时租户下已分配的角色对应的功能也得相应减少，否则数据会不对，该方法内部已经处理该情形
	 * @param cf
	 * @return
	 */
	@ApiOperation("租户分配功能菜单")
	@PreAuthorize("hasAuthority('admin:web:tenantFunc:tenantAssignFunc')")
	@PostMapping("/tenant/assign/func")
	@OpLog(name = "租户分配功能菜单",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.relAsign)
	@ResponseStatus(HttpStatus.CREATED)
	public Response tenantAssignFunc(@RequestBody TenantAssignFuncCommand cf) {
		return iTenantFuncApplicationService.tenantAssignFunc(cf);
	}

	@ApiOperation("根据租户ID查询已分配的功能菜单id")
	@PreAuthorize("hasAuthority('admin:web:tenantFunc:queryFuncIdsByTenantId')")
	@GetMapping("/queryFuncIdsByTenantId")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<Long> queryFuncIdsByTenantId(TenantFuncQueryFuncIdsByTenantIdCommand funcIdCommand) {
		return iTenantFuncRepresentationApplicationService.queryFuncIdsByTenantId( funcIdCommand);
	}
}
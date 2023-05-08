package com.particle.tenant.adapter.createapply.web.admin;

import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.security.security.login.LoginUser;
import com.particle.tenant.client.createapply.api.ITenantCreateApplyApplicationService;
import com.particle.tenant.client.createapply.api.representation.ITenantCreateApplyRepresentationApplicationService;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyAuditCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyCreateCommand;
import com.particle.tenant.client.createapply.dto.data.TenantCreateApplyVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyUpdateCommand;
import com.particle.tenant.client.createapply.dto.command.representation.TenantCreateApplyPageQueryCommand;
import com.particle.tenant.client.createapply.dto.command.representation.TenantCreateApplyQueryListCommand;
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
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 租户创建申请后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Api(tags = "租户创建申请pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/tenant_create_apply")
public class TenantCreateApplyAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITenantCreateApplyApplicationService iTenantCreateApplyApplicationService;
	@Autowired
	private ITenantCreateApplyRepresentationApplicationService iTenantCreateApplyRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:tenantCreateApply:create')")
	@ApiOperation("添加租户创建申请")
	@PostMapping("/create")
	@OpLog(name = "添加租户创建申请",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.create)
	public SingleResponse<TenantCreateApplyVO> create(@RequestBody TenantCreateApplyCreateCommand tenantCreateApplyCreateCommand){
		return iTenantCreateApplyApplicationService.create(tenantCreateApplyCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantCreateApply:delete')")
	@ApiOperation("删除租户创建申请")
	@DeleteMapping("/delete")
	@OpLog(name = "删除租户创建申请",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.delete)
	public SingleResponse<TenantCreateApplyVO> delete(@RequestBody IdCommand deleteCommand){
		return iTenantCreateApplyApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantCreateApply:update')")
	@ApiOperation("更新租户创建申请")
	@PutMapping("/update")
	@OpLog(name = "更新租户创建申请",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.update)
	public SingleResponse<TenantCreateApplyVO> update(@RequestBody TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand){
		return iTenantCreateApplyApplicationService.update(tenantCreateApplyUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantCreateApply:update')")
	@ApiOperation("租户创建申请更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<TenantCreateApplyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iTenantCreateApplyRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantCreateApply:detail')")
	@ApiOperation("租户创建申请详情展示")
	@GetMapping("/detail")
	public SingleResponse<TenantCreateApplyVO> queryDetail(IdCommand detailCommand){
		return iTenantCreateApplyRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantCreateApply:queryList')")
	@ApiOperation("列表查询租户创建申请")
	@GetMapping("/list")
	public MultiResponse<TenantCreateApplyVO> queryList(TenantCreateApplyQueryListCommand tenantCreateApplyQueryListCommand){
		return iTenantCreateApplyRepresentationApplicationService.queryList(tenantCreateApplyQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantCreateApply:pageQuery')")
	@ApiOperation("分页查询租户创建申请")
	@GetMapping("/page")
	public PageResponse<TenantCreateApplyVO> pageQueryList(TenantCreateApplyPageQueryCommand tenantCreateApplyPageQueryCommand){
		return iTenantCreateApplyRepresentationApplicationService.pageQuery(tenantCreateApplyPageQueryCommand);
	}



	@PreAuthorize("hasAuthority('admin:web:tenantCreateApply:audit')")
	@ApiOperation("审核租户创建申请")
	@PutMapping("/audit")
	@OpLog(name = "审核租户创建申请",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.audit)
	public SingleResponse<TenantCreateApplyVO> audit(@RequestBody TenantCreateApplyAuditCommand tenantCreateApplyAuditCommand, @ApiIgnore LoginUser loginUser){
		tenantCreateApplyAuditCommand.setTenantSuperAdminRoleCode(LoginUser.tenant_super_admin_role);
		tenantCreateApplyAuditCommand.setAuditUserId(loginUser.getId());
		return iTenantCreateApplyApplicationService.audit(tenantCreateApplyAuditCommand);
	}
}
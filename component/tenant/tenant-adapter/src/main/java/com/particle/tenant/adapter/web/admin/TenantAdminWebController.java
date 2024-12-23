package com.particle.tenant.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.LoginUser;
import com.particle.tenant.client.api.ITenantApplicationService;
import com.particle.tenant.client.api.representation.ITenantRepresentationApplicationService;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyAuditPassCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyCreateCommand;
import com.particle.tenant.client.dto.command.TenantCreateCommand;
import com.particle.tenant.client.dto.command.TenantUpdateCommand;
import com.particle.tenant.client.dto.command.representation.TenantPageQueryCommand;
import com.particle.tenant.client.dto.command.representation.TenantQueryListCommand;
import com.particle.tenant.client.dto.data.TenantVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 租户后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Tag(name = "租户pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/tenant")
public class TenantAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITenantApplicationService iTenantApplicationService;
	@Autowired
	private ITenantRepresentationApplicationService iTenantRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:tenant:create')")
	@Operation(summary = "添加租户")
	@PostMapping("/create")
	@OpLog(name = "添加租户",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.create)
	public SingleResponse<TenantVO> create(@RequestBody TenantCreateCommand tenantCreateCommand){
		return iTenantApplicationService.create(tenantCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenant:delete')")
	@Operation(summary = "删除租户")
	@DeleteMapping("/delete")
	@OpLog(name = "删除租户",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.delete)
	public SingleResponse<TenantVO> delete(@RequestBody IdCommand deleteCommand){
		deleteCommand.dcdo(DataConstraintConstants.data_object_tenant_tenant, DataConstraintContext.Action.delete.name());
		return iTenantApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenant:update')")
	@Operation(summary = "更新租户")
	@PutMapping("/update")
	@OpLog(name = "更新租户",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.update)
	public SingleResponse<TenantVO> update(@RequestBody TenantUpdateCommand tenantUpdateCommand){
		tenantUpdateCommand.dcdo(DataConstraintConstants.data_object_tenant_tenant,DataConstraintContext.Action.update.name());
		return iTenantApplicationService.update(tenantUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenant:update')")
	@Operation(summary = "租户更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<TenantVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iTenantRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenant:detail')")
	@Operation(summary = "租户详情展示")
	@GetMapping("/detail")
	public SingleResponse<TenantVO> queryDetail(IdCommand detailCommand){
		return iTenantRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenant:queryList')")
	@Operation(summary = "列表查询租户")
	@GetMapping("/list")
	public MultiResponse<TenantVO> queryList(TenantQueryListCommand tenantQueryListCommand){
		tenantQueryListCommand.dcdo(DataConstraintConstants.data_object_tenant_tenant,DataConstraintContext.Action.query.name());
		return iTenantRepresentationApplicationService.queryList(tenantQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenant:pageQuery')")
	@Operation(summary = "分页查询租户")
	@GetMapping("/page")
	public PageResponse<TenantVO> pageQueryList(TenantPageQueryCommand tenantPageQueryCommand){
		tenantPageQueryCommand.dcdo(DataConstraintConstants.data_object_tenant_tenant,DataConstraintContext.Action.query.name());
		return iTenantRepresentationApplicationService.pageQuery(tenantPageQueryCommand);
	}


	/**
	 * 主要逻辑为，先创建租户申请再自动审批通过
	 * @param tenantCreateApplyCreateCommand
	 * @param loginUser
	 * @return
	 */
	@PreAuthorize("hasAuthority('admin:web:tenant:oneClickCreate')")
	@Operation(summary = "一键添加租户")
	@PostMapping("/oneClickCreate")
	@OpLog(name = "一键添加租户",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.create)
	public SingleResponse<TenantVO> oneClickCreate(@RequestBody TenantCreateApplyCreateCommand tenantCreateApplyCreateCommand, @Parameter(hidden = true) LoginUser loginUser){
		// 其它属性在 iTenantApplicationService.oneClickCreate(tenantCreateApplyCreateCommand) 内部添加
		TenantCreateApplyAuditPassCommand tenantCreateApplyAuditPassCommand = new TenantCreateApplyAuditPassCommand();
		tenantCreateApplyAuditPassCommand.setTenantSuperAdminRoleCode(LoginUser.tenant_super_admin_role);
		tenantCreateApplyAuditPassCommand.setAuditUserId(loginUser.getId());
		tenantCreateApplyAuditPassCommand.setAuditStatusComment("一键添加租户默认审批通过");
		return iTenantApplicationService.oneClickCreate(tenantCreateApplyCreateCommand, tenantCreateApplyAuditPassCommand);
	}
}

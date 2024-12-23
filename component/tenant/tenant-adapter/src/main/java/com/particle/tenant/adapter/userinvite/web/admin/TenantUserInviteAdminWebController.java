package com.particle.tenant.adapter.userinvite.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.userinvite.api.ITenantUserInviteApplicationService;
import com.particle.tenant.client.userinvite.api.representation.ITenantUserInviteRepresentationApplicationService;
import com.particle.tenant.client.userinvite.dto.command.TenantUserInviteCreateCommand;
import com.particle.tenant.client.userinvite.dto.command.TenantUserInviteUpdateCommand;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInvitePageQueryCommand;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInviteQueryListCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 租户用户邀请后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Tag(name = "租户用户邀请pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/tenant_user_invite")
public class TenantUserInviteAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITenantUserInviteApplicationService iTenantUserInviteApplicationService;
	@Autowired
	private ITenantUserInviteRepresentationApplicationService iTenantUserInviteRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:tenantUserInvite:create')")
	@Operation(summary = "添加租户用户邀请")
	@PostMapping("/create")
	@OpLog(name = "添加租户用户邀请",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.create)
	public SingleResponse<TenantUserInviteVO> create(@RequestBody TenantUserInviteCreateCommand tenantUserInviteCreateCommand){
		return iTenantUserInviteApplicationService.create(tenantUserInviteCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUserInvite:delete')")
	@Operation(summary = "删除租户用户邀请")
	@DeleteMapping("/delete")
	@OpLog(name = "删除租户用户邀请",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.delete)
	public SingleResponse<TenantUserInviteVO> delete(@RequestBody IdCommand deleteCommand){
		return iTenantUserInviteApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUserInvite:update')")
	@Operation(summary = "更新租户用户邀请")
	@PutMapping("/update")
	@OpLog(name = "更新租户用户邀请",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.update)
	public SingleResponse<TenantUserInviteVO> update(@RequestBody TenantUserInviteUpdateCommand tenantUserInviteUpdateCommand){
		return iTenantUserInviteApplicationService.update(tenantUserInviteUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUserInvite:update')")
	@Operation(summary = "租户用户邀请更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<TenantUserInviteVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iTenantUserInviteRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUserInvite:detail')")
	@Operation(summary = "租户用户邀请详情展示")
	@GetMapping("/detail")
	public SingleResponse<TenantUserInviteVO> queryDetail(IdCommand detailCommand){
		return iTenantUserInviteRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUserInvite:queryList')")
	@Operation(summary = "列表查询租户用户邀请")
	@GetMapping("/list")
	public MultiResponse<TenantUserInviteVO> queryList(TenantUserInviteQueryListCommand tenantUserInviteQueryListCommand){
		return iTenantUserInviteRepresentationApplicationService.queryList(tenantUserInviteQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUserInvite:pageQuery')")
	@Operation(summary = "分页查询租户用户邀请")
	@GetMapping("/page")
	public PageResponse<TenantUserInviteVO> pageQueryList(TenantUserInvitePageQueryCommand tenantUserInvitePageQueryCommand){
		return iTenantUserInviteRepresentationApplicationService.pageQuery(tenantUserInvitePageQueryCommand);
	}

}

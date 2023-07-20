package com.particle.tenant.adapter.userinvite.web.admin;

import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.tenant.client.userinvite.api.ITenantUserInviteUserRecordApplicationService;
import com.particle.tenant.client.userinvite.api.representation.ITenantUserInviteUserRecordRepresentationApplicationService;
import com.particle.tenant.client.userinvite.dto.command.TenantUserInviteUserRecordCreateCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteUserRecordVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.tenant.client.userinvite.dto.command.TenantUserInviteUserRecordUpdateCommand;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInviteUserRecordPageQueryCommand;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInviteUserRecordQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
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
 * 租户用户邀请记录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Tag(name = "租户用户邀请记录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/tenant_user_invite_user_record")
public class TenantUserInviteUserRecordAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITenantUserInviteUserRecordApplicationService iTenantUserInviteUserRecordApplicationService;
	@Autowired
	private ITenantUserInviteUserRecordRepresentationApplicationService iTenantUserInviteUserRecordRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:tenantUserInviteUserRecord:create')")
	@Operation(summary = "添加租户用户邀请记录")
	@PostMapping("/create")
	@OpLog(name = "添加租户用户邀请记录",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.create)
	public SingleResponse<TenantUserInviteUserRecordVO> create(@RequestBody TenantUserInviteUserRecordCreateCommand tenantUserInviteUserRecordCreateCommand){
		return iTenantUserInviteUserRecordApplicationService.create(tenantUserInviteUserRecordCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUserInviteUserRecord:delete')")
	@Operation(summary = "删除租户用户邀请记录")
	@DeleteMapping("/delete")
	@OpLog(name = "删除租户用户邀请记录",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.delete)
	public SingleResponse<TenantUserInviteUserRecordVO> delete(@RequestBody IdCommand deleteCommand){
		return iTenantUserInviteUserRecordApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUserInviteUserRecord:update')")
	@Operation(summary = "更新租户用户邀请记录")
	@PutMapping("/update")
	@OpLog(name = "更新租户用户邀请记录",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.update)
	public SingleResponse<TenantUserInviteUserRecordVO> update(@RequestBody TenantUserInviteUserRecordUpdateCommand tenantUserInviteUserRecordUpdateCommand){
		return iTenantUserInviteUserRecordApplicationService.update(tenantUserInviteUserRecordUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUserInviteUserRecord:update')")
	@Operation(summary = "租户用户邀请记录更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<TenantUserInviteUserRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iTenantUserInviteUserRecordRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUserInviteUserRecord:detail')")
	@Operation(summary = "租户用户邀请记录详情展示")
	@GetMapping("/detail")
	public SingleResponse<TenantUserInviteUserRecordVO> queryDetail(IdCommand detailCommand){
		return iTenantUserInviteUserRecordRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUserInviteUserRecord:queryList')")
	@Operation(summary = "列表查询租户用户邀请记录")
	@GetMapping("/list")
	public MultiResponse<TenantUserInviteUserRecordVO> queryList(TenantUserInviteUserRecordQueryListCommand tenantUserInviteUserRecordQueryListCommand){
		return iTenantUserInviteUserRecordRepresentationApplicationService.queryList(tenantUserInviteUserRecordQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUserInviteUserRecord:pageQuery')")
	@Operation(summary = "分页查询租户用户邀请记录")
	@GetMapping("/page")
	public PageResponse<TenantUserInviteUserRecordVO> pageQueryList(TenantUserInviteUserRecordPageQueryCommand tenantUserInviteUserRecordPageQueryCommand){
		return iTenantUserInviteUserRecordRepresentationApplicationService.pageQuery(tenantUserInviteUserRecordPageQueryCommand);
	}

}
package com.particle.tenant.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.LoginUser;
import com.particle.tenant.client.api.ITenantUserApplicationService;
import com.particle.tenant.client.api.representation.ITenantUserRepresentationApplicationService;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.tenant.client.dto.command.TenantUserUpdateCommand;
import com.particle.tenant.client.dto.command.representation.TenantUserPageQueryCommand;
import com.particle.tenant.client.dto.command.representation.TenantUserQueryListCommand;
import com.particle.tenant.client.dto.data.TenantUserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 租户用户前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Tag(name = "租户用户pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/tenant_user")
public class TenantUserFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITenantUserApplicationService iTenantUserApplicationService;
	@Autowired
	private ITenantUserRepresentationApplicationService iTenantUserRepresentationApplicationService;

	@PreAuthorize("hasAuthority('front:web:tenantUser:create')")
	@Operation(summary = "添加租户用户")
	@PostMapping("/create")
	@OpLog(name = "添加租户用户",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.create)
	public SingleResponse<TenantUserVO> create(@RequestBody TenantUserCreateCommand tenantUserCreateCommand, @Parameter(hidden = true) LoginUser loginUser){
		if (tenantUserCreateCommand.getTenantId() == null) {
			tenantUserCreateCommand.setTenantId(loginUser.getCurrentTenant().getId());
		}
		return iTenantUserApplicationService.create(tenantUserCreateCommand);
	}

	@PreAuthorize("hasAuthority('front:web:tenantUser:delete')")
	@Operation(summary = "删除租户用户")
	@DeleteMapping("/delete")
	@OpLog(name = "删除租户用户",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.delete)
	public SingleResponse<TenantUserVO> delete(@RequestBody IdCommand deleteCommand){
		return iTenantUserApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('front:web:tenantUser:update')")
	@Operation(summary = "更新租户用户")
	@PutMapping("/update")
	@OpLog(name = "更新租户用户",module = OpLogConstants.Module.tenant,type = OpLogConstants.Type.update)
	public SingleResponse<TenantUserVO> update(@RequestBody TenantUserUpdateCommand tenantUserUpdateCommand){
		return iTenantUserApplicationService.update(tenantUserUpdateCommand);
	}

	@PreAuthorize("hasAuthority('front:web:tenantUser:update')")
	@Operation(summary = "租户用户更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<TenantUserVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iTenantUserRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('front:web:tenantUser:detail')")
	@Operation(summary = "租户用户详情展示")
	@GetMapping("/detail")
	public SingleResponse<TenantUserVO> queryDetail(IdCommand detailCommand){
		return iTenantUserRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('front:web:tenantUser:queryList')")
	@Operation(summary = "列表查询租户用户")
	@GetMapping("/list")
	public MultiResponse<TenantUserVO> queryList(TenantUserQueryListCommand tenantUserQueryListCommand){
		return iTenantUserRepresentationApplicationService.queryList(tenantUserQueryListCommand);
	}

	@PreAuthorize("hasAuthority('front:web:tenantUser:pageQuery')")
	@Operation(summary = "分页查询租户用户")
	@GetMapping("/page")
	public PageResponse<TenantUserVO> pageQueryList(TenantUserPageQueryCommand tenantUserPageQueryCommand){
		return iTenantUserRepresentationApplicationService.pageQuery(tenantUserPageQueryCommand);
	}

}

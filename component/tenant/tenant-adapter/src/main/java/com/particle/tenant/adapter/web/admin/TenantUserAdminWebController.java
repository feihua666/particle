package com.particle.tenant.adapter.web.admin;

import com.particle.tenant.client.api.ITenantUserApplicationService;
import com.particle.tenant.client.api.representation.ITenantUserRepresentationApplicationService;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.tenant.client.dto.data.TenantUserVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.tenant.client.dto.command.TenantUserUpdateCommand;
import com.particle.tenant.client.dto.command.representation.TenantUserPageQueryCommand;
import com.particle.tenant.client.dto.command.representation.TenantUserQueryListCommand;
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
 * 租户用户后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Api(tags = "租户用户pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/tenant_user")
public class TenantUserAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITenantUserApplicationService iTenantUserApplicationService;
	@Autowired
	private ITenantUserRepresentationApplicationService iTenantUserRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:tenantUser:create')")
	@ApiOperation("添加租户用户")
	@PostMapping("/create")
	public SingleResponse<TenantUserVO> create(@RequestBody TenantUserCreateCommand tenantUserCreateCommand){
		return iTenantUserApplicationService.create(tenantUserCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUser:delete')")
	@ApiOperation("删除租户用户")
	@DeleteMapping("/delete")
	public SingleResponse<TenantUserVO> delete(@RequestBody IdCommand deleteCommand){
		return iTenantUserApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUser:update')")
	@ApiOperation("更新租户用户")
	@PutMapping("/update")
	public SingleResponse<TenantUserVO> update(@RequestBody TenantUserUpdateCommand tenantUserUpdateCommand){
		return iTenantUserApplicationService.update(tenantUserUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUser:update')")
	@ApiOperation("租户用户更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<TenantUserVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iTenantUserRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUser:detail')")
	@ApiOperation("租户用户详情展示")
	@GetMapping("/detail")
	public SingleResponse<TenantUserVO> queryDetail(IdCommand detailCommand){
		return iTenantUserRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUser:queryList')")
	@ApiOperation("列表查询租户用户")
	@GetMapping("/list")
	public MultiResponse<TenantUserVO> queryList(TenantUserQueryListCommand tenantUserQueryListCommand){
		return iTenantUserRepresentationApplicationService.queryList(tenantUserQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenantUser:pageQuery')")
	@ApiOperation("分页查询租户用户")
	@GetMapping("/page")
	public PageResponse<TenantUserVO> pageQueryList(TenantUserPageQueryCommand tenantUserPageQueryCommand){
		return iTenantUserRepresentationApplicationService.pageQuery(tenantUserPageQueryCommand);
	}

}
package com.particle.tenant.adapter.web.admin;

import com.particle.tenant.client.api.ITenantApplicationService;
import com.particle.tenant.client.api.representation.ITenantRepresentationApplicationService;
import com.particle.tenant.client.dto.command.TenantCreateCommand;
import com.particle.tenant.client.dto.data.TenantVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.tenant.client.dto.command.TenantUpdateCommand;
import com.particle.tenant.client.dto.command.representation.TenantPageQueryCommand;
import com.particle.tenant.client.dto.command.representation.TenantQueryListCommand;
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
 * 租户后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Api(tags = "租户pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/tenant")
public class TenantAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITenantApplicationService iTenantApplicationService;
	@Autowired
	private ITenantRepresentationApplicationService iTenantRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:tenant:create')")
	@ApiOperation("添加租户")
	@PostMapping("/create")
	public SingleResponse<TenantVO> create(@RequestBody TenantCreateCommand tenantCreateCommand){
		return iTenantApplicationService.create(tenantCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenant:delete')")
	@ApiOperation("删除租户")
	@DeleteMapping("/delete")
	public SingleResponse<TenantVO> delete(@RequestBody IdCommand deleteCommand){
		return iTenantApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenant:update')")
	@ApiOperation("更新租户")
	@PutMapping("/update")
	public SingleResponse<TenantVO> update(@RequestBody TenantUpdateCommand tenantUpdateCommand){
		return iTenantApplicationService.update(tenantUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenant:update')")
	@ApiOperation("租户更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<TenantVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iTenantRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenant:detail')")
	@ApiOperation("租户详情展示")
	@GetMapping("/detail")
	public SingleResponse<TenantVO> queryDetail(IdCommand detailCommand){
		return iTenantRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenant:queryList')")
	@ApiOperation("列表查询租户")
	@GetMapping("/list")
	public MultiResponse<TenantVO> queryList(TenantQueryListCommand tenantQueryListCommand){
		return iTenantRepresentationApplicationService.queryList(tenantQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:tenant:pageQuery')")
	@ApiOperation("分页查询租户")
	@GetMapping("/page")
	public PageResponse<TenantVO> pageQueryList(TenantPageQueryCommand tenantPageQueryCommand){
		return iTenantRepresentationApplicationService.pageQuery(tenantPageQueryCommand);
	}

}
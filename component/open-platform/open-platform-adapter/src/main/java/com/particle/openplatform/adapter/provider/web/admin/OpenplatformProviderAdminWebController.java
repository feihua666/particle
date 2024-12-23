package com.particle.openplatform.adapter.provider.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.provider.api.IOpenplatformProviderApplicationService;
import com.particle.openplatform.client.provider.api.representation.IOpenplatformProviderRepresentationApplicationService;
import com.particle.openplatform.client.provider.dto.command.OpenplatformProviderCreateCommand;
import com.particle.openplatform.client.provider.dto.command.OpenplatformProviderUpdateCommand;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderPageQueryCommand;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderQueryListCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 开放平台开放接口供应商后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Tag(name = "开放平台开放接口供应商pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_provider")
public class OpenplatformProviderAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformProviderApplicationService iOpenplatformProviderApplicationService;
	@Autowired
	private IOpenplatformProviderRepresentationApplicationService iOpenplatformProviderRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformProvider:create')")
	@Operation(summary = "添加开放平台开放接口供应商")
	@PostMapping("/create")
	@OpLog(name = "添加开放平台开放接口供应商",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformProviderVO> create(@RequestBody OpenplatformProviderCreateCommand openplatformProviderCreateCommand){
		return iOpenplatformProviderApplicationService.create(openplatformProviderCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformProvider:delete')")
	@Operation(summary = "删除开放平台开放接口供应商")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放平台开放接口供应商",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformProviderVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformProviderApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformProvider:update')")
	@Operation(summary = "更新开放平台开放接口供应商")
	@PutMapping("/update")
	@OpLog(name = "更新开放平台开放接口供应商",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformProviderVO> update(@RequestBody OpenplatformProviderUpdateCommand openplatformProviderUpdateCommand){
		return iOpenplatformProviderApplicationService.update(openplatformProviderUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformProvider:update')")
	@Operation(summary = "开放平台开放接口供应商更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformProviderVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformProviderRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformProvider:detail')")
	@Operation(summary = "开放平台开放接口供应商详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformProviderVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformProviderRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformProvider:queryList')")
	@Operation(summary = "列表查询开放平台开放接口供应商")
	@GetMapping("/list")
	public MultiResponse<OpenplatformProviderVO> queryList(OpenplatformProviderQueryListCommand openplatformProviderQueryListCommand){
		return iOpenplatformProviderRepresentationApplicationService.queryList(openplatformProviderQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformProvider:pageQuery')")
	@Operation(summary = "分页查询开放平台开放接口供应商")
	@GetMapping("/page")
	public PageResponse<OpenplatformProviderVO> pageQueryList(OpenplatformProviderPageQueryCommand openplatformProviderPageQueryCommand){
		return iOpenplatformProviderRepresentationApplicationService.pageQuery(openplatformProviderPageQueryCommand);
	}

}

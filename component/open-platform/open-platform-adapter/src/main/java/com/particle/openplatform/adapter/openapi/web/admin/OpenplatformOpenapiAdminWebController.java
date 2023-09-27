package com.particle.openplatform.adapter.openapi.web.admin;

import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiApplicationService;
import com.particle.openplatform.client.openapi.api.representation.IOpenplatformOpenapiRepresentationApplicationService;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiCreateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiUpdateCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiPageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiQueryListCommand;
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
import com.particle.global.dataaudit.op.OpLog;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
/**
 * <p>
 * 开放平台开放接口后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Tag(name = "开放平台开放接口pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_openapi")
public class OpenplatformOpenapiAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformOpenapiApplicationService iOpenplatformOpenapiApplicationService;
	@Autowired
	private IOpenplatformOpenapiRepresentationApplicationService iOpenplatformOpenapiRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapi:create')")
	@Operation(summary = "添加开放平台开放接口")
	@PostMapping("/create")
	@OpLog(name = "添加开放平台开放接口",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformOpenapiVO> create(@RequestBody OpenplatformOpenapiCreateCommand openplatformOpenapiCreateCommand){
		return iOpenplatformOpenapiApplicationService.create(openplatformOpenapiCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapi:delete')")
	@Operation(summary = "删除开放平台开放接口")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放平台开放接口",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformOpenapiVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformOpenapiApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapi:update')")
	@Operation(summary = "更新开放平台开放接口")
	@PutMapping("/update")
	@OpLog(name = "更新开放平台开放接口",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformOpenapiVO> update(@RequestBody OpenplatformOpenapiUpdateCommand openplatformOpenapiUpdateCommand){
		return iOpenplatformOpenapiApplicationService.update(openplatformOpenapiUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapi:update')")
	@Operation(summary = "开放平台开放接口更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformOpenapiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformOpenapiRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapi:detail')")
	@Operation(summary = "开放平台开放接口详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformOpenapiVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformOpenapiRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapi:queryList')")
	@Operation(summary = "列表查询开放平台开放接口")
	@GetMapping("/list")
	public MultiResponse<OpenplatformOpenapiVO> queryList(OpenplatformOpenapiQueryListCommand openplatformOpenapiQueryListCommand){
		return iOpenplatformOpenapiRepresentationApplicationService.queryList(openplatformOpenapiQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapi:pageQuery')")
	@Operation(summary = "分页查询开放平台开放接口")
	@GetMapping("/page")
	public PageResponse<OpenplatformOpenapiVO> pageQueryList(OpenplatformOpenapiPageQueryCommand openplatformOpenapiPageQueryCommand){
		return iOpenplatformOpenapiRepresentationApplicationService.pageQuery(openplatformOpenapiPageQueryCommand);
	}

}
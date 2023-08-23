package com.particle.openplatform.adapter.app.web.admin;

import com.particle.openplatform.client.app.api.IOpenplatformAppApplicationService;
import com.particle.openplatform.client.app.api.representation.IOpenplatformAppRepresentationApplicationService;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppCreateCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppUpdateCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppPageQueryCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppQueryListCommand;
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
 * 开放平台应用后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Tag(name = "开放平台应用pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_app")
public class OpenplatformAppAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformAppApplicationService iOpenplatformAppApplicationService;
	@Autowired
	private IOpenplatformAppRepresentationApplicationService iOpenplatformAppRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformApp:create')")
	@Operation(summary = "添加开放平台应用")
	@PostMapping("/create")
	@OpLog(name = "添加开放平台应用",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformAppVO> create(@RequestBody OpenplatformAppCreateCommand openplatformAppCreateCommand){
		return iOpenplatformAppApplicationService.create(openplatformAppCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformApp:delete')")
	@Operation(summary = "删除开放平台应用")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放平台应用",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformAppVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformAppApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformApp:update')")
	@Operation(summary = "更新开放平台应用")
	@PutMapping("/update")
	@OpLog(name = "更新开放平台应用",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformAppVO> update(@RequestBody OpenplatformAppUpdateCommand openplatformAppUpdateCommand){
		return iOpenplatformAppApplicationService.update(openplatformAppUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformApp:update')")
	@Operation(summary = "开放平台应用更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformAppVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformAppRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformApp:detail')")
	@Operation(summary = "开放平台应用详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformAppVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformAppRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformApp:queryList')")
	@Operation(summary = "列表查询开放平台应用")
	@GetMapping("/list")
	public MultiResponse<OpenplatformAppVO> queryList(OpenplatformAppQueryListCommand openplatformAppQueryListCommand){
		return iOpenplatformAppRepresentationApplicationService.queryList(openplatformAppQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformApp:pageQuery')")
	@Operation(summary = "分页查询开放平台应用")
	@GetMapping("/page")
	public PageResponse<OpenplatformAppVO> pageQueryList(OpenplatformAppPageQueryCommand openplatformAppPageQueryCommand){
		return iOpenplatformAppRepresentationApplicationService.pageQuery(openplatformAppPageQueryCommand);
	}

}
package com.particle.openplatform.adapter.doc.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDirRelApplicationService;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDirRelRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDirRelCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDirRelUpdateCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDirRelPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDirRelQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDirRelVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 开放接口文档接口与目录关系后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Tag(name = "开放接口文档接口与目录关系pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_doc_api_dir_rel")
public class OpenplatformDocApiDirRelAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformDocApiDirRelApplicationService iOpenplatformDocApiDirRelApplicationService;
	@Autowired
	private IOpenplatformDocApiDirRelRepresentationApplicationService iOpenplatformDocApiDirRelRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDirRel:create')")
	@Operation(summary = "添加开放接口文档接口与目录关系")
	@PostMapping("/create")
	@OpLog(name = "添加开放接口文档接口与目录关系",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformDocApiDirRelVO> create(@RequestBody OpenplatformDocApiDirRelCreateCommand openplatformDocApiDirRelCreateCommand){
		return iOpenplatformDocApiDirRelApplicationService.create(openplatformDocApiDirRelCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDirRel:delete')")
	@Operation(summary = "删除开放接口文档接口与目录关系")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放接口文档接口与目录关系",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformDocApiDirRelVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformDocApiDirRelApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDirRel:update')")
	@Operation(summary = "更新开放接口文档接口与目录关系")
	@PutMapping("/update")
	@OpLog(name = "更新开放接口文档接口与目录关系",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformDocApiDirRelVO> update(@RequestBody OpenplatformDocApiDirRelUpdateCommand openplatformDocApiDirRelUpdateCommand){
		return iOpenplatformDocApiDirRelApplicationService.update(openplatformDocApiDirRelUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDirRel:update')")
	@Operation(summary = "开放接口文档接口与目录关系更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformDocApiDirRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformDocApiDirRelRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDirRel:detail')")
	@Operation(summary = "开放接口文档接口与目录关系详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformDocApiDirRelVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformDocApiDirRelRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDirRel:queryList')")
	@Operation(summary = "列表查询开放接口文档接口与目录关系")
	@GetMapping("/list")
	public MultiResponse<OpenplatformDocApiDirRelVO> queryList(OpenplatformDocApiDirRelQueryListCommand openplatformDocApiDirRelQueryListCommand){
		return iOpenplatformDocApiDirRelRepresentationApplicationService.queryList(openplatformDocApiDirRelQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDirRel:pageQuery')")
	@Operation(summary = "分页查询开放接口文档接口与目录关系")
	@GetMapping("/page")
	public PageResponse<OpenplatformDocApiDirRelVO> pageQueryList(OpenplatformDocApiDirRelPageQueryCommand openplatformDocApiDirRelPageQueryCommand){
		return iOpenplatformDocApiDirRelRepresentationApplicationService.pageQuery(openplatformDocApiDirRelPageQueryCommand);
	}

}

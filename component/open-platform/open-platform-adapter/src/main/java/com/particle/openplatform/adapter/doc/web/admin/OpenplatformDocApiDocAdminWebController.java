package com.particle.openplatform.adapter.doc.web.admin;

import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocApplicationService;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDocRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocUpdateCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocQueryListCommand;
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
 * 开放接口文档后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Tag(name = "开放接口文档pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_doc_api_doc")
public class OpenplatformDocApiDocAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformDocApiDocApplicationService iOpenplatformDocApiDocApplicationService;
	@Autowired
	private IOpenplatformDocApiDocRepresentationApplicationService iOpenplatformDocApiDocRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDoc:create')")
	@Operation(summary = "添加开放接口文档")
	@PostMapping("/create")
	@OpLog(name = "添加开放接口文档",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformDocApiDocVO> create(@RequestBody OpenplatformDocApiDocCreateCommand openplatformDocApiDocCreateCommand){
		return iOpenplatformDocApiDocApplicationService.create(openplatformDocApiDocCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDoc:delete')")
	@Operation(summary = "删除开放接口文档")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放接口文档",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformDocApiDocVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformDocApiDocApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDoc:update')")
	@Operation(summary = "更新开放接口文档")
	@PutMapping("/update")
	@OpLog(name = "更新开放接口文档",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformDocApiDocVO> update(@RequestBody OpenplatformDocApiDocUpdateCommand openplatformDocApiDocUpdateCommand){
		return iOpenplatformDocApiDocApplicationService.update(openplatformDocApiDocUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDoc:update')")
	@Operation(summary = "开放接口文档更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformDocApiDocVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformDocApiDocRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDoc:detail')")
	@Operation(summary = "开放接口文档详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformDocApiDocVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformDocApiDocRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDoc:queryList')")
	@Operation(summary = "列表查询开放接口文档")
	@GetMapping("/list")
	public MultiResponse<OpenplatformDocApiDocVO> queryList(OpenplatformDocApiDocQueryListCommand openplatformDocApiDocQueryListCommand){
		return iOpenplatformDocApiDocRepresentationApplicationService.queryList(openplatformDocApiDocQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDoc:pageQuery')")
	@Operation(summary = "分页查询开放接口文档")
	@GetMapping("/page")
	public PageResponse<OpenplatformDocApiDocVO> pageQueryList(OpenplatformDocApiDocPageQueryCommand openplatformDocApiDocPageQueryCommand){
		return iOpenplatformDocApiDocRepresentationApplicationService.pageQuery(openplatformDocApiDocPageQueryCommand);
	}

}
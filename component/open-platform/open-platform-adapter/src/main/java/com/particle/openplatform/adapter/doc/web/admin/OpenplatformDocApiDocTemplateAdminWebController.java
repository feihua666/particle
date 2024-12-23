package com.particle.openplatform.adapter.doc.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocTemplateApplicationService;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDocTemplateRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateUpdateCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplatePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 开放接口文档模板后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Tag(name = "开放接口文档模板pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_doc_api_doc_template")
public class OpenplatformDocApiDocTemplateAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformDocApiDocTemplateApplicationService iOpenplatformDocApiDocTemplateApplicationService;
	@Autowired
	private IOpenplatformDocApiDocTemplateRepresentationApplicationService iOpenplatformDocApiDocTemplateRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplate:create')")
	@Operation(summary = "添加开放接口文档模板")
	@PostMapping("/create")
	@OpLog(name = "添加开放接口文档模板",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformDocApiDocTemplateVO> create(@RequestBody OpenplatformDocApiDocTemplateCreateCommand openplatformDocApiDocTemplateCreateCommand){
		return iOpenplatformDocApiDocTemplateApplicationService.create(openplatformDocApiDocTemplateCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplate:delete')")
	@Operation(summary = "删除开放接口文档模板")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放接口文档模板",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformDocApiDocTemplateVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformDocApiDocTemplateApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplate:update')")
	@Operation(summary = "更新开放接口文档模板")
	@PutMapping("/update")
	@OpLog(name = "更新开放接口文档模板",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformDocApiDocTemplateVO> update(@RequestBody OpenplatformDocApiDocTemplateUpdateCommand openplatformDocApiDocTemplateUpdateCommand){
		return iOpenplatformDocApiDocTemplateApplicationService.update(openplatformDocApiDocTemplateUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplate:update')")
	@Operation(summary = "开放接口文档模板更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformDocApiDocTemplateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformDocApiDocTemplateRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplate:detail')")
	@Operation(summary = "开放接口文档模板详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformDocApiDocTemplateVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformDocApiDocTemplateRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplate:queryList')")
	@Operation(summary = "列表查询开放接口文档模板")
	@GetMapping("/list")
	public MultiResponse<OpenplatformDocApiDocTemplateVO> queryList(OpenplatformDocApiDocTemplateQueryListCommand openplatformDocApiDocTemplateQueryListCommand){
		return iOpenplatformDocApiDocTemplateRepresentationApplicationService.queryList(openplatformDocApiDocTemplateQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplate:pageQuery')")
	@Operation(summary = "分页查询开放接口文档模板")
	@GetMapping("/page")
	public PageResponse<OpenplatformDocApiDocTemplateVO> pageQueryList(OpenplatformDocApiDocTemplatePageQueryCommand openplatformDocApiDocTemplatePageQueryCommand){
		return iOpenplatformDocApiDocTemplateRepresentationApplicationService.pageQuery(openplatformDocApiDocTemplatePageQueryCommand);
	}

}

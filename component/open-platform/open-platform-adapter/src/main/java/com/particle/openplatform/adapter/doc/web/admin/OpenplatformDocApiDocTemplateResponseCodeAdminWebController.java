package com.particle.openplatform.adapter.doc.web.admin;

import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocTemplateResponseCodeApplicationService;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDocTemplateResponseCodeRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateResponseCodeCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateResponseCodeVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateResponseCodeUpdateCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateResponseCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateResponseCodeQueryListCommand;
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
 * 开放接口文档模板响应码后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:49:10
 */
@Tag(name = "开放接口文档模板响应码pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_doc_api_doc_template_response_code")
public class OpenplatformDocApiDocTemplateResponseCodeAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformDocApiDocTemplateResponseCodeApplicationService iOpenplatformDocApiDocTemplateResponseCodeApplicationService;
	@Autowired
	private IOpenplatformDocApiDocTemplateResponseCodeRepresentationApplicationService iOpenplatformDocApiDocTemplateResponseCodeRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateResponseCode:create')")
	@Operation(summary = "添加开放接口文档模板响应码")
	@PostMapping("/create")
	@OpLog(name = "添加开放接口文档模板响应码",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformDocApiDocTemplateResponseCodeVO> create(@RequestBody OpenplatformDocApiDocTemplateResponseCodeCreateCommand openplatformDocApiDocTemplateResponseCodeCreateCommand){
		return iOpenplatformDocApiDocTemplateResponseCodeApplicationService.create(openplatformDocApiDocTemplateResponseCodeCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateResponseCode:delete')")
	@Operation(summary = "删除开放接口文档模板响应码")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放接口文档模板响应码",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformDocApiDocTemplateResponseCodeVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformDocApiDocTemplateResponseCodeApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateResponseCode:update')")
	@Operation(summary = "更新开放接口文档模板响应码")
	@PutMapping("/update")
	@OpLog(name = "更新开放接口文档模板响应码",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformDocApiDocTemplateResponseCodeVO> update(@RequestBody OpenplatformDocApiDocTemplateResponseCodeUpdateCommand openplatformDocApiDocTemplateResponseCodeUpdateCommand){
		return iOpenplatformDocApiDocTemplateResponseCodeApplicationService.update(openplatformDocApiDocTemplateResponseCodeUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateResponseCode:update')")
	@Operation(summary = "开放接口文档模板响应码更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformDocApiDocTemplateResponseCodeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformDocApiDocTemplateResponseCodeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateResponseCode:detail')")
	@Operation(summary = "开放接口文档模板响应码详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformDocApiDocTemplateResponseCodeVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformDocApiDocTemplateResponseCodeRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateResponseCode:queryList')")
	@Operation(summary = "列表查询开放接口文档模板响应码")
	@GetMapping("/list")
	public MultiResponse<OpenplatformDocApiDocTemplateResponseCodeVO> queryList(OpenplatformDocApiDocTemplateResponseCodeQueryListCommand openplatformDocApiDocTemplateResponseCodeQueryListCommand){
		return iOpenplatformDocApiDocTemplateResponseCodeRepresentationApplicationService.queryList(openplatformDocApiDocTemplateResponseCodeQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateResponseCode:pageQuery')")
	@Operation(summary = "分页查询开放接口文档模板响应码")
	@GetMapping("/page")
	public PageResponse<OpenplatformDocApiDocTemplateResponseCodeVO> pageQueryList(OpenplatformDocApiDocTemplateResponseCodePageQueryCommand openplatformDocApiDocTemplateResponseCodePageQueryCommand){
		return iOpenplatformDocApiDocTemplateResponseCodeRepresentationApplicationService.pageQuery(openplatformDocApiDocTemplateResponseCodePageQueryCommand);
	}

}
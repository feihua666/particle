package com.particle.openplatform.adapter.doc.web.admin;

import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocTemplateParamFieldApplicationService;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDocTemplateParamFieldRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateParamFieldCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateParamFieldVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateParamFieldUpdateCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateParamFieldPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateParamFieldQueryListCommand;
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
 * 开放接口文档模板参数字段后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
@Tag(name = "开放接口文档模板参数字段pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_doc_api_doc_template_param_field")
public class OpenplatformDocApiDocTemplateParamFieldAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformDocApiDocTemplateParamFieldApplicationService iOpenplatformDocApiDocTemplateParamFieldApplicationService;
	@Autowired
	private IOpenplatformDocApiDocTemplateParamFieldRepresentationApplicationService iOpenplatformDocApiDocTemplateParamFieldRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateParamField:create')")
	@Operation(summary = "添加开放接口文档模板参数字段")
	@PostMapping("/create")
	@OpLog(name = "添加开放接口文档模板参数字段",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> create(@RequestBody OpenplatformDocApiDocTemplateParamFieldCreateCommand openplatformDocApiDocTemplateParamFieldCreateCommand){
		return iOpenplatformDocApiDocTemplateParamFieldApplicationService.create(openplatformDocApiDocTemplateParamFieldCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateParamField:delete')")
	@Operation(summary = "删除开放接口文档模板参数字段")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放接口文档模板参数字段",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformDocApiDocTemplateParamFieldApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateParamField:update')")
	@Operation(summary = "更新开放接口文档模板参数字段")
	@PutMapping("/update")
	@OpLog(name = "更新开放接口文档模板参数字段",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> update(@RequestBody OpenplatformDocApiDocTemplateParamFieldUpdateCommand openplatformDocApiDocTemplateParamFieldUpdateCommand){
		return iOpenplatformDocApiDocTemplateParamFieldApplicationService.update(openplatformDocApiDocTemplateParamFieldUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateParamField:update')")
	@Operation(summary = "开放接口文档模板参数字段更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformDocApiDocTemplateParamFieldRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateParamField:detail')")
	@Operation(summary = "开放接口文档模板参数字段详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformDocApiDocTemplateParamFieldRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateParamField:queryList')")
	@Operation(summary = "列表查询开放接口文档模板参数字段")
	@GetMapping("/list")
	public MultiResponse<OpenplatformDocApiDocTemplateParamFieldVO> queryList(OpenplatformDocApiDocTemplateParamFieldQueryListCommand openplatformDocApiDocTemplateParamFieldQueryListCommand){
		return iOpenplatformDocApiDocTemplateParamFieldRepresentationApplicationService.queryList(openplatformDocApiDocTemplateParamFieldQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateParamField:pageQuery')")
	@Operation(summary = "分页查询开放接口文档模板参数字段")
	@GetMapping("/page")
	public PageResponse<OpenplatformDocApiDocTemplateParamFieldVO> pageQueryList(OpenplatformDocApiDocTemplateParamFieldPageQueryCommand openplatformDocApiDocTemplateParamFieldPageQueryCommand){
		return iOpenplatformDocApiDocTemplateParamFieldRepresentationApplicationService.pageQuery(openplatformDocApiDocTemplateParamFieldPageQueryCommand);
	}

}
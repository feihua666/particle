package com.particle.openplatform.adapter.doc.web.admin;

import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocParamFieldApplicationService;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDocParamFieldRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocParamFieldCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocParamFieldVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocParamFieldUpdateCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocParamFieldPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocParamFieldQueryListCommand;
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
 * 开放接口文档参数字段后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@Tag(name = "开放接口文档参数字段pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_doc_api_doc_param_field")
public class OpenplatformDocApiDocParamFieldAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformDocApiDocParamFieldApplicationService iOpenplatformDocApiDocParamFieldApplicationService;
	@Autowired
	private IOpenplatformDocApiDocParamFieldRepresentationApplicationService iOpenplatformDocApiDocParamFieldRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocParamField:create')")
	@Operation(summary = "添加开放接口文档参数字段")
	@PostMapping("/create")
	@OpLog(name = "添加开放接口文档参数字段",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformDocApiDocParamFieldVO> create(@RequestBody OpenplatformDocApiDocParamFieldCreateCommand openplatformDocApiDocParamFieldCreateCommand){
		return iOpenplatformDocApiDocParamFieldApplicationService.create(openplatformDocApiDocParamFieldCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocParamField:delete')")
	@Operation(summary = "删除开放接口文档参数字段")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放接口文档参数字段",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformDocApiDocParamFieldVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformDocApiDocParamFieldApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocParamField:update')")
	@Operation(summary = "更新开放接口文档参数字段")
	@PutMapping("/update")
	@OpLog(name = "更新开放接口文档参数字段",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformDocApiDocParamFieldVO> update(@RequestBody OpenplatformDocApiDocParamFieldUpdateCommand openplatformDocApiDocParamFieldUpdateCommand){
		return iOpenplatformDocApiDocParamFieldApplicationService.update(openplatformDocApiDocParamFieldUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocParamField:update')")
	@Operation(summary = "开放接口文档参数字段更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformDocApiDocParamFieldVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformDocApiDocParamFieldRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocParamField:detail')")
	@Operation(summary = "开放接口文档参数字段详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformDocApiDocParamFieldVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformDocApiDocParamFieldRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocParamField:queryList')")
	@Operation(summary = "列表查询开放接口文档参数字段")
	@GetMapping("/list")
	public MultiResponse<OpenplatformDocApiDocParamFieldVO> queryList(OpenplatformDocApiDocParamFieldQueryListCommand openplatformDocApiDocParamFieldQueryListCommand){
		return iOpenplatformDocApiDocParamFieldRepresentationApplicationService.queryList(openplatformDocApiDocParamFieldQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocParamField:pageQuery')")
	@Operation(summary = "分页查询开放接口文档参数字段")
	@GetMapping("/page")
	public PageResponse<OpenplatformDocApiDocParamFieldVO> pageQueryList(OpenplatformDocApiDocParamFieldPageQueryCommand openplatformDocApiDocParamFieldPageQueryCommand){
		return iOpenplatformDocApiDocParamFieldRepresentationApplicationService.pageQuery(openplatformDocApiDocParamFieldPageQueryCommand);
	}

}
package com.particle.openplatform.adapter.doc.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocTemplateExampleCodeApplicationService;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDocTemplateExampleCodeRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateExampleCodeCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateExampleCodeUpdateCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateExampleCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateExampleCodeQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateExampleCodeVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 开放接口文档模板示例代码后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:26
 */
@Tag(name = "开放接口文档模板示例代码pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_doc_api_doc_template_example_code")
public class OpenplatformDocApiDocTemplateExampleCodeAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformDocApiDocTemplateExampleCodeApplicationService iOpenplatformDocApiDocTemplateExampleCodeApplicationService;
	@Autowired
	private IOpenplatformDocApiDocTemplateExampleCodeRepresentationApplicationService iOpenplatformDocApiDocTemplateExampleCodeRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateExampleCode:create')")
	@Operation(summary = "添加开放接口文档模板示例代码")
	@PostMapping("/create")
	@OpLog(name = "添加开放接口文档模板示例代码",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> create(@RequestBody OpenplatformDocApiDocTemplateExampleCodeCreateCommand openplatformDocApiDocTemplateExampleCodeCreateCommand){
		return iOpenplatformDocApiDocTemplateExampleCodeApplicationService.create(openplatformDocApiDocTemplateExampleCodeCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateExampleCode:delete')")
	@Operation(summary = "删除开放接口文档模板示例代码")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放接口文档模板示例代码",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformDocApiDocTemplateExampleCodeApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateExampleCode:update')")
	@Operation(summary = "更新开放接口文档模板示例代码")
	@PutMapping("/update")
	@OpLog(name = "更新开放接口文档模板示例代码",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> update(@RequestBody OpenplatformDocApiDocTemplateExampleCodeUpdateCommand openplatformDocApiDocTemplateExampleCodeUpdateCommand){
		return iOpenplatformDocApiDocTemplateExampleCodeApplicationService.update(openplatformDocApiDocTemplateExampleCodeUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateExampleCode:update')")
	@Operation(summary = "开放接口文档模板示例代码更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformDocApiDocTemplateExampleCodeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateExampleCode:detail')")
	@Operation(summary = "开放接口文档模板示例代码详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformDocApiDocTemplateExampleCodeRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateExampleCode:queryList')")
	@Operation(summary = "列表查询开放接口文档模板示例代码")
	@GetMapping("/list")
	public MultiResponse<OpenplatformDocApiDocTemplateExampleCodeVO> queryList(OpenplatformDocApiDocTemplateExampleCodeQueryListCommand openplatformDocApiDocTemplateExampleCodeQueryListCommand){
		return iOpenplatformDocApiDocTemplateExampleCodeRepresentationApplicationService.queryList(openplatformDocApiDocTemplateExampleCodeQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocTemplateExampleCode:pageQuery')")
	@Operation(summary = "分页查询开放接口文档模板示例代码")
	@GetMapping("/page")
	public PageResponse<OpenplatformDocApiDocTemplateExampleCodeVO> pageQueryList(OpenplatformDocApiDocTemplateExampleCodePageQueryCommand openplatformDocApiDocTemplateExampleCodePageQueryCommand){
		return iOpenplatformDocApiDocTemplateExampleCodeRepresentationApplicationService.pageQuery(openplatformDocApiDocTemplateExampleCodePageQueryCommand);
	}

}

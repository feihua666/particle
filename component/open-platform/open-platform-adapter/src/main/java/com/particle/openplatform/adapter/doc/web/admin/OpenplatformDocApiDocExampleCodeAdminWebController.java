package com.particle.openplatform.adapter.doc.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocExampleCodeApplicationService;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDocExampleCodeRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocExampleCodeCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocExampleCodeUpdateCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocExampleCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocExampleCodeQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocExampleCodeVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 开放接口文档示例代码后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
@Tag(name = "开放接口文档示例代码pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_doc_api_doc_example_code")
public class OpenplatformDocApiDocExampleCodeAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformDocApiDocExampleCodeApplicationService iOpenplatformDocApiDocExampleCodeApplicationService;
	@Autowired
	private IOpenplatformDocApiDocExampleCodeRepresentationApplicationService iOpenplatformDocApiDocExampleCodeRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocExampleCode:create')")
	@Operation(summary = "添加开放接口文档示例代码")
	@PostMapping("/create")
	@OpLog(name = "添加开放接口文档示例代码",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformDocApiDocExampleCodeVO> create(@RequestBody OpenplatformDocApiDocExampleCodeCreateCommand openplatformDocApiDocExampleCodeCreateCommand){
		return iOpenplatformDocApiDocExampleCodeApplicationService.create(openplatformDocApiDocExampleCodeCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocExampleCode:delete')")
	@Operation(summary = "删除开放接口文档示例代码")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放接口文档示例代码",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformDocApiDocExampleCodeVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformDocApiDocExampleCodeApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocExampleCode:update')")
	@Operation(summary = "更新开放接口文档示例代码")
	@PutMapping("/update")
	@OpLog(name = "更新开放接口文档示例代码",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformDocApiDocExampleCodeVO> update(@RequestBody OpenplatformDocApiDocExampleCodeUpdateCommand openplatformDocApiDocExampleCodeUpdateCommand){
		return iOpenplatformDocApiDocExampleCodeApplicationService.update(openplatformDocApiDocExampleCodeUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocExampleCode:update')")
	@Operation(summary = "开放接口文档示例代码更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformDocApiDocExampleCodeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformDocApiDocExampleCodeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocExampleCode:detail')")
	@Operation(summary = "开放接口文档示例代码详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformDocApiDocExampleCodeVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformDocApiDocExampleCodeRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocExampleCode:queryList')")
	@Operation(summary = "列表查询开放接口文档示例代码")
	@GetMapping("/list")
	public MultiResponse<OpenplatformDocApiDocExampleCodeVO> queryList(OpenplatformDocApiDocExampleCodeQueryListCommand openplatformDocApiDocExampleCodeQueryListCommand){
		return iOpenplatformDocApiDocExampleCodeRepresentationApplicationService.queryList(openplatformDocApiDocExampleCodeQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocExampleCode:pageQuery')")
	@Operation(summary = "分页查询开放接口文档示例代码")
	@GetMapping("/page")
	public PageResponse<OpenplatformDocApiDocExampleCodeVO> pageQueryList(OpenplatformDocApiDocExampleCodePageQueryCommand openplatformDocApiDocExampleCodePageQueryCommand){
		return iOpenplatformDocApiDocExampleCodeRepresentationApplicationService.pageQuery(openplatformDocApiDocExampleCodePageQueryCommand);
	}

}

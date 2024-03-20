package com.particle.openplatform.adapter.doc.web.admin;

import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocResponseCodeApplicationService;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDocResponseCodeRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocResponseCodeCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocResponseCodeVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocResponseCodeUpdateCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocResponseCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocResponseCodeQueryListCommand;
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
 * 开放接口文档响应码后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
@Tag(name = "开放接口文档响应码pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_doc_api_doc_response_code")
public class OpenplatformDocApiDocResponseCodeAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformDocApiDocResponseCodeApplicationService iOpenplatformDocApiDocResponseCodeApplicationService;
	@Autowired
	private IOpenplatformDocApiDocResponseCodeRepresentationApplicationService iOpenplatformDocApiDocResponseCodeRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocResponseCode:create')")
	@Operation(summary = "添加开放接口文档响应码")
	@PostMapping("/create")
	@OpLog(name = "添加开放接口文档响应码",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformDocApiDocResponseCodeVO> create(@RequestBody OpenplatformDocApiDocResponseCodeCreateCommand openplatformDocApiDocResponseCodeCreateCommand){
		return iOpenplatformDocApiDocResponseCodeApplicationService.create(openplatformDocApiDocResponseCodeCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocResponseCode:delete')")
	@Operation(summary = "删除开放接口文档响应码")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放接口文档响应码",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformDocApiDocResponseCodeVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformDocApiDocResponseCodeApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocResponseCode:update')")
	@Operation(summary = "更新开放接口文档响应码")
	@PutMapping("/update")
	@OpLog(name = "更新开放接口文档响应码",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformDocApiDocResponseCodeVO> update(@RequestBody OpenplatformDocApiDocResponseCodeUpdateCommand openplatformDocApiDocResponseCodeUpdateCommand){
		return iOpenplatformDocApiDocResponseCodeApplicationService.update(openplatformDocApiDocResponseCodeUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocResponseCode:update')")
	@Operation(summary = "开放接口文档响应码更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformDocApiDocResponseCodeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformDocApiDocResponseCodeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocResponseCode:detail')")
	@Operation(summary = "开放接口文档响应码详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformDocApiDocResponseCodeVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformDocApiDocResponseCodeRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocResponseCode:queryList')")
	@Operation(summary = "列表查询开放接口文档响应码")
	@GetMapping("/list")
	public MultiResponse<OpenplatformDocApiDocResponseCodeVO> queryList(OpenplatformDocApiDocResponseCodeQueryListCommand openplatformDocApiDocResponseCodeQueryListCommand){
		return iOpenplatformDocApiDocResponseCodeRepresentationApplicationService.queryList(openplatformDocApiDocResponseCodeQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApiDocResponseCode:pageQuery')")
	@Operation(summary = "分页查询开放接口文档响应码")
	@GetMapping("/page")
	public PageResponse<OpenplatformDocApiDocResponseCodeVO> pageQueryList(OpenplatformDocApiDocResponseCodePageQueryCommand openplatformDocApiDocResponseCodePageQueryCommand){
		return iOpenplatformDocApiDocResponseCodeRepresentationApplicationService.pageQuery(openplatformDocApiDocResponseCodePageQueryCommand);
	}

}
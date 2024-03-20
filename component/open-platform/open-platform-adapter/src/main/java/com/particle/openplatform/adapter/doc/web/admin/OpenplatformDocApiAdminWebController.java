package com.particle.openplatform.adapter.doc.web.admin;

import com.particle.openplatform.client.doc.api.IOpenplatformDocApiApplicationService;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiUpdateCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiQueryListCommand;
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
 * 开放接口文档接口后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Tag(name = "开放接口文档接口pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_doc_api")
public class OpenplatformDocApiAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformDocApiApplicationService iOpenplatformDocApiApplicationService;
	@Autowired
	private IOpenplatformDocApiRepresentationApplicationService iOpenplatformDocApiRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApi:create')")
	@Operation(summary = "添加开放接口文档接口")
	@PostMapping("/create")
	@OpLog(name = "添加开放接口文档接口",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformDocApiVO> create(@RequestBody OpenplatformDocApiCreateCommand openplatformDocApiCreateCommand){
		return iOpenplatformDocApiApplicationService.create(openplatformDocApiCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApi:delete')")
	@Operation(summary = "删除开放接口文档接口")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放接口文档接口",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformDocApiVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformDocApiApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApi:update')")
	@Operation(summary = "更新开放接口文档接口")
	@PutMapping("/update")
	@OpLog(name = "更新开放接口文档接口",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformDocApiVO> update(@RequestBody OpenplatformDocApiUpdateCommand openplatformDocApiUpdateCommand){
		return iOpenplatformDocApiApplicationService.update(openplatformDocApiUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApi:update')")
	@Operation(summary = "开放接口文档接口更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformDocApiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformDocApiRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApi:detail')")
	@Operation(summary = "开放接口文档接口详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformDocApiVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformDocApiRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApi:queryList')")
	@Operation(summary = "列表查询开放接口文档接口")
	@GetMapping("/list")
	public MultiResponse<OpenplatformDocApiVO> queryList(OpenplatformDocApiQueryListCommand openplatformDocApiQueryListCommand){
		return iOpenplatformDocApiRepresentationApplicationService.queryList(openplatformDocApiQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocApi:pageQuery')")
	@Operation(summary = "分页查询开放接口文档接口")
	@GetMapping("/page")
	public PageResponse<OpenplatformDocApiVO> pageQueryList(OpenplatformDocApiPageQueryCommand openplatformDocApiPageQueryCommand){
		return iOpenplatformDocApiRepresentationApplicationService.pageQuery(openplatformDocApiPageQueryCommand);
	}

}
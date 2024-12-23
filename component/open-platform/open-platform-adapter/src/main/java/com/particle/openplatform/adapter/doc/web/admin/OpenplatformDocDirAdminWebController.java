package com.particle.openplatform.adapter.doc.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.api.IOpenplatformDocDirApplicationService;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocDirRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocDirCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocDirUpdateCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 开放接口目录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@Tag(name = "开放接口目录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_doc_dir")
public class OpenplatformDocDirAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformDocDirApplicationService iOpenplatformDocDirApplicationService;
	@Autowired
	private IOpenplatformDocDirRepresentationApplicationService iOpenplatformDocDirRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformDocDir:create')")
	@Operation(summary = "添加开放接口目录")
	@PostMapping("/create")
	@OpLog(name = "添加开放接口目录",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformDocDirVO> create(@RequestBody OpenplatformDocDirCreateCommand openplatformDocDirCreateCommand){
		return iOpenplatformDocDirApplicationService.create(openplatformDocDirCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocDir:delete')")
	@Operation(summary = "删除开放接口目录")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放接口目录",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformDocDirVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformDocDirApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocDir:update')")
	@Operation(summary = "更新开放接口目录")
	@PutMapping("/update")
	@OpLog(name = "更新开放接口目录",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformDocDirVO> update(@RequestBody OpenplatformDocDirUpdateCommand openplatformDocDirUpdateCommand){
		return iOpenplatformDocDirApplicationService.update(openplatformDocDirUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocDir:update')")
	@Operation(summary = "开放接口目录更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformDocDirVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformDocDirRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocDir:detail')")
	@Operation(summary = "开放接口目录详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformDocDirVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformDocDirRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocDir:queryList')")
	@Operation(summary = "列表查询开放接口目录")
	@GetMapping("/list")
	public MultiResponse<OpenplatformDocDirVO> queryList(OpenplatformDocDirQueryListCommand openplatformDocDirQueryListCommand){
		return iOpenplatformDocDirRepresentationApplicationService.queryList(openplatformDocDirQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocDir:pageQuery')")
	@Operation(summary = "分页查询开放接口目录")
	@GetMapping("/page")
	public PageResponse<OpenplatformDocDirVO> pageQueryList(OpenplatformDocDirPageQueryCommand openplatformDocDirPageQueryCommand){
		return iOpenplatformDocDirRepresentationApplicationService.pageQuery(openplatformDocDirPageQueryCommand);
	}

}

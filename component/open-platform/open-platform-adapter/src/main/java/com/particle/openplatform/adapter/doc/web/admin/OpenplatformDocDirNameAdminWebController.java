package com.particle.openplatform.adapter.doc.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.api.IOpenplatformDocDirNameApplicationService;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocDirNameRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocDirNameCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocDirNameUpdateCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirNamePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirNameQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirNameVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 开放接口目录名称后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@Tag(name = "开放接口目录名称pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_doc_dir_name")
public class OpenplatformDocDirNameAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformDocDirNameApplicationService iOpenplatformDocDirNameApplicationService;
	@Autowired
	private IOpenplatformDocDirNameRepresentationApplicationService iOpenplatformDocDirNameRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformDocDirName:create')")
	@Operation(summary = "添加开放接口目录名称")
	@PostMapping("/create")
	@OpLog(name = "添加开放接口目录名称",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformDocDirNameVO> create(@RequestBody OpenplatformDocDirNameCreateCommand openplatformDocDirNameCreateCommand){
		return iOpenplatformDocDirNameApplicationService.create(openplatformDocDirNameCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocDirName:delete')")
	@Operation(summary = "删除开放接口目录名称")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放接口目录名称",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformDocDirNameVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformDocDirNameApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocDirName:update')")
	@Operation(summary = "更新开放接口目录名称")
	@PutMapping("/update")
	@OpLog(name = "更新开放接口目录名称",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformDocDirNameVO> update(@RequestBody OpenplatformDocDirNameUpdateCommand openplatformDocDirNameUpdateCommand){
		return iOpenplatformDocDirNameApplicationService.update(openplatformDocDirNameUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocDirName:update')")
	@Operation(summary = "开放接口目录名称更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformDocDirNameVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformDocDirNameRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocDirName:detail')")
	@Operation(summary = "开放接口目录名称详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformDocDirNameVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformDocDirNameRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocDirName:queryList')")
	@Operation(summary = "列表查询开放接口目录名称")
	@GetMapping("/list")
	public MultiResponse<OpenplatformDocDirNameVO> queryList(OpenplatformDocDirNameQueryListCommand openplatformDocDirNameQueryListCommand){
		return iOpenplatformDocDirNameRepresentationApplicationService.queryList(openplatformDocDirNameQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformDocDirName:pageQuery')")
	@Operation(summary = "分页查询开放接口目录名称")
	@GetMapping("/page")
	public PageResponse<OpenplatformDocDirNameVO> pageQueryList(OpenplatformDocDirNamePageQueryCommand openplatformDocDirNamePageQueryCommand){
		return iOpenplatformDocDirNameRepresentationApplicationService.pageQuery(openplatformDocDirNamePageQueryCommand);
	}

}

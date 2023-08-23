package com.particle.openplatform.adapter.openapirecord.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.openapirecord.api.IOpenplatformOpenapiRecordApplicationService;
import com.particle.openplatform.client.openapirecord.api.representation.IOpenplatformOpenapiRecordRepresentationApplicationService;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordPageQueryCommand;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordQueryListCommand;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 开放平台开放接口调用记录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@Tag(name = "开放平台开放接口调用记录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_openapi_record")
public class OpenplatformOpenapiRecordAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformOpenapiRecordApplicationService iOpenplatformOpenapiRecordApplicationService;
	@Autowired
	private IOpenplatformOpenapiRecordRepresentationApplicationService iOpenplatformOpenapiRecordRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecord:delete')")
	@Operation(summary = "删除开放平台开放接口调用记录")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放平台开放接口调用记录",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformOpenapiRecordVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformOpenapiRecordApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecord:detail')")
	@Operation(summary = "开放平台开放接口调用记录详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformOpenapiRecordVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformOpenapiRecordRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecord:queryList')")
	@Operation(summary = "列表查询开放平台开放接口调用记录")
	@GetMapping("/list")
	public MultiResponse<OpenplatformOpenapiRecordVO> queryList(OpenplatformOpenapiRecordQueryListCommand openplatformOpenapiRecordQueryListCommand){
		return iOpenplatformOpenapiRecordRepresentationApplicationService.queryList(openplatformOpenapiRecordQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecord:pageQuery')")
	@Operation(summary = "分页查询开放平台开放接口调用记录")
	@GetMapping("/page")
	public PageResponse<OpenplatformOpenapiRecordVO> pageQueryList(OpenplatformOpenapiRecordPageQueryCommand openplatformOpenapiRecordPageQueryCommand){
		return iOpenplatformOpenapiRecordRepresentationApplicationService.pageQuery(openplatformOpenapiRecordPageQueryCommand);
	}

}
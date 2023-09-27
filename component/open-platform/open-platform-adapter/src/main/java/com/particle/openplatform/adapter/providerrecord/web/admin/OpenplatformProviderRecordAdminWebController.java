package com.particle.openplatform.adapter.providerrecord.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.providerrecord.api.IOpenplatformProviderRecordApplicationService;
import com.particle.openplatform.client.providerrecord.api.representation.IOpenplatformProviderRecordRepresentationApplicationService;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordPageQueryCommand;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordQueryListCommand;
import com.particle.openplatform.client.providerrecord.dto.data.OpenplatformProviderRecordVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 开放平台开放接口供应商调用记录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:17:25
 */
@Tag(name = "开放平台开放接口供应商调用记录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_provider_record")
public class OpenplatformProviderRecordAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformProviderRecordApplicationService iOpenplatformProviderRecordApplicationService;
	@Autowired
	private IOpenplatformProviderRecordRepresentationApplicationService iOpenplatformProviderRecordRepresentationApplicationService;


	@PreAuthorize("hasAuthority('admin:web:openplatformProviderRecord:delete')")
	@Operation(summary = "删除开放平台开放接口供应商调用记录")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放平台开放接口供应商调用记录",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformProviderRecordVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformProviderRecordApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformProviderRecord:detail')")
	@Operation(summary = "开放平台开放接口供应商调用记录详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformProviderRecordVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformProviderRecordRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformProviderRecord:queryList')")
	@Operation(summary = "列表查询开放平台开放接口供应商调用记录")
	@GetMapping("/list")
	public MultiResponse<OpenplatformProviderRecordVO> queryList(OpenplatformProviderRecordQueryListCommand openplatformProviderRecordQueryListCommand){
		return iOpenplatformProviderRecordRepresentationApplicationService.queryList(openplatformProviderRecordQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformProviderRecord:pageQuery')")
	@Operation(summary = "分页查询开放平台开放接口供应商调用记录")
	@GetMapping("/page")
	public PageResponse<OpenplatformProviderRecordVO> pageQueryList(OpenplatformProviderRecordPageQueryCommand openplatformProviderRecordPageQueryCommand){
		return iOpenplatformProviderRecordRepresentationApplicationService.pageQuery(openplatformProviderRecordPageQueryCommand);
	}

}
package com.particle.openplatform.adapter.providerrecord.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.providerrecord.api.IOpenplatformProviderRecordParamApplicationService;
import com.particle.openplatform.client.providerrecord.api.representation.IOpenplatformProviderRecordParamRepresentationApplicationService;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordParamPageQueryCommand;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordParamQueryListCommand;
import com.particle.openplatform.client.providerrecord.dto.data.OpenplatformProviderRecordParamVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 开放平台开放接口供应商调用记录参数后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
@Tag(name = "开放平台开放接口供应商调用记录参数pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_provider_record_param")
public class OpenplatformProviderRecordParamAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformProviderRecordParamApplicationService iOpenplatformProviderRecordParamApplicationService;
	@Autowired
	private IOpenplatformProviderRecordParamRepresentationApplicationService iOpenplatformProviderRecordParamRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordParam:delete')")
	@Operation(summary = "删除开放平台开放接口供应商调用记录参数")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放平台开放接口供应商调用记录参数",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformProviderRecordParamVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformProviderRecordParamApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordParam:detail')")
	@Operation(summary = "开放平台开放接口供应商调用记录参数详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformProviderRecordParamVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformProviderRecordParamRepresentationApplicationService.queryDetail(detailCommand);
	}


	@PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordParam:detail')")
	@Operation(summary = "开放平台开放接口供应商调用记录参数详情展示1")
	@GetMapping("/detailByOpenplatformProviderRecordId")
	public SingleResponse<OpenplatformProviderRecordParamVO> detailByOpenplatformProviderRecordId(IdCommand detailCommand){
		return iOpenplatformProviderRecordParamRepresentationApplicationService.detailByOpenplatformProviderRecordId(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordParam:queryList')")
	@Operation(summary = "列表查询开放平台开放接口供应商调用记录参数")
	@GetMapping("/list")
	public MultiResponse<OpenplatformProviderRecordParamVO> queryList(OpenplatformProviderRecordParamQueryListCommand openplatformProviderRecordParamQueryListCommand){
		return iOpenplatformProviderRecordParamRepresentationApplicationService.queryList(openplatformProviderRecordParamQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordParam:pageQuery')")
	@Operation(summary = "分页查询开放平台开放接口供应商调用记录参数")
	@GetMapping("/page")
	public PageResponse<OpenplatformProviderRecordParamVO> pageQueryList(OpenplatformProviderRecordParamPageQueryCommand openplatformProviderRecordParamPageQueryCommand){
		return iOpenplatformProviderRecordParamRepresentationApplicationService.pageQuery(openplatformProviderRecordParamPageQueryCommand);
	}

}

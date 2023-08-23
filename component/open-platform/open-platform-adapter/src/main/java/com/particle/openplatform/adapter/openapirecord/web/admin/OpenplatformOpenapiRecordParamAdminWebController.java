package com.particle.openplatform.adapter.openapirecord.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.openapirecord.api.IOpenplatformOpenapiRecordParamApplicationService;
import com.particle.openplatform.client.openapirecord.api.representation.IOpenplatformOpenapiRecordParamRepresentationApplicationService;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordParamPageQueryCommand;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordParamQueryListCommand;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordParamVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 开放平台开放接口调用记录参数后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:03
 */
@Tag(name = "开放平台开放接口调用记录参数pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_openapi_record_param")
public class OpenplatformOpenapiRecordParamAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformOpenapiRecordParamApplicationService iOpenplatformOpenapiRecordParamApplicationService;
	@Autowired
	private IOpenplatformOpenapiRecordParamRepresentationApplicationService iOpenplatformOpenapiRecordParamRepresentationApplicationService;


	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordParam:delete')")
	@Operation(summary = "删除开放平台开放接口调用记录参数")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放平台开放接口调用记录参数",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformOpenapiRecordParamVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformOpenapiRecordParamApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordParam:detail')")
	@Operation(summary = "开放平台开放接口调用记录参数详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformOpenapiRecordParamVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformOpenapiRecordParamRepresentationApplicationService.queryDetail(detailCommand);
	}


	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordParam:detail')")
	@Operation(summary = "开放平台开放接口调用记录参数详情展示1")
	@GetMapping("/detailByOpenplatformOpenapiRecordId")
	public SingleResponse<OpenplatformOpenapiRecordParamVO> detailByOpenplatformOpenapiRecordId(IdCommand detailCommand){
		return iOpenplatformOpenapiRecordParamRepresentationApplicationService.detailByOpenplatformOpenapiRecordId(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordParam:queryList')")
	@Operation(summary = "列表查询开放平台开放接口调用记录参数")
	@GetMapping("/list")
	public MultiResponse<OpenplatformOpenapiRecordParamVO> queryList(OpenplatformOpenapiRecordParamQueryListCommand openplatformOpenapiRecordParamQueryListCommand){
		return iOpenplatformOpenapiRecordParamRepresentationApplicationService.queryList(openplatformOpenapiRecordParamQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordParam:pageQuery')")
	@Operation(summary = "分页查询开放平台开放接口调用记录参数")
	@GetMapping("/page")
	public PageResponse<OpenplatformOpenapiRecordParamVO> pageQueryList(OpenplatformOpenapiRecordParamPageQueryCommand openplatformOpenapiRecordParamPageQueryCommand){
		return iOpenplatformOpenapiRecordParamRepresentationApplicationService.pageQuery(openplatformOpenapiRecordParamPageQueryCommand);
	}

}
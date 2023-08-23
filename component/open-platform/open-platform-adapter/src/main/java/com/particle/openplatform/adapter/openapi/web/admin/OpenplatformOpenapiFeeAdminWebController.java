package com.particle.openplatform.adapter.openapi.web.admin;

import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiFeeApplicationService;
import com.particle.openplatform.client.openapi.api.representation.IOpenplatformOpenapiFeeRepresentationApplicationService;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiFeeCreateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiFeeVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiFeeUpdateCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiFeePageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiFeeQueryListCommand;
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
 * 开放平台开放接口费用后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@Tag(name = "开放平台开放接口费用pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_openapi_fee")
public class OpenplatformOpenapiFeeAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformOpenapiFeeApplicationService iOpenplatformOpenapiFeeApplicationService;
	@Autowired
	private IOpenplatformOpenapiFeeRepresentationApplicationService iOpenplatformOpenapiFeeRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapiFee:create')")
	@Operation(summary = "添加开放平台开放接口费用")
	@PostMapping("/create")
	@OpLog(name = "添加开放平台开放接口费用",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformOpenapiFeeVO> create(@RequestBody OpenplatformOpenapiFeeCreateCommand openplatformOpenapiFeeCreateCommand){
		return iOpenplatformOpenapiFeeApplicationService.create(openplatformOpenapiFeeCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapiFee:delete')")
	@Operation(summary = "删除开放平台开放接口费用")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放平台开放接口费用",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformOpenapiFeeVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformOpenapiFeeApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapiFee:update')")
	@Operation(summary = "更新开放平台开放接口费用")
	@PutMapping("/update")
	@OpLog(name = "更新开放平台开放接口费用",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformOpenapiFeeVO> update(@RequestBody OpenplatformOpenapiFeeUpdateCommand openplatformOpenapiFeeUpdateCommand){
		return iOpenplatformOpenapiFeeApplicationService.update(openplatformOpenapiFeeUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapiFee:update')")
	@Operation(summary = "开放平台开放接口费用更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformOpenapiFeeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformOpenapiFeeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapiFee:detail')")
	@Operation(summary = "开放平台开放接口费用详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformOpenapiFeeVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformOpenapiFeeRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapiFee:queryList')")
	@Operation(summary = "列表查询开放平台开放接口费用")
	@GetMapping("/list")
	public MultiResponse<OpenplatformOpenapiFeeVO> queryList(OpenplatformOpenapiFeeQueryListCommand openplatformOpenapiFeeQueryListCommand){
		return iOpenplatformOpenapiFeeRepresentationApplicationService.queryList(openplatformOpenapiFeeQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformOpenapiFee:pageQuery')")
	@Operation(summary = "分页查询开放平台开放接口费用")
	@GetMapping("/page")
	public PageResponse<OpenplatformOpenapiFeeVO> pageQueryList(OpenplatformOpenapiFeePageQueryCommand openplatformOpenapiFeePageQueryCommand){
		return iOpenplatformOpenapiFeeRepresentationApplicationService.pageQuery(openplatformOpenapiFeePageQueryCommand);
	}

}
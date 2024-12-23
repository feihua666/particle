package com.particle.openplatform.adapter.app.web.admin;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.net.NetUtil;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.api.AbstractGlobalOpenapi;
import com.particle.openplatform.client.app.api.IOpenplatformAppOpenapiApplicationService;
import com.particle.openplatform.client.app.api.representation.IOpenplatformAppOpenapiRepresentationApplicationService;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppOpenapiCreateCommand;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppOpenapiUpdateCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppOpenapiPageQueryCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppOpenapiQueryListCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppOpenapiVO;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppService;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiDO;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 开放平台应用与开放接口配置后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Tag(name = "开放平台应用与开放接口配置pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_app_openapi")
public class OpenplatformAppOpenapiAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformAppOpenapiApplicationService iOpenplatformAppOpenapiApplicationService;
	@Autowired
	private IOpenplatformAppOpenapiRepresentationApplicationService iOpenplatformAppOpenapiRepresentationApplicationService;

	@Autowired
	private IOpenplatformAppService iOpenplatformAppService;
	@Autowired
	private IOpenplatformOpenapiService iOpenplatformOpenapiService;


	@Autowired(required = false)
	private List<AbstractGlobalOpenapi> globalOpenapiList;
	@PreAuthorize("hasAuthority('admin:web:openplatformAppOpenapi:create')")
	@Operation(summary = "添加开放平台应用与开放接口配置")
	@PostMapping("/create")
	@OpLog(name = "添加开放平台应用与开放接口配置",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
	public SingleResponse<OpenplatformAppOpenapiVO> create(@RequestBody OpenplatformAppOpenapiCreateCommand openplatformAppOpenapiCreateCommand){
		return iOpenplatformAppOpenapiApplicationService.create(openplatformAppOpenapiCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformAppOpenapi:delete')")
	@Operation(summary = "删除开放平台应用与开放接口配置")
	@DeleteMapping("/delete")
	@OpLog(name = "删除开放平台应用与开放接口配置",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
	public SingleResponse<OpenplatformAppOpenapiVO> delete(@RequestBody IdCommand deleteCommand){
		return iOpenplatformAppOpenapiApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformAppOpenapi:update')")
	@Operation(summary = "更新开放平台应用与开放接口配置")
	@PutMapping("/update")
	@OpLog(name = "更新开放平台应用与开放接口配置",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
	public SingleResponse<OpenplatformAppOpenapiVO> update(@RequestBody OpenplatformAppOpenapiUpdateCommand openplatformAppOpenapiUpdateCommand){
		return iOpenplatformAppOpenapiApplicationService.update(openplatformAppOpenapiUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformAppOpenapi:update')")
	@Operation(summary = "开放平台应用与开放接口配置更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<OpenplatformAppOpenapiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iOpenplatformAppOpenapiRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformAppOpenapi:detail')")
	@Operation(summary = "开放平台应用与开放接口配置详情展示")
	@GetMapping("/detail")
	public SingleResponse<OpenplatformAppOpenapiVO> queryDetail(IdCommand detailCommand){
		return iOpenplatformAppOpenapiRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformAppOpenapi:queryList')")
	@Operation(summary = "列表查询开放平台应用与开放接口配置")
	@GetMapping("/list")
	public MultiResponse<OpenplatformAppOpenapiVO> queryList(OpenplatformAppOpenapiQueryListCommand openplatformAppOpenapiQueryListCommand){
		return iOpenplatformAppOpenapiRepresentationApplicationService.queryList(openplatformAppOpenapiQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformAppOpenapi:pageQuery')")
	@Operation(summary = "分页查询开放平台应用与开放接口配置")
	@GetMapping("/page")
	public PageResponse<OpenplatformAppOpenapiVO> pageQueryList(OpenplatformAppOpenapiPageQueryCommand openplatformAppOpenapiPageQueryCommand){
		return iOpenplatformAppOpenapiRepresentationApplicationService.pageQuery(openplatformAppOpenapiPageQueryCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:openplatformAppOpenapi:refreshCache')")
	@Operation(summary = "刷新开放平台应用配置的单个开放接口信息缓存")
	@PutMapping("/refreshCache")
	@OpLog(name = "刷新开放平台应用配置的单个开放接口信息缓存",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
	public SingleResponse<String> refreshCache(@RequestBody IdCommand deleteCommand){
		SingleResponse<OpenplatformAppOpenapiVO> openplatformAppOpenapiVOSingleResponse = iOpenplatformAppOpenapiRepresentationApplicationService.queryDetail(deleteCommand);
		OpenplatformAppDO openplatformAppDO = iOpenplatformAppService.getById(openplatformAppOpenapiVOSingleResponse.getData().getOpenplatformAppId());
		OpenplatformOpenapiDO openplatformOpenapiDO = iOpenplatformOpenapiService.getById(openplatformAppOpenapiVOSingleResponse.getData().getOpenplatformOpenapiId());
		if (CollectionUtil.isNotEmpty(globalOpenapiList)) {
			for (AbstractGlobalOpenapi abstractGlobalOpenapi : globalOpenapiList) {
				abstractGlobalOpenapi.refreshApiInfoCache(openplatformOpenapiDO.getUrl(), openplatformAppDO.getAppId());
			}
		}
		// 返回服务的地址，以方便在多实例部署时，区分机器已刷新
		return SingleResponse.of(NetUtil.getLocalhostStr());
	}
}

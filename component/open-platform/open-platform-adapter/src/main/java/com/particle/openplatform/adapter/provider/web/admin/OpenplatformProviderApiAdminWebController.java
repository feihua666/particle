package com.particle.openplatform.adapter.provider.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.provider.api.IOpenplatformProviderApiApplicationService;
import com.particle.openplatform.client.provider.api.representation.IOpenplatformProviderApiRepresentationApplicationService;
import com.particle.openplatform.client.provider.dto.command.OpenplatformProviderApiCreateCommand;
import com.particle.openplatform.client.provider.dto.command.OpenplatformProviderApiUpdateCommand;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderApiPageQueryCommand;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderApiQueryListCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderApiVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 开放平台供应商接口后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Tag(name = "开放平台供应商接口pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_provider_api")
public class OpenplatformProviderApiAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IOpenplatformProviderApiApplicationService iOpenplatformProviderApiApplicationService;
    @Autowired
    private IOpenplatformProviderApiRepresentationApplicationService iOpenplatformProviderApiRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderApi:create')")
    @Operation(summary = "添加开放平台供应商接口")
    @PostMapping("/create")
    @OpLog(name = "添加开放平台供应商接口",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
    public SingleResponse<OpenplatformProviderApiVO> create(@RequestBody OpenplatformProviderApiCreateCommand openplatformProviderApiCreateCommand){
        return iOpenplatformProviderApiApplicationService.create(openplatformProviderApiCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderApi:delete')")
    @Operation(summary = "删除开放平台供应商接口")
    @DeleteMapping("/delete")
    @OpLog(name = "删除开放平台供应商接口",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
    public SingleResponse<OpenplatformProviderApiVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iOpenplatformProviderApiApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderApi:update')")
    @Operation(summary = "更新开放平台供应商接口")
    @PutMapping("/update")
    @OpLog(name = "更新开放平台供应商接口",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
    public SingleResponse<OpenplatformProviderApiVO> update(@RequestBody OpenplatformProviderApiUpdateCommand openplatformProviderApiUpdateCommand){
        openplatformProviderApiUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iOpenplatformProviderApiApplicationService.update(openplatformProviderApiUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderApi:update')")
    @Operation(summary = "开放平台供应商接口更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<OpenplatformProviderApiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iOpenplatformProviderApiRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderApi:detail')")
    @Operation(summary = "开放平台供应商接口详情展示")
    @GetMapping("/detail")
    public SingleResponse<OpenplatformProviderApiVO> queryDetail(IdCommand detailCommand){
        return iOpenplatformProviderApiRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderApi:queryList')")
    @Operation(summary = "列表查询开放平台供应商接口")
    @GetMapping("/list")
    public MultiResponse<OpenplatformProviderApiVO> queryList(OpenplatformProviderApiQueryListCommand openplatformProviderApiQueryListCommand){
        openplatformProviderApiQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformProviderApiRepresentationApplicationService.queryList(openplatformProviderApiQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderApi:pageQuery')")
    @Operation(summary = "分页查询开放平台供应商接口")
    @GetMapping("/page")
    public PageResponse<OpenplatformProviderApiVO> pageQueryList(OpenplatformProviderApiPageQueryCommand openplatformProviderApiPageQueryCommand){
        openplatformProviderApiPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformProviderApiRepresentationApplicationService.pageQuery(openplatformProviderApiPageQueryCommand);
    }
}

package com.particle.openplatform.adapter.app.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.app.api.IOpenplatformAppQuotaApplicationService;
import com.particle.openplatform.client.app.api.representation.IOpenplatformAppQuotaRepresentationApplicationService;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppQuotaCreateCommand;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppQuotaUpdateCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppQuotaPageQueryCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppQuotaQueryListCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppQuotaVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 开放平台应用额度后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Tag(name = "开放平台应用额度pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_app_quota")
public class OpenplatformAppQuotaAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IOpenplatformAppQuotaApplicationService iOpenplatformAppQuotaApplicationService;
    @Autowired
    private IOpenplatformAppQuotaRepresentationApplicationService iOpenplatformAppQuotaRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:openplatformAppQuota:create')")
    @Operation(summary = "添加开放平台应用额度")
    @PostMapping("/create")
    @OpLog(name = "添加开放平台应用额度",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
    public SingleResponse<OpenplatformAppQuotaVO> create(@RequestBody OpenplatformAppQuotaCreateCommand openplatformAppQuotaCreateCommand){
        return iOpenplatformAppQuotaApplicationService.create(openplatformAppQuotaCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformAppQuota:delete')")
    @Operation(summary = "删除开放平台应用额度")
    @DeleteMapping("/delete")
    @OpLog(name = "删除开放平台应用额度",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
    public SingleResponse<OpenplatformAppQuotaVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iOpenplatformAppQuotaApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformAppQuota:update')")
    @Operation(summary = "更新开放平台应用额度")
    @PutMapping("/update")
    @OpLog(name = "更新开放平台应用额度",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
    public SingleResponse<OpenplatformAppQuotaVO> update(@RequestBody OpenplatformAppQuotaUpdateCommand openplatformAppQuotaUpdateCommand){
        openplatformAppQuotaUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iOpenplatformAppQuotaApplicationService.update(openplatformAppQuotaUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformAppQuota:update')")
    @Operation(summary = "开放平台应用额度更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<OpenplatformAppQuotaVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iOpenplatformAppQuotaRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformAppQuota:detail')")
    @Operation(summary = "开放平台应用额度详情展示")
    @GetMapping("/detail")
    public SingleResponse<OpenplatformAppQuotaVO> queryDetail(IdCommand detailCommand){
        return iOpenplatformAppQuotaRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformAppQuota:queryList')")
    @Operation(summary = "列表查询开放平台应用额度")
    @GetMapping("/list")
    public MultiResponse<OpenplatformAppQuotaVO> queryList(OpenplatformAppQuotaQueryListCommand openplatformAppQuotaQueryListCommand){
        openplatformAppQuotaQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformAppQuotaRepresentationApplicationService.queryList(openplatformAppQuotaQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformAppQuota:pageQuery')")
    @Operation(summary = "分页查询开放平台应用额度")
    @GetMapping("/page")
    public PageResponse<OpenplatformAppQuotaVO> pageQueryList(OpenplatformAppQuotaPageQueryCommand openplatformAppQuotaPageQueryCommand){
        openplatformAppQuotaPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformAppQuotaRepresentationApplicationService.pageQuery(openplatformAppQuotaPageQueryCommand);
    }
}

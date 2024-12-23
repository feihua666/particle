package com.particle.openplatform.adapter.bill.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.bill.api.IOpenplatformProviderRecordPrdApiMonthSummaryApplicationService;
import com.particle.openplatform.client.bill.api.representation.IOpenplatformProviderRecordPrdApiMonthSummaryRepresentationApplicationService;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdApiMonthSummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiMonthSummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiMonthSummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiMonthSummaryVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 开放平台供应商接口月汇总后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:34
 */
@Tag(name = "开放平台供应商接口月汇总pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_provider_record_prd_api_month_summary")
public class OpenplatformProviderRecordPrdApiMonthSummaryAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IOpenplatformProviderRecordPrdApiMonthSummaryApplicationService iOpenplatformProviderRecordPrdApiMonthSummaryApplicationService;
    @Autowired
    private IOpenplatformProviderRecordPrdApiMonthSummaryRepresentationApplicationService iOpenplatformProviderRecordPrdApiMonthSummaryRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdApiMonthSummary:create')")
    @Operation(summary = "添加开放平台供应商接口月汇总")
    @PostMapping("/create")
    @OpLog(name = "添加开放平台供应商接口月汇总",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
    public SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> create(@RequestBody OpenplatformProviderRecordPrdApiMonthSummaryCreateCommand openplatformProviderRecordPrdApiMonthSummaryCreateCommand){
        return iOpenplatformProviderRecordPrdApiMonthSummaryApplicationService.create(openplatformProviderRecordPrdApiMonthSummaryCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdApiMonthSummary:delete')")
    @Operation(summary = "删除开放平台供应商接口月汇总")
    @DeleteMapping("/delete")
    @OpLog(name = "删除开放平台供应商接口月汇总",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
    public SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iOpenplatformProviderRecordPrdApiMonthSummaryApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdApiMonthSummary:update')")
    @Operation(summary = "更新开放平台供应商接口月汇总")
    @PutMapping("/update")
    @OpLog(name = "更新开放平台供应商接口月汇总",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
    public SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> update(@RequestBody OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommand openplatformProviderRecordPrdApiMonthSummaryUpdateCommand){
        openplatformProviderRecordPrdApiMonthSummaryUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iOpenplatformProviderRecordPrdApiMonthSummaryApplicationService.update(openplatformProviderRecordPrdApiMonthSummaryUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdApiMonthSummary:update')")
    @Operation(summary = "开放平台供应商接口月汇总更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iOpenplatformProviderRecordPrdApiMonthSummaryRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdApiMonthSummary:detail')")
    @Operation(summary = "开放平台供应商接口月汇总详情展示")
    @GetMapping("/detail")
    public SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> queryDetail(IdCommand detailCommand){
        return iOpenplatformProviderRecordPrdApiMonthSummaryRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdApiMonthSummary:queryList')")
    @Operation(summary = "列表查询开放平台供应商接口月汇总")
    @GetMapping("/list")
    public MultiResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> queryList(OpenplatformProviderRecordPrdApiMonthSummaryQueryListCommand openplatformProviderRecordPrdApiMonthSummaryQueryListCommand){
        openplatformProviderRecordPrdApiMonthSummaryQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformProviderRecordPrdApiMonthSummaryRepresentationApplicationService.queryList(openplatformProviderRecordPrdApiMonthSummaryQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdApiMonthSummary:pageQuery')")
    @Operation(summary = "分页查询开放平台供应商接口月汇总")
    @GetMapping("/page")
    public PageResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> pageQueryList(OpenplatformProviderRecordPrdApiMonthSummaryPageQueryCommand openplatformProviderRecordPrdApiMonthSummaryPageQueryCommand){
        openplatformProviderRecordPrdApiMonthSummaryPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformProviderRecordPrdApiMonthSummaryRepresentationApplicationService.pageQuery(openplatformProviderRecordPrdApiMonthSummaryPageQueryCommand);
    }
}

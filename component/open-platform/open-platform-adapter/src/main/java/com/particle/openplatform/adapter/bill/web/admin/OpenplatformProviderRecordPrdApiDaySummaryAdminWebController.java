package com.particle.openplatform.adapter.bill.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.openplatform.client.bill.api.IOpenplatformProviderRecordPrdApiDaySummaryApplicationService;
import com.particle.openplatform.client.bill.api.representation.IOpenplatformProviderRecordPrdApiDaySummaryRepresentationApplicationService;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdApiDaySummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiDaySummaryVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdApiDaySummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiDaySummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiDaySummaryQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
/**
 * <p>
 * 开放平台供应商接口日汇总后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Tag(name = "开放平台供应商接口日汇总pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_provider_record_prd_api_day_summary")
public class OpenplatformProviderRecordPrdApiDaySummaryAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IOpenplatformProviderRecordPrdApiDaySummaryApplicationService iOpenplatformProviderRecordPrdApiDaySummaryApplicationService;
    @Autowired
    private IOpenplatformProviderRecordPrdApiDaySummaryRepresentationApplicationService iOpenplatformProviderRecordPrdApiDaySummaryRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdApiDaySummary:create')")
    @Operation(summary = "添加开放平台供应商接口日汇总")
    @PostMapping("/create")
    @OpLog(name = "添加开放平台供应商接口日汇总",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
    public SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> create(@RequestBody OpenplatformProviderRecordPrdApiDaySummaryCreateCommand openplatformProviderRecordPrdApiDaySummaryCreateCommand){
        return iOpenplatformProviderRecordPrdApiDaySummaryApplicationService.create(openplatformProviderRecordPrdApiDaySummaryCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdApiDaySummary:delete')")
    @Operation(summary = "删除开放平台供应商接口日汇总")
    @DeleteMapping("/delete")
    @OpLog(name = "删除开放平台供应商接口日汇总",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
    public SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iOpenplatformProviderRecordPrdApiDaySummaryApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdApiDaySummary:update')")
    @Operation(summary = "更新开放平台供应商接口日汇总")
    @PutMapping("/update")
    @OpLog(name = "更新开放平台供应商接口日汇总",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
    public SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> update(@RequestBody OpenplatformProviderRecordPrdApiDaySummaryUpdateCommand openplatformProviderRecordPrdApiDaySummaryUpdateCommand){
        openplatformProviderRecordPrdApiDaySummaryUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iOpenplatformProviderRecordPrdApiDaySummaryApplicationService.update(openplatformProviderRecordPrdApiDaySummaryUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdApiDaySummary:update')")
    @Operation(summary = "开放平台供应商接口日汇总更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iOpenplatformProviderRecordPrdApiDaySummaryRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdApiDaySummary:detail')")
    @Operation(summary = "开放平台供应商接口日汇总详情展示")
    @GetMapping("/detail")
    public SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> queryDetail(IdCommand detailCommand){
        return iOpenplatformProviderRecordPrdApiDaySummaryRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdApiDaySummary:queryList')")
    @Operation(summary = "列表查询开放平台供应商接口日汇总")
    @GetMapping("/list")
    public MultiResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> queryList(OpenplatformProviderRecordPrdApiDaySummaryQueryListCommand openplatformProviderRecordPrdApiDaySummaryQueryListCommand){
        openplatformProviderRecordPrdApiDaySummaryQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformProviderRecordPrdApiDaySummaryRepresentationApplicationService.queryList(openplatformProviderRecordPrdApiDaySummaryQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdApiDaySummary:pageQuery')")
    @Operation(summary = "分页查询开放平台供应商接口日汇总")
    @GetMapping("/page")
    public PageResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> pageQueryList(OpenplatformProviderRecordPrdApiDaySummaryPageQueryCommand openplatformProviderRecordPrdApiDaySummaryPageQueryCommand){
        openplatformProviderRecordPrdApiDaySummaryPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformProviderRecordPrdApiDaySummaryRepresentationApplicationService.pageQuery(openplatformProviderRecordPrdApiDaySummaryPageQueryCommand);
    }
}
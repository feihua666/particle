package com.particle.openplatform.adapter.bill.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryApplicationService;
import com.particle.openplatform.client.bill.api.representation.IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryRepresentationApplicationService;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand;
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
 * 开放平台应用开放接口日实时汇总后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-10-15 10:30:43
 */
@Tag(name = "开放平台应用开放接口日实时汇总pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_openapi_record_app_openapi_day_rt_summary")
public class OpenplatformOpenapiRecordAppOpenapiDayRtSummaryAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryApplicationService iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryApplicationService;
    @Autowired
    private IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryRepresentationApplicationService iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiDayRtSummary:create')")
    @Operation(summary = "添加开放平台应用开放接口日实时汇总")
    @PostMapping("/create")
    @OpLog(name = "添加开放平台应用开放接口日实时汇总",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> create(@RequestBody OpenplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommand){
        return iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryApplicationService.create(openplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiDayRtSummary:delete')")
    @Operation(summary = "删除开放平台应用开放接口日实时汇总")
    @DeleteMapping("/delete")
    @OpLog(name = "删除开放平台应用开放接口日实时汇总",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiDayRtSummary:update')")
    @Operation(summary = "更新开放平台应用开放接口日实时汇总")
    @PutMapping("/update")
    @OpLog(name = "更新开放平台应用开放接口日实时汇总",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> update(@RequestBody OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand){
        openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryApplicationService.update(openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiDayRtSummary:update')")
    @Operation(summary = "开放平台应用开放接口日实时汇总更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiDayRtSummary:detail')")
    @Operation(summary = "开放平台应用开放接口日实时汇总详情展示")
    @GetMapping("/detail")
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> queryDetail(IdCommand detailCommand){
        return iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiDayRtSummary:queryList')")
    @Operation(summary = "列表查询开放平台应用开放接口日实时汇总")
    @GetMapping("/list")
    public MultiResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> queryList(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand){
        openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryRepresentationApplicationService.queryList(openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiDayRtSummary:pageQuery')")
    @Operation(summary = "分页查询开放平台应用开放接口日实时汇总")
    @GetMapping("/page")
    public PageResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> pageQueryList(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand){
        openplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryRepresentationApplicationService.pageQuery(openplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand);
    }
}
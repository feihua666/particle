package com.particle.openplatform.adapter.bill.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordAppMonthBillApplicationService;
import com.particle.openplatform.client.bill.api.representation.IOpenplatformOpenapiRecordAppMonthBillRepresentationApplicationService;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppMonthBillCreateCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppMonthBillStatisticCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppMonthBillUpdateCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppMonthBillPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppMonthBillQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppMonthBillVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * <p>
 * 开放平台应用月账单后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Tag(name = "开放平台应用月账单pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_openapi_record_app_month_bill")
public class OpenplatformOpenapiRecordAppMonthBillAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IOpenplatformOpenapiRecordAppMonthBillApplicationService iOpenplatformOpenapiRecordAppMonthBillApplicationService;
    @Autowired
    private IOpenplatformOpenapiRecordAppMonthBillRepresentationApplicationService iOpenplatformOpenapiRecordAppMonthBillRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppMonthBill:create')")
    @Operation(summary = "添加开放平台应用月账单")
    @PostMapping("/create")
    @OpLog(name = "添加开放平台应用月账单",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> create(@RequestBody OpenplatformOpenapiRecordAppMonthBillCreateCommand openplatformOpenapiRecordAppMonthBillCreateCommand){
        return iOpenplatformOpenapiRecordAppMonthBillApplicationService.create(openplatformOpenapiRecordAppMonthBillCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppMonthBill:delete')")
    @Operation(summary = "删除开放平台应用月账单")
    @DeleteMapping("/delete")
    @OpLog(name = "删除开放平台应用月账单",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iOpenplatformOpenapiRecordAppMonthBillApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppMonthBill:update')")
    @Operation(summary = "更新开放平台应用月账单")
    @PutMapping("/update")
    @OpLog(name = "更新开放平台应用月账单",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> update(@RequestBody OpenplatformOpenapiRecordAppMonthBillUpdateCommand openplatformOpenapiRecordAppMonthBillUpdateCommand){
        openplatformOpenapiRecordAppMonthBillUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iOpenplatformOpenapiRecordAppMonthBillApplicationService.update(openplatformOpenapiRecordAppMonthBillUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppMonthBill:update')")
    @Operation(summary = "开放平台应用月账单更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iOpenplatformOpenapiRecordAppMonthBillRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppMonthBill:detail')")
    @Operation(summary = "开放平台应用月账单详情展示")
    @GetMapping("/detail")
    public SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> queryDetail(IdCommand detailCommand){
        return iOpenplatformOpenapiRecordAppMonthBillRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppMonthBill:queryList')")
    @Operation(summary = "列表查询开放平台应用月账单")
    @GetMapping("/list")
    public MultiResponse<OpenplatformOpenapiRecordAppMonthBillVO> queryList(OpenplatformOpenapiRecordAppMonthBillQueryListCommand openplatformOpenapiRecordAppMonthBillQueryListCommand){
        openplatformOpenapiRecordAppMonthBillQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformOpenapiRecordAppMonthBillRepresentationApplicationService.queryList(openplatformOpenapiRecordAppMonthBillQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppMonthBill:pageQuery')")
    @Operation(summary = "分页查询开放平台应用月账单")
    @GetMapping("/page")
    public PageResponse<OpenplatformOpenapiRecordAppMonthBillVO> pageQueryList(OpenplatformOpenapiRecordAppMonthBillPageQueryCommand openplatformOpenapiRecordAppMonthBillPageQueryCommand){
        openplatformOpenapiRecordAppMonthBillPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformOpenapiRecordAppMonthBillRepresentationApplicationService.pageQuery(openplatformOpenapiRecordAppMonthBillPageQueryCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppMonthBill:lastMonthStatistic')")
    @Operation(summary = "统计上月开放平台应用月账单")
    @PostMapping("/lastMonthStatistic")
    @OpLog(name = "统计上月开放平台应用月账单",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
    public Response lastMonthStatistic(@RequestBody OpenplatformOpenapiRecordAppMonthBillStatisticCommand command){
        LocalDate localDate = LocalDate.now();
        LocalDate lastMonth = localDate.minusMonths(1);
        Integer year = lastMonth.getYear();
        Integer month = lastMonth.getMonthValue();
        return iOpenplatformOpenapiRecordAppMonthBillApplicationService.statistic(year,month,command.getIsIncludeMonthSummary(),command.getIsIncludeDaySummary(),null);
    }
    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppMonthBill:thisMonthStatistic')")
    @Operation(summary = "统计本月开放平台应用月账单")
    @PostMapping("/thisMonthStatistic")
    @OpLog(name = "统计本月开放平台应用月账单",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
    public Response thisMonthStatistic(@RequestBody OpenplatformOpenapiRecordAppMonthBillStatisticCommand command){
        LocalDate localDate = LocalDate.now();
        Integer year = localDate.getYear();
        Integer month = localDate.getMonthValue();
        return iOpenplatformOpenapiRecordAppMonthBillApplicationService.statistic(year,month,command.getIsIncludeMonthSummary(),command.getIsIncludeDaySummary(),null);
    }
}

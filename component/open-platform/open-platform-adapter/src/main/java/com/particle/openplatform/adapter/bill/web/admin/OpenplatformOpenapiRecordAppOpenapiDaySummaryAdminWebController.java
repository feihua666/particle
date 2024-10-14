package com.particle.openplatform.adapter.bill.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService;
import com.particle.openplatform.client.bill.api.representation.IOpenplatformOpenapiRecordAppOpenapiDaySummaryRepresentationApplicationService;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDaySummaryVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand;
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

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 开放平台应用开放接口日汇总后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Tag(name = "开放平台应用开放接口日汇总pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_openapi_record_app_openapi_day_summary")
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService iOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService;
    @Autowired
    private IOpenplatformOpenapiRecordAppOpenapiDaySummaryRepresentationApplicationService iOpenplatformOpenapiRecordAppOpenapiDaySummaryRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiDaySummary:create')")
    @Operation(summary = "添加开放平台应用开放接口日汇总")
    @PostMapping("/create")
    @OpLog(name = "添加开放平台应用开放接口日汇总",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> create(@RequestBody OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand){
        return iOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService.create(openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiDaySummary:delete')")
    @Operation(summary = "删除开放平台应用开放接口日汇总")
    @DeleteMapping("/delete")
    @OpLog(name = "删除开放平台应用开放接口日汇总",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.delete)
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiDaySummary:update')")
    @Operation(summary = "更新开放平台应用开放接口日汇总")
    @PutMapping("/update")
    @OpLog(name = "更新开放平台应用开放接口日汇总",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.update)
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> update(@RequestBody OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand){
        openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService.update(openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiDaySummary:update')")
    @Operation(summary = "开放平台应用开放接口日汇总更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iOpenplatformOpenapiRecordAppOpenapiDaySummaryRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiDaySummary:detail')")
    @Operation(summary = "开放平台应用开放接口日汇总详情展示")
    @GetMapping("/detail")
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> queryDetail(IdCommand detailCommand){
        return iOpenplatformOpenapiRecordAppOpenapiDaySummaryRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiDaySummary:queryList')")
    @Operation(summary = "列表查询开放平台应用开放接口日汇总")
    @GetMapping("/list")
    public MultiResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> queryList(OpenplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand openplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand){
        openplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformOpenapiRecordAppOpenapiDaySummaryRepresentationApplicationService.queryList(openplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiDaySummary:pageQuery')")
    @Operation(summary = "分页查询开放平台应用开放接口日汇总")
    @GetMapping("/page")
    public PageResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> pageQueryList(OpenplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand openplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand){
        openplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformOpenapiRecordAppOpenapiDaySummaryRepresentationApplicationService.pageQuery(openplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiDaySummary:yesterdayStatistic')")
    @Operation(summary = "统计昨天开放平台应用开放接口日汇总")
    @PostMapping("/yesterdayStatistic")
    @OpLog(name = "统计昨天开放平台应用开放接口日汇总",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
    public Response yesterdayStatistic(){
        LocalDate localDate = LocalDate.now();
        LocalDate yesterday = localDate.minusDays(1);
        return iOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService.statistic(yesterday,null);
    }
    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiDaySummary:todayStatistic')")
    @Operation(summary = "统计今天天开放平台应用开放接口日汇总")
    @PostMapping("/todayStatistic")
    @OpLog(name = "统计今天天开放平台应用开放接口日汇总",module = OpLogConstants.Module.openPlatform,type = OpLogConstants.Type.create)
    public Response todayStatistic(){
        LocalDate localDate = LocalDate.now();
        return iOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService.statistic(localDate,null);
    }
}
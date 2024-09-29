package com.particle.openplatform.adapter.bill.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordAppOpenapiMonthSummaryApplicationService;
import com.particle.openplatform.client.bill.api.representation.IOpenplatformOpenapiRecordAppOpenapiMonthSummaryRepresentationApplicationService;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand;
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
 * 开放平台应用开放接口月汇总后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Tag(name = "开放平台应用开放接口月汇总pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_openapi_record_app_openapi_month_summary")
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IOpenplatformOpenapiRecordAppOpenapiMonthSummaryApplicationService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryApplicationService;
    @Autowired
    private IOpenplatformOpenapiRecordAppOpenapiMonthSummaryRepresentationApplicationService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiMonthSummary:create')")
    @Operation(summary = "添加开放平台应用开放接口月汇总")
    @PostMapping("/create")
    @OpLog(name = "添加开放平台应用开放接口月汇总",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> create(@RequestBody OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand){
        return iOpenplatformOpenapiRecordAppOpenapiMonthSummaryApplicationService.create(openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiMonthSummary:delete')")
    @Operation(summary = "删除开放平台应用开放接口月汇总")
    @DeleteMapping("/delete")
    @OpLog(name = "删除开放平台应用开放接口月汇总",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iOpenplatformOpenapiRecordAppOpenapiMonthSummaryApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiMonthSummary:update')")
    @Operation(summary = "更新开放平台应用开放接口月汇总")
    @PutMapping("/update")
    @OpLog(name = "更新开放平台应用开放接口月汇总",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> update(@RequestBody OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand){
        openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iOpenplatformOpenapiRecordAppOpenapiMonthSummaryApplicationService.update(openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiMonthSummary:update')")
    @Operation(summary = "开放平台应用开放接口月汇总更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iOpenplatformOpenapiRecordAppOpenapiMonthSummaryRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiMonthSummary:detail')")
    @Operation(summary = "开放平台应用开放接口月汇总详情展示")
    @GetMapping("/detail")
    public SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> queryDetail(IdCommand detailCommand){
        return iOpenplatformOpenapiRecordAppOpenapiMonthSummaryRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiMonthSummary:queryList')")
    @Operation(summary = "列表查询开放平台应用开放接口月汇总")
    @GetMapping("/list")
    public MultiResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> queryList(OpenplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand openplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand){
        openplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformOpenapiRecordAppOpenapiMonthSummaryRepresentationApplicationService.queryList(openplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordAppOpenapiMonthSummary:pageQuery')")
    @Operation(summary = "分页查询开放平台应用开放接口月汇总")
    @GetMapping("/page")
    public PageResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> pageQueryList(OpenplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand openplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand){
        openplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformOpenapiRecordAppOpenapiMonthSummaryRepresentationApplicationService.pageQuery(openplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand);
    }
}
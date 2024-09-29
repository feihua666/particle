package com.particle.openplatform.adapter.bill.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.openplatform.client.bill.api.IOpenplatformProviderRecordPrdMonthBillApplicationService;
import com.particle.openplatform.client.bill.api.representation.IOpenplatformProviderRecordPrdMonthBillRepresentationApplicationService;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdMonthBillCreateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdMonthBillVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdMonthBillUpdateCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdMonthBillPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdMonthBillQueryListCommand;
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
 * 开放平台供应商月账单后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:53
 */
@Tag(name = "开放平台供应商月账单pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_provider_record_prd_month_bill")
public class OpenplatformProviderRecordPrdMonthBillAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IOpenplatformProviderRecordPrdMonthBillApplicationService iOpenplatformProviderRecordPrdMonthBillApplicationService;
    @Autowired
    private IOpenplatformProviderRecordPrdMonthBillRepresentationApplicationService iOpenplatformProviderRecordPrdMonthBillRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdMonthBill:create')")
    @Operation(summary = "添加开放平台供应商月账单")
    @PostMapping("/create")
    @OpLog(name = "添加开放平台供应商月账单",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> create(@RequestBody OpenplatformProviderRecordPrdMonthBillCreateCommand openplatformProviderRecordPrdMonthBillCreateCommand){
        return iOpenplatformProviderRecordPrdMonthBillApplicationService.create(openplatformProviderRecordPrdMonthBillCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdMonthBill:delete')")
    @Operation(summary = "删除开放平台供应商月账单")
    @DeleteMapping("/delete")
    @OpLog(name = "删除开放平台供应商月账单",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iOpenplatformProviderRecordPrdMonthBillApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdMonthBill:update')")
    @Operation(summary = "更新开放平台供应商月账单")
    @PutMapping("/update")
    @OpLog(name = "更新开放平台供应商月账单",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> update(@RequestBody OpenplatformProviderRecordPrdMonthBillUpdateCommand openplatformProviderRecordPrdMonthBillUpdateCommand){
        openplatformProviderRecordPrdMonthBillUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iOpenplatformProviderRecordPrdMonthBillApplicationService.update(openplatformProviderRecordPrdMonthBillUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdMonthBill:update')")
    @Operation(summary = "开放平台供应商月账单更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iOpenplatformProviderRecordPrdMonthBillRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdMonthBill:detail')")
    @Operation(summary = "开放平台供应商月账单详情展示")
    @GetMapping("/detail")
    public SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> queryDetail(IdCommand detailCommand){
        return iOpenplatformProviderRecordPrdMonthBillRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdMonthBill:queryList')")
    @Operation(summary = "列表查询开放平台供应商月账单")
    @GetMapping("/list")
    public MultiResponse<OpenplatformProviderRecordPrdMonthBillVO> queryList(OpenplatformProviderRecordPrdMonthBillQueryListCommand openplatformProviderRecordPrdMonthBillQueryListCommand){
        openplatformProviderRecordPrdMonthBillQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformProviderRecordPrdMonthBillRepresentationApplicationService.queryList(openplatformProviderRecordPrdMonthBillQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformProviderRecordPrdMonthBill:pageQuery')")
    @Operation(summary = "分页查询开放平台供应商月账单")
    @GetMapping("/page")
    public PageResponse<OpenplatformProviderRecordPrdMonthBillVO> pageQueryList(OpenplatformProviderRecordPrdMonthBillPageQueryCommand openplatformProviderRecordPrdMonthBillPageQueryCommand){
        openplatformProviderRecordPrdMonthBillPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformProviderRecordPrdMonthBillRepresentationApplicationService.pageQuery(openplatformProviderRecordPrdMonthBillPageQueryCommand);
    }
}
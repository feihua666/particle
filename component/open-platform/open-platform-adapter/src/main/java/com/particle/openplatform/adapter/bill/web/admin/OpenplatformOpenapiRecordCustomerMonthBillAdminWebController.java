package com.particle.openplatform.adapter.bill.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordCustomerMonthBillApplicationService;
import com.particle.openplatform.client.bill.api.representation.IOpenplatformOpenapiRecordCustomerMonthBillRepresentationApplicationService;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordCustomerMonthBillCreateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordCustomerMonthBillVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordCustomerMonthBillUpdateCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordCustomerMonthBillPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordCustomerMonthBillQueryListCommand;
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
 * 开放平台客户月账单后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@Tag(name = "开放平台客户月账单pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_openapi_record_customer_month_bill")
public class OpenplatformOpenapiRecordCustomerMonthBillAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IOpenplatformOpenapiRecordCustomerMonthBillApplicationService iOpenplatformOpenapiRecordCustomerMonthBillApplicationService;
    @Autowired
    private IOpenplatformOpenapiRecordCustomerMonthBillRepresentationApplicationService iOpenplatformOpenapiRecordCustomerMonthBillRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordCustomerMonthBill:create')")
    @Operation(summary = "添加开放平台客户月账单")
    @PostMapping("/create")
    @OpLog(name = "添加开放平台客户月账单",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> create(@RequestBody OpenplatformOpenapiRecordCustomerMonthBillCreateCommand openplatformOpenapiRecordCustomerMonthBillCreateCommand){
        return iOpenplatformOpenapiRecordCustomerMonthBillApplicationService.create(openplatformOpenapiRecordCustomerMonthBillCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordCustomerMonthBill:delete')")
    @Operation(summary = "删除开放平台客户月账单")
    @DeleteMapping("/delete")
    @OpLog(name = "删除开放平台客户月账单",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iOpenplatformOpenapiRecordCustomerMonthBillApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordCustomerMonthBill:update')")
    @Operation(summary = "更新开放平台客户月账单")
    @PutMapping("/update")
    @OpLog(name = "更新开放平台客户月账单",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> update(@RequestBody OpenplatformOpenapiRecordCustomerMonthBillUpdateCommand openplatformOpenapiRecordCustomerMonthBillUpdateCommand){
        openplatformOpenapiRecordCustomerMonthBillUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iOpenplatformOpenapiRecordCustomerMonthBillApplicationService.update(openplatformOpenapiRecordCustomerMonthBillUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordCustomerMonthBill:update')")
    @Operation(summary = "开放平台客户月账单更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iOpenplatformOpenapiRecordCustomerMonthBillRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordCustomerMonthBill:detail')")
    @Operation(summary = "开放平台客户月账单详情展示")
    @GetMapping("/detail")
    public SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> queryDetail(IdCommand detailCommand){
        return iOpenplatformOpenapiRecordCustomerMonthBillRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordCustomerMonthBill:queryList')")
    @Operation(summary = "列表查询开放平台客户月账单")
    @GetMapping("/list")
    public MultiResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> queryList(OpenplatformOpenapiRecordCustomerMonthBillQueryListCommand openplatformOpenapiRecordCustomerMonthBillQueryListCommand){
        openplatformOpenapiRecordCustomerMonthBillQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformOpenapiRecordCustomerMonthBillRepresentationApplicationService.queryList(openplatformOpenapiRecordCustomerMonthBillQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiRecordCustomerMonthBill:pageQuery')")
    @Operation(summary = "分页查询开放平台客户月账单")
    @GetMapping("/page")
    public PageResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> pageQueryList(OpenplatformOpenapiRecordCustomerMonthBillPageQueryCommand openplatformOpenapiRecordCustomerMonthBillPageQueryCommand){
        openplatformOpenapiRecordCustomerMonthBillPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformOpenapiRecordCustomerMonthBillRepresentationApplicationService.pageQuery(openplatformOpenapiRecordCustomerMonthBillPageQueryCommand);
    }
}
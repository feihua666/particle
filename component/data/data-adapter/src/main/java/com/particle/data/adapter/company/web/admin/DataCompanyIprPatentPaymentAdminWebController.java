package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPatentPaymentApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentPaymentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPaymentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPaymentVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPaymentUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPaymentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPaymentQueryListCommand;
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
 * 企业知识产权专利缴费信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:27
 */
@Tag(name = "企业知识产权专利缴费信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_patent_payment")
public class DataCompanyIprPatentPaymentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPatentPaymentApplicationService iDataCompanyIprPatentPaymentApplicationService;
    @Autowired
    private IDataCompanyIprPatentPaymentRepresentationApplicationService iDataCompanyIprPatentPaymentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentPayment:create')")
    @Operation(summary = "添加企业知识产权专利缴费信息")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权专利缴费信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPatentPaymentVO> create(@RequestBody DataCompanyIprPatentPaymentCreateCommand dataCompanyIprPatentPaymentCreateCommand){
        return iDataCompanyIprPatentPaymentApplicationService.create(dataCompanyIprPatentPaymentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentPayment:delete')")
    @Operation(summary = "删除企业知识产权专利缴费信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权专利缴费信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPatentPaymentVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPatentPaymentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentPayment:update')")
    @Operation(summary = "更新企业知识产权专利缴费信息")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权专利缴费信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPatentPaymentVO> update(@RequestBody DataCompanyIprPatentPaymentUpdateCommand dataCompanyIprPatentPaymentUpdateCommand){
        dataCompanyIprPatentPaymentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPatentPaymentApplicationService.update(dataCompanyIprPatentPaymentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentPayment:update')")
    @Operation(summary = "企业知识产权专利缴费信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPatentPaymentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPatentPaymentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentPayment:detail')")
    @Operation(summary = "企业知识产权专利缴费信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPatentPaymentVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPatentPaymentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentPayment:queryList')")
    @Operation(summary = "列表查询企业知识产权专利缴费信息")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPatentPaymentVO> queryList(DataCompanyIprPatentPaymentQueryListCommand dataCompanyIprPatentPaymentQueryListCommand){
        dataCompanyIprPatentPaymentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentPaymentRepresentationApplicationService.queryList(dataCompanyIprPatentPaymentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentPayment:pageQuery')")
    @Operation(summary = "分页查询企业知识产权专利缴费信息")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPatentPaymentVO> pageQueryList(DataCompanyIprPatentPaymentPageQueryCommand dataCompanyIprPatentPaymentPageQueryCommand){
        dataCompanyIprPatentPaymentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentPaymentRepresentationApplicationService.pageQuery(dataCompanyIprPatentPaymentPageQueryCommand);
    }
}
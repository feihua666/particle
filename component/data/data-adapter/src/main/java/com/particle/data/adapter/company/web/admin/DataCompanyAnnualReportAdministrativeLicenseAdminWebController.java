package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyAnnualReportAdministrativeLicenseApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportAdministrativeLicenseRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAdministrativeLicenseCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAdministrativeLicenseVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAdministrativeLicenseUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAdministrativeLicensePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAdministrativeLicenseQueryListCommand;
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
 * 企业年报行政许可后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
@Tag(name = "企业年报行政许可pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_annual_report_administrative_license")
public class DataCompanyAnnualReportAdministrativeLicenseAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyAnnualReportAdministrativeLicenseApplicationService iDataCompanyAnnualReportAdministrativeLicenseApplicationService;
    @Autowired
    private IDataCompanyAnnualReportAdministrativeLicenseRepresentationApplicationService iDataCompanyAnnualReportAdministrativeLicenseRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportAdministrativeLicense:create')")
    @Operation(summary = "添加企业年报行政许可")
    @PostMapping("/create")
    @OpLog(name = "添加企业年报行政许可",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> create(@RequestBody DataCompanyAnnualReportAdministrativeLicenseCreateCommand dataCompanyAnnualReportAdministrativeLicenseCreateCommand){
        return iDataCompanyAnnualReportAdministrativeLicenseApplicationService.create(dataCompanyAnnualReportAdministrativeLicenseCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportAdministrativeLicense:delete')")
    @Operation(summary = "删除企业年报行政许可")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业年报行政许可",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyAnnualReportAdministrativeLicenseApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportAdministrativeLicense:update')")
    @Operation(summary = "更新企业年报行政许可")
    @PutMapping("/update")
    @OpLog(name = "更新企业年报行政许可",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> update(@RequestBody DataCompanyAnnualReportAdministrativeLicenseUpdateCommand dataCompanyAnnualReportAdministrativeLicenseUpdateCommand){
        dataCompanyAnnualReportAdministrativeLicenseUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyAnnualReportAdministrativeLicenseApplicationService.update(dataCompanyAnnualReportAdministrativeLicenseUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportAdministrativeLicense:update')")
    @Operation(summary = "企业年报行政许可更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyAnnualReportAdministrativeLicenseRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportAdministrativeLicense:detail')")
    @Operation(summary = "企业年报行政许可详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyAnnualReportAdministrativeLicenseRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportAdministrativeLicense:queryList')")
    @Operation(summary = "列表查询企业年报行政许可")
    @GetMapping("/list")
    public MultiResponse<DataCompanyAnnualReportAdministrativeLicenseVO> queryList(DataCompanyAnnualReportAdministrativeLicenseQueryListCommand dataCompanyAnnualReportAdministrativeLicenseQueryListCommand){
        dataCompanyAnnualReportAdministrativeLicenseQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportAdministrativeLicenseRepresentationApplicationService.queryList(dataCompanyAnnualReportAdministrativeLicenseQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportAdministrativeLicense:pageQuery')")
    @Operation(summary = "分页查询企业年报行政许可")
    @GetMapping("/page")
    public PageResponse<DataCompanyAnnualReportAdministrativeLicenseVO> pageQueryList(DataCompanyAnnualReportAdministrativeLicensePageQueryCommand dataCompanyAnnualReportAdministrativeLicensePageQueryCommand){
        dataCompanyAnnualReportAdministrativeLicensePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportAdministrativeLicenseRepresentationApplicationService.pageQuery(dataCompanyAnnualReportAdministrativeLicensePageQueryCommand);
    }
}
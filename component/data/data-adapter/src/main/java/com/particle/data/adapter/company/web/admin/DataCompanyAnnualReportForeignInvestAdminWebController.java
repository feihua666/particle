package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyAnnualReportForeignInvestApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportForeignInvestRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignInvestCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignInvestVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignInvestUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignInvestPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignInvestQueryListCommand;
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
 * 企业年报对外投资后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
@Tag(name = "企业年报对外投资pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_annual_report_foreign_invest")
public class DataCompanyAnnualReportForeignInvestAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyAnnualReportForeignInvestApplicationService iDataCompanyAnnualReportForeignInvestApplicationService;
    @Autowired
    private IDataCompanyAnnualReportForeignInvestRepresentationApplicationService iDataCompanyAnnualReportForeignInvestRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportForeignInvest:create')")
    @Operation(summary = "添加企业年报对外投资")
    @PostMapping("/create")
    @OpLog(name = "添加企业年报对外投资",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyAnnualReportForeignInvestVO> create(@RequestBody DataCompanyAnnualReportForeignInvestCreateCommand dataCompanyAnnualReportForeignInvestCreateCommand){
        return iDataCompanyAnnualReportForeignInvestApplicationService.create(dataCompanyAnnualReportForeignInvestCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportForeignInvest:delete')")
    @Operation(summary = "删除企业年报对外投资")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业年报对外投资",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyAnnualReportForeignInvestVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyAnnualReportForeignInvestApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportForeignInvest:update')")
    @Operation(summary = "更新企业年报对外投资")
    @PutMapping("/update")
    @OpLog(name = "更新企业年报对外投资",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyAnnualReportForeignInvestVO> update(@RequestBody DataCompanyAnnualReportForeignInvestUpdateCommand dataCompanyAnnualReportForeignInvestUpdateCommand){
        dataCompanyAnnualReportForeignInvestUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyAnnualReportForeignInvestApplicationService.update(dataCompanyAnnualReportForeignInvestUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportForeignInvest:update')")
    @Operation(summary = "企业年报对外投资更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyAnnualReportForeignInvestVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyAnnualReportForeignInvestRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportForeignInvest:detail')")
    @Operation(summary = "企业年报对外投资详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyAnnualReportForeignInvestVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyAnnualReportForeignInvestRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportForeignInvest:queryList')")
    @Operation(summary = "列表查询企业年报对外投资")
    @GetMapping("/list")
    public MultiResponse<DataCompanyAnnualReportForeignInvestVO> queryList(DataCompanyAnnualReportForeignInvestQueryListCommand dataCompanyAnnualReportForeignInvestQueryListCommand){
        dataCompanyAnnualReportForeignInvestQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportForeignInvestRepresentationApplicationService.queryList(dataCompanyAnnualReportForeignInvestQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportForeignInvest:pageQuery')")
    @Operation(summary = "分页查询企业年报对外投资")
    @GetMapping("/page")
    public PageResponse<DataCompanyAnnualReportForeignInvestVO> pageQueryList(DataCompanyAnnualReportForeignInvestPageQueryCommand dataCompanyAnnualReportForeignInvestPageQueryCommand){
        dataCompanyAnnualReportForeignInvestPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportForeignInvestRepresentationApplicationService.pageQuery(dataCompanyAnnualReportForeignInvestPageQueryCommand);
    }
}
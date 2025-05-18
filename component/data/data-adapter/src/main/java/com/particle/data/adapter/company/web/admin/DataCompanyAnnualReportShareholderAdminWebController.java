package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyAnnualReportShareholderApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportShareholderRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportShareholderCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportShareholderVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportShareholderUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportShareholderPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportShareholderQueryListCommand;
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
 * 企业年报股东后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Tag(name = "企业年报股东pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_annual_report_shareholder")
public class DataCompanyAnnualReportShareholderAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyAnnualReportShareholderApplicationService iDataCompanyAnnualReportShareholderApplicationService;
    @Autowired
    private IDataCompanyAnnualReportShareholderRepresentationApplicationService iDataCompanyAnnualReportShareholderRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportShareholder:create')")
    @Operation(summary = "添加企业年报股东")
    @PostMapping("/create")
    @OpLog(name = "添加企业年报股东",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyAnnualReportShareholderVO> create(@RequestBody DataCompanyAnnualReportShareholderCreateCommand dataCompanyAnnualReportShareholderCreateCommand){
        return iDataCompanyAnnualReportShareholderApplicationService.create(dataCompanyAnnualReportShareholderCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportShareholder:delete')")
    @Operation(summary = "删除企业年报股东")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业年报股东",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyAnnualReportShareholderVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyAnnualReportShareholderApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportShareholder:update')")
    @Operation(summary = "更新企业年报股东")
    @PutMapping("/update")
    @OpLog(name = "更新企业年报股东",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyAnnualReportShareholderVO> update(@RequestBody DataCompanyAnnualReportShareholderUpdateCommand dataCompanyAnnualReportShareholderUpdateCommand){
        dataCompanyAnnualReportShareholderUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyAnnualReportShareholderApplicationService.update(dataCompanyAnnualReportShareholderUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportShareholder:update')")
    @Operation(summary = "企业年报股东更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyAnnualReportShareholderVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyAnnualReportShareholderRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportShareholder:detail')")
    @Operation(summary = "企业年报股东详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyAnnualReportShareholderVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyAnnualReportShareholderRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportShareholder:queryList')")
    @Operation(summary = "列表查询企业年报股东")
    @GetMapping("/list")
    public MultiResponse<DataCompanyAnnualReportShareholderVO> queryList(DataCompanyAnnualReportShareholderQueryListCommand dataCompanyAnnualReportShareholderQueryListCommand){
        dataCompanyAnnualReportShareholderQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportShareholderRepresentationApplicationService.queryList(dataCompanyAnnualReportShareholderQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportShareholder:pageQuery')")
    @Operation(summary = "分页查询企业年报股东")
    @GetMapping("/page")
    public PageResponse<DataCompanyAnnualReportShareholderVO> pageQueryList(DataCompanyAnnualReportShareholderPageQueryCommand dataCompanyAnnualReportShareholderPageQueryCommand){
        dataCompanyAnnualReportShareholderPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportShareholderRepresentationApplicationService.pageQuery(dataCompanyAnnualReportShareholderPageQueryCommand);
    }
}
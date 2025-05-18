package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyAnnualReportAssetsApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportAssetsRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAssetsCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAssetsVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAssetsUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAssetsPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAssetsQueryListCommand;
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
 * 企业资产状况信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:31
 */
@Tag(name = "企业资产状况信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_annual_report_assets")
public class DataCompanyAnnualReportAssetsAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyAnnualReportAssetsApplicationService iDataCompanyAnnualReportAssetsApplicationService;
    @Autowired
    private IDataCompanyAnnualReportAssetsRepresentationApplicationService iDataCompanyAnnualReportAssetsRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportAssets:create')")
    @Operation(summary = "添加企业资产状况信息")
    @PostMapping("/create")
    @OpLog(name = "添加企业资产状况信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyAnnualReportAssetsVO> create(@RequestBody DataCompanyAnnualReportAssetsCreateCommand dataCompanyAnnualReportAssetsCreateCommand){
        return iDataCompanyAnnualReportAssetsApplicationService.create(dataCompanyAnnualReportAssetsCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportAssets:delete')")
    @Operation(summary = "删除企业资产状况信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业资产状况信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyAnnualReportAssetsVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyAnnualReportAssetsApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportAssets:update')")
    @Operation(summary = "更新企业资产状况信息")
    @PutMapping("/update")
    @OpLog(name = "更新企业资产状况信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyAnnualReportAssetsVO> update(@RequestBody DataCompanyAnnualReportAssetsUpdateCommand dataCompanyAnnualReportAssetsUpdateCommand){
        dataCompanyAnnualReportAssetsUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyAnnualReportAssetsApplicationService.update(dataCompanyAnnualReportAssetsUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportAssets:update')")
    @Operation(summary = "企业资产状况信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyAnnualReportAssetsVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyAnnualReportAssetsRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportAssets:detail')")
    @Operation(summary = "企业资产状况信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyAnnualReportAssetsVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyAnnualReportAssetsRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportAssets:queryList')")
    @Operation(summary = "列表查询企业资产状况信息")
    @GetMapping("/list")
    public MultiResponse<DataCompanyAnnualReportAssetsVO> queryList(DataCompanyAnnualReportAssetsQueryListCommand dataCompanyAnnualReportAssetsQueryListCommand){
        dataCompanyAnnualReportAssetsQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportAssetsRepresentationApplicationService.queryList(dataCompanyAnnualReportAssetsQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportAssets:pageQuery')")
    @Operation(summary = "分页查询企业资产状况信息")
    @GetMapping("/page")
    public PageResponse<DataCompanyAnnualReportAssetsVO> pageQueryList(DataCompanyAnnualReportAssetsPageQueryCommand dataCompanyAnnualReportAssetsPageQueryCommand){
        dataCompanyAnnualReportAssetsPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportAssetsRepresentationApplicationService.pageQuery(dataCompanyAnnualReportAssetsPageQueryCommand);
    }
}
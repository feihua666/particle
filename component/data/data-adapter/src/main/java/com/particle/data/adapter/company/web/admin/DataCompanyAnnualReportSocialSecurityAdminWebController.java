package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyAnnualReportSocialSecurityApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportSocialSecurityRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportSocialSecurityCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportSocialSecurityVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportSocialSecurityUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportSocialSecurityPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportSocialSecurityQueryListCommand;
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
 * 企业年报社保后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
@Tag(name = "企业年报社保pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_annual_report_social_security")
public class DataCompanyAnnualReportSocialSecurityAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyAnnualReportSocialSecurityApplicationService iDataCompanyAnnualReportSocialSecurityApplicationService;
    @Autowired
    private IDataCompanyAnnualReportSocialSecurityRepresentationApplicationService iDataCompanyAnnualReportSocialSecurityRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportSocialSecurity:create')")
    @Operation(summary = "添加企业年报社保")
    @PostMapping("/create")
    @OpLog(name = "添加企业年报社保",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyAnnualReportSocialSecurityVO> create(@RequestBody DataCompanyAnnualReportSocialSecurityCreateCommand dataCompanyAnnualReportSocialSecurityCreateCommand){
        return iDataCompanyAnnualReportSocialSecurityApplicationService.create(dataCompanyAnnualReportSocialSecurityCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportSocialSecurity:delete')")
    @Operation(summary = "删除企业年报社保")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业年报社保",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyAnnualReportSocialSecurityVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyAnnualReportSocialSecurityApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportSocialSecurity:update')")
    @Operation(summary = "更新企业年报社保")
    @PutMapping("/update")
    @OpLog(name = "更新企业年报社保",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyAnnualReportSocialSecurityVO> update(@RequestBody DataCompanyAnnualReportSocialSecurityUpdateCommand dataCompanyAnnualReportSocialSecurityUpdateCommand){
        dataCompanyAnnualReportSocialSecurityUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyAnnualReportSocialSecurityApplicationService.update(dataCompanyAnnualReportSocialSecurityUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportSocialSecurity:update')")
    @Operation(summary = "企业年报社保更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyAnnualReportSocialSecurityVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyAnnualReportSocialSecurityRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportSocialSecurity:detail')")
    @Operation(summary = "企业年报社保详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyAnnualReportSocialSecurityVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyAnnualReportSocialSecurityRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportSocialSecurity:queryList')")
    @Operation(summary = "列表查询企业年报社保")
    @GetMapping("/list")
    public MultiResponse<DataCompanyAnnualReportSocialSecurityVO> queryList(DataCompanyAnnualReportSocialSecurityQueryListCommand dataCompanyAnnualReportSocialSecurityQueryListCommand){
        dataCompanyAnnualReportSocialSecurityQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportSocialSecurityRepresentationApplicationService.queryList(dataCompanyAnnualReportSocialSecurityQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportSocialSecurity:pageQuery')")
    @Operation(summary = "分页查询企业年报社保")
    @GetMapping("/page")
    public PageResponse<DataCompanyAnnualReportSocialSecurityVO> pageQueryList(DataCompanyAnnualReportSocialSecurityPageQueryCommand dataCompanyAnnualReportSocialSecurityPageQueryCommand){
        dataCompanyAnnualReportSocialSecurityPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportSocialSecurityRepresentationApplicationService.pageQuery(dataCompanyAnnualReportSocialSecurityPageQueryCommand);
    }
}
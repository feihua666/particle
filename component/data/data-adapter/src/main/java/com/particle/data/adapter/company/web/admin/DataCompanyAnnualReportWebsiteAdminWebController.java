package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyAnnualReportWebsiteApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportWebsiteRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportWebsiteCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportWebsiteVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportWebsiteUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportWebsitePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportWebsiteQueryListCommand;
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
 * 企业年报网站网店后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Tag(name = "企业年报网站网店pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_annual_report_website")
public class DataCompanyAnnualReportWebsiteAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyAnnualReportWebsiteApplicationService iDataCompanyAnnualReportWebsiteApplicationService;
    @Autowired
    private IDataCompanyAnnualReportWebsiteRepresentationApplicationService iDataCompanyAnnualReportWebsiteRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportWebsite:create')")
    @Operation(summary = "添加企业年报网站网店")
    @PostMapping("/create")
    @OpLog(name = "添加企业年报网站网店",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyAnnualReportWebsiteVO> create(@RequestBody DataCompanyAnnualReportWebsiteCreateCommand dataCompanyAnnualReportWebsiteCreateCommand){
        return iDataCompanyAnnualReportWebsiteApplicationService.create(dataCompanyAnnualReportWebsiteCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportWebsite:delete')")
    @Operation(summary = "删除企业年报网站网店")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业年报网站网店",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyAnnualReportWebsiteVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyAnnualReportWebsiteApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportWebsite:update')")
    @Operation(summary = "更新企业年报网站网店")
    @PutMapping("/update")
    @OpLog(name = "更新企业年报网站网店",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyAnnualReportWebsiteVO> update(@RequestBody DataCompanyAnnualReportWebsiteUpdateCommand dataCompanyAnnualReportWebsiteUpdateCommand){
        dataCompanyAnnualReportWebsiteUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyAnnualReportWebsiteApplicationService.update(dataCompanyAnnualReportWebsiteUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportWebsite:update')")
    @Operation(summary = "企业年报网站网店更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyAnnualReportWebsiteVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyAnnualReportWebsiteRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportWebsite:detail')")
    @Operation(summary = "企业年报网站网店详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyAnnualReportWebsiteVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyAnnualReportWebsiteRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportWebsite:queryList')")
    @Operation(summary = "列表查询企业年报网站网店")
    @GetMapping("/list")
    public MultiResponse<DataCompanyAnnualReportWebsiteVO> queryList(DataCompanyAnnualReportWebsiteQueryListCommand dataCompanyAnnualReportWebsiteQueryListCommand){
        dataCompanyAnnualReportWebsiteQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportWebsiteRepresentationApplicationService.queryList(dataCompanyAnnualReportWebsiteQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportWebsite:pageQuery')")
    @Operation(summary = "分页查询企业年报网站网店")
    @GetMapping("/page")
    public PageResponse<DataCompanyAnnualReportWebsiteVO> pageQueryList(DataCompanyAnnualReportWebsitePageQueryCommand dataCompanyAnnualReportWebsitePageQueryCommand){
        dataCompanyAnnualReportWebsitePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportWebsiteRepresentationApplicationService.pageQuery(dataCompanyAnnualReportWebsitePageQueryCommand);
    }
}
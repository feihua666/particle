package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyAnnualReportApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportQueryListCommand;
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
 * 企业年报后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
@Tag(name = "企业年报pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_annual_report")
public class DataCompanyAnnualReportAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyAnnualReportApplicationService iDataCompanyAnnualReportApplicationService;
    @Autowired
    private IDataCompanyAnnualReportRepresentationApplicationService iDataCompanyAnnualReportRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReport:create')")
    @Operation(summary = "添加企业年报")
    @PostMapping("/create")
    @OpLog(name = "添加企业年报",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyAnnualReportVO> create(@RequestBody DataCompanyAnnualReportCreateCommand dataCompanyAnnualReportCreateCommand){
        return iDataCompanyAnnualReportApplicationService.create(dataCompanyAnnualReportCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReport:delete')")
    @Operation(summary = "删除企业年报")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业年报",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyAnnualReportVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyAnnualReportApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReport:update')")
    @Operation(summary = "更新企业年报")
    @PutMapping("/update")
    @OpLog(name = "更新企业年报",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyAnnualReportVO> update(@RequestBody DataCompanyAnnualReportUpdateCommand dataCompanyAnnualReportUpdateCommand){
        dataCompanyAnnualReportUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyAnnualReportApplicationService.update(dataCompanyAnnualReportUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReport:update')")
    @Operation(summary = "企业年报更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyAnnualReportVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyAnnualReportRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReport:detail')")
    @Operation(summary = "企业年报详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyAnnualReportVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyAnnualReportRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReport:queryList')")
    @Operation(summary = "列表查询企业年报")
    @GetMapping("/list")
    public MultiResponse<DataCompanyAnnualReportVO> queryList(DataCompanyAnnualReportQueryListCommand dataCompanyAnnualReportQueryListCommand){
        dataCompanyAnnualReportQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportRepresentationApplicationService.queryList(dataCompanyAnnualReportQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReport:pageQuery')")
    @Operation(summary = "分页查询企业年报")
    @GetMapping("/page")
    public PageResponse<DataCompanyAnnualReportVO> pageQueryList(DataCompanyAnnualReportPageQueryCommand dataCompanyAnnualReportPageQueryCommand){
        dataCompanyAnnualReportPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportRepresentationApplicationService.pageQuery(dataCompanyAnnualReportPageQueryCommand);
    }
}
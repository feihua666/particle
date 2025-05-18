package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyAnnualReportEquityChangeApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportEquityChangeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportEquityChangeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportEquityChangeVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportEquityChangeUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportEquityChangePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportEquityChangeQueryListCommand;
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
 * 企业年报股权变更后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:57
 */
@Tag(name = "企业年报股权变更pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_annual_report_equity_change")
public class DataCompanyAnnualReportEquityChangeAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyAnnualReportEquityChangeApplicationService iDataCompanyAnnualReportEquityChangeApplicationService;
    @Autowired
    private IDataCompanyAnnualReportEquityChangeRepresentationApplicationService iDataCompanyAnnualReportEquityChangeRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportEquityChange:create')")
    @Operation(summary = "添加企业年报股权变更")
    @PostMapping("/create")
    @OpLog(name = "添加企业年报股权变更",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyAnnualReportEquityChangeVO> create(@RequestBody DataCompanyAnnualReportEquityChangeCreateCommand dataCompanyAnnualReportEquityChangeCreateCommand){
        return iDataCompanyAnnualReportEquityChangeApplicationService.create(dataCompanyAnnualReportEquityChangeCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportEquityChange:delete')")
    @Operation(summary = "删除企业年报股权变更")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业年报股权变更",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyAnnualReportEquityChangeVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyAnnualReportEquityChangeApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportEquityChange:update')")
    @Operation(summary = "更新企业年报股权变更")
    @PutMapping("/update")
    @OpLog(name = "更新企业年报股权变更",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyAnnualReportEquityChangeVO> update(@RequestBody DataCompanyAnnualReportEquityChangeUpdateCommand dataCompanyAnnualReportEquityChangeUpdateCommand){
        dataCompanyAnnualReportEquityChangeUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyAnnualReportEquityChangeApplicationService.update(dataCompanyAnnualReportEquityChangeUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportEquityChange:update')")
    @Operation(summary = "企业年报股权变更更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyAnnualReportEquityChangeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyAnnualReportEquityChangeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportEquityChange:detail')")
    @Operation(summary = "企业年报股权变更详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyAnnualReportEquityChangeVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyAnnualReportEquityChangeRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportEquityChange:queryList')")
    @Operation(summary = "列表查询企业年报股权变更")
    @GetMapping("/list")
    public MultiResponse<DataCompanyAnnualReportEquityChangeVO> queryList(DataCompanyAnnualReportEquityChangeQueryListCommand dataCompanyAnnualReportEquityChangeQueryListCommand){
        dataCompanyAnnualReportEquityChangeQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportEquityChangeRepresentationApplicationService.queryList(dataCompanyAnnualReportEquityChangeQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportEquityChange:pageQuery')")
    @Operation(summary = "分页查询企业年报股权变更")
    @GetMapping("/page")
    public PageResponse<DataCompanyAnnualReportEquityChangeVO> pageQueryList(DataCompanyAnnualReportEquityChangePageQueryCommand dataCompanyAnnualReportEquityChangePageQueryCommand){
        dataCompanyAnnualReportEquityChangePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportEquityChangeRepresentationApplicationService.pageQuery(dataCompanyAnnualReportEquityChangePageQueryCommand);
    }
}
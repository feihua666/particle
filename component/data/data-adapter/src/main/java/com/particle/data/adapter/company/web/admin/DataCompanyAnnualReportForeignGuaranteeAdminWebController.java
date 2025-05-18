package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyAnnualReportForeignGuaranteeApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyAnnualReportForeignGuaranteeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignGuaranteeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignGuaranteeVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignGuaranteeUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignGuaranteePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignGuaranteeQueryListCommand;
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
 * 企业年报对外担保后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
@Tag(name = "企业年报对外担保pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_annual_report_foreign_guarantee")
public class DataCompanyAnnualReportForeignGuaranteeAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyAnnualReportForeignGuaranteeApplicationService iDataCompanyAnnualReportForeignGuaranteeApplicationService;
    @Autowired
    private IDataCompanyAnnualReportForeignGuaranteeRepresentationApplicationService iDataCompanyAnnualReportForeignGuaranteeRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportForeignGuarantee:create')")
    @Operation(summary = "添加企业年报对外担保")
    @PostMapping("/create")
    @OpLog(name = "添加企业年报对外担保",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> create(@RequestBody DataCompanyAnnualReportForeignGuaranteeCreateCommand dataCompanyAnnualReportForeignGuaranteeCreateCommand){
        return iDataCompanyAnnualReportForeignGuaranteeApplicationService.create(dataCompanyAnnualReportForeignGuaranteeCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportForeignGuarantee:delete')")
    @Operation(summary = "删除企业年报对外担保")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业年报对外担保",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyAnnualReportForeignGuaranteeApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportForeignGuarantee:update')")
    @Operation(summary = "更新企业年报对外担保")
    @PutMapping("/update")
    @OpLog(name = "更新企业年报对外担保",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> update(@RequestBody DataCompanyAnnualReportForeignGuaranteeUpdateCommand dataCompanyAnnualReportForeignGuaranteeUpdateCommand){
        dataCompanyAnnualReportForeignGuaranteeUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyAnnualReportForeignGuaranteeApplicationService.update(dataCompanyAnnualReportForeignGuaranteeUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportForeignGuarantee:update')")
    @Operation(summary = "企业年报对外担保更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyAnnualReportForeignGuaranteeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportForeignGuarantee:detail')")
    @Operation(summary = "企业年报对外担保详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyAnnualReportForeignGuaranteeRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportForeignGuarantee:queryList')")
    @Operation(summary = "列表查询企业年报对外担保")
    @GetMapping("/list")
    public MultiResponse<DataCompanyAnnualReportForeignGuaranteeVO> queryList(DataCompanyAnnualReportForeignGuaranteeQueryListCommand dataCompanyAnnualReportForeignGuaranteeQueryListCommand){
        dataCompanyAnnualReportForeignGuaranteeQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportForeignGuaranteeRepresentationApplicationService.queryList(dataCompanyAnnualReportForeignGuaranteeQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAnnualReportForeignGuarantee:pageQuery')")
    @Operation(summary = "分页查询企业年报对外担保")
    @GetMapping("/page")
    public PageResponse<DataCompanyAnnualReportForeignGuaranteeVO> pageQueryList(DataCompanyAnnualReportForeignGuaranteePageQueryCommand dataCompanyAnnualReportForeignGuaranteePageQueryCommand){
        dataCompanyAnnualReportForeignGuaranteePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAnnualReportForeignGuaranteeRepresentationApplicationService.pageQuery(dataCompanyAnnualReportForeignGuaranteePageQueryCommand);
    }
}
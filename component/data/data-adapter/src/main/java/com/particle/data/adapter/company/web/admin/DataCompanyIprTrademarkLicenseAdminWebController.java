package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprTrademarkLicenseApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprTrademarkLicenseRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicenseCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicenseVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicenseUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicensePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicenseQueryListCommand;
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
 * 企业知识产权商标许可信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:10
 */
@Tag(name = "企业知识产权商标许可信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_trademark_license")
public class DataCompanyIprTrademarkLicenseAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprTrademarkLicenseApplicationService iDataCompanyIprTrademarkLicenseApplicationService;
    @Autowired
    private IDataCompanyIprTrademarkLicenseRepresentationApplicationService iDataCompanyIprTrademarkLicenseRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkLicense:create')")
    @Operation(summary = "添加企业知识产权商标许可信息")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权商标许可信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprTrademarkLicenseVO> create(@RequestBody DataCompanyIprTrademarkLicenseCreateCommand dataCompanyIprTrademarkLicenseCreateCommand){
        return iDataCompanyIprTrademarkLicenseApplicationService.create(dataCompanyIprTrademarkLicenseCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkLicense:delete')")
    @Operation(summary = "删除企业知识产权商标许可信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权商标许可信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprTrademarkLicenseVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprTrademarkLicenseApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkLicense:update')")
    @Operation(summary = "更新企业知识产权商标许可信息")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权商标许可信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprTrademarkLicenseVO> update(@RequestBody DataCompanyIprTrademarkLicenseUpdateCommand dataCompanyIprTrademarkLicenseUpdateCommand){
        dataCompanyIprTrademarkLicenseUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprTrademarkLicenseApplicationService.update(dataCompanyIprTrademarkLicenseUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkLicense:update')")
    @Operation(summary = "企业知识产权商标许可信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprTrademarkLicenseVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprTrademarkLicenseRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkLicense:detail')")
    @Operation(summary = "企业知识产权商标许可信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprTrademarkLicenseVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprTrademarkLicenseRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkLicense:queryList')")
    @Operation(summary = "列表查询企业知识产权商标许可信息")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprTrademarkLicenseVO> queryList(DataCompanyIprTrademarkLicenseQueryListCommand dataCompanyIprTrademarkLicenseQueryListCommand){
        dataCompanyIprTrademarkLicenseQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprTrademarkLicenseRepresentationApplicationService.queryList(dataCompanyIprTrademarkLicenseQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprTrademarkLicense:pageQuery')")
    @Operation(summary = "分页查询企业知识产权商标许可信息")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprTrademarkLicenseVO> pageQueryList(DataCompanyIprTrademarkLicensePageQueryCommand dataCompanyIprTrademarkLicensePageQueryCommand){
        dataCompanyIprTrademarkLicensePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprTrademarkLicenseRepresentationApplicationService.pageQuery(dataCompanyIprTrademarkLicensePageQueryCommand);
    }
}
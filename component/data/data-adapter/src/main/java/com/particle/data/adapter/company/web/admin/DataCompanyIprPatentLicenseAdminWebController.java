package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPatentLicenseApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentLicenseRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLicenseCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLicenseVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLicenseUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLicensePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLicenseQueryListCommand;
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
 * 企业知识产权专利许可信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:59
 */
@Tag(name = "企业知识产权专利许可信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_patent_license")
public class DataCompanyIprPatentLicenseAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPatentLicenseApplicationService iDataCompanyIprPatentLicenseApplicationService;
    @Autowired
    private IDataCompanyIprPatentLicenseRepresentationApplicationService iDataCompanyIprPatentLicenseRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentLicense:create')")
    @Operation(summary = "添加企业知识产权专利许可信息")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权专利许可信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPatentLicenseVO> create(@RequestBody DataCompanyIprPatentLicenseCreateCommand dataCompanyIprPatentLicenseCreateCommand){
        return iDataCompanyIprPatentLicenseApplicationService.create(dataCompanyIprPatentLicenseCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentLicense:delete')")
    @Operation(summary = "删除企业知识产权专利许可信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权专利许可信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPatentLicenseVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPatentLicenseApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentLicense:update')")
    @Operation(summary = "更新企业知识产权专利许可信息")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权专利许可信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPatentLicenseVO> update(@RequestBody DataCompanyIprPatentLicenseUpdateCommand dataCompanyIprPatentLicenseUpdateCommand){
        dataCompanyIprPatentLicenseUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPatentLicenseApplicationService.update(dataCompanyIprPatentLicenseUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentLicense:update')")
    @Operation(summary = "企业知识产权专利许可信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPatentLicenseVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPatentLicenseRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentLicense:detail')")
    @Operation(summary = "企业知识产权专利许可信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPatentLicenseVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPatentLicenseRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentLicense:queryList')")
    @Operation(summary = "列表查询企业知识产权专利许可信息")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPatentLicenseVO> queryList(DataCompanyIprPatentLicenseQueryListCommand dataCompanyIprPatentLicenseQueryListCommand){
        dataCompanyIprPatentLicenseQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentLicenseRepresentationApplicationService.queryList(dataCompanyIprPatentLicenseQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentLicense:pageQuery')")
    @Operation(summary = "分页查询企业知识产权专利许可信息")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPatentLicenseVO> pageQueryList(DataCompanyIprPatentLicensePageQueryCommand dataCompanyIprPatentLicensePageQueryCommand){
        dataCompanyIprPatentLicensePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentLicenseRepresentationApplicationService.pageQuery(dataCompanyIprPatentLicensePageQueryCommand);
    }
}
package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPatentCertificateApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentCertificateRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCertificateCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCertificateVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCertificateUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCertificatePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCertificateQueryListCommand;
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
 * 企业知识产权专利证书信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:02
 */
@Tag(name = "企业知识产权专利证书信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_patent_certificate")
public class DataCompanyIprPatentCertificateAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPatentCertificateApplicationService iDataCompanyIprPatentCertificateApplicationService;
    @Autowired
    private IDataCompanyIprPatentCertificateRepresentationApplicationService iDataCompanyIprPatentCertificateRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentCertificate:create')")
    @Operation(summary = "添加企业知识产权专利证书信息")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权专利证书信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPatentCertificateVO> create(@RequestBody DataCompanyIprPatentCertificateCreateCommand dataCompanyIprPatentCertificateCreateCommand){
        return iDataCompanyIprPatentCertificateApplicationService.create(dataCompanyIprPatentCertificateCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentCertificate:delete')")
    @Operation(summary = "删除企业知识产权专利证书信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权专利证书信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPatentCertificateVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPatentCertificateApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentCertificate:update')")
    @Operation(summary = "更新企业知识产权专利证书信息")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权专利证书信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPatentCertificateVO> update(@RequestBody DataCompanyIprPatentCertificateUpdateCommand dataCompanyIprPatentCertificateUpdateCommand){
        dataCompanyIprPatentCertificateUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPatentCertificateApplicationService.update(dataCompanyIprPatentCertificateUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentCertificate:update')")
    @Operation(summary = "企业知识产权专利证书信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPatentCertificateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPatentCertificateRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentCertificate:detail')")
    @Operation(summary = "企业知识产权专利证书信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPatentCertificateVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPatentCertificateRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentCertificate:queryList')")
    @Operation(summary = "列表查询企业知识产权专利证书信息")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPatentCertificateVO> queryList(DataCompanyIprPatentCertificateQueryListCommand dataCompanyIprPatentCertificateQueryListCommand){
        dataCompanyIprPatentCertificateQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentCertificateRepresentationApplicationService.queryList(dataCompanyIprPatentCertificateQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentCertificate:pageQuery')")
    @Operation(summary = "分页查询企业知识产权专利证书信息")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPatentCertificateVO> pageQueryList(DataCompanyIprPatentCertificatePageQueryCommand dataCompanyIprPatentCertificatePageQueryCommand){
        dataCompanyIprPatentCertificatePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentCertificateRepresentationApplicationService.pageQuery(dataCompanyIprPatentCertificatePageQueryCommand);
    }
}
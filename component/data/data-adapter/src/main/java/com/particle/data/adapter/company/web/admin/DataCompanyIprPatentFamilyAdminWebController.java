package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPatentFamilyApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentFamilyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentFamilyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentFamilyVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentFamilyUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentFamilyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentFamilyQueryListCommand;
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
 * 企业知识产权专利同族信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:43
 */
@Tag(name = "企业知识产权专利同族信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_patent_family")
public class DataCompanyIprPatentFamilyAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPatentFamilyApplicationService iDataCompanyIprPatentFamilyApplicationService;
    @Autowired
    private IDataCompanyIprPatentFamilyRepresentationApplicationService iDataCompanyIprPatentFamilyRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentFamily:create')")
    @Operation(summary = "添加企业知识产权专利同族信息")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权专利同族信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPatentFamilyVO> create(@RequestBody DataCompanyIprPatentFamilyCreateCommand dataCompanyIprPatentFamilyCreateCommand){
        return iDataCompanyIprPatentFamilyApplicationService.create(dataCompanyIprPatentFamilyCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentFamily:delete')")
    @Operation(summary = "删除企业知识产权专利同族信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权专利同族信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPatentFamilyVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPatentFamilyApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentFamily:update')")
    @Operation(summary = "更新企业知识产权专利同族信息")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权专利同族信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPatentFamilyVO> update(@RequestBody DataCompanyIprPatentFamilyUpdateCommand dataCompanyIprPatentFamilyUpdateCommand){
        dataCompanyIprPatentFamilyUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPatentFamilyApplicationService.update(dataCompanyIprPatentFamilyUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentFamily:update')")
    @Operation(summary = "企业知识产权专利同族信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPatentFamilyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPatentFamilyRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentFamily:detail')")
    @Operation(summary = "企业知识产权专利同族信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPatentFamilyVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPatentFamilyRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentFamily:queryList')")
    @Operation(summary = "列表查询企业知识产权专利同族信息")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPatentFamilyVO> queryList(DataCompanyIprPatentFamilyQueryListCommand dataCompanyIprPatentFamilyQueryListCommand){
        dataCompanyIprPatentFamilyQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentFamilyRepresentationApplicationService.queryList(dataCompanyIprPatentFamilyQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPatentFamily:pageQuery')")
    @Operation(summary = "分页查询企业知识产权专利同族信息")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPatentFamilyVO> pageQueryList(DataCompanyIprPatentFamilyPageQueryCommand dataCompanyIprPatentFamilyPageQueryCommand){
        dataCompanyIprPatentFamilyPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPatentFamilyRepresentationApplicationService.pageQuery(dataCompanyIprPatentFamilyPageQueryCommand);
    }
}
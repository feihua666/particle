package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyAdministrativeLicenseApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyAdministrativeLicenseRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAdministrativeLicenseCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAdministrativeLicenseVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAdministrativeLicenseUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAdministrativeLicensePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAdministrativeLicenseQueryListCommand;
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
 * 企业行政许可后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
@Tag(name = "企业行政许可pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_administrative_license")
public class DataCompanyAdministrativeLicenseAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyAdministrativeLicenseApplicationService iDataCompanyAdministrativeLicenseApplicationService;
    @Autowired
    private IDataCompanyAdministrativeLicenseRepresentationApplicationService iDataCompanyAdministrativeLicenseRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAdministrativeLicense:create')")
    @Operation(summary = "添加企业行政许可")
    @PostMapping("/create")
    @OpLog(name = "添加企业行政许可",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyAdministrativeLicenseVO> create(@RequestBody DataCompanyAdministrativeLicenseCreateCommand dataCompanyAdministrativeLicenseCreateCommand){
        return iDataCompanyAdministrativeLicenseApplicationService.create(dataCompanyAdministrativeLicenseCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAdministrativeLicense:delete')")
    @Operation(summary = "删除企业行政许可")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业行政许可",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyAdministrativeLicenseVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyAdministrativeLicenseApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAdministrativeLicense:update')")
    @Operation(summary = "更新企业行政许可")
    @PutMapping("/update")
    @OpLog(name = "更新企业行政许可",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyAdministrativeLicenseVO> update(@RequestBody DataCompanyAdministrativeLicenseUpdateCommand dataCompanyAdministrativeLicenseUpdateCommand){
        dataCompanyAdministrativeLicenseUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyAdministrativeLicenseApplicationService.update(dataCompanyAdministrativeLicenseUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAdministrativeLicense:update')")
    @Operation(summary = "企业行政许可更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyAdministrativeLicenseVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyAdministrativeLicenseRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAdministrativeLicense:detail')")
    @Operation(summary = "企业行政许可详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyAdministrativeLicenseVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyAdministrativeLicenseRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAdministrativeLicense:queryList')")
    @Operation(summary = "列表查询企业行政许可")
    @GetMapping("/list")
    public MultiResponse<DataCompanyAdministrativeLicenseVO> queryList(DataCompanyAdministrativeLicenseQueryListCommand dataCompanyAdministrativeLicenseQueryListCommand){
        dataCompanyAdministrativeLicenseQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAdministrativeLicenseRepresentationApplicationService.queryList(dataCompanyAdministrativeLicenseQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyAdministrativeLicense:pageQuery')")
    @Operation(summary = "分页查询企业行政许可")
    @GetMapping("/page")
    public PageResponse<DataCompanyAdministrativeLicenseVO> pageQueryList(DataCompanyAdministrativeLicensePageQueryCommand dataCompanyAdministrativeLicensePageQueryCommand){
        dataCompanyAdministrativeLicensePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyAdministrativeLicenseRepresentationApplicationService.pageQuery(dataCompanyAdministrativeLicensePageQueryCommand);
    }
}
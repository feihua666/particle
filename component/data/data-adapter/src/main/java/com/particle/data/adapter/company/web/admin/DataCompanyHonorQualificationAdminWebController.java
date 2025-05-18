package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyHonorQualificationApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyHonorQualificationRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyHonorQualificationCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyHonorQualificationVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyHonorQualificationUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyHonorQualificationPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyHonorQualificationQueryListCommand;
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
 * 企业荣誉资质后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Tag(name = "企业荣誉资质pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_honor_qualification")
public class DataCompanyHonorQualificationAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyHonorQualificationApplicationService iDataCompanyHonorQualificationApplicationService;
    @Autowired
    private IDataCompanyHonorQualificationRepresentationApplicationService iDataCompanyHonorQualificationRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyHonorQualification:create')")
    @Operation(summary = "添加企业荣誉资质")
    @PostMapping("/create")
    @OpLog(name = "添加企业荣誉资质",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyHonorQualificationVO> create(@RequestBody DataCompanyHonorQualificationCreateCommand dataCompanyHonorQualificationCreateCommand){
        return iDataCompanyHonorQualificationApplicationService.create(dataCompanyHonorQualificationCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyHonorQualification:delete')")
    @Operation(summary = "删除企业荣誉资质")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业荣誉资质",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyHonorQualificationVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyHonorQualificationApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyHonorQualification:update')")
    @Operation(summary = "更新企业荣誉资质")
    @PutMapping("/update")
    @OpLog(name = "更新企业荣誉资质",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyHonorQualificationVO> update(@RequestBody DataCompanyHonorQualificationUpdateCommand dataCompanyHonorQualificationUpdateCommand){
        dataCompanyHonorQualificationUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyHonorQualificationApplicationService.update(dataCompanyHonorQualificationUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyHonorQualification:update')")
    @Operation(summary = "企业荣誉资质更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyHonorQualificationVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyHonorQualificationRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyHonorQualification:detail')")
    @Operation(summary = "企业荣誉资质详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyHonorQualificationVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyHonorQualificationRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyHonorQualification:queryList')")
    @Operation(summary = "列表查询企业荣誉资质")
    @GetMapping("/list")
    public MultiResponse<DataCompanyHonorQualificationVO> queryList(DataCompanyHonorQualificationQueryListCommand dataCompanyHonorQualificationQueryListCommand){
        dataCompanyHonorQualificationQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyHonorQualificationRepresentationApplicationService.queryList(dataCompanyHonorQualificationQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyHonorQualification:pageQuery')")
    @Operation(summary = "分页查询企业荣誉资质")
    @GetMapping("/page")
    public PageResponse<DataCompanyHonorQualificationVO> pageQueryList(DataCompanyHonorQualificationPageQueryCommand dataCompanyHonorQualificationPageQueryCommand){
        dataCompanyHonorQualificationPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyHonorQualificationRepresentationApplicationService.pageQuery(dataCompanyHonorQualificationPageQueryCommand);
    }
}
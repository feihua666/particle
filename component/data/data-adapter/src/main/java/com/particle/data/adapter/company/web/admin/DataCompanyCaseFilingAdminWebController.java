package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyCaseFilingApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyCaseFilingRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingQueryListCommand;
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
 * 企业立案信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Tag(name = "企业立案信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_case_filing")
public class DataCompanyCaseFilingAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyCaseFilingApplicationService iDataCompanyCaseFilingApplicationService;
    @Autowired
    private IDataCompanyCaseFilingRepresentationApplicationService iDataCompanyCaseFilingRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCaseFiling:create')")
    @Operation(summary = "添加企业立案信息")
    @PostMapping("/create")
    @OpLog(name = "添加企业立案信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyCaseFilingVO> create(@RequestBody DataCompanyCaseFilingCreateCommand dataCompanyCaseFilingCreateCommand){
        return iDataCompanyCaseFilingApplicationService.create(dataCompanyCaseFilingCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCaseFiling:delete')")
    @Operation(summary = "删除企业立案信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业立案信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyCaseFilingVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyCaseFilingApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCaseFiling:update')")
    @Operation(summary = "更新企业立案信息")
    @PutMapping("/update")
    @OpLog(name = "更新企业立案信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyCaseFilingVO> update(@RequestBody DataCompanyCaseFilingUpdateCommand dataCompanyCaseFilingUpdateCommand){
        dataCompanyCaseFilingUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyCaseFilingApplicationService.update(dataCompanyCaseFilingUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCaseFiling:update')")
    @Operation(summary = "企业立案信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyCaseFilingVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyCaseFilingRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCaseFiling:detail')")
    @Operation(summary = "企业立案信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyCaseFilingVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyCaseFilingRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCaseFiling:queryList')")
    @Operation(summary = "列表查询企业立案信息")
    @GetMapping("/list")
    public MultiResponse<DataCompanyCaseFilingVO> queryList(DataCompanyCaseFilingQueryListCommand dataCompanyCaseFilingQueryListCommand){
        dataCompanyCaseFilingQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyCaseFilingRepresentationApplicationService.queryList(dataCompanyCaseFilingQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCaseFiling:pageQuery')")
    @Operation(summary = "分页查询企业立案信息")
    @GetMapping("/page")
    public PageResponse<DataCompanyCaseFilingVO> pageQueryList(DataCompanyCaseFilingPageQueryCommand dataCompanyCaseFilingPageQueryCommand){
        dataCompanyCaseFilingPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyCaseFilingRepresentationApplicationService.pageQuery(dataCompanyCaseFilingPageQueryCommand);
    }
}
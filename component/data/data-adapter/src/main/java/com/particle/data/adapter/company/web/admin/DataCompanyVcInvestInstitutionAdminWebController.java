package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyVcInvestInstitutionApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyVcInvestInstitutionRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyVcInvestInstitutionCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcInvestInstitutionVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcInvestInstitutionUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcInvestInstitutionPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcInvestInstitutionQueryListCommand;
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
 * 企业投资机构后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:13
 */
@Tag(name = "企业投资机构pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_vc_invest_institution")
public class DataCompanyVcInvestInstitutionAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyVcInvestInstitutionApplicationService iDataCompanyVcInvestInstitutionApplicationService;
    @Autowired
    private IDataCompanyVcInvestInstitutionRepresentationApplicationService iDataCompanyVcInvestInstitutionRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcInvestInstitution:create')")
    @Operation(summary = "添加企业投资机构")
    @PostMapping("/create")
    @OpLog(name = "添加企业投资机构",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyVcInvestInstitutionVO> create(@RequestBody DataCompanyVcInvestInstitutionCreateCommand dataCompanyVcInvestInstitutionCreateCommand){
        return iDataCompanyVcInvestInstitutionApplicationService.create(dataCompanyVcInvestInstitutionCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcInvestInstitution:delete')")
    @Operation(summary = "删除企业投资机构")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业投资机构",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyVcInvestInstitutionVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyVcInvestInstitutionApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcInvestInstitution:update')")
    @Operation(summary = "更新企业投资机构")
    @PutMapping("/update")
    @OpLog(name = "更新企业投资机构",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyVcInvestInstitutionVO> update(@RequestBody DataCompanyVcInvestInstitutionUpdateCommand dataCompanyVcInvestInstitutionUpdateCommand){
        dataCompanyVcInvestInstitutionUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyVcInvestInstitutionApplicationService.update(dataCompanyVcInvestInstitutionUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcInvestInstitution:update')")
    @Operation(summary = "企业投资机构更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyVcInvestInstitutionVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyVcInvestInstitutionRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcInvestInstitution:detail')")
    @Operation(summary = "企业投资机构详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyVcInvestInstitutionVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyVcInvestInstitutionRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcInvestInstitution:queryList')")
    @Operation(summary = "列表查询企业投资机构")
    @GetMapping("/list")
    public MultiResponse<DataCompanyVcInvestInstitutionVO> queryList(DataCompanyVcInvestInstitutionQueryListCommand dataCompanyVcInvestInstitutionQueryListCommand){
        dataCompanyVcInvestInstitutionQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyVcInvestInstitutionRepresentationApplicationService.queryList(dataCompanyVcInvestInstitutionQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcInvestInstitution:pageQuery')")
    @Operation(summary = "分页查询企业投资机构")
    @GetMapping("/page")
    public PageResponse<DataCompanyVcInvestInstitutionVO> pageQueryList(DataCompanyVcInvestInstitutionPageQueryCommand dataCompanyVcInvestInstitutionPageQueryCommand){
        dataCompanyVcInvestInstitutionPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyVcInvestInstitutionRepresentationApplicationService.pageQuery(dataCompanyVcInvestInstitutionPageQueryCommand);
    }
}
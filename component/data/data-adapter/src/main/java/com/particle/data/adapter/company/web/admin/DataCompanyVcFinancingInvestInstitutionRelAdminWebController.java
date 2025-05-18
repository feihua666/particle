package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyVcFinancingInvestInstitutionRelApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyVcFinancingInvestInstitutionRelRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingInvestInstitutionRelCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingInvestInstitutionRelVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingInvestInstitutionRelUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingInvestInstitutionRelPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingInvestInstitutionRelQueryListCommand;
import com.particle.data.client.company.dto.command.CompanyVcFinancingAssignCompanyVcInvestInstitutionCommand;
import com.particle.data.client.company.dto.command.CompanyVcInvestInstitutionAssignCompanyVcFinancingCommand;
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
 * 企业融资历史投资机构关系后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Tag(name = "企业融资历史投资机构关系pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_vc_financing_invest_institution_rel")
public class DataCompanyVcFinancingInvestInstitutionRelAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyVcFinancingInvestInstitutionRelApplicationService iDataCompanyVcFinancingInvestInstitutionRelApplicationService;
    @Autowired
    private IDataCompanyVcFinancingInvestInstitutionRelRepresentationApplicationService iDataCompanyVcFinancingInvestInstitutionRelRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancingInvestInstitutionRel:create')")
    @Operation(summary = "添加企业融资历史投资机构关系")
    @PostMapping("/create")
    @OpLog(name = "添加企业融资历史投资机构关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> create(@RequestBody DataCompanyVcFinancingInvestInstitutionRelCreateCommand dataCompanyVcFinancingInvestInstitutionRelCreateCommand){
        return iDataCompanyVcFinancingInvestInstitutionRelApplicationService.create(dataCompanyVcFinancingInvestInstitutionRelCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancingInvestInstitutionRel:delete')")
    @Operation(summary = "删除企业融资历史投资机构关系")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业融资历史投资机构关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyVcFinancingInvestInstitutionRelApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancingInvestInstitutionRel:update')")
    @Operation(summary = "更新企业融资历史投资机构关系")
    @PutMapping("/update")
    @OpLog(name = "更新企业融资历史投资机构关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> update(@RequestBody DataCompanyVcFinancingInvestInstitutionRelUpdateCommand dataCompanyVcFinancingInvestInstitutionRelUpdateCommand){
        dataCompanyVcFinancingInvestInstitutionRelUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyVcFinancingInvestInstitutionRelApplicationService.update(dataCompanyVcFinancingInvestInstitutionRelUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancingInvestInstitutionRel:update')")
    @Operation(summary = "企业融资历史投资机构关系更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyVcFinancingInvestInstitutionRelRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancingInvestInstitutionRel:detail')")
    @Operation(summary = "企业融资历史投资机构关系详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyVcFinancingInvestInstitutionRelRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancingInvestInstitutionRel:queryList')")
    @Operation(summary = "列表查询企业融资历史投资机构关系")
    @GetMapping("/list")
    public MultiResponse<DataCompanyVcFinancingInvestInstitutionRelVO> queryList(DataCompanyVcFinancingInvestInstitutionRelQueryListCommand dataCompanyVcFinancingInvestInstitutionRelQueryListCommand){
        dataCompanyVcFinancingInvestInstitutionRelQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyVcFinancingInvestInstitutionRelRepresentationApplicationService.queryList(dataCompanyVcFinancingInvestInstitutionRelQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancingInvestInstitutionRel:pageQuery')")
    @Operation(summary = "分页查询企业融资历史投资机构关系")
    @GetMapping("/page")
    public PageResponse<DataCompanyVcFinancingInvestInstitutionRelVO> pageQueryList(DataCompanyVcFinancingInvestInstitutionRelPageQueryCommand dataCompanyVcFinancingInvestInstitutionRelPageQueryCommand){
        dataCompanyVcFinancingInvestInstitutionRelPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyVcFinancingInvestInstitutionRelRepresentationApplicationService.pageQuery(dataCompanyVcFinancingInvestInstitutionRelPageQueryCommand);
    }
    
    @Operation(summary = "企业融资表ID分配企业投资机构表")
    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancingInvestInstitutionRel:companyVcFinancingAssignCompanyVcInvestInstitution')")
    @PostMapping("/companyVcFinancing/assign/companyVcInvestInstitution")
    @ResponseStatus(HttpStatus.CREATED)
    @OpLog(name = "企业融资表ID分配企业投资机构表",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.relAsign)
    public Response companyVcFinancingAssignCompanyVcInvestInstitution(@RequestBody CompanyVcFinancingAssignCompanyVcInvestInstitutionCommand cf) {
        return iDataCompanyVcFinancingInvestInstitutionRelApplicationService.companyVcFinancingAssignCompanyVcInvestInstitution(cf);
    }

    @Operation(summary = "根据企业融资表IDID查询已分配的企业投资机构表id")
    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancingInvestInstitutionRel:queryCompanyVcInvestInstitutionIdsByCompanyVcFinancingId')")
    @GetMapping("/queryCompanyVcInvestInstitutionIdsByCompanyVcFinancingId")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<Long> queryCompanyVcInvestInstitutionIdsByCompanyVcFinancingId(IdCommand idCommand) {
        return iDataCompanyVcFinancingInvestInstitutionRelRepresentationApplicationService.queryCompanyVcInvestInstitutionIdsByCompanyVcFinancingId( idCommand);
    }

    @Operation(summary = "清空企业融资表ID下的所有企业投资机构表")
    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancingInvestInstitutionRel:deleteByCompanyVcFinancingId')")
    @DeleteMapping("/deleteByCompanyVcFinancingId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @OpLog(name = "清空企业融资表ID下的所有企业投资机构表",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public Response deleteByCompanyVcFinancingId(@RequestBody IdCommand idCommand) {
        return iDataCompanyVcFinancingInvestInstitutionRelApplicationService.deleteByCompanyVcFinancingId(idCommand);
    }


    @Operation(summary = "企业投资机构表分配企业融资表ID")
    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancingInvestInstitutionRel:companyVcInvestInstitutionAssignCompanyVcFinancing')")
    @PostMapping("/companyVcInvestInstitution/assign/companyVcFinancing")
    @ResponseStatus(HttpStatus.CREATED)
    @OpLog(name = "企业投资机构表分配企业融资表ID",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.relAsign)
    public Response companyVcInvestInstitutionAssignCompanyVcFinancing(@RequestBody CompanyVcInvestInstitutionAssignCompanyVcFinancingCommand cf) {
        return iDataCompanyVcFinancingInvestInstitutionRelApplicationService.companyVcInvestInstitutionAssignCompanyVcFinancing(cf);
    }

    @Operation(summary = "根据企业投资机构表ID查询已分配的企业融资表IDid")
    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancingInvestInstitutionRel:queryCompanyVcFinancingIdsByCompanyVcInvestInstitutionId')")
    @GetMapping("/queryCompanyVcFinancingIdsByCompanyVcInvestInstitutionId")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<Long> queryByCompanyVcInvestInstitutionId(IdCommand idCommand) {
        return iDataCompanyVcFinancingInvestInstitutionRelRepresentationApplicationService.queryCompanyVcFinancingIdsByCompanyVcInvestInstitutionId( idCommand);

    }

    @Operation(summary = "清空企业投资机构表下的所有企业融资表ID")
    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancingInvestInstitutionRel:deleteByCompanyVcInvestInstitutionId')")
    @DeleteMapping("/deleteByCompanyVcInvestInstitutionId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @OpLog(name = "清空企业投资机构表下的所有企业融资表ID",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public Response deleteByCompanyVcInvestInstitutionId(@RequestBody IdCommand idCommand) {
        return iDataCompanyVcFinancingInvestInstitutionRelApplicationService.deleteByCompanyVcInvestInstitutionId(idCommand);
    }

}

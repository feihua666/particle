package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyVcProductCompetitiveProductRelApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyVcProductCompetitiveProductRelRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyVcProductCompetitiveProductRelCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductCompetitiveProductRelVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcProductCompetitiveProductRelUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductCompetitiveProductRelPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductCompetitiveProductRelQueryListCommand;
import com.particle.data.client.company.dto.command.CompanyVcProductAssignCompanyVcCompetitiveProductCommand;
import com.particle.data.client.company.dto.command.CompanyVcCompetitiveProductAssignCompanyVcProductCommand;
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
 * 企业融资产品竞品关系后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Tag(name = "企业融资产品竞品关系pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_vc_product_competitive_product_rel")
public class DataCompanyVcProductCompetitiveProductRelAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyVcProductCompetitiveProductRelApplicationService iDataCompanyVcProductCompetitiveProductRelApplicationService;
    @Autowired
    private IDataCompanyVcProductCompetitiveProductRelRepresentationApplicationService iDataCompanyVcProductCompetitiveProductRelRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProductCompetitiveProductRel:create')")
    @Operation(summary = "添加企业融资产品竞品关系")
    @PostMapping("/create")
    @OpLog(name = "添加企业融资产品竞品关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> create(@RequestBody DataCompanyVcProductCompetitiveProductRelCreateCommand dataCompanyVcProductCompetitiveProductRelCreateCommand){
        return iDataCompanyVcProductCompetitiveProductRelApplicationService.create(dataCompanyVcProductCompetitiveProductRelCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProductCompetitiveProductRel:delete')")
    @Operation(summary = "删除企业融资产品竞品关系")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业融资产品竞品关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyVcProductCompetitiveProductRelApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProductCompetitiveProductRel:update')")
    @Operation(summary = "更新企业融资产品竞品关系")
    @PutMapping("/update")
    @OpLog(name = "更新企业融资产品竞品关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> update(@RequestBody DataCompanyVcProductCompetitiveProductRelUpdateCommand dataCompanyVcProductCompetitiveProductRelUpdateCommand){
        dataCompanyVcProductCompetitiveProductRelUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyVcProductCompetitiveProductRelApplicationService.update(dataCompanyVcProductCompetitiveProductRelUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProductCompetitiveProductRel:update')")
    @Operation(summary = "企业融资产品竞品关系更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyVcProductCompetitiveProductRelRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProductCompetitiveProductRel:detail')")
    @Operation(summary = "企业融资产品竞品关系详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyVcProductCompetitiveProductRelRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProductCompetitiveProductRel:queryList')")
    @Operation(summary = "列表查询企业融资产品竞品关系")
    @GetMapping("/list")
    public MultiResponse<DataCompanyVcProductCompetitiveProductRelVO> queryList(DataCompanyVcProductCompetitiveProductRelQueryListCommand dataCompanyVcProductCompetitiveProductRelQueryListCommand){
        dataCompanyVcProductCompetitiveProductRelQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyVcProductCompetitiveProductRelRepresentationApplicationService.queryList(dataCompanyVcProductCompetitiveProductRelQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProductCompetitiveProductRel:pageQuery')")
    @Operation(summary = "分页查询企业融资产品竞品关系")
    @GetMapping("/page")
    public PageResponse<DataCompanyVcProductCompetitiveProductRelVO> pageQueryList(DataCompanyVcProductCompetitiveProductRelPageQueryCommand dataCompanyVcProductCompetitiveProductRelPageQueryCommand){
        dataCompanyVcProductCompetitiveProductRelPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyVcProductCompetitiveProductRelRepresentationApplicationService.pageQuery(dataCompanyVcProductCompetitiveProductRelPageQueryCommand);
    }
    
    @Operation(summary = "企业融资产品表ID分配企业竞品")
    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProductCompetitiveProductRel:companyVcProductAssignCompanyVcCompetitiveProduct')")
    @PostMapping("/companyVcProduct/assign/companyVcCompetitiveProduct")
    @ResponseStatus(HttpStatus.CREATED)
    @OpLog(name = "企业融资产品表ID分配企业竞品",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.relAsign)
    public Response companyVcProductAssignCompanyVcCompetitiveProduct(@RequestBody CompanyVcProductAssignCompanyVcCompetitiveProductCommand cf) {
        return iDataCompanyVcProductCompetitiveProductRelApplicationService.companyVcProductAssignCompanyVcCompetitiveProduct(cf);
    }

    @Operation(summary = "根据企业融资产品表IDID查询已分配的企业竞品id")
    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProductCompetitiveProductRel:queryCompanyVcCompetitiveProductIdsByCompanyVcProductId')")
    @GetMapping("/queryCompanyVcCompetitiveProductIdsByCompanyVcProductId")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<Long> queryCompanyVcCompetitiveProductIdsByCompanyVcProductId(IdCommand idCommand) {
        return iDataCompanyVcProductCompetitiveProductRelRepresentationApplicationService.queryCompanyVcCompetitiveProductIdsByCompanyVcProductId( idCommand);
    }

    @Operation(summary = "清空企业融资产品表ID下的所有企业竞品")
    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProductCompetitiveProductRel:deleteByCompanyVcProductId')")
    @DeleteMapping("/deleteByCompanyVcProductId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @OpLog(name = "清空企业融资产品表ID下的所有企业竞品",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public Response deleteByCompanyVcProductId(@RequestBody IdCommand idCommand) {
        return iDataCompanyVcProductCompetitiveProductRelApplicationService.deleteByCompanyVcProductId(idCommand);
    }


    @Operation(summary = "企业竞品分配企业融资产品表ID")
    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProductCompetitiveProductRel:companyVcCompetitiveProductAssignCompanyVcProduct')")
    @PostMapping("/companyVcCompetitiveProduct/assign/companyVcProduct")
    @ResponseStatus(HttpStatus.CREATED)
    @OpLog(name = "企业竞品分配企业融资产品表ID",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.relAsign)
    public Response companyVcCompetitiveProductAssignCompanyVcProduct(@RequestBody CompanyVcCompetitiveProductAssignCompanyVcProductCommand cf) {
        return iDataCompanyVcProductCompetitiveProductRelApplicationService.companyVcCompetitiveProductAssignCompanyVcProduct(cf);
    }

    @Operation(summary = "根据企业竞品ID查询已分配的企业融资产品表IDid")
    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProductCompetitiveProductRel:queryCompanyVcProductIdsByCompanyVcCompetitiveProductId')")
    @GetMapping("/queryCompanyVcProductIdsByCompanyVcCompetitiveProductId")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<Long> queryByCompanyVcCompetitiveProductId(IdCommand idCommand) {
        return iDataCompanyVcProductCompetitiveProductRelRepresentationApplicationService.queryCompanyVcProductIdsByCompanyVcCompetitiveProductId( idCommand);

    }

    @Operation(summary = "清空企业竞品下的所有企业融资产品表ID")
    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProductCompetitiveProductRel:deleteByCompanyVcCompetitiveProductId')")
    @DeleteMapping("/deleteByCompanyVcCompetitiveProductId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @OpLog(name = "清空企业竞品下的所有企业融资产品表ID",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public Response deleteByCompanyVcCompetitiveProductId(@RequestBody IdCommand idCommand) {
        return iDataCompanyVcProductCompetitiveProductRelApplicationService.deleteByCompanyVcCompetitiveProductId(idCommand);
    }

}
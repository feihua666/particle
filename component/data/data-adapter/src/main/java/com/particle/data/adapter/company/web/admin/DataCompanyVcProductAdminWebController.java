package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyVcProductApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyVcProductRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyVcProductCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcProductUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductQueryListCommand;
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
 * 企业融资产品后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@Tag(name = "企业融资产品pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_vc_product")
public class DataCompanyVcProductAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyVcProductApplicationService iDataCompanyVcProductApplicationService;
    @Autowired
    private IDataCompanyVcProductRepresentationApplicationService iDataCompanyVcProductRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProduct:create')")
    @Operation(summary = "添加企业融资产品")
    @PostMapping("/create")
    @OpLog(name = "添加企业融资产品",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyVcProductVO> create(@RequestBody DataCompanyVcProductCreateCommand dataCompanyVcProductCreateCommand){
        return iDataCompanyVcProductApplicationService.create(dataCompanyVcProductCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProduct:delete')")
    @Operation(summary = "删除企业融资产品")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业融资产品",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyVcProductVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyVcProductApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProduct:update')")
    @Operation(summary = "更新企业融资产品")
    @PutMapping("/update")
    @OpLog(name = "更新企业融资产品",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyVcProductVO> update(@RequestBody DataCompanyVcProductUpdateCommand dataCompanyVcProductUpdateCommand){
        dataCompanyVcProductUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyVcProductApplicationService.update(dataCompanyVcProductUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProduct:update')")
    @Operation(summary = "企业融资产品更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyVcProductVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyVcProductRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProduct:detail')")
    @Operation(summary = "企业融资产品详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyVcProductVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyVcProductRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProduct:queryList')")
    @Operation(summary = "列表查询企业融资产品")
    @GetMapping("/list")
    public MultiResponse<DataCompanyVcProductVO> queryList(DataCompanyVcProductQueryListCommand dataCompanyVcProductQueryListCommand){
        dataCompanyVcProductQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyVcProductRepresentationApplicationService.queryList(dataCompanyVcProductQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcProduct:pageQuery')")
    @Operation(summary = "分页查询企业融资产品")
    @GetMapping("/page")
    public PageResponse<DataCompanyVcProductVO> pageQueryList(DataCompanyVcProductPageQueryCommand dataCompanyVcProductPageQueryCommand){
        dataCompanyVcProductPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyVcProductRepresentationApplicationService.pageQuery(dataCompanyVcProductPageQueryCommand);
    }
}
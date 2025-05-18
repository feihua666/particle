package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyVcFinancingApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyVcFinancingRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingQueryListCommand;
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
 * 企业融资后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@Tag(name = "企业融资pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_vc_financing")
public class DataCompanyVcFinancingAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyVcFinancingApplicationService iDataCompanyVcFinancingApplicationService;
    @Autowired
    private IDataCompanyVcFinancingRepresentationApplicationService iDataCompanyVcFinancingRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancing:create')")
    @Operation(summary = "添加企业融资")
    @PostMapping("/create")
    @OpLog(name = "添加企业融资",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyVcFinancingVO> create(@RequestBody DataCompanyVcFinancingCreateCommand dataCompanyVcFinancingCreateCommand){
        return iDataCompanyVcFinancingApplicationService.create(dataCompanyVcFinancingCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancing:delete')")
    @Operation(summary = "删除企业融资")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业融资",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyVcFinancingVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyVcFinancingApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancing:update')")
    @Operation(summary = "更新企业融资")
    @PutMapping("/update")
    @OpLog(name = "更新企业融资",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyVcFinancingVO> update(@RequestBody DataCompanyVcFinancingUpdateCommand dataCompanyVcFinancingUpdateCommand){
        dataCompanyVcFinancingUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyVcFinancingApplicationService.update(dataCompanyVcFinancingUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancing:update')")
    @Operation(summary = "企业融资更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyVcFinancingVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyVcFinancingRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancing:detail')")
    @Operation(summary = "企业融资详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyVcFinancingVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyVcFinancingRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancing:queryList')")
    @Operation(summary = "列表查询企业融资")
    @GetMapping("/list")
    public MultiResponse<DataCompanyVcFinancingVO> queryList(DataCompanyVcFinancingQueryListCommand dataCompanyVcFinancingQueryListCommand){
        dataCompanyVcFinancingQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyVcFinancingRepresentationApplicationService.queryList(dataCompanyVcFinancingQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyVcFinancing:pageQuery')")
    @Operation(summary = "分页查询企业融资")
    @GetMapping("/page")
    public PageResponse<DataCompanyVcFinancingVO> pageQueryList(DataCompanyVcFinancingPageQueryCommand dataCompanyVcFinancingPageQueryCommand){
        dataCompanyVcFinancingPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyVcFinancingRepresentationApplicationService.pageQuery(dataCompanyVcFinancingPageQueryCommand);
    }
}
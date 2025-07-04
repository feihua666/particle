package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPlantVarietyApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPlantVarietyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyQueryListCommand;
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
 * 企业知识产权植物新品种后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:40
 */
@Tag(name = "企业知识产权植物新品种pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_plant_variety")
public class DataCompanyIprPlantVarietyAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPlantVarietyApplicationService iDataCompanyIprPlantVarietyApplicationService;
    @Autowired
    private IDataCompanyIprPlantVarietyRepresentationApplicationService iDataCompanyIprPlantVarietyRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPlantVariety:create')")
    @Operation(summary = "添加企业知识产权植物新品种")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权植物新品种",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPlantVarietyVO> create(@RequestBody DataCompanyIprPlantVarietyCreateCommand dataCompanyIprPlantVarietyCreateCommand){
        return iDataCompanyIprPlantVarietyApplicationService.create(dataCompanyIprPlantVarietyCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPlantVariety:delete')")
    @Operation(summary = "删除企业知识产权植物新品种")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权植物新品种",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPlantVarietyVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPlantVarietyApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPlantVariety:update')")
    @Operation(summary = "更新企业知识产权植物新品种")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权植物新品种",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPlantVarietyVO> update(@RequestBody DataCompanyIprPlantVarietyUpdateCommand dataCompanyIprPlantVarietyUpdateCommand){
        dataCompanyIprPlantVarietyUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPlantVarietyApplicationService.update(dataCompanyIprPlantVarietyUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPlantVariety:update')")
    @Operation(summary = "企业知识产权植物新品种更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPlantVarietyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPlantVarietyRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPlantVariety:detail')")
    @Operation(summary = "企业知识产权植物新品种详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPlantVarietyVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPlantVarietyRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPlantVariety:queryList')")
    @Operation(summary = "列表查询企业知识产权植物新品种")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPlantVarietyVO> queryList(DataCompanyIprPlantVarietyQueryListCommand dataCompanyIprPlantVarietyQueryListCommand){
        dataCompanyIprPlantVarietyQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPlantVarietyRepresentationApplicationService.queryList(dataCompanyIprPlantVarietyQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPlantVariety:pageQuery')")
    @Operation(summary = "分页查询企业知识产权植物新品种")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPlantVarietyVO> pageQueryList(DataCompanyIprPlantVarietyPageQueryCommand dataCompanyIprPlantVarietyPageQueryCommand){
        dataCompanyIprPlantVarietyPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPlantVarietyRepresentationApplicationService.pageQuery(dataCompanyIprPlantVarietyPageQueryCommand);
    }
}
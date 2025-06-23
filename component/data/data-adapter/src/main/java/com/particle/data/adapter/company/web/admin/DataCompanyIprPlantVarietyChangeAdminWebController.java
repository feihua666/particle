package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprPlantVarietyChangeApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprPlantVarietyChangeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyChangeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyChangeVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyChangeUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyChangePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyChangeQueryListCommand;
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
 * 企业知识产权植物新品种变更信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:52
 */
@Tag(name = "企业知识产权植物新品种变更信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_plant_variety_change")
public class DataCompanyIprPlantVarietyChangeAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprPlantVarietyChangeApplicationService iDataCompanyIprPlantVarietyChangeApplicationService;
    @Autowired
    private IDataCompanyIprPlantVarietyChangeRepresentationApplicationService iDataCompanyIprPlantVarietyChangeRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPlantVarietyChange:create')")
    @Operation(summary = "添加企业知识产权植物新品种变更信息")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权植物新品种变更信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprPlantVarietyChangeVO> create(@RequestBody DataCompanyIprPlantVarietyChangeCreateCommand dataCompanyIprPlantVarietyChangeCreateCommand){
        return iDataCompanyIprPlantVarietyChangeApplicationService.create(dataCompanyIprPlantVarietyChangeCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPlantVarietyChange:delete')")
    @Operation(summary = "删除企业知识产权植物新品种变更信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权植物新品种变更信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprPlantVarietyChangeVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprPlantVarietyChangeApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPlantVarietyChange:update')")
    @Operation(summary = "更新企业知识产权植物新品种变更信息")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权植物新品种变更信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprPlantVarietyChangeVO> update(@RequestBody DataCompanyIprPlantVarietyChangeUpdateCommand dataCompanyIprPlantVarietyChangeUpdateCommand){
        dataCompanyIprPlantVarietyChangeUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprPlantVarietyChangeApplicationService.update(dataCompanyIprPlantVarietyChangeUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPlantVarietyChange:update')")
    @Operation(summary = "企业知识产权植物新品种变更信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprPlantVarietyChangeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprPlantVarietyChangeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPlantVarietyChange:detail')")
    @Operation(summary = "企业知识产权植物新品种变更信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprPlantVarietyChangeVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprPlantVarietyChangeRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPlantVarietyChange:queryList')")
    @Operation(summary = "列表查询企业知识产权植物新品种变更信息")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprPlantVarietyChangeVO> queryList(DataCompanyIprPlantVarietyChangeQueryListCommand dataCompanyIprPlantVarietyChangeQueryListCommand){
        dataCompanyIprPlantVarietyChangeQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPlantVarietyChangeRepresentationApplicationService.queryList(dataCompanyIprPlantVarietyChangeQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprPlantVarietyChange:pageQuery')")
    @Operation(summary = "分页查询企业知识产权植物新品种变更信息")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprPlantVarietyChangeVO> pageQueryList(DataCompanyIprPlantVarietyChangePageQueryCommand dataCompanyIprPlantVarietyChangePageQueryCommand){
        dataCompanyIprPlantVarietyChangePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprPlantVarietyChangeRepresentationApplicationService.pageQuery(dataCompanyIprPlantVarietyChangePageQueryCommand);
    }
}
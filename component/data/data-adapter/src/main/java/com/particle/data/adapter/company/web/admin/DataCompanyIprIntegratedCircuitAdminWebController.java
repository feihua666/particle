package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyIprIntegratedCircuitApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyIprIntegratedCircuitRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprIntegratedCircuitCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprIntegratedCircuitVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprIntegratedCircuitUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprIntegratedCircuitPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprIntegratedCircuitQueryListCommand;
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
 * 企业知识产权集成电路后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
@Tag(name = "企业知识产权集成电路pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_ipr_integrated_circuit")
public class DataCompanyIprIntegratedCircuitAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyIprIntegratedCircuitApplicationService iDataCompanyIprIntegratedCircuitApplicationService;
    @Autowired
    private IDataCompanyIprIntegratedCircuitRepresentationApplicationService iDataCompanyIprIntegratedCircuitRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprIntegratedCircuit:create')")
    @Operation(summary = "添加企业知识产权集成电路")
    @PostMapping("/create")
    @OpLog(name = "添加企业知识产权集成电路",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyIprIntegratedCircuitVO> create(@RequestBody DataCompanyIprIntegratedCircuitCreateCommand dataCompanyIprIntegratedCircuitCreateCommand){
        return iDataCompanyIprIntegratedCircuitApplicationService.create(dataCompanyIprIntegratedCircuitCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprIntegratedCircuit:delete')")
    @Operation(summary = "删除企业知识产权集成电路")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业知识产权集成电路",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyIprIntegratedCircuitVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyIprIntegratedCircuitApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprIntegratedCircuit:update')")
    @Operation(summary = "更新企业知识产权集成电路")
    @PutMapping("/update")
    @OpLog(name = "更新企业知识产权集成电路",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyIprIntegratedCircuitVO> update(@RequestBody DataCompanyIprIntegratedCircuitUpdateCommand dataCompanyIprIntegratedCircuitUpdateCommand){
        dataCompanyIprIntegratedCircuitUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyIprIntegratedCircuitApplicationService.update(dataCompanyIprIntegratedCircuitUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprIntegratedCircuit:update')")
    @Operation(summary = "企业知识产权集成电路更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyIprIntegratedCircuitVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyIprIntegratedCircuitRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprIntegratedCircuit:detail')")
    @Operation(summary = "企业知识产权集成电路详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyIprIntegratedCircuitVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyIprIntegratedCircuitRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprIntegratedCircuit:queryList')")
    @Operation(summary = "列表查询企业知识产权集成电路")
    @GetMapping("/list")
    public MultiResponse<DataCompanyIprIntegratedCircuitVO> queryList(DataCompanyIprIntegratedCircuitQueryListCommand dataCompanyIprIntegratedCircuitQueryListCommand){
        dataCompanyIprIntegratedCircuitQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprIntegratedCircuitRepresentationApplicationService.queryList(dataCompanyIprIntegratedCircuitQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyIprIntegratedCircuit:pageQuery')")
    @Operation(summary = "分页查询企业知识产权集成电路")
    @GetMapping("/page")
    public PageResponse<DataCompanyIprIntegratedCircuitVO> pageQueryList(DataCompanyIprIntegratedCircuitPageQueryCommand dataCompanyIprIntegratedCircuitPageQueryCommand){
        dataCompanyIprIntegratedCircuitPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyIprIntegratedCircuitRepresentationApplicationService.pageQuery(dataCompanyIprIntegratedCircuitPageQueryCommand);
    }
}
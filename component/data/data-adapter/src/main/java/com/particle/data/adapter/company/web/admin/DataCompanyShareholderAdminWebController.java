package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyShareholderApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyShareholderRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyShareholderCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyShareholderVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyShareholderUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyShareholderPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyShareholderQueryListCommand;
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
 * 企业股东后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:01
 */
@Tag(name = "企业股东pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_shareholder")
public class DataCompanyShareholderAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyShareholderApplicationService iDataCompanyShareholderApplicationService;
    @Autowired
    private IDataCompanyShareholderRepresentationApplicationService iDataCompanyShareholderRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyShareholder:create')")
    @Operation(summary = "添加企业股东")
    @PostMapping("/create")
    @OpLog(name = "添加企业股东",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyShareholderVO> create(@RequestBody DataCompanyShareholderCreateCommand dataCompanyShareholderCreateCommand){
        return iDataCompanyShareholderApplicationService.create(dataCompanyShareholderCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyShareholder:delete')")
    @Operation(summary = "删除企业股东")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业股东",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyShareholderVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyShareholderApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyShareholder:update')")
    @Operation(summary = "更新企业股东")
    @PutMapping("/update")
    @OpLog(name = "更新企业股东",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyShareholderVO> update(@RequestBody DataCompanyShareholderUpdateCommand dataCompanyShareholderUpdateCommand){
        dataCompanyShareholderUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyShareholderApplicationService.update(dataCompanyShareholderUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyShareholder:update')")
    @Operation(summary = "企业股东更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyShareholderVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyShareholderRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyShareholder:detail')")
    @Operation(summary = "企业股东详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyShareholderVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyShareholderRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyShareholder:queryList')")
    @Operation(summary = "列表查询企业股东")
    @GetMapping("/list")
    public MultiResponse<DataCompanyShareholderVO> queryList(DataCompanyShareholderQueryListCommand dataCompanyShareholderQueryListCommand){
        dataCompanyShareholderQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyShareholderRepresentationApplicationService.queryList(dataCompanyShareholderQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyShareholder:pageQuery')")
    @Operation(summary = "分页查询企业股东")
    @GetMapping("/page")
    public PageResponse<DataCompanyShareholderVO> pageQueryList(DataCompanyShareholderPageQueryCommand dataCompanyShareholderPageQueryCommand){
        dataCompanyShareholderPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyShareholderRepresentationApplicationService.pageQuery(dataCompanyShareholderPageQueryCommand);
    }
}
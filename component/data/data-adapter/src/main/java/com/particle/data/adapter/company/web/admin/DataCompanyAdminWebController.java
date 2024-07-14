package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyQueryListCommand;
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
 * 企业后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Tag(name = "企业pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company")
public class DataCompanyAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyApplicationService iDataCompanyApplicationService;
    @Autowired
    private IDataCompanyRepresentationApplicationService iDataCompanyRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompany:create')")
    @Operation(summary = "添加企业")
    @PostMapping("/create")
    @OpLog(name = "添加企业",module = OpLogConstants.Module.data,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyVO> create(@RequestBody DataCompanyCreateCommand dataCompanyCreateCommand){
        return iDataCompanyApplicationService.create(dataCompanyCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompany:delete')")
    @Operation(summary = "删除企业")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业",module = OpLogConstants.Module.data,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompany:update')")
    @Operation(summary = "更新企业")
    @PutMapping("/update")
    @OpLog(name = "更新企业",module = OpLogConstants.Module.data,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyVO> update(@RequestBody DataCompanyUpdateCommand dataCompanyUpdateCommand){
        dataCompanyUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyApplicationService.update(dataCompanyUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompany:update')")
    @Operation(summary = "企业更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompany:detail')")
    @Operation(summary = "企业详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompany:queryList')")
    @Operation(summary = "列表查询企业")
    @GetMapping("/list")
    public MultiResponse<DataCompanyVO> queryList(DataCompanyQueryListCommand dataCompanyQueryListCommand){
        dataCompanyQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyRepresentationApplicationService.queryList(dataCompanyQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompany:pageQuery')")
    @Operation(summary = "分页查询企业")
    @GetMapping("/page")
    public PageResponse<DataCompanyVO> pageQueryList(DataCompanyPageQueryCommand dataCompanyPageQueryCommand){
        dataCompanyPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyRepresentationApplicationService.pageQuery(dataCompanyPageQueryCommand);
    }
}
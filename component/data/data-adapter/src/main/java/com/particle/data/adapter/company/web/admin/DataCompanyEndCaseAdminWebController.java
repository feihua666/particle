package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyEndCaseApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyEndCaseRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyEndCaseCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyEndCaseVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyEndCaseUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyEndCasePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyEndCaseQueryListCommand;
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
 * 企业终本案件后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:57
 */
@Tag(name = "企业终本案件pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_end_case")
public class DataCompanyEndCaseAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyEndCaseApplicationService iDataCompanyEndCaseApplicationService;
    @Autowired
    private IDataCompanyEndCaseRepresentationApplicationService iDataCompanyEndCaseRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyEndCase:create')")
    @Operation(summary = "添加企业终本案件")
    @PostMapping("/create")
    @OpLog(name = "添加企业终本案件",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyEndCaseVO> create(@RequestBody DataCompanyEndCaseCreateCommand dataCompanyEndCaseCreateCommand){
        return iDataCompanyEndCaseApplicationService.create(dataCompanyEndCaseCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyEndCase:delete')")
    @Operation(summary = "删除企业终本案件")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业终本案件",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyEndCaseVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyEndCaseApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyEndCase:update')")
    @Operation(summary = "更新企业终本案件")
    @PutMapping("/update")
    @OpLog(name = "更新企业终本案件",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyEndCaseVO> update(@RequestBody DataCompanyEndCaseUpdateCommand dataCompanyEndCaseUpdateCommand){
        dataCompanyEndCaseUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyEndCaseApplicationService.update(dataCompanyEndCaseUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyEndCase:update')")
    @Operation(summary = "企业终本案件更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyEndCaseVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyEndCaseRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyEndCase:detail')")
    @Operation(summary = "企业终本案件详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyEndCaseVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyEndCaseRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyEndCase:queryList')")
    @Operation(summary = "列表查询企业终本案件")
    @GetMapping("/list")
    public MultiResponse<DataCompanyEndCaseVO> queryList(DataCompanyEndCaseQueryListCommand dataCompanyEndCaseQueryListCommand){
        dataCompanyEndCaseQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyEndCaseRepresentationApplicationService.queryList(dataCompanyEndCaseQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyEndCase:pageQuery')")
    @Operation(summary = "分页查询企业终本案件")
    @GetMapping("/page")
    public PageResponse<DataCompanyEndCaseVO> pageQueryList(DataCompanyEndCasePageQueryCommand dataCompanyEndCasePageQueryCommand){
        dataCompanyEndCasePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyEndCaseRepresentationApplicationService.pageQuery(dataCompanyEndCasePageQueryCommand);
    }
}
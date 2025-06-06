package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyCaseFilingPartyApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyCaseFilingPartyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingPartyVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingPartyUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingPartyQueryListCommand;
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
 * 企业立案信息当事人后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:50
 */
@Tag(name = "企业立案信息当事人pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_case_filing_party")
public class DataCompanyCaseFilingPartyAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyCaseFilingPartyApplicationService iDataCompanyCaseFilingPartyApplicationService;
    @Autowired
    private IDataCompanyCaseFilingPartyRepresentationApplicationService iDataCompanyCaseFilingPartyRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCaseFilingParty:create')")
    @Operation(summary = "添加企业立案信息当事人")
    @PostMapping("/create")
    @OpLog(name = "添加企业立案信息当事人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyCaseFilingPartyVO> create(@RequestBody DataCompanyCaseFilingPartyCreateCommand dataCompanyCaseFilingPartyCreateCommand){
        return iDataCompanyCaseFilingPartyApplicationService.create(dataCompanyCaseFilingPartyCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCaseFilingParty:delete')")
    @Operation(summary = "删除企业立案信息当事人")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业立案信息当事人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyCaseFilingPartyVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyCaseFilingPartyApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCaseFilingParty:update')")
    @Operation(summary = "更新企业立案信息当事人")
    @PutMapping("/update")
    @OpLog(name = "更新企业立案信息当事人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyCaseFilingPartyVO> update(@RequestBody DataCompanyCaseFilingPartyUpdateCommand dataCompanyCaseFilingPartyUpdateCommand){
        dataCompanyCaseFilingPartyUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyCaseFilingPartyApplicationService.update(dataCompanyCaseFilingPartyUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCaseFilingParty:update')")
    @Operation(summary = "企业立案信息当事人更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyCaseFilingPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyCaseFilingPartyRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCaseFilingParty:detail')")
    @Operation(summary = "企业立案信息当事人详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyCaseFilingPartyVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyCaseFilingPartyRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCaseFilingParty:queryList')")
    @Operation(summary = "列表查询企业立案信息当事人")
    @GetMapping("/list")
    public MultiResponse<DataCompanyCaseFilingPartyVO> queryList(DataCompanyCaseFilingPartyQueryListCommand dataCompanyCaseFilingPartyQueryListCommand){
        dataCompanyCaseFilingPartyQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyCaseFilingPartyRepresentationApplicationService.queryList(dataCompanyCaseFilingPartyQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyCaseFilingParty:pageQuery')")
    @Operation(summary = "分页查询企业立案信息当事人")
    @GetMapping("/page")
    public PageResponse<DataCompanyCaseFilingPartyVO> pageQueryList(DataCompanyCaseFilingPartyPageQueryCommand dataCompanyCaseFilingPartyPageQueryCommand){
        dataCompanyCaseFilingPartyPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyCaseFilingPartyRepresentationApplicationService.pageQuery(dataCompanyCaseFilingPartyPageQueryCommand);
    }
}
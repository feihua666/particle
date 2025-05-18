package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyJudgmentDocumentPartyApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyJudgmentDocumentPartyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentPartyVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentPartyUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentPartyQueryListCommand;
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
 * 企业裁判文书当事人后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:05
 */
@Tag(name = "企业裁判文书当事人pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_judgment_document_party")
public class DataCompanyJudgmentDocumentPartyAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyJudgmentDocumentPartyApplicationService iDataCompanyJudgmentDocumentPartyApplicationService;
    @Autowired
    private IDataCompanyJudgmentDocumentPartyRepresentationApplicationService iDataCompanyJudgmentDocumentPartyRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocumentParty:create')")
    @Operation(summary = "添加企业裁判文书当事人")
    @PostMapping("/create")
    @OpLog(name = "添加企业裁判文书当事人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyJudgmentDocumentPartyVO> create(@RequestBody DataCompanyJudgmentDocumentPartyCreateCommand dataCompanyJudgmentDocumentPartyCreateCommand){
        return iDataCompanyJudgmentDocumentPartyApplicationService.create(dataCompanyJudgmentDocumentPartyCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocumentParty:delete')")
    @Operation(summary = "删除企业裁判文书当事人")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业裁判文书当事人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyJudgmentDocumentPartyVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyJudgmentDocumentPartyApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocumentParty:update')")
    @Operation(summary = "更新企业裁判文书当事人")
    @PutMapping("/update")
    @OpLog(name = "更新企业裁判文书当事人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyJudgmentDocumentPartyVO> update(@RequestBody DataCompanyJudgmentDocumentPartyUpdateCommand dataCompanyJudgmentDocumentPartyUpdateCommand){
        dataCompanyJudgmentDocumentPartyUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyJudgmentDocumentPartyApplicationService.update(dataCompanyJudgmentDocumentPartyUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocumentParty:update')")
    @Operation(summary = "企业裁判文书当事人更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyJudgmentDocumentPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyJudgmentDocumentPartyRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocumentParty:detail')")
    @Operation(summary = "企业裁判文书当事人详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyJudgmentDocumentPartyVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyJudgmentDocumentPartyRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocumentParty:queryList')")
    @Operation(summary = "列表查询企业裁判文书当事人")
    @GetMapping("/list")
    public MultiResponse<DataCompanyJudgmentDocumentPartyVO> queryList(DataCompanyJudgmentDocumentPartyQueryListCommand dataCompanyJudgmentDocumentPartyQueryListCommand){
        dataCompanyJudgmentDocumentPartyQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyJudgmentDocumentPartyRepresentationApplicationService.queryList(dataCompanyJudgmentDocumentPartyQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocumentParty:pageQuery')")
    @Operation(summary = "分页查询企业裁判文书当事人")
    @GetMapping("/page")
    public PageResponse<DataCompanyJudgmentDocumentPartyVO> pageQueryList(DataCompanyJudgmentDocumentPartyPageQueryCommand dataCompanyJudgmentDocumentPartyPageQueryCommand){
        dataCompanyJudgmentDocumentPartyPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyJudgmentDocumentPartyRepresentationApplicationService.pageQuery(dataCompanyJudgmentDocumentPartyPageQueryCommand);
    }
}
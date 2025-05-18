package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyJudgmentDocumentApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyJudgmentDocumentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentQueryListCommand;
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
 * 企业裁判文书后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
@Tag(name = "企业裁判文书pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_judgment_document")
public class DataCompanyJudgmentDocumentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyJudgmentDocumentApplicationService iDataCompanyJudgmentDocumentApplicationService;
    @Autowired
    private IDataCompanyJudgmentDocumentRepresentationApplicationService iDataCompanyJudgmentDocumentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocument:create')")
    @Operation(summary = "添加企业裁判文书")
    @PostMapping("/create")
    @OpLog(name = "添加企业裁判文书",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyJudgmentDocumentVO> create(@RequestBody DataCompanyJudgmentDocumentCreateCommand dataCompanyJudgmentDocumentCreateCommand){
        return iDataCompanyJudgmentDocumentApplicationService.create(dataCompanyJudgmentDocumentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocument:delete')")
    @Operation(summary = "删除企业裁判文书")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业裁判文书",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyJudgmentDocumentVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyJudgmentDocumentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocument:update')")
    @Operation(summary = "更新企业裁判文书")
    @PutMapping("/update")
    @OpLog(name = "更新企业裁判文书",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyJudgmentDocumentVO> update(@RequestBody DataCompanyJudgmentDocumentUpdateCommand dataCompanyJudgmentDocumentUpdateCommand){
        dataCompanyJudgmentDocumentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyJudgmentDocumentApplicationService.update(dataCompanyJudgmentDocumentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocument:update')")
    @Operation(summary = "企业裁判文书更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyJudgmentDocumentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyJudgmentDocumentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocument:detail')")
    @Operation(summary = "企业裁判文书详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyJudgmentDocumentVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyJudgmentDocumentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocument:queryList')")
    @Operation(summary = "列表查询企业裁判文书")
    @GetMapping("/list")
    public MultiResponse<DataCompanyJudgmentDocumentVO> queryList(DataCompanyJudgmentDocumentQueryListCommand dataCompanyJudgmentDocumentQueryListCommand){
        dataCompanyJudgmentDocumentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyJudgmentDocumentRepresentationApplicationService.queryList(dataCompanyJudgmentDocumentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocument:pageQuery')")
    @Operation(summary = "分页查询企业裁判文书")
    @GetMapping("/page")
    public PageResponse<DataCompanyJudgmentDocumentVO> pageQueryList(DataCompanyJudgmentDocumentPageQueryCommand dataCompanyJudgmentDocumentPageQueryCommand){
        dataCompanyJudgmentDocumentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyJudgmentDocumentRepresentationApplicationService.pageQuery(dataCompanyJudgmentDocumentPageQueryCommand);
    }
}
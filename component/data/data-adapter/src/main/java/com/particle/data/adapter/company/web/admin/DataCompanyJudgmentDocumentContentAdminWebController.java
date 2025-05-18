package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyJudgmentDocumentContentApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyJudgmentDocumentContentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentContentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentContentVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentContentUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentContentQueryListCommand;
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
 * 企业裁判文书内容后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@Tag(name = "企业裁判文书内容pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_judgment_document_content")
public class DataCompanyJudgmentDocumentContentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyJudgmentDocumentContentApplicationService iDataCompanyJudgmentDocumentContentApplicationService;
    @Autowired
    private IDataCompanyJudgmentDocumentContentRepresentationApplicationService iDataCompanyJudgmentDocumentContentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocumentContent:create')")
    @Operation(summary = "添加企业裁判文书内容")
    @PostMapping("/create")
    @OpLog(name = "添加企业裁判文书内容",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyJudgmentDocumentContentVO> create(@RequestBody DataCompanyJudgmentDocumentContentCreateCommand dataCompanyJudgmentDocumentContentCreateCommand){
        return iDataCompanyJudgmentDocumentContentApplicationService.create(dataCompanyJudgmentDocumentContentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocumentContent:delete')")
    @Operation(summary = "删除企业裁判文书内容")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业裁判文书内容",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyJudgmentDocumentContentVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyJudgmentDocumentContentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocumentContent:update')")
    @Operation(summary = "更新企业裁判文书内容")
    @PutMapping("/update")
    @OpLog(name = "更新企业裁判文书内容",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyJudgmentDocumentContentVO> update(@RequestBody DataCompanyJudgmentDocumentContentUpdateCommand dataCompanyJudgmentDocumentContentUpdateCommand){
        dataCompanyJudgmentDocumentContentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyJudgmentDocumentContentApplicationService.update(dataCompanyJudgmentDocumentContentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocumentContent:update')")
    @Operation(summary = "企业裁判文书内容更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyJudgmentDocumentContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyJudgmentDocumentContentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocumentContent:detail')")
    @Operation(summary = "企业裁判文书内容详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyJudgmentDocumentContentVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyJudgmentDocumentContentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocumentContent:queryList')")
    @Operation(summary = "列表查询企业裁判文书内容")
    @GetMapping("/list")
    public MultiResponse<DataCompanyJudgmentDocumentContentVO> queryList(DataCompanyJudgmentDocumentContentQueryListCommand dataCompanyJudgmentDocumentContentQueryListCommand){
        dataCompanyJudgmentDocumentContentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyJudgmentDocumentContentRepresentationApplicationService.queryList(dataCompanyJudgmentDocumentContentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDocumentContent:pageQuery')")
    @Operation(summary = "分页查询企业裁判文书内容")
    @GetMapping("/page")
    public PageResponse<DataCompanyJudgmentDocumentContentVO> pageQueryList(DataCompanyJudgmentDocumentContentPageQueryCommand dataCompanyJudgmentDocumentContentPageQueryCommand){
        dataCompanyJudgmentDocumentContentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyJudgmentDocumentContentRepresentationApplicationService.pageQuery(dataCompanyJudgmentDocumentContentPageQueryCommand);
    }
}
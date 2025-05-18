package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyDiscreditedJudgmentDebtorApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyDiscreditedJudgmentDebtorRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyDiscreditedJudgmentDebtorCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDiscreditedJudgmentDebtorVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyDiscreditedJudgmentDebtorUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDiscreditedJudgmentDebtorPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDiscreditedJudgmentDebtorQueryListCommand;
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
 * 企业失信被执行人后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
@Tag(name = "企业失信被执行人pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_discredited_judgment_debtor")
public class DataCompanyDiscreditedJudgmentDebtorAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyDiscreditedJudgmentDebtorApplicationService iDataCompanyDiscreditedJudgmentDebtorApplicationService;
    @Autowired
    private IDataCompanyDiscreditedJudgmentDebtorRepresentationApplicationService iDataCompanyDiscreditedJudgmentDebtorRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDiscreditedJudgmentDebtor:create')")
    @Operation(summary = "添加企业失信被执行人")
    @PostMapping("/create")
    @OpLog(name = "添加企业失信被执行人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> create(@RequestBody DataCompanyDiscreditedJudgmentDebtorCreateCommand dataCompanyDiscreditedJudgmentDebtorCreateCommand){
        return iDataCompanyDiscreditedJudgmentDebtorApplicationService.create(dataCompanyDiscreditedJudgmentDebtorCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDiscreditedJudgmentDebtor:delete')")
    @Operation(summary = "删除企业失信被执行人")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业失信被执行人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyDiscreditedJudgmentDebtorApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDiscreditedJudgmentDebtor:update')")
    @Operation(summary = "更新企业失信被执行人")
    @PutMapping("/update")
    @OpLog(name = "更新企业失信被执行人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> update(@RequestBody DataCompanyDiscreditedJudgmentDebtorUpdateCommand dataCompanyDiscreditedJudgmentDebtorUpdateCommand){
        dataCompanyDiscreditedJudgmentDebtorUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyDiscreditedJudgmentDebtorApplicationService.update(dataCompanyDiscreditedJudgmentDebtorUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDiscreditedJudgmentDebtor:update')")
    @Operation(summary = "企业失信被执行人更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyDiscreditedJudgmentDebtorRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDiscreditedJudgmentDebtor:detail')")
    @Operation(summary = "企业失信被执行人详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyDiscreditedJudgmentDebtorRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDiscreditedJudgmentDebtor:queryList')")
    @Operation(summary = "列表查询企业失信被执行人")
    @GetMapping("/list")
    public MultiResponse<DataCompanyDiscreditedJudgmentDebtorVO> queryList(DataCompanyDiscreditedJudgmentDebtorQueryListCommand dataCompanyDiscreditedJudgmentDebtorQueryListCommand){
        dataCompanyDiscreditedJudgmentDebtorQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyDiscreditedJudgmentDebtorRepresentationApplicationService.queryList(dataCompanyDiscreditedJudgmentDebtorQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyDiscreditedJudgmentDebtor:pageQuery')")
    @Operation(summary = "分页查询企业失信被执行人")
    @GetMapping("/page")
    public PageResponse<DataCompanyDiscreditedJudgmentDebtorVO> pageQueryList(DataCompanyDiscreditedJudgmentDebtorPageQueryCommand dataCompanyDiscreditedJudgmentDebtorPageQueryCommand){
        dataCompanyDiscreditedJudgmentDebtorPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyDiscreditedJudgmentDebtorRepresentationApplicationService.pageQuery(dataCompanyDiscreditedJudgmentDebtorPageQueryCommand);
    }
}
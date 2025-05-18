package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyJudgmentDebtorApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyJudgmentDebtorRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDebtorCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDebtorVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDebtorUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDebtorPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDebtorQueryListCommand;
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
 * 企业被执行人后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:07
 */
@Tag(name = "企业被执行人pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_judgment_debtor")
public class DataCompanyJudgmentDebtorAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyJudgmentDebtorApplicationService iDataCompanyJudgmentDebtorApplicationService;
    @Autowired
    private IDataCompanyJudgmentDebtorRepresentationApplicationService iDataCompanyJudgmentDebtorRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDebtor:create')")
    @Operation(summary = "添加企业被执行人")
    @PostMapping("/create")
    @OpLog(name = "添加企业被执行人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyJudgmentDebtorVO> create(@RequestBody DataCompanyJudgmentDebtorCreateCommand dataCompanyJudgmentDebtorCreateCommand){
        return iDataCompanyJudgmentDebtorApplicationService.create(dataCompanyJudgmentDebtorCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDebtor:delete')")
    @Operation(summary = "删除企业被执行人")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业被执行人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyJudgmentDebtorVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyJudgmentDebtorApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDebtor:update')")
    @Operation(summary = "更新企业被执行人")
    @PutMapping("/update")
    @OpLog(name = "更新企业被执行人",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyJudgmentDebtorVO> update(@RequestBody DataCompanyJudgmentDebtorUpdateCommand dataCompanyJudgmentDebtorUpdateCommand){
        dataCompanyJudgmentDebtorUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyJudgmentDebtorApplicationService.update(dataCompanyJudgmentDebtorUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDebtor:update')")
    @Operation(summary = "企业被执行人更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyJudgmentDebtorVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyJudgmentDebtorRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDebtor:detail')")
    @Operation(summary = "企业被执行人详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyJudgmentDebtorVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyJudgmentDebtorRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDebtor:queryList')")
    @Operation(summary = "列表查询企业被执行人")
    @GetMapping("/list")
    public MultiResponse<DataCompanyJudgmentDebtorVO> queryList(DataCompanyJudgmentDebtorQueryListCommand dataCompanyJudgmentDebtorQueryListCommand){
        dataCompanyJudgmentDebtorQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyJudgmentDebtorRepresentationApplicationService.queryList(dataCompanyJudgmentDebtorQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyJudgmentDebtor:pageQuery')")
    @Operation(summary = "分页查询企业被执行人")
    @GetMapping("/page")
    public PageResponse<DataCompanyJudgmentDebtorVO> pageQueryList(DataCompanyJudgmentDebtorPageQueryCommand dataCompanyJudgmentDebtorPageQueryCommand){
        dataCompanyJudgmentDebtorPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyJudgmentDebtorRepresentationApplicationService.pageQuery(dataCompanyJudgmentDebtorPageQueryCommand);
    }
}
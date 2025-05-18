package com.particle.data.adapter.company.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.data.client.company.api.IDataCompanyPunishmentApplicationService;
import com.particle.data.client.company.api.representation.IDataCompanyPunishmentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyPunishmentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPunishmentVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyPunishmentUpdateCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPunishmentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPunishmentQueryListCommand;
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
 * 企业行政处罚后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:37
 */
@Tag(name = "企业行政处罚pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_company_punishment")
public class DataCompanyPunishmentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDataCompanyPunishmentApplicationService iDataCompanyPunishmentApplicationService;
    @Autowired
    private IDataCompanyPunishmentRepresentationApplicationService iDataCompanyPunishmentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPunishment:create')")
    @Operation(summary = "添加企业行政处罚")
    @PostMapping("/create")
    @OpLog(name = "添加企业行政处罚",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<DataCompanyPunishmentVO> create(@RequestBody DataCompanyPunishmentCreateCommand dataCompanyPunishmentCreateCommand){
        return iDataCompanyPunishmentApplicationService.create(dataCompanyPunishmentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPunishment:delete')")
    @Operation(summary = "删除企业行政处罚")
    @DeleteMapping("/delete")
    @OpLog(name = "删除企业行政处罚",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<DataCompanyPunishmentVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDataCompanyPunishmentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPunishment:update')")
    @Operation(summary = "更新企业行政处罚")
    @PutMapping("/update")
    @OpLog(name = "更新企业行政处罚",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<DataCompanyPunishmentVO> update(@RequestBody DataCompanyPunishmentUpdateCommand dataCompanyPunishmentUpdateCommand){
        dataCompanyPunishmentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDataCompanyPunishmentApplicationService.update(dataCompanyPunishmentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPunishment:update')")
    @Operation(summary = "企业行政处罚更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DataCompanyPunishmentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDataCompanyPunishmentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPunishment:detail')")
    @Operation(summary = "企业行政处罚详情展示")
    @GetMapping("/detail")
    public SingleResponse<DataCompanyPunishmentVO> queryDetail(IdCommand detailCommand){
        return iDataCompanyPunishmentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPunishment:queryList')")
    @Operation(summary = "列表查询企业行政处罚")
    @GetMapping("/list")
    public MultiResponse<DataCompanyPunishmentVO> queryList(DataCompanyPunishmentQueryListCommand dataCompanyPunishmentQueryListCommand){
        dataCompanyPunishmentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyPunishmentRepresentationApplicationService.queryList(dataCompanyPunishmentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dataCompanyPunishment:pageQuery')")
    @Operation(summary = "分页查询企业行政处罚")
    @GetMapping("/page")
    public PageResponse<DataCompanyPunishmentVO> pageQueryList(DataCompanyPunishmentPageQueryCommand dataCompanyPunishmentPageQueryCommand){
        dataCompanyPunishmentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDataCompanyPunishmentRepresentationApplicationService.pageQuery(dataCompanyPunishmentPageQueryCommand);
    }
}
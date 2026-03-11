package com.particle.audit.adapter.auditrecord.web.admin;

import com.particle.audit.client.auditrecord.api.IAuditRecordApplicationService;
import com.particle.audit.client.auditrecord.api.representation.IAuditRecordRepresentationApplicationService;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordCreateCommand;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordUpdateCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordPageQueryCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordQueryListCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordVO;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 审核记录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Tag(name = "审核记录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/audit_record")
public class AuditRecordAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IAuditRecordApplicationService iAuditRecordApplicationService;
    @Autowired
    private IAuditRecordRepresentationApplicationService iAuditRecordRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:auditRecord:create')")
    @Operation(summary = "添加审核记录")
    @PostMapping("/create")
    @OpLog(name = "添加审核记录",module = OpLogConstants.Module.audit,type = OpLogConstants.Type.create)
    public SingleResponse<AuditRecordVO> create(@RequestBody AuditRecordCreateCommand auditRecordCreateCommand){
        return iAuditRecordApplicationService.create(auditRecordCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecord:delete')")
    @Operation(summary = "删除审核记录")
    @DeleteMapping("/delete")
    @OpLog(name = "删除审核记录",module = OpLogConstants.Module.audit,type = OpLogConstants.Type.delete)
    public SingleResponse<AuditRecordVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iAuditRecordApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecord:update')")
    @Operation(summary = "更新审核记录")
    @PutMapping("/update")
    @OpLog(name = "更新审核记录",module = OpLogConstants.Module.audit,type = OpLogConstants.Type.update)
    public SingleResponse<AuditRecordVO> update(@RequestBody AuditRecordUpdateCommand auditRecordUpdateCommand){
        auditRecordUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iAuditRecordApplicationService.update(auditRecordUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecord:update')")
    @Operation(summary = "审核记录更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<AuditRecordVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iAuditRecordRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecord:detail')")
    @Operation(summary = "审核记录详情展示")
    @GetMapping("/detail")
    public SingleResponse<AuditRecordVO> queryDetail(CommonIdCommand detailCommand){
        return iAuditRecordRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecord:queryList')")
    @Operation(summary = "列表查询审核记录")
    @GetMapping("/list")
    public MultiResponse<AuditRecordVO> queryList(AuditRecordQueryListCommand auditRecordQueryListCommand){
        auditRecordQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAuditRecordRepresentationApplicationService.queryList(auditRecordQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecord:pageQuery')")
    @Operation(summary = "分页查询审核记录")
    @GetMapping("/page")
    public PageResponse<AuditRecordVO> pageQueryList(AuditRecordPageQueryCommand auditRecordPageQueryCommand){
        auditRecordPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAuditRecordRepresentationApplicationService.pageQuery(auditRecordPageQueryCommand);
    }
}

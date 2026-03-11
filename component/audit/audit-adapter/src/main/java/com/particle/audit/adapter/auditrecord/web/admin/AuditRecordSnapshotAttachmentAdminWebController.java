package com.particle.audit.adapter.auditrecord.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.audit.client.auditrecord.api.IAuditRecordSnapshotAttachmentApplicationService;
import com.particle.audit.client.auditrecord.api.representation.IAuditRecordSnapshotAttachmentRepresentationApplicationService;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordSnapshotAttachmentCreateCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotAttachmentVO;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordSnapshotAttachmentUpdateCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotAttachmentPageQueryCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotAttachmentQueryListCommand;
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
 * 审核记录附件快照后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Tag(name = "审核记录附件快照pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/audit_record_snapshot_attachment")
public class AuditRecordSnapshotAttachmentAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IAuditRecordSnapshotAttachmentApplicationService iAuditRecordSnapshotAttachmentApplicationService;
    @Autowired
    private IAuditRecordSnapshotAttachmentRepresentationApplicationService iAuditRecordSnapshotAttachmentRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:auditRecordSnapshotAttachment:create')")
    @Operation(summary = "添加审核记录附件快照")
    @PostMapping("/create")
    @OpLog(name = "添加审核记录附件快照",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<AuditRecordSnapshotAttachmentVO> create(@RequestBody AuditRecordSnapshotAttachmentCreateCommand auditRecordSnapshotAttachmentCreateCommand){
        return iAuditRecordSnapshotAttachmentApplicationService.create(auditRecordSnapshotAttachmentCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecordSnapshotAttachment:delete')")
    @Operation(summary = "删除审核记录附件快照")
    @DeleteMapping("/delete")
    @OpLog(name = "删除审核记录附件快照",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<AuditRecordSnapshotAttachmentVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iAuditRecordSnapshotAttachmentApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecordSnapshotAttachment:update')")
    @Operation(summary = "更新审核记录附件快照")
    @PutMapping("/update")
    @OpLog(name = "更新审核记录附件快照",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<AuditRecordSnapshotAttachmentVO> update(@RequestBody AuditRecordSnapshotAttachmentUpdateCommand auditRecordSnapshotAttachmentUpdateCommand){
        auditRecordSnapshotAttachmentUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iAuditRecordSnapshotAttachmentApplicationService.update(auditRecordSnapshotAttachmentUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecordSnapshotAttachment:update')")
    @Operation(summary = "审核记录附件快照更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<AuditRecordSnapshotAttachmentVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iAuditRecordSnapshotAttachmentRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecordSnapshotAttachment:detail')")
    @Operation(summary = "审核记录附件快照详情展示")
    @GetMapping("/detail")
    public SingleResponse<AuditRecordSnapshotAttachmentVO> queryDetail(CommonIdCommand detailCommand){
        return iAuditRecordSnapshotAttachmentRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecordSnapshotAttachment:queryList')")
    @Operation(summary = "列表查询审核记录附件快照")
    @GetMapping("/list")
    public MultiResponse<AuditRecordSnapshotAttachmentVO> queryList(AuditRecordSnapshotAttachmentQueryListCommand auditRecordSnapshotAttachmentQueryListCommand){
        auditRecordSnapshotAttachmentQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAuditRecordSnapshotAttachmentRepresentationApplicationService.queryList(auditRecordSnapshotAttachmentQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecordSnapshotAttachment:pageQuery')")
    @Operation(summary = "分页查询审核记录附件快照")
    @GetMapping("/page")
    public PageResponse<AuditRecordSnapshotAttachmentVO> pageQueryList(AuditRecordSnapshotAttachmentPageQueryCommand auditRecordSnapshotAttachmentPageQueryCommand){
        auditRecordSnapshotAttachmentPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAuditRecordSnapshotAttachmentRepresentationApplicationService.pageQuery(auditRecordSnapshotAttachmentPageQueryCommand);
    }
}

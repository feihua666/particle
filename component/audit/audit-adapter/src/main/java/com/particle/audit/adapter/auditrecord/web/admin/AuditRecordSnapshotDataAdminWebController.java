package com.particle.audit.adapter.auditrecord.web.admin;

import com.particle.audit.client.auditrecord.api.IAuditRecordSnapshotDataApplicationService;
import com.particle.audit.client.auditrecord.api.representation.IAuditRecordSnapshotDataRepresentationApplicationService;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordSnapshotDataCreateCommand;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordSnapshotDataUpdateCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotDataPageQueryCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotDataQueryListCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotDataVO;
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
 * 审核记录数据快照后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Tag(name = "审核记录数据快照pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/audit_record_snapshot_data")
public class AuditRecordSnapshotDataAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IAuditRecordSnapshotDataApplicationService iAuditRecordSnapshotDataApplicationService;
    @Autowired
    private IAuditRecordSnapshotDataRepresentationApplicationService iAuditRecordSnapshotDataRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:auditRecordSnapshotData:create')")
    @Operation(summary = "添加审核记录数据快照")
    @PostMapping("/create")
    @OpLog(name = "添加审核记录数据快照",module = OpLogConstants.Module.audit,type = OpLogConstants.Type.create)
    public SingleResponse<AuditRecordSnapshotDataVO> create(@RequestBody AuditRecordSnapshotDataCreateCommand auditRecordSnapshotDataCreateCommand){
        return iAuditRecordSnapshotDataApplicationService.create(auditRecordSnapshotDataCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecordSnapshotData:delete')")
    @Operation(summary = "删除审核记录数据快照")
    @DeleteMapping("/delete")
    @OpLog(name = "删除审核记录数据快照",module = OpLogConstants.Module.audit,type = OpLogConstants.Type.delete)
    public SingleResponse<AuditRecordSnapshotDataVO> delete(@RequestBody CommonIdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iAuditRecordSnapshotDataApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecordSnapshotData:update')")
    @Operation(summary = "更新审核记录数据快照")
    @PutMapping("/update")
    @OpLog(name = "更新审核记录数据快照",module = OpLogConstants.Module.audit,type = OpLogConstants.Type.update)
    public SingleResponse<AuditRecordSnapshotDataVO> update(@RequestBody AuditRecordSnapshotDataUpdateCommand auditRecordSnapshotDataUpdateCommand){
        auditRecordSnapshotDataUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iAuditRecordSnapshotDataApplicationService.update(auditRecordSnapshotDataUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecordSnapshotData:update')")
    @Operation(summary = "审核记录数据快照更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<AuditRecordSnapshotDataVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand){
        return iAuditRecordSnapshotDataRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecordSnapshotData:detail')")
    @Operation(summary = "审核记录数据快照详情展示")
    @GetMapping("/detail")
    public SingleResponse<AuditRecordSnapshotDataVO> queryDetail(CommonIdCommand detailCommand){
        return iAuditRecordSnapshotDataRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecordSnapshotData:queryList')")
    @Operation(summary = "列表查询审核记录数据快照")
    @GetMapping("/list")
    public MultiResponse<AuditRecordSnapshotDataVO> queryList(AuditRecordSnapshotDataQueryListCommand auditRecordSnapshotDataQueryListCommand){
        auditRecordSnapshotDataQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAuditRecordSnapshotDataRepresentationApplicationService.queryList(auditRecordSnapshotDataQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:auditRecordSnapshotData:pageQuery')")
    @Operation(summary = "分页查询审核记录数据快照")
    @GetMapping("/page")
    public PageResponse<AuditRecordSnapshotDataVO> pageQueryList(AuditRecordSnapshotDataPageQueryCommand auditRecordSnapshotDataPageQueryCommand){
        auditRecordSnapshotDataPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iAuditRecordSnapshotDataRepresentationApplicationService.pageQuery(auditRecordSnapshotDataPageQueryCommand);
    }
}

package com.particle.audit.client.auditrecord.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 审核记录数据快照 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Data
@Schema
public class AuditRecordSnapshotDataCreateCommand extends AuditRecordSnapshotDataCreateSimpleCommand {


    @NotNull(message = "审核记录id 不能为空")
    @Schema(description = "审核记录id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long auditRecordId;


    @NotNull(message = "数据id 不能为空")
    @Schema(description = "数据id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataId;


    public static AuditRecordSnapshotDataCreateCommand create(
            Long auditRecordId,
            Long dataId,
            AuditRecordSnapshotDataCreateSimpleCommand  auditRecordSnapshotDataCreateSimpleCommand
            ) {
        AuditRecordSnapshotDataCreateCommand auditRecordSnapshotDataCreateCommand = new AuditRecordSnapshotDataCreateCommand();
        auditRecordSnapshotDataCreateCommand.auditRecordId =  auditRecordId;
        auditRecordSnapshotDataCreateCommand.dataId =  dataId;
        auditRecordSnapshotDataCreateCommand.setDataSnapshotDataId(auditRecordSnapshotDataCreateSimpleCommand.getDataSnapshotDataId());
        auditRecordSnapshotDataCreateCommand.setSnapshotJson(auditRecordSnapshotDataCreateSimpleCommand.getSnapshotJson());
        return auditRecordSnapshotDataCreateCommand;

    }
}

package com.particle.audit.client.auditrecord.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 审核记录附件快照 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Data
@Schema
public class AuditRecordSnapshotAttachmentCreateCommand extends AuditRecordSnapshotAttachmentCreateSimpleCommand {


    @NotNull(message = "审核记录id 不能为空")
    @Schema(description = "审核记录id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long auditRecordId;


    @NotNull(message = "数据id 不能为空")
    @Schema(description = "数据id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataId;

    public static AuditRecordSnapshotAttachmentCreateCommand create(
            Long auditRecordId,
            Long dataId,
            AuditRecordSnapshotAttachmentCreateSimpleCommand auditRecordSnapshotAttachmentCreateSimpleCommand
    ) {
        AuditRecordSnapshotAttachmentCreateCommand command = new AuditRecordSnapshotAttachmentCreateCommand();
        command.setAuditRecordId(auditRecordId);
        command.setDataId(dataId);

        command.setDataSnapshotDataId(auditRecordSnapshotAttachmentCreateSimpleCommand.getDataSnapshotDataId());
        command.setAttachmentName(auditRecordSnapshotAttachmentCreateSimpleCommand.getAttachmentName());
        command.setAttachmentUrl(auditRecordSnapshotAttachmentCreateSimpleCommand.getAttachmentUrl());

        return command;
    }

}

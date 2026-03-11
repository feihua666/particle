package com.particle.audit.client.auditrecord.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 审核记录附件快照 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Data
@Schema
public class AuditRecordSnapshotAttachmentUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "审核记录id 不能为空")
        @Schema(description = "审核记录id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long auditRecordId;


    @NotNull(message = "数据id 不能为空")
        @Schema(description = "数据id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataId;


    @NotNull(message = "快照数据id 不能为空")
        @Schema(description = "快照数据id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataSnapshotDataId;


    @NotEmpty(message = "附件名称 不能为空")
        @Schema(description = "附件名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String attachmentName;


    @NotEmpty(message = "附件地址 不能为空")
        @Schema(description = "附件地址",requiredMode = Schema.RequiredMode.REQUIRED)
    private String attachmentUrl;









}

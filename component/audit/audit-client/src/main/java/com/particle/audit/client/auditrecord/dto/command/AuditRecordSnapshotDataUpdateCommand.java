package com.particle.audit.client.auditrecord.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 审核记录数据快照 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Data
@Schema
public class AuditRecordSnapshotDataUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "审核记录id 不能为空")
        @Schema(description = "审核记录id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long auditRecordId;


    @NotNull(message = "数据id 不能为空")
        @Schema(description = "数据id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataId;

	@Schema(description = "快照数据id，标识是哪个快照数据的id")
	private Long dataSnapshotDataId;


    @NotEmpty(message = "快照内容json 不能为空")
        @Schema(description = "快照内容json",requiredMode = Schema.RequiredMode.REQUIRED)
    private String snapshotJson;


}

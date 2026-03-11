package com.particle.audit.client.auditrecord.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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
public class AuditRecordSnapshotDataCreateSimpleCommand extends AbstractBaseCommand {

    @NotNull(message = "数据id 不能为空")
    @Schema(description = "快照数据id，标识是哪个快照数据的id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataSnapshotDataId;


    @NotEmpty(message = "快照内容json 不能为空")
    @Schema(description = "快照内容json", requiredMode = Schema.RequiredMode.REQUIRED)
    private String snapshotJson;


}

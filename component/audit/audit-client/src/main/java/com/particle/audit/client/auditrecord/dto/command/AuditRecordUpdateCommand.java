package com.particle.audit.client.auditrecord.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * <p>
 * 审核记录 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Data
@Schema
public class AuditRecordUpdateCommand extends AbstractBaseUpdateCommand {


    @NotNull(message = "数据id 不能为空")
    @Schema(description = "数据id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataId;


    @NotNull(message = "审核结果类型 不能为空")
    @Schema(description = "审核结果类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long auditResultDictId;


    @Schema(description = "审核意见")
    private String auditComment;


    @NotNull(message = "审核时间 不能为空")
    @Schema(description = "审核时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime auditAt;


    @NotNull(message = "审核人 不能为空")
    @Schema(description = "审核人", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long auditBy;


    @Schema(description = "数据审核之前状态")
    private Long dataPreStatusDictId;


    @NotNull(message = "数据审核之后状态 不能为空")
    @Schema(description = "数据审核之后状态", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataPostStatusDictId;


    @NotEmpty(message = "分组标识 不能为空")
    @Schema(description = "分组标识", requiredMode = Schema.RequiredMode.REQUIRED)
    private String groupFlag;


    @NotEmpty(message = "分组标识备忘 不能为空")
    @Schema(description = "分组标识备忘", requiredMode = Schema.RequiredMode.REQUIRED)
    private String groupFlagMemo;


    @Schema(description = "描述")
    private String remark;


}

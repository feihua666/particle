package com.particle.tenant.client.userinvite.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 租户用户邀请 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Data
@Schema
public class TenantUserInviteUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "邀请码 不能为空")
        @Schema(description = "邀请码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String inviteCode;


    @NotNull(message = "最大邀请人数 不能为空")
        @Schema(description = "最大邀请人数",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer maxInviteCount;


    @Schema(description = "已邀请人数")
    private Integer invitedCount;


    @NotNull(message = "是否过期 不能为空")
        @Schema(description = "是否过期",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isExpired;


    @Schema(description = "过期原因")
    private String expiredReason;


    @Schema(description = "到期时间")
    private LocalDateTime expireAt;


    @NotNull(message = "邀请人用户id 不能为空")
        @Schema(description = "邀请人用户id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long inviterUserId;









}

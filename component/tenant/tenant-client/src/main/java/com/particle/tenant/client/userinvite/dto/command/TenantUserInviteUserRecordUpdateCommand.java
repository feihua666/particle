package com.particle.tenant.client.userinvite.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 租户用户邀请记录 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Data
@Schema
public class TenantUserInviteUserRecordUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "租户用户邀请id 不能为空")
        @Schema(description = "租户用户邀请id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer tenantUserInviteId;


    @NotNull(message = "被邀请人用户id 不能为空")
        @Schema(description = "被邀请人用户id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long invitedUserId;


    @Schema(description = "用户加入时间")
    private LocalDateTime joinAt;
    

    @Schema(description = "描述")
    private String remark;









}

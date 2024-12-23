package com.particle.tenant.client.userinvite.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 租户用户邀请 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Data
@Schema
public class TenantUserInviteQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "邀请码")
    private String inviteCode;




    @Schema(description = "是否过期")
    private Boolean isExpired;




    @Schema(description = "邀请人用户id")
    private Long inviterUserId;









}

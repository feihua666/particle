package com.particle.tenant.client.userinvite.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 租户用户邀请记录 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Data
@Schema
public class TenantUserInviteUserRecordQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "租户用户邀请id")
    private Integer tenantUserInviteId;


    @Schema(description = "被邀请人用户id")
    private Long invitedUserId;











}

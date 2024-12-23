package com.particle.tenant.client.userinvite.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 租户用户邀请 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Data
@Schema
public class TenantUserInviteVO extends AbstractBaseIdVO {

    @Schema(description = "邀请码")
    private String inviteCode;

    @Schema(description = "最大邀请人数")
    private Integer maxInviteCount;

    @Schema(description = "已邀请人数")
    private Integer invitedCount;

    @Schema(description = "是否过期")
    private Boolean isExpired;

    @Schema(description = "过期原因")
    private String expiredReason;

    @Schema(description = "到期时间")
    private LocalDateTime expireAt;

    @Schema(description = "邀请人用户id")
    private Long inviterUserId;



}

package com.particle.tenant.client.userinvite.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 租户用户邀请 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Data
@ApiModel
public class TenantUserInviteCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "邀请码 不能为空")
        @ApiModelProperty(value = "邀请码",required = true)
    private String inviteCode;


    @NotNull(message = "最大邀请人数 不能为空")
        @ApiModelProperty(value = "最大邀请人数",required = true)
    private Integer maxInviteCount;


    @ApiModelProperty(value = "已邀请人数")
    private Integer invitedCount;


    @NotNull(message = "是否过期 不能为空")
        @ApiModelProperty(value = "是否过期",required = true)
    private Boolean isExpired;


    @ApiModelProperty(value = "过期原因")
    private String expiredReason;


    @ApiModelProperty(value = "到期时间")
    private LocalDateTime expireAt;
    

    @NotNull(message = "邀请人用户id 不能为空")
        @ApiModelProperty(value = "邀请人用户id",required = true)
    private Long inviterUserId;









}

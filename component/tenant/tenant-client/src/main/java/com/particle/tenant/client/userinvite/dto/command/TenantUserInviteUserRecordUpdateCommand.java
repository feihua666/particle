package com.particle.tenant.client.userinvite.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class TenantUserInviteUserRecordUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "租户用户邀请id 不能为空")
        @ApiModelProperty(value = "租户用户邀请id",required = true)
    private Integer tenantUserInviteId;


    @NotNull(message = "被邀请人用户id 不能为空")
        @ApiModelProperty(value = "被邀请人用户id",required = true)
    private Long invitedUserId;


    @ApiModelProperty(value = "用户加入时间")
    private LocalDateTime joinAt;
    

    @ApiModelProperty(value = "描述")
    private String remark;









}

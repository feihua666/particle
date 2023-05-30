package com.particle.tenant.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 租户用户 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Data
@ApiModel
public class TenantUserUpdateCommand extends AbstractBaseUpdateCommand {

	@ApiModelProperty("是否正式，1=正式，0=试用")
	private Boolean isFormal;

    @NotNull(message = "用户id 不能为空")
    @ApiModelProperty(value = "用户id",required = true)
    private Long userId;

    @ApiModelProperty(value = "真实姓名")
    private String name;

    @NotNull(message = "是否过期 不能为空")
    @ApiModelProperty(value = "是否过期",required = true)
    private Boolean isExpired;

    @ApiModelProperty(value = "过期原因")
    private String expiredReason;


    @ApiModelProperty(value = "到期时间")
    private LocalDateTime expireAt;

    @ApiModelProperty("是否离职或退出")
    private Boolean isLeave;

    @ApiModelProperty("离职或退出原因")
    private String leaveReason;

    @ApiModelProperty("离职或退出时间")
    private LocalDateTime leaveAt;

    @NotNull(message = "租户id 不能为空")
    @ApiModelProperty(value = "租户id",required = true)
    private Long tenantId;
}
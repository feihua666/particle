package com.particle.user.client.login.dto.command.representation;


import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户登录设备 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Data
@ApiModel
public class UserLoginDeviceQueryListCommand extends AbstractBaseQueryCommand {


    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("设备id，可以用来唯一标识一个设备")
    private String deviceId;

}

package com.particle.user.client.login.dto.command.representation;


import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class UserLoginDeviceQueryListCommand extends AbstractBaseQueryCommand {


    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "设备id，可以用来唯一标识一个设备")
    private String deviceId;

}

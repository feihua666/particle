package com.particle.user.client.login.dto.command.representation;

import java.time.LocalDateTime;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户登录设备 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Data
@ApiModel
public class UserLoginDevicePageQueryCommand extends AbstractBasePageQueryCommand {


    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("首次登录时间")
    private LocalDateTime loginFirstAt;

    @ApiModelProperty("最后一次登录时间")
    private LocalDateTime loginLastAt;

    @ApiModelProperty("设备id，可以用来唯一标识一个设备")
    private String deviceId;

    @ApiModelProperty("设备名称，如：xxx的Iphone")
    private String deviceName;

    @ApiModelProperty("验证通过时间")
    private LocalDateTime validateAt;

    @ApiModelProperty("操作系统及版本")
    private String operatingSystem;


}

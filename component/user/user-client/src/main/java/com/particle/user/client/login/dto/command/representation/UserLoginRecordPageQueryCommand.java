package com.particle.user.client.login.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户登录记录 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Data
@ApiModel
public class UserLoginRecordPageQueryCommand extends AbstractBasePageQueryCommand {


    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("设备id，可以用来唯一标识一个设备")
    private String deviceId;

    @ApiModelProperty("登录标识id")
    private Long userIdentifierId;

    @ApiModelProperty("会话标识，登录成功后的会话")
    private String session;

    @ApiModelProperty("会话标识的md5摘要值，考虑到会话标识可能会很长")
    private String sessionMd5;

    @ApiModelProperty("登录是否成功")
    private Boolean isSuccess;

    @ApiModelProperty("追踪id")
    private String traceId;

    @ApiModelProperty("api数量，该次登录请求的api数，登录算第1次")
    private Long apiCount;

}

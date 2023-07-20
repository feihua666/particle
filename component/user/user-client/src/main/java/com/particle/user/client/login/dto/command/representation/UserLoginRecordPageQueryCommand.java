package com.particle.user.client.login.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class UserLoginRecordPageQueryCommand extends AbstractBasePageQueryCommand {


    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "设备id，可以用来唯一标识一个设备")
    private String deviceId;

    @Schema(description = "登录标识id")
    private Long userIdentifierId;

    @Schema(description = "会话标识，登录成功后的会话")
    private String session;

    @Schema(description = "会话标识的md5摘要值，考虑到会话标识可能会很长")
    private String sessionMd5;

    @Schema(description = "登录是否成功")
    private Boolean isSuccess;

    @Schema(description = "追踪id")
    private String traceId;

    @Schema(description = "api数量，该次登录请求的api数，登录算第1次")
    private Long apiCount;

}

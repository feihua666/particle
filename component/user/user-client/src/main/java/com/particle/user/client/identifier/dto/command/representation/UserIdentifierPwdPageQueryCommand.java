package com.particle.user.client.identifier.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户密码 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Schema
public class UserIdentifierPwdPageQueryCommand extends AbstractBasePageQueryCommand {


    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "用户标识id")
    private Long identifierId;

    @Schema(description = "密码加密方式标识")
    private String pwdEncryptFlag;

    @Schema(description = "是否过期，过期后该密码不能登录")
    private Boolean isExpired;

    @Schema(description = "是否需要提示修改密码")
    private Boolean isNeedUpdate;

    @Schema(description = "分组标识")
    private String groupFlag;


}

package com.particle.user.client.identifier.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户密码 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@ApiModel
public class UserIdentifierPwdCreateCommand extends AbstractBaseCommand {


    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户标识id")
    private Long identifierId;

    @ApiModelProperty("密码")
    private String pwd;

    @ApiModelProperty("密码加密方式标识")
    private String pwdEncryptFlag;

    @ApiModelProperty("是否过期，过期后该密码不能登录")
    private Boolean isExpired;

    @ApiModelProperty("过期原因")
    private String expiredReason;

    @ApiModelProperty("到期时间，为空永不到期")
    private LocalDateTime expireAt;

    @ApiModelProperty("是否需要提示修改密码")
    private Boolean isNeedUpdate;

    @ApiModelProperty("密码的修改时间")
    private LocalDateTime pwdModifiedAt;

    @ApiModelProperty("复杂度，数字越高越复杂，取值 1-100")
    private Integer complexity;

    @ApiModelProperty("分组标识")
    private String groupFlag;


}

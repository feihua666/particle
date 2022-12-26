package com.particle.user.client.identifier.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户密码 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@ApiModel
public class UserIdentifierPwdQueryListCommand extends AbstractBaseQueryCommand {


    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户标识id")
    private Long identifierId;

    @ApiModelProperty("密码加密方式标识")
    private String pwdEncryptFlag;

    @ApiModelProperty("是否过期，过期后该密码不能登录")
    private Boolean isExpired;

    @ApiModelProperty("是否需要提示修改密码")
    private Boolean isNeedUpdate;

    @ApiModelProperty("分组标识")
    private String groupFlag;


}

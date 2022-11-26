package com.particle.user.client.identifier.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户登录标识 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@ApiModel
public class UserIdentifierQueryListCommand extends AbstractBaseQueryCommand {


    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("登录标识")
    private String identifier;

    @ApiModelProperty("授权类型,字典id")
    private Long identityTypeDictId;

    @ApiModelProperty("锁定状态，0=未锁定；1=锁定")
    private Boolean isLock;

    @ApiModelProperty("锁定原因")
    private String lockReason;

    @ApiModelProperty("unionId，支持第三方登录unionId")
    private String unionId;

    @ApiModelProperty("是否过期")
    private Boolean isExpired;

    @ApiModelProperty("是否禁用")
    private Boolean isDisabled;

    @ApiModelProperty("禁用原因")
    private String disabledReason;

    @ApiModelProperty("分组标识")
    private String groupFlag;

    @ApiModelProperty("最后一次登录时间")
    private LocalDateTime lastLoginAt;

    @ApiModelProperty("最后一次登录ip")
    private String lastLoginIp;


}

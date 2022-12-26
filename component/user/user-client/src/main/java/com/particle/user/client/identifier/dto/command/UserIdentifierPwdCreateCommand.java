package com.particle.user.client.identifier.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户密码 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@PropValid
@Data
@ApiModel
public class UserIdentifierPwdCreateCommand extends AbstractBaseCommand {


    @NotNull(message = "用户id 不能为空")
    @ApiModelProperty(value = "用户id",required = true)
    private Long userId;

    @NotNull(message = "用户标识id 不能为空")
    @ApiModelProperty(value = "用户标识id",required = true)
    private Long identifierId;

    /**
     * 后台处理，传参无效
     */
    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "密码",hidden = true)
    private String pwd;

    /**
     * 后台处理，传参无效
     */
    @ApiModelProperty(value = "密码加密方式标识",hidden = true)
    private String pwdEncryptFlag;

    @ApiModelProperty("是否过期，过期后该密码不能登录")
    private Boolean isExpired;

    @PropValid.DependCondition(message = "过期原因不能为空",dependProp = "isExpired",ifEqual = "true")
    @ApiModelProperty("过期原因")
    private String expiredReason;

    @ApiModelProperty("到期时间，为空永不到期")
    private LocalDateTime expireAt;

    @ApiModelProperty("是否需要提示修改密码")
    private Boolean isNeedUpdate;

    @PropValid.DependCondition(message = "提示修改密码消息内容不能为空",dependProp = "isNeedUpdate",ifEqual = "true")
    @ApiModelProperty("提示修改密码消息内容")
    private String needUpdateMessage;
    /**
     * 后台处理，传参无效
     */
    @ApiModelProperty(value = "密码的修改时间",hidden = true)
    private LocalDateTime pwdModifiedAt;

    /**
     * 后台处理，传参无效
     */
    @ApiModelProperty(value = "复杂度，数字越高越复杂，取值 1-100",hidden = true)
    private Integer complexity;

    @ApiModelProperty("分组标识")
    private String groupFlag;


}

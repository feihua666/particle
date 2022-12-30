package com.particle.user.client.identifier.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import com.particle.global.validation.props.PropValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户密码 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@ApiModel
public class UserIdentifierPwdUpdateCommand extends AbstractBaseUpdateCommand {


    @NotNull(message = "用户id 不能为空")
    @ApiModelProperty(value = "用户id",required = true)
    private Long userId;

    @NotNull(message = "用户标识id 不能为空")
    @ApiModelProperty(value = "用户标识id",required = true)
    private Long identifierId;

    @ApiModelProperty(value = "密码")
    private String pwd;

    /**
     * 后台处理，传参无效
     */
    @ApiModelProperty(value = "密码加密方式标识",hidden = true)
    private String pwdEncryptFlag;

    @NotNull(message = "是否过期不能为空")
    @ApiModelProperty(value = "是否过期，过期后该密码不能登录",required = true)
    private Boolean isExpired;

    @SetNullWhenNull
    @PropValid.DependCondition(message = "过期原因不能为空",dependProp = "isExpired",ifEqual = "true")
    @ApiModelProperty("过期原因")
    private String expiredReason;

    @ApiModelProperty("到期时间，为空永不到期")
    private LocalDateTime expireAt;

    @NotNull(message = "是否需要提示修改密码不能为空")
    @ApiModelProperty(value = "是否需要提示修改密码",required = true)
    private Boolean isNeedUpdate;

    @PropValid.DependCondition(message = "提示修改密码消息内容不能为空",dependProp = "isNeedUpdate",ifEqual = "true")
    @ApiModelProperty("提示修改密码消息内容")
    private String needUpdateMessage;

    /**
     * 后台处理，传参无效
     */
    @ApiModelProperty(value = "复杂度，数字越高越复杂，取值 1-100",hidden = true)
    private Integer complexity;

    @ApiModelProperty("分组标识")
    private String groupFlag;
}

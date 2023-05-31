package com.particle.user.client.identifier.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户登录标识 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@PropValid
@Data
@ApiModel
public class UserIdentifierSimpleCreateCommand extends AbstractBaseCommand {

    @NotEmpty(message = "登录标识不能为空")
    @ApiModelProperty(value = "登录标识",required = true)
    private String identifier;

    @PropValid.Depend(message = "授权类型,字典id 不能为空",dependProp = "identityTypeDictValue",dependPropHasValue = false)
    @ApiModelProperty(value = "授权类型,字典id，授权类型,字典值二选一")
    private Long identityTypeDictId;

    @PropValid.Depend(message = "授权类型,字典值不能为空",dependProp = "identityTypeDictId",dependPropHasValue = false)
    @ApiModelProperty(value = "授权类型,字典值，授权类型,字典id二选一")
    private String identityTypeDictValue;

    @NotNull(message = "是否锁定不能为空")
    @ApiModelProperty(value = "锁定状态，0=未锁定；1=锁定",required = true)
    private Boolean isLock = false;

    @PropValid.DependCondition(message = "锁定原因不能为空" ,dependProp = "isLock",ifEqual = "true")
    @ApiModelProperty("锁定原因")
    private String lockReason;

    @ApiModelProperty("unionId，支持第三方登录unionId")
    private String unionId;

    @NotNull(message = "是否过期不能为空")
    @ApiModelProperty(value = "是否过期",required = true)
    private Boolean isExpired = false;

    @PropValid.DependCondition(message = "过期原因不能为空" ,dependProp = "isExpired",ifEqual = "true")
    @ApiModelProperty("过期原因")
    private String expiredReason;

    @ApiModelProperty("到期时间，为空永不到期")
    private LocalDateTime expireAt;

    @ApiModelProperty("分组标识")
    private String groupFlag;


}

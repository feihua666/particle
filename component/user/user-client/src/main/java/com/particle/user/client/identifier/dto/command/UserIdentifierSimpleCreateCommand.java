package com.particle.user.client.identifier.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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
@Schema
public class UserIdentifierSimpleCreateCommand extends AbstractBaseCommand {

    @NotEmpty(message = "登录标识不能为空")
    @Schema(description = "登录标识",requiredMode = Schema.RequiredMode.REQUIRED)
    private String identifier;

    @PropValid.Depend(message = "授权类型,字典id 不能为空",dependProp = "identityTypeDictValue",dependPropHasValue = false)
    @Schema(description = "授权类型,字典id，授权类型,字典值二选一")
    private Long identityTypeDictId;

    @PropValid.Depend(message = "授权类型,字典值不能为空",dependProp = "identityTypeDictId",dependPropHasValue = false)
    @Schema(description = "授权类型,字典值，授权类型,字典id二选一")
    private String identityTypeDictValue;

    @NotNull(message = "是否锁定不能为空")
    @Schema(description = "锁定状态，0=未锁定；1=锁定",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isLock = false;

    @PropValid.DependCondition(message = "锁定原因不能为空" ,dependProp = "isLock",ifEqual = "true")
    @Schema(description = "锁定原因")
    private String lockReason;

    @Schema(description = "unionId，支持第三方登录unionId")
    private String unionId;

    @NotNull(message = "是否过期不能为空")
    @Schema(description = "是否过期",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isExpired = false;

    @PropValid.DependCondition(message = "过期原因不能为空" ,dependProp = "isExpired",ifEqual = "true")
    @Schema(description = "过期原因")
    private String expiredReason;

    @Schema(description = "到期时间，为空永不到期")
    private LocalDateTime expireAt;

    @Schema(description = "分组标识")
    private String groupFlag;


}

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
 * 密码基础类
 * </p>
 *
 * @author yangwei
 * @since 2022-12-25 21:19
 */
@PropValid
@Data
@Schema
public class UserIdentifierPwdCommand extends AbstractBaseCommand {


	@NotEmpty(message = "密码不能为空")
	@Schema(description = "密码",requiredMode = Schema.RequiredMode.REQUIRED)
	private String password;

	@NotNull(message = "密码是否过期不能为空")
	@Schema(description = "密码是否过期，过期后该密码不能登录",requiredMode = Schema.RequiredMode.REQUIRED)
	private Boolean isPwdExpired = false;

	@PropValid.DependCondition(message = "密码过期原因不能为空",dependProp = "isExpired",ifEqual = "true")
	@Schema(description = "密码过期原因")
	private String pwdExpiredReason;

	@Schema(description = "密码到期时间，为空永不到期")
	private LocalDateTime pwdExpireAt;

	@NotNull(message = "密码是否需要提示修改密码不能为空")
	@Schema(description = "密码是否需要提示修改密码",requiredMode = Schema.RequiredMode.REQUIRED)
	private Boolean isPwdNeedUpdate = false;

	@PropValid.DependCondition(message = "密码提示修改密码消息内容不能为空",dependProp = "isNeedUpdate",ifEqual = "true")
	@Schema(description = "密码提示修改密码消息内容")
	private String pwdNeedUpdateMessage;

	/**
	 * 后台处理，传参无效
	 */
	@Schema(description = "加密后的密码",hidden = true)
	private String pwdEncoded;
	/**
	 * 后台处理，传参无效
	 */
	@Schema(description = "密码加密方式",hidden = true)
	private String pwdEncryptFlag;
	/**
	 * 后台处理，传参无效
	 */
	@Schema(description = "密码复杂度",hidden = true)
	private Integer pwdComplexity;

}

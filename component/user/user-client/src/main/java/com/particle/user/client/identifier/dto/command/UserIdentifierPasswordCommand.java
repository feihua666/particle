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
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-12-25 21:19
 */
@PropValid
@Data
@ApiModel
public class UserIdentifierPasswordCommand extends AbstractBaseCommand {


	@NotEmpty(message = "密码不能为空")
	@ApiModelProperty(value = "密码",required = true)
	private String password;

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
	@ApiModelProperty(value = "加密后的密码",hidden = true)
	private String encodedPassword;
	/**
	 * 后台处理，传参无效
	 */
	@ApiModelProperty(value = "密码加密方式",hidden = true)
	private String pwdEncryptFlag;
	/**
	 * 后台处理，传参无效
	 */
	@ApiModelProperty(value = "密码复杂度",hidden = true)
	private Integer complexity;

}
